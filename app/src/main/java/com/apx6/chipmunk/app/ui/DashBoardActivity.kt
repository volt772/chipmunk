package com.apx6.chipmunk.app.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.constants.CmdCategoryDialogType
import com.apx6.chipmunk.app.ext.getDfFromToday
import com.apx6.chipmunk.app.ext.getTodayMillis
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.showToast
import com.apx6.chipmunk.app.ext.visibilityExt
import com.apx6.chipmunk.app.ui.adapter.CheckListAdapter
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.common.CmSnackBar
import com.apx6.chipmunk.app.ui.dialog.CategoryListDialog
import com.apx6.chipmunk.app.ui.dialog.CheckListDetailDialog
import com.apx6.chipmunk.databinding.ActivityDashboardBinding
import com.apx6.domain.State
import com.apx6.domain.constants.CmdConstants
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdCheckListDetail
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DashBoardActivity : BaseActivity<DashBoardViewModel, ActivityDashboardBinding>() {

    override val viewModel: DashBoardViewModel by viewModels()
    override fun getViewBinding(): ActivityDashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)

    private val checkListAdapter = CheckListAdapter(this::selectCheckList)

    private var userId: Int = 0
    private var categoryList = listOf<CmdCategory>()

    private var dfTodayCount: Int = 0
    private var dfFutureCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = " "
        binding.fab.setOnSingleClickListener {
            moveToRegister()
        }

        binding.appBar.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }


                if (scrollRange + verticalOffset == 0) {
                    /* 접혔을때*/
                    isShow = true
                } else if (isShow) {
                    /* 펴졌을때*/
                    isShow = false
                }

                makeSummaryTitle()
            }
        })

        initView()
        subscribers()
        viewModel.getUser()

    }

    private fun initView() {
        binding.inContent.run {
            rvCheckList.adapter = checkListAdapter
//            swTaskRefresh.setOnRefreshListener {  }
        }
    }

    private fun goToModify(cl: CmdCheckList) {
        moveToRegister(cl.id)
    }

    private fun deleteCheckList(cl: CmdCheckList) {
        viewModel.delCheckList(cl)
    }

    private fun selectCheckList(cl: CmdCheckList) {
        viewModel.getSelectedCategoryName(cl)
    }

    private fun openDetailDialog(cld: CmdCheckListDetail) {
        val dialog = CheckListDetailDialog.newInstance(
            cld = cld,
            toModify = ::goToModify,
            toDelete = ::deleteCheckList
        )

        supportFragmentManager.beginTransaction().add(dialog, TAG).commitAllowingStateLoss()
    }

    private fun subscribers() {
        lifecycleScope.run {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    progress?.start()
                    viewModel.user.collect { user ->
                        user?.let { _user ->
                            val millis = getTodayMillis()
                            viewModel.getCheckLists(_user.id, millis)
                            viewModel.getCategories(_user.id)
                            userId = _user.id
                        } ?: run {
                            progress?.stop()
                            val vw = binding.coDashboardRoot
                            CmSnackBar.make(vw, getString(R.string.failed_get_user_info), "") { }.apply {
                                show()
                            }
                        }
                    }
                }
            }

            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.checkLists.collect { state ->
                        when (state) {
                            is State.Loading -> { }
                            is State.Success -> {
                                progress?.stop()
                                val cl = state.data.toMutableList()
                                showEmptyCheckListView(cl.count())
                                checkListAdapter.submitList(cl)
                                makeCheckListSummary(cl)
                            }
                            is State.Error -> {
                                progress?.stop()
                            }
                        }
                    }
                }
            }

            launch {
                viewModel.selCheckListDetail.collectLatest { details ->
                    openDetailDialog(details)
                }
            }

            launch {
                viewModel.checkListDeleted.collectLatest { deleted ->
                    showToast(
                        getString(
                            if (deleted) R.string.dlg_checklist_delete_success
                            else R.string.dlg_checklist_delete_fail
                        ),
                        false
                    )
                }
            }

            launch {
                viewModel.category.collect { state ->
                    when (state) {
                        is State.Loading -> { }
                        is State.Success -> {
                            categoryList = state.data.toMutableList()
                        }
                        is State.Error -> {
                        }
                    }
                }
            }
        }
    }

    private fun showEmptyCheckListView(count: Int) {
        binding.clNoChecklist.visibilityExt(count <= 0)
    }

    private fun makeCheckListSummary(cl: List<CmdCheckList>) {
        val dfToday = cl.filter {
            val df = it.endDate.getDfFromToday()
            df == 0
        }

        val dfFuture = cl.filter {
            val df = it.endDate.getDfFromToday()
            df > 0
        }

        dfTodayCount = dfToday.count()
        dfFutureCount = dfFuture.count()

        makeSummaryTitle()
    }

    private fun makeSummaryTitle() {
        val summaryLabel = if (dfTodayCount > 0) {
            /* 오늘기준*/
            getString(R.string.dashboard_checklist_summary_today, dfTodayCount)
        } else if (dfTodayCount == 0 && (dfFutureCount in 1..7)) {
            /* 미래기준(금방)*/
            getString(R.string.dashboard_checklist_summary_future, dfFutureCount)
        } else {
            /* 기타*/
            getString(R.string.dashboard_checklist_summary_else)
        }

        binding.tvSummaryTitle.text = summaryLabel
    }

    private fun moveToRegister(clId: Int?= null) {
        val intent = Intent(this, RegisterActivity::class.java).apply {
            putExtra(CmdConstants.Intent.CHECKLIST_ID, clId)
        }
        startActivity(intent)
    }

    private fun moveToSetting() {
        val intent = Intent(this, SettingActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun doFilter() {
        val categoryListDialog = CategoryListDialog.newInstance(
            categoryList,
            CmdCategory.default(),
            CmdCategoryDialogType.DASHBOARD,
            ::selectCategory,
            ::clearFilter
        )
        supportFragmentManager.beginTransaction().add(categoryListDialog, RegisterActivity.TAG).commitAllowingStateLoss()
    }

    private fun selectCategory(category: CmdCategory) {
        println("probe :: dashboard : select category : $category")
        val millis = getTodayMillis()
        viewModel.getCheckLists(userId, millis, category.id)
    }

    private fun clearFilter() {
        val millis = getTodayMillis()
        viewModel.getCheckLists(userId, millis)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> {
                moveToSetting()
                true
            }

            R.id.action_filter -> {
                doFilter()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val TAG = "DashBoardActivity"
    }
}
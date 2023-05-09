package com.apx6.chipmunk.app.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
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

    private lateinit var menu: Menu

    private val checkListAdapter = CheckListAdapter(this::selectCheckList)

    private var userId: Int = 0
    private var categoryList = listOf<CmdCategory>()

    private var dfTodayCount: Int = 0
    private var dfFutureCount: Int = 0

    private var filteredCategory: CmdCategory = CmdCategory.default()

    private val backCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val intent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_HOME)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }

            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        this.onBackPressedDispatcher.addCallback(this, backCallback)

        setSupportActionBar(findViewById(R.id.toolbar))

        initView()
        subscribers()
        viewModel.getUser()

    }

    private fun initView() {
        with(binding) {
            inContent.run {
                rvCheckList.adapter = checkListAdapter
                srfRefresh.setOnRefreshListener {
                    clearFilter()
                    srfRefresh.isRefreshing = false
                }
            }

            toolbarLayout.title = " "
            fab.setOnSingleClickListener {
                moveToRegister()
            }

            appBar.addOnOffsetChangedListener(object : OnOffsetChangedListener {
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
        }
    }

    /* 액션 : 수정*/
    private fun goToModify(cl: CmdCheckList) {
        moveToRegister(cl.id)
    }

    /* 액션 : 삭제*/
    private fun deleteCheckList(cl: CmdCheckList) {
        viewModel.delCheckList(cl)
    }

    /* 체크리스트 선택*/
    private fun selectCheckList(cl: CmdCheckList) {
        viewModel.getSelectedCategoryName(cl)
    }

    /* Dialog ; 체크리스트 상세보기*/
    private fun openDetailDialog(cld: CmdCheckListDetail) {
        val dialog = CheckListDetailDialog.newInstance(
            cld = cld,
            toModify = ::goToModify,
            toDelete = ::deleteCheckList
        )

        supportFragmentManager.beginTransaction().add(dialog, TAG).commitAllowingStateLoss()
    }

    /* Subscribers*/
    private fun subscribers() {
        lifecycleScope.run {
            launch {
                /* 사용자*/
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.user.collect { user ->
                        user?.let { _user ->
                            viewModel.run {
                                getCheckLists(_user.id, todayMillis)
                                getCategories(_user.id)
                            }
                            userId = _user.id
                        } ?: run {
                            showToast(R.string.failed_get_user_info, false)
                        }
                    }
                }
            }

            launch {
                /* 체크리스트*/
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.checkLists.collect { state ->
                        when (state) {
                            is State.Loading -> { }
                            is State.Success -> {
                                val cl = state.data.toMutableList()
                                showEmptyCheckListView(cl.count())
                                checkListAdapter.submitList(cl)
                                makeCheckListSummary(cl)
                            }
                            is State.Error -> {
                                showToast(R.string.failed_get_checklists, false)
                            }
                        }
                    }
                }
            }

            /* 체크리스트 상세보기*/
            launch {
                viewModel.selCheckListDetail.collectLatest { details ->
                    openDetailDialog(details)
                }
            }

            /* 체크리스트 삭제후*/
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

            /* 카테고리 리스트*/
            launch {
                viewModel.category.collect { state ->
                    when (state) {
                        is State.Loading -> { }
                        is State.Success -> {
                            categoryList = state.data.toMutableList()
                        }
                        is State.Error -> {
                            showToast(R.string.try_again, false)
                        }
                    }
                }
            }

            /* 필터링*/
            launch {
                viewModel.filtered.collect { category ->
                    filteredCategory = category
                }
            }
        }
    }

    /* 뷰 : 체크리스트 없음*/
    private fun showEmptyCheckListView(count: Int) {
        binding.clNoChecklist.visibilityExt(count <= 0)
    }

    /* 뷰 : 체크리스트 요약 메세지 (상단)*/
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

    /* 뷰 : 체크리스트 요약 메세지 (상단)*/
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
            filteredCategory,
            CmdCategoryDialogType.DASHBOARD,
            ::selectCategory,
            ::clearFilter
        )
        supportFragmentManager.beginTransaction().add(categoryListDialog, RegisterActivity.TAG).commitAllowingStateLoss()
    }

    private fun selectCategory(category: CmdCategory) {
        viewModel.run {
            getCheckLists(userId, todayMillis, category.id)
            setFilteredCategory(category)
        }
    }

    private fun clearFilter() {
        viewModel.run {
            getCheckLists(userId, todayMillis)
            setFilteredCategory(CmdCategory.default())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu
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
                if (categoryList.isEmpty()) {
                    showToast(R.string.no_category_add_first, false)
                } else {
                    doFilter()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val TAG = "DashBoardActivity"
        val todayMillis = getTodayMillis()
    }
}
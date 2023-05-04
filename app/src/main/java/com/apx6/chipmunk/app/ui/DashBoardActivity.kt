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
import com.apx6.chipmunk.app.ext.getTodayMillis
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.showToast
import com.apx6.chipmunk.app.ext.visibilityExt
import com.apx6.chipmunk.app.ui.adapter.CheckListAdapter
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.common.CmSnackBar
import com.apx6.chipmunk.app.ui.dialog.CheckListDetailDialog
import com.apx6.chipmunk.databinding.ActivityDashboardBinding
import com.apx6.domain.State
import com.apx6.domain.constants.CmdConstants
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
                    isShow = true
                    /* 접혔을때*/
                    println("probe :: dashboard :: is Show True")
                    binding.toolbarLayout.title = title
//                    showOption(R.id.action_info)
                } else if (isShow) {
                    isShow = false
                    /* 펴졌을때*/
                    binding.toolbarLayout.title = ""
                    println("probe :: dashboard :: is Show False")
//                    hideOption(R.id.action_info)
                }
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
                                checkListAdapter.submitList(state.data.toMutableList())
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
        }
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
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val TAG = "DashBoardActivity"
    }
}
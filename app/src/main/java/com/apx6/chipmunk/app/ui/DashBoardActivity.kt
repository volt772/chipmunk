package com.apx6.chipmunk.app.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ui.adapter.CheckListAdapter
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.common.CmSnackBar
import com.apx6.chipmunk.databinding.ActivityDashboardBinding
import com.apx6.domain.State
import com.apx6.domain.constants.CmdConstants
import com.apx6.domain.dto.CmdCheckList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DashBoardActivity : BaseActivity<DashBoardViewModel, ActivityDashboardBinding>() {

    override val viewModel: DashBoardViewModel by viewModels()
    override fun getViewBinding(): ActivityDashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)

    private val checkListAdapter = CheckListAdapter(this::onItemClicked)

    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            moveToRegister()
        }

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

    private fun onItemClicked(checkList: CmdCheckList, imageView: ImageView) {
        /* TODO*/
    }

    private fun subscribers() {
        lifecycleScope.run {
            launchWhenStarted {
                progress?.start()
                viewModel.user.collect { user ->
                    user?.let { _user ->
                        viewModel.getCheckLists(_user.id)
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

            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.checkLists.collect { state ->
                        when (state) {
                            is State.Loading -> { }
                            is State.Success -> {
                                progress?.stop()
                                checkListAdapter.submitList(state.data.toMutableList())
                                println("probe :: checkList : ${state.data}")
                            }
                            is State.Error -> {
                                progress?.stop()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun moveToRegister() {
        val intent = Intent(this, RegisterActivity::class.java).apply {
            putExtra(CmdConstants.Intent.USER_ID, userId)
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
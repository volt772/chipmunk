package com.apx6.chipmunk.app.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.vms.AppUpdateViewModel
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.dialog.AppUpdateDialog
import com.apx6.chipmunk.databinding.ActivityAppUpdateBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AppUpdateActivity : BaseActivity<AppUpdateViewModel, ActivityAppUpdateBinding>() {

    override val viewModel: AppUpdateViewModel by viewModels()
    override fun getViewBinding(): ActivityAppUpdateBinding = ActivityAppUpdateBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()
        showUpdateDialog()
    }

    private fun showUpdateDialog() {
        val uv = viewModel.getUpdateDetails()
        val dialog = AppUpdateDialog.newInstance(
            update = uv,
            toUpdate = ::doUpdate
        )

        supportFragmentManager.beginTransaction().add(dialog, TAG).commitAllowingStateLoss()
    }

    private fun doUpdate() { }

    private fun initView() {
        this.statusBar(R.color.white)
    }

    companion object {
        const val TAG = "AppUpdateActivity"
    }
}
package com.apx6.chipmunk.app.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.openActivity
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.fcm.FcmHelper
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.vms.SplashViewModel
import com.apx6.chipmunk.databinding.ActivitySplashBinding
import com.apx6.domain.utils.CmdRemoteConfigCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {
    override val viewModel: SplashViewModel by viewModels()

    override fun getViewBinding(): ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)

    @Inject lateinit var fcmHelper: FcmHelper

    override fun preLoad() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coJobs()
    }

    override fun initView() {
        this.statusBar(R.color.white)
    }

    private fun coJobs() {
        lifecycleScope.run {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.syncVersion(
                        object : CmdRemoteConfigCallback {
                            override fun success(newVersionExists: Boolean) {
                                checkUpdate()
                            }

                            override fun fail() {
                                subscribeUser()
                            }
                        },
                    )
                }
            }

            launch {
                viewModel.user.collect { user ->
                    user?.let {
                        moveToDashBoard()
                    } ?: run {
                        moveToLogin()
                    }
                }
            }
        }
    }

    private fun checkUpdate() {
        val uv = viewModel.getUpdateDetails()

        val updateNeeded = (uv.currAppVersionCode < uv.remoteAppVersionCode)

        if (updateNeeded) {
            moveToAppUpdate()
        } else {
            subscribeUser()
        }
    }

    private fun subscribeUser() {
        viewModel.getUser()
    }

    private fun moveToLogin() {
        viewModel.setFcmToken()
        openActivity(LoginActivity::class.java)
    }

    private fun moveToDashBoard() {
        openActivity(DashBoardActivity::class.java)
    }

    private fun moveToAppUpdate() {
        openActivity(AppUpdateActivity::class.java)
    }

    companion object {
        const val TAG = "SplashActivity"
    }

//    private fun test() {
//        fcmHelper.sendNotification(CmdNotification.default())
//    }
}

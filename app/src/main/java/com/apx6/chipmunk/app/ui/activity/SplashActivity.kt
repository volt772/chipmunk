package com.apx6.chipmunk.app.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.vms.SplashViewModel
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivitySplashBinding
import com.apx6.domain.utils.CmdRemoteConfigCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override val viewModel: SplashViewModel by viewModels()
    override fun getViewBinding(): ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        initView()
        splashScreen.setKeepOnScreenCondition { true }

        observeSplashFlow()
    }

    private fun observeSplashFlow() {
        lifecycleScope.run {
            launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.syncVersion(object: CmdRemoteConfigCallback {
                        override fun success(newVersionExists: Boolean) {
                            checkUpdate()
                        }

                        override fun fail() {
                            subscribeUser()
                        }
                    })
                }
            }

            launch {
                viewModel.user.collect { user ->
                    user?.let {
                        moveToDashBoard()
                    } ?: run {
                        viewModel.setFcmToken()
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

    private fun initView() {
        this.statusBar(R.color.white)
    }

    private fun moveToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun moveToDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
    }

    private fun moveToAppUpdate() {
        val intent = Intent(this, AppUpdateActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val TAG = "SplashActivity"
        const val SCREEN_MOVE_DELAY = 1000L
    }
}
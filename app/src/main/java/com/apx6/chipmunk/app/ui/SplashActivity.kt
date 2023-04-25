package com.apx6.chipmunk.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override val viewModel: SplashViewModel by viewModels()
    override fun getViewBinding(): ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()
        observeUser()
    }

    private fun observeUser() {
        lifecycleScope.launchWhenStarted {
            viewModel.user.collect { user ->
                user?.let {
                    delay(SCREEN_MOVE_DELAY)
                    moveToDashBoard()
                } ?: run {
                    delay(SCREEN_MOVE_DELAY)
                    viewModel.setFcmToken()
                    moveToLogin()
                }
            }
        }
    }

    private fun initView() {
        this.statusBar(R.color.material_blue_400)
    }

    private fun moveToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun moveToDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val SCREEN_MOVE_DELAY = 1000L
    }
}
package com.apx6.chipmunk.app.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override val viewModel: SplashViewModel by viewModels()
    override fun getViewBinding(): ActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready.
                    return if (viewModel.isReady) {
                        // The content is ready; start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        moveToDashBoard()
                        true
                    } else {
                        // The content is not ready; suspend.
                        false
                    }
                }
            }
        )
    }

    private fun moveToDashBoard() {
        val intent = Intent(this, DashBoardActivity::class.java)
        startActivity(intent)
    }
}
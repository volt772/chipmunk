package com.apx6.chipmunk.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.adapter.CategoryAdapter
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivityRegisterBinding
import com.apx6.domain.dto.CmdCategory
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {

    override val viewModel: RegisterViewModel by viewModels()
    override fun getViewBinding(): ActivityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)

    private val categoryAdapter = CategoryAdapter(this::onItemClicked)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()
//        observeUser()
    }

    private fun observeUser() {
//        lifecycleScope.launchWhenStarted {
//            viewModel.user.collect { user ->
//                user?.let {
//                    delay(SCREEN_MOVE_DELAY)
//                    moveToDashBoard()
//                } ?: run {
//                    delay(SCREEN_MOVE_DELAY)
//                    viewModel.setFcmToken()
//                    moveToLogin()
//                }
//            }
//        }
    }

    private fun onItemClicked(checkList: CmdCategory) {
        /* TODO*/
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        with(binding.rvCategory) {
            layoutManager = LinearLayoutManager(this@RegisterActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
    }

}
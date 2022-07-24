package com.apx6.chipmunk.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.adapter.CategoryAdapter
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.common.CmSnackBar
import com.apx6.chipmunk.databinding.ActivityRegisterBinding
import com.apx6.domain.State
import com.apx6.domain.dto.CmdCategory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {

    override val viewModel: RegisterViewModel by viewModels()
    override fun getViewBinding(): ActivityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)

    private val categoryAdapter = CategoryAdapter(this::onItemClicked)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initView()
        subscribers()

        viewModel.getCategories(1)
//        observeUser()
    }

    private fun subscribers() {
        lifecycleScope.run {
            launchWhenStarted {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.category.collect { state ->
                        when (state) {
                            is State.Loading -> { }
                            is State.Success -> {
//                                progress?.stop()
                                categoryAdapter.submitList(state.data.toMutableList())
                            }
                            is State.Error -> {
//                                progress?.stop()
                            }
                        }
                    }
                }
            }
        }
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
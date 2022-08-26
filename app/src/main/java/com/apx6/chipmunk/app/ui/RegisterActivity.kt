package com.apx6.chipmunk.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.adapter.CategoryAdapter
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.common.CmSnackBar
import com.apx6.chipmunk.databinding.ActivityRegisterBinding
import com.apx6.domain.State
import com.apx6.domain.constants.CmdConstants
import com.apx6.domain.dto.CmdCategory
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>() {

    override val viewModel: RegisterViewModel by viewModels()
    override fun getViewBinding(): ActivityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)

    private val categoryAdapter = CategoryAdapter(this::onItemClicked)

    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        with(intent) {
            userId = getIntExtra(CmdConstants.Intent.USER_ID, 0)

            if (userId == 0) {
                val vw = binding.ablRegister
                CmSnackBar.make(vw, getString(R.string.failed_get_user_info), "") { }.apply {
                    show()
                }
            }
        }

        initView()
        subscribers()

        viewModel.getCategories(userId)
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
                                addCategoryChips(state.data.toMutableList())
                            }
                            is State.Error -> {
                            }
                        }
                    }
                }
            }
        }
    }

    private fun addCategoryChips(categories: List<CmdCategory>) {
        categories.forEach { _category ->
            val chip = Chip(this)
            chip.text = _category.name
            chip.setOnSingleClickListener {
                selectChip(_category)
            }

            binding.cgCategory.addView(chip)
        }
    }

    private fun selectChip(category: CmdCategory) {
        println("probe :: category :: ${category.name}")
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

    private fun onItemClicked(category: CmdCategory) {
        /* TODO*/
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

//        with(binding.rvCategory) {
//            layoutManager = LinearLayoutManager(this@RegisterActivity, LinearLayoutManager.HORIZONTAL, false)
//            adapter = categoryAdapter
//        }
    }

}
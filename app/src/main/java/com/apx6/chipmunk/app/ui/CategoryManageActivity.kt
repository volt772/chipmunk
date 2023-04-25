package com.apx6.chipmunk.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivityCategoryManageBinding
import com.apx6.domain.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CategoryManageActivity : BaseActivity<CategoryManageViewModel, ActivityCategoryManageBinding>() {

    override val viewModel: CategoryManageViewModel by viewModels()
    override fun getViewBinding(): ActivityCategoryManageBinding = ActivityCategoryManageBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)

        initView()
        subscribers()
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        binding.ivClose.setOnSingleClickListener {
            finish()
        }
    }

    private fun subscribers() {
        lifecycleScope.run {
            launchWhenStarted {
                viewModel.userId.collect { uid ->
                    uid?.let { _uid ->
                        viewModel.getCategories(_uid)
                    }
                }
            }

            launch {
                viewModel.category.collect { state ->
                    when (state) {
                        is State.Loading -> { }
                        is State.Success -> {
                            println("probe :: category :: collect : ${state.data.toMutableList()}")
//                            addCategoryChips(state.data.toMutableList())
                        }
                        is State.Error -> {
                        }
                    }
                }
            }        }
    }
}
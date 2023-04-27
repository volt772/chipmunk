package com.apx6.chipmunk.app.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ext.visibilityExt
import com.apx6.chipmunk.app.ui.adapter.CategoryManageAdapter
import com.apx6.chipmunk.app.ui.adapter.CheckListAdapter
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivityCategoryManageBinding
import com.apx6.domain.State
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CategoryManageActivity : BaseActivity<CategoryManageViewModel, ActivityCategoryManageBinding>() {

    override val viewModel: CategoryManageViewModel by viewModels()
    override fun getViewBinding(): ActivityCategoryManageBinding = ActivityCategoryManageBinding.inflate(layoutInflater)

    private val categoryManageAdapter = CategoryManageAdapter(this::onItemClicked)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)

        initView()

        lifecycleScope.launch {
            subscribeUser()
        }

        subscribers()
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        with(binding) {
            ivClose.setOnSingleClickListener {
                /* 종료*/
                finish()
            }

            ivAdd.setOnSingleClickListener {
                /* 카테고리 추가*/
                TODO()
            }

            rvCategories.apply {
                layoutManager = LinearLayoutManager(this@CategoryManageActivity, LinearLayoutManager.VERTICAL, false)
                adapter = categoryManageAdapter
            }
        }
    }

    private suspend fun subscribeUser() {
        lifecycleScope.run {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userId.collect { uid ->
                    uid?.let { _uid ->
                        viewModel.getCategories(_uid)
                    }
                }
            }
        }
    }


    private fun subscribers() {
        lifecycleScope.run {
            launch {
                viewModel.category.collect { state ->
                    when (state) {
                        is State.Loading -> { }
                        is State.Success -> {
                            println("probe :: category :: collect : ${state.data.toMutableList()}")
                            val categories = state.data.toMutableList()
                            val count = categories.count()

                            switchListView(count > 0)
                            categoryManageAdapter.submitList(state.data.toMutableList())
                        }
                        is State.Error -> {
                        }
                    }
                }
            }        }
    }

    private fun onItemClicked(checkList: CmdCategory) {
        /* TODO*/
    }

    private fun switchListView(lv: Boolean) {
        with(binding) {
            svCategory.visibilityExt(lv)
            clNoCategories.visibilityExt(!lv)
        }
    }
}
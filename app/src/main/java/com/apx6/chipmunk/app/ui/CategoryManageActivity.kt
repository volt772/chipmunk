package com.apx6.chipmunk.app.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.ext.visibilityExt
import com.apx6.chipmunk.app.ui.adapter.category_manage.CMLoadStateAdapter
import com.apx6.chipmunk.app.ui.adapter.category_manage.CMPagingAdapter
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.base.Dialogs
import com.apx6.chipmunk.app.ui.dialog.CategoryAddDialog
import com.apx6.chipmunk.databinding.ActivityCategoryManageBinding
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CategoryManageActivity : BaseActivity<CategoryManageViewModel, ActivityCategoryManageBinding>() {

    override val viewModel: CategoryManageViewModel by viewModels()
    override fun getViewBinding(): ActivityCategoryManageBinding = ActivityCategoryManageBinding.inflate(layoutInflater)

    private val categoryManageAdapter by lazy { CMPagingAdapter(this::onCategoryLongPressed) }

    private lateinit var delTargetCategory: CmdCategory

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)

        initView()

        subscribeFlow()

        subscribeCheckList()
    }

    private fun initView() {
        this.statusBar(R.color.material_amber_700)

        with(binding) {
            ivClose.setOnSingleClickListener {
                /* 종료*/
                finish()
            }

        }

        initCategoryManageAdapter()
    }

    private fun setCategoryAddDialog(uid: Int) {
        binding.ivAdd.setOnSingleClickListener {
            /* 카테고리 추가*/
            val categoryAddDialog = CategoryAddDialog.newInstance(uid, ::postCategoryResultToast)
            supportFragmentManager.beginTransaction().add(categoryAddDialog, TAG).commitAllowingStateLoss()
        }
    }

    private fun postCategoryResultToast(result: Int) {
        val msg = if (result > 0) {
            getString(R.string.add_category_success)
        } else {
            getString(R.string.add_category_fail)
        }

        binding.rvCategories.smoothScrollToPosition(0)
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun initCategoryManageAdapter(isMediator: Boolean = false) {
        binding.apply {
            rvCategories.apply {
                addItemDecoration(DividerItemDecoration(this@CategoryManageActivity, DividerItemDecoration.VERTICAL))
                layoutManager = LinearLayoutManager(this@CategoryManageActivity, LinearLayoutManager.VERTICAL, false)
                adapter = categoryManageAdapter.withLoadStateHeaderAndFooter(
                    header = CMLoadStateAdapter { categoryManageAdapter.retry() },
                    footer = CMLoadStateAdapter { categoryManageAdapter.retry() }
                )
            }

            categoryManageAdapter.addLoadStateListener { loadState ->
                val isVisible = loadState.source.refresh is LoadState.NotLoading &&
                        loadState.append.endOfPaginationReached &&
                        categoryManageAdapter.itemCount < 1

                switchListView(!isVisible)

                val refreshState = if (isMediator) {
                    loadState.mediator?.refresh
                } else {
                    loadState.source.refresh
                }
                rvCategories.isVisible = refreshState is LoadState.NotLoading
                pbLoadingCategory.isVisible = refreshState is LoadState.Loading
                btnRetryCategory.isVisible = refreshState is LoadState.Error
                handleError(loadState)
            }

            btnRetryCategory.setOnClickListener {
                categoryManageAdapter.retry()
            }
        }
    }

    private fun subscribeFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userId.collect { uid ->
                    uid?.let { _uid ->
                        setCategoryAddDialog(_uid)
                        subscribeCategory(_uid)
                    }
                }
            }
        }
    }

    private fun subscribeCategory(uid: Int) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchCategories(uid).collectLatest { categories ->
                    categoryManageAdapter.submitData(categories)
                }
            }
        }
    }

    private fun subscribeCheckList() {
        lifecycleScope.launch {
            viewModel.checkList.collectLatest { checkList ->
                categoryDeleteConfirm(checkList)
            }
        }
    }

    private fun categoryDeleteConfirm(checkList: List<CmdCheckList>) {
        if (checkList.isNotEmpty()) {
            val delMsg = if (checkList.count() == 1) {
                "%s\n\n* %s".format(
                    getString(R.string.add_category_delete_with_checklist),
                    getString(R.string.check_list_count_only_1, checkList[0].title)
                )
            } else {
                "%s\n\n* %s".format(
                    getString(R.string.add_category_delete_with_checklist),
                    getString(R.string.check_list_count_over_2, checkList[0].title, checkList.count() - 1)
                )
            }

            Dialogs.confirm(
                context = this,
                btnYes = getString(R.string.dlg_confirm),
                btnNo = getString(R.string.dlg_cancel),
                message = delMsg,
                cancelable = true,
                positiveListener = { _, _ -> run {
                    viewModel.deleteCategory(delTargetCategory)
                }},
                negativeListener = { _, _ -> },
                title = getString(R.string.caution)
            )
        } else {
            viewModel.deleteCategory(delTargetCategory)
        }
    }

    private fun onCategoryLongPressed(category: CmdCategory) {
        delTargetCategory = category
        viewModel.getCheckListInCategory(category.uid, category.id)
    }

    private fun switchListView(lv: Boolean) {
        with(binding) {
            clCategory.visibilityExt(lv)
            clNoCategories.visibilityExt(!lv)
        }
    }

    private fun handleError(loadState: CombinedLoadStates) {
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error

        errorState?.let {
            Toast.makeText(this, "${it.error}", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val TAG = "CategoryManageActivity"
    }
}
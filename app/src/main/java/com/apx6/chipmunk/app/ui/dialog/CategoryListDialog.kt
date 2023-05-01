package com.apx6.chipmunk.app.ui.dialog

import android.view.ViewTreeObserver
import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ui.adapter.CategoryListDialogAdapter
import com.apx6.chipmunk.app.ui.base.BaseBottomSheetDialog
import com.apx6.chipmunk.databinding.DialogCategoryListBinding
import com.apx6.domain.dto.CmdCategory


class CategoryListDialog : BaseBottomSheetDialog<DialogCategoryListBinding, List<CmdCategory>>(
    R.layout.dialog_category_list,
    R.style.BottomDialogRoundStyle
) {
    private var callback: ((CmdCategory) -> Unit)? = null
    private var selected: CmdCategory = CmdCategory.default()

    override fun prepareDialog(param: List<CmdCategory>) {
        binding.apply {
            rvCategoryList.layoutManager = LinearLayoutManager(context)
            val categoryListAdapter = CategoryListDialogAdapter(param, selected).apply {
                setItemClickListener(object : CategoryListDialogAdapter.OnItemClickListener {
                    override fun itemClick(category: CmdCategory) {
                        callback?.invoke(category)
                        dismiss()
                    }
                })
            }

            rvCategoryList.apply {
                adapter = categoryListAdapter
                viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        rvCategoryList.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
            }

            /* 닫기버튼 */
            dialogHeader.ivListClose.setOnClickListener {
                dismiss()
            }
        }
    }

//    private fun scrollToCenter(position: Int, height: Int) {
//        val layoutManager = binding.rvCategoryList.layoutManager as LinearLayoutManager
//        layoutManager.scrollToPositionWithOffset(position, height / 3)
//    }

    companion object {
        fun newInstance(
            categoryList: List<CmdCategory>,
            selected: CmdCategory,
            callback: ((CmdCategory) -> Unit)?
        ) = CategoryListDialog().apply {
            this.callback = callback
            this.selected = selected
            param = categoryList
        }
    }
}

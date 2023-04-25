package com.apx6.chipmunk.app.ui.dialog

import androidx.recyclerview.widget.LinearLayoutManager
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ui.base.BaseBottomSheetDialog
import com.apx6.chipmunk.databinding.DialogCategoryListBinding
import com.apx6.domain.dto.CmdCategory

/**
 * Later TODO
 */
class CategoryListDialog : BaseBottomSheetDialog<DialogCategoryListBinding, List<CmdCategory>>(
    R.layout.dialog_category_list,
    R.style.BottomDialogRoundStyle
) {
//    private var callback: ((MpIds, String) -> Unit)? = null
//    private var ids: MpIds? = null

    override fun prepareDialog(param: List<CmdCategory>) {
//        binding.apply {
//            rvSectionList.layoutManager = LinearLayoutManager(context)
//            ids?.let { _ids ->
//                val sectionListAdapter = SectionListDialogAdapter(param, _ids).apply {
//                    setItemClickListener(object : SectionListDialogAdapter.OnItemClickListener {
//                        override fun itemClick(ids: MpIds, displayName: String) {
//                            callback?.invoke(ids, displayName)
//                            dismiss()
//                        }
//                    })
//                }
//                rvSectionList.adapter = sectionListAdapter
//                rvSectionList.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
//                    override fun onGlobalLayout() {
//                        rvSectionList.viewTreeObserver.removeOnGlobalLayoutListener(this)
//                        scrollToCenter(sectionListAdapter.getCurrentPosition(), rvSectionList.measuredHeight)
//                    }
//                })
//            }
//
//            /* 닫기버튼 */
//            dialogHeader.ivListClose.setOnClickListener {
//                dismiss()
//            }
//        }
    }

    private fun scrollToCenter(position: Int, height: Int) {
        val layoutManager = binding.rvSectionList.layoutManager as LinearLayoutManager
        layoutManager.scrollToPositionWithOffset(position, height / 3)
    }

    companion object {
        fun newInstance(
//            sectionList: List<CmdCategory>,
//            ids: MpIds?,
//            callback: ((MpIds, String) -> Unit)?
        ) = CategoryListDialog().apply {
//            this.ids = ids
//            this.callback = callback
//            param = sectionList
        }
    }
}

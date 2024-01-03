package com.apx6.chipmunk.app.ui.adapter.category_manage

import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.databinding.ItemCategoryManageBinding
import com.apx6.domain.dto.CmdCategory

class CMViewHolder(
    val binding: ItemCategoryManageBinding,
    val modifyCategoryName: (CmdCategory) -> Unit,
    val deleteCategory: (CmdCategory) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: CmdCategory) {

        binding.apply {
            tvCategoryName.text = category.name

            ivCategoryModify.setOnSingleClickListener {
                modifyCategoryName(category)
            }

            ivCategoryDelete.setOnSingleClickListener {
                deleteCategory(category)
            }
        }
//
//        binding.tvCategoryName.apply {
//            text = category.name
//            setOnLongClickListener {
//                onCategoryLongPressed(category)
//                false
//            }
//        }
    }
}

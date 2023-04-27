package com.apx6.chipmunk.app.ui.adapter.category_manage

import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.databinding.ItemCategoryManageBinding
import com.apx6.domain.dto.CmdCategory

class CMViewHolder(
    val binding: ItemCategoryManageBinding,
    val onItemClicked: (CmdCategory) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: CmdCategory) {

        binding.tvCategoryName.apply {
            text = category.name
            setOnClickListener {
                onItemClicked(category)
            }
        }
    }
}

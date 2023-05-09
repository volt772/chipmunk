package com.apx6.chipmunk.app.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.databinding.ItemCategoryBinding
import com.apx6.domain.dto.CmdCategory


class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: CmdCategory, onItemClicked: (CmdCategory) -> Unit) {

        binding.apply {
            chCategoryName.text = category.name
            chCategoryName.setOnClickListener {
                onItemClicked(category)
            }
        }
    }
}

package com.apx6.chipmunk.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apx6.chipmunk.app.ui.viewholder.CategoryViewHolder
import com.apx6.chipmunk.databinding.ItemCategoryBinding
import com.apx6.domain.dto.CmdCategory


class CategoryAdapter(
    private val onItemClicked: (CmdCategory) -> Unit
) : ListAdapter<CmdCategory, CategoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder(
        ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CmdCategory>() {
            override fun areItemsTheSame(oldItem: CmdCategory, newItem: CmdCategory): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CmdCategory, newItem: CmdCategory): Boolean =
                oldItem == newItem
        }
    }
}

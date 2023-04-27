package com.apx6.chipmunk.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apx6.chipmunk.app.ui.viewholder.CategoryManageViewHolder
import com.apx6.chipmunk.databinding.ItemCategoryManageBinding
import com.apx6.domain.dto.CmdCategory


class CategoryManageAdapter(
    private val onItemClicked: (CmdCategory) -> Unit
) : ListAdapter<CmdCategory, CategoryManageViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryManageViewHolder(
        ItemCategoryManageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

//    override fun getItemCount(): Int {
//        println("probe :: category :: item count : ${super.getItemCount()}")
//        return super.getItemCount()
//    }

    override fun onBindViewHolder(holder: CategoryManageViewHolder, position: Int) =
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

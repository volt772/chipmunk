package com.apx6.chipmunk.app.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.databinding.ItemCategoryManageBinding
import com.apx6.domain.dto.CmdCategory


class CategoryManageViewHolder(private val binding: ItemCategoryManageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: CmdCategory, onItemClicked: (CmdCategory) -> Unit) {

        binding.tvCategoryName.apply {
            text = category.name
//            chCategoryName.text = category.name
            setOnClickListener {
                onItemClicked(category)
            }
        }
//        binding.postTitle.text = post.title
//        binding.postAuthor.text = post.author
//        binding.imageView.load(post.imageUrl) {
//            placeholder(R.drawable.ic_photo)
//            error(R.drawable.ic_broken_image)
//        }
//
//        binding.root.setOnClickListener {
//            onItemClicked(post, binding.imageView)
//        }
    }
}

package com.apx6.chipmunk.app.ui.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.databinding.ItemCategoryBinding
import com.apx6.chipmunk.databinding.ItemChecklistBinding
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.dto.CmdCheckList


class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(category: CmdCategory, onItemClicked: (CmdCategory) -> Unit) {

        binding.apply {
            tvChecklistTitle.text = category.name
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

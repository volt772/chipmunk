package com.apx6.chipmunk.app.ui.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.databinding.ItemTaskBinding
import com.apx6.domain.dto.CmdTask


class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: CmdTask, onItemClicked: (CmdTask, ImageView) -> Unit) {

        binding.apply {
            tvTaskTitle.text = task.title
            tvTaskMemo.text = task.memo
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

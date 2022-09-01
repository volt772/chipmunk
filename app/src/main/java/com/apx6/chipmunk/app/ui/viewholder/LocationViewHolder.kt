package com.apx6.chipmunk.app.ui.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.databinding.ItemChecklistBinding
import com.apx6.domain.dto.CmdCheckList


class LocationViewHolder(private val binding: ItemChecklistBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(checkList: CmdCheckList, onItemClicked: (CmdCheckList, ImageView) -> Unit) {

        binding.apply {
            tvChecklistTitle.text = checkList.title
            tvChecklistMemo.text = checkList.memo
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

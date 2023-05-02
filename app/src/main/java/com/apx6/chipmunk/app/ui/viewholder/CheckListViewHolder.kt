package com.apx6.chipmunk.app.ui.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.databinding.ItemChecklistBinding
import com.apx6.domain.dto.CmdCheckList


class CheckListViewHolder(private val binding: ItemChecklistBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(checkList: CmdCheckList, showDetailDialog: (CmdCheckList) -> Unit) {

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
        binding.root.setOnSingleClickListener {
            showDetailDialog.invoke(checkList)
        }
    }
}

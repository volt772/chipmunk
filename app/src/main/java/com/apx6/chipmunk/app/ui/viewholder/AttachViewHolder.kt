package com.apx6.chipmunk.app.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.databinding.ItemAttachBinding
import com.apx6.domain.dto.CmdAttachment


class AttachViewHolder(
    private val binding: ItemAttachBinding,
    private val deleteAttach: (CmdAttachment) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(attachment: CmdAttachment) {

        binding.apply {
            tvAttachName.text = attachment.name
            tvAttachSize.text = attachment.size.toString()
            ivDelete.setOnSingleClickListener {
                deleteAttach.invoke(attachment)
            }
        }
    }
}

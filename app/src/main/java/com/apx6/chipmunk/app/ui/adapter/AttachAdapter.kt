package com.apx6.chipmunk.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apx6.chipmunk.app.ui.viewholder.AttachViewHolder
import com.apx6.chipmunk.databinding.ItemAttachBinding
import com.apx6.domain.dto.CmdAttachment
import com.apx6.domain.dto.CmdCategory


class AttachAdapter(
) : ListAdapter<CmdAttachment, AttachViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AttachViewHolder(
        ItemAttachBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: AttachViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CmdAttachment>() {
            override fun areItemsTheSame(oldItem: CmdAttachment, newItem: CmdAttachment): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CmdAttachment, newItem: CmdAttachment): Boolean =
                oldItem == newItem
        }
    }
}

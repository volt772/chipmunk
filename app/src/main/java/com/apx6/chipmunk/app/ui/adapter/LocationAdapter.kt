package com.apx6.chipmunk.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apx6.chipmunk.app.ui.viewholder.CheckListViewHolder
import com.apx6.chipmunk.databinding.ItemChecklistBinding
import com.apx6.domain.dto.CmdCheckList


class LocationAdapter(
    private val onItemClicked: (CmdCheckList, ImageView) -> Unit
) : ListAdapter<CmdCheckList, CheckListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CheckListViewHolder(
        ItemChecklistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CheckListViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CmdCheckList>() {
            override fun areItemsTheSame(oldItem: CmdCheckList, newItem: CmdCheckList): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CmdCheckList, newItem: CmdCheckList): Boolean =
                oldItem == newItem
        }
    }
}

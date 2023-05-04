package com.apx6.chipmunk.app.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apx6.chipmunk.app.ui.viewholder.CheckListViewHolder
import com.apx6.domain.dto.CmdCheckList


class CheckListAdapter(
    private val selectCheckList: (CmdCheckList) -> Unit
) : ListAdapter<CmdCheckList, CheckListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CheckListViewHolder(parent)

    override fun onBindViewHolder(holder: CheckListViewHolder, position: Int) =
        holder.bind(getItem(position), selectCheckList)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CmdCheckList>() {
            override fun areItemsTheSame(oldItem: CmdCheckList, newItem: CmdCheckList): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CmdCheckList, newItem: CmdCheckList): Boolean =
                oldItem == newItem
        }
    }
}

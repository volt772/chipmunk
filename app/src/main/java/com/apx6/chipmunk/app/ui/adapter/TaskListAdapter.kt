package com.apx6.chipmunk.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apx6.chipmunk.app.ui.viewholder.TaskViewHolder
import com.apx6.chipmunk.databinding.ItemTaskBinding
import com.apx6.domain.dto.CmdTask


class TaskListAdapter(
    private val onItemClicked: (CmdTask, ImageView) -> Unit
) : ListAdapter<CmdTask, TaskViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TaskViewHolder(
        ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CmdTask>() {
            override fun areItemsTheSame(oldItem: CmdTask, newItem: CmdTask): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CmdTask, newItem: CmdTask): Boolean =
                oldItem == newItem
        }
    }
}

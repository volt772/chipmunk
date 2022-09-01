package com.apx6.chipmunk.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apx6.chipmunk.app.ui.viewholder.CheckListViewHolder
import com.apx6.chipmunk.app.ui.viewholder.LocationViewHolder
import com.apx6.chipmunk.databinding.ItemChecklistBinding
import com.apx6.chipmunk.databinding.ItemLocationBinding
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdLocationDoc


class LocationAdapter(
    private val onItemClicked: (CmdLocationDoc) -> Unit
) : ListAdapter<CmdLocationDoc, LocationViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LocationViewHolder(
        ItemLocationBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CmdLocationDoc>() {
            override fun areItemsTheSame(oldItem: CmdLocationDoc, newItem: CmdLocationDoc): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CmdLocationDoc, newItem: CmdLocationDoc): Boolean =
                oldItem == newItem
        }
    }
}

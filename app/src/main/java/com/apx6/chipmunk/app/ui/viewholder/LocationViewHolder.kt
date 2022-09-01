package com.apx6.chipmunk.app.ui.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.databinding.ItemChecklistBinding
import com.apx6.chipmunk.databinding.ItemLocationBinding
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdLocationDoc


class LocationViewHolder(private val binding: ItemLocationBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(location: CmdLocationDoc, onItemClicked: (CmdLocationDoc) -> Unit) {

        binding.apply {
            tvLocationName.text = location.placeName
            tvLocationAddr.text = location.addressName
        }
//        binding.postTitle.text = post.title
//        binding.postAuthor.text = post.author
//        binding.imageView.load(post.imageUrl) {
//            placeholder(R.drawable.ic_photo)
//            error(R.drawable.ic_broken_image)
//        }
//
        binding.root.setOnClickListener {
            onItemClicked(location)
        }
    }
}

package com.apx6.chipmunk.app.ui.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.databinding.ItemChecklistBinding
import com.apx6.domain.dto.CmdCheckList


class CheckListViewHolder(
    private val binding: ItemChecklistBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(checkList: CmdCheckList, selectCheckList: (CmdCheckList) -> Unit) {

        binding.apply {
            tvChecklistTitle.text = checkList.title
            tvChecklistMemo.text = checkList.memo
        }

        binding.root.setOnSingleClickListener {
            selectCheckList.invoke(checkList)
        }
    }
}

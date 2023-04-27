package com.apx6.chipmunk.app.ui.adapter.category_manage

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.apx6.domain.dto.CmdCategory

class CMPagingDiffCallBack : DiffUtil.ItemCallback<CmdCategory>() {
    override fun areItemsTheSame(oldItem: CmdCategory, newItem: CmdCategory): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: CmdCategory, newItem: CmdCategory): Boolean {
        return oldItem == newItem
    }
}

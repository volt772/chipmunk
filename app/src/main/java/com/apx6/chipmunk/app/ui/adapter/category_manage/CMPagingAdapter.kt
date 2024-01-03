package com.apx6.chipmunk.app.ui.adapter.category_manage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.apx6.chipmunk.databinding.ItemCategoryManageBinding
import com.apx6.domain.dto.CmdCategory
import javax.inject.Inject

class CMPagingAdapter @Inject constructor(
    private val modifyCategoryName: (CmdCategory) -> Unit,
    private val deleteCategory: (CmdCategory) -> Unit
) : PagingDataAdapter<CmdCategory, CMViewHolder>(
    CMPagingDiffCallBack()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CMViewHolder {

        return CMViewHolder(
            ItemCategoryManageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            modifyCategoryName,
            deleteCategory
        )
    }

    override fun onBindViewHolder(holder: CMViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}

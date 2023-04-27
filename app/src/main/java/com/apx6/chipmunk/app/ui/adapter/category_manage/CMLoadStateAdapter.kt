package com.apx6.chipmunk.app.ui.adapter.category_manage

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class CMLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<CMLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: CMLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CMLoadStateViewHolder {
        return CMLoadStateViewHolder.create(parent, retry)
    }
}

package com.apx6.chipmunk.app.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.convertDate
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.model.HistoryModel
import com.apx6.chipmunk.app.ui.base.BaseViewHolder
import com.apx6.chipmunk.databinding.ItemHistoryBinding


/**
 * HistoryAdapter
 */
class HistoryAdapter(
    private val searchHistory: (String) -> Unit,
    private val deleteHistory: (HistoryModel) -> Unit
) : ListAdapter<HistoryModel, HistoryAdapter.HistoryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HistoryViewHolder(parent)

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) =
        holder.bind(getItem(position), deleteHistory)


    inner class HistoryViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder<HistoryModel, ItemHistoryBinding>(
        parent,
        R.layout.item_history
    ) {

        fun bind(hm: HistoryModel, deleteHistory: (HistoryModel) -> Unit) {

            binding.apply {
                tvKeyword.text = hm.keyword     // Recent Search Keyword
                tvRegdate.text = hm.regDate.convertDate()    // Search Date (millis)

                /* listener : keyword select -> search*/
                tvKeyword.setOnSingleClickListener {
                    searchHistory.invoke(hm.keyword)
                }

                /* listener : delete from history list*/
                ivDelete.setOnSingleClickListener {
                    deleteHistory.invoke(hm)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HistoryModel>() {
            override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean =
                oldItem.keyword == newItem.keyword && oldItem.regDate == newItem.regDate

            override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel): Boolean =
                oldItem == newItem
        }
    }
}




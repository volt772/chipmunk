package com.apx6.chipmunk.app.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.visibilityExt
import com.apx6.chipmunk.app.ui.base.BaseViewHolder
import com.apx6.chipmunk.databinding.ItemListCategoryBinding
import com.apx6.domain.dto.CmdCategory


class CategoryListDialogAdapter(
    private val list: List<CmdCategory>,
    private val selected: CmdCategory
) : RecyclerView.Adapter<CategoryListDialogViewHolder>() {

    interface OnItemClickListener {
        fun itemClick(category: CmdCategory)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryListDialogViewHolder(parent)

    override fun onBindViewHolder(holder: CategoryListDialogViewHolder, position: Int) {
        holder.bind(list[position], selected, itemClickListener)
    }

    override fun getItemCount() = list.size
}

/**
 * ViewHolder
 */
class CategoryListDialogViewHolder(parent: ViewGroup) : BaseViewHolder<CmdCategory, ItemListCategoryBinding>(
    parent,
    R.layout.item_list_category
) {
    override fun bind(data: CmdCategory) { }
    fun bind(category: CmdCategory, selected: CmdCategory, listener: CategoryListDialogAdapter.OnItemClickListener?) {
        binding.apply {
            tvCategoryName.text = category.name

            if (selected.id == category.id) {
                ivCategorySelect.visibilityExt(true)
            } else {
                ivCategorySelect.visibilityExt(false)
            }

            clCategoryName.setOnSingleClickListener {
                listener?.itemClick(category)
            }
        }
    }
}

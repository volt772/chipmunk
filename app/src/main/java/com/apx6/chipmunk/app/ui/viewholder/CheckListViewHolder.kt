package com.apx6.chipmunk.app.ui.viewholder

import android.view.ViewGroup
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.constants.CmdCategoryDiffLabel
import com.apx6.chipmunk.app.ext.convertDateLabel
import com.apx6.chipmunk.app.ext.getDfFromToday
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ui.base.BaseViewHolder
import com.apx6.chipmunk.databinding.ItemChecklistBinding
import com.apx6.domain.dto.CmdCheckList


class CheckListViewHolder(
    parent: ViewGroup
) : BaseViewHolder<CmdCheckList, ItemChecklistBinding>(
    parent,
    R.layout.item_checklist
) {

    fun bind(cl: CmdCheckList, selectCheckList: (CmdCheckList) -> Unit) {

        binding.apply {
            tvChecklistTitle.text = cl.title
            tvDay.text = convertDateLabel(cl.endDate)

            val dfDays = cl.endDate.getDfFromToday()
            tvDay.setTextColor(
                context.getColor(CmdCategoryDiffLabel.getColorByDiffDays(dfDays).color)
            )

            tvChecklistDesc.text = cl.memo
        }

        binding.root.setOnSingleClickListener {
            selectCheckList.invoke(cl)
        }
    }

    override fun bind(data: CmdCheckList) { }
}

package com.apx6.chipmunk.app.ui.viewholder

import android.content.res.ColorStateList
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
            tvDay.text = convertDateLabel(cl.endDate, true)

            val dfDays = cl.endDate.getDfFromToday()

            val dayColor = context.getColor(CmdCategoryDiffLabel.getColorByDiffDays(dfDays).color)

            ivDayStatus.setColorFilter(dayColor)
            tvDay.backgroundTintList = ColorStateList.valueOf(dayColor)

            tvChecklistDesc.text = cl.memo
        }

        binding.root.setOnSingleClickListener {
            selectCheckList.invoke(cl)
        }
    }

    override fun bind(data: CmdCheckList) { }
}

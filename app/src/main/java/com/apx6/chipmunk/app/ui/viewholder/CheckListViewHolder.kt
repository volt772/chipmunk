package com.apx6.chipmunk.app.ui.viewholder

import android.content.res.ColorStateList
import android.view.ViewGroup
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.constants.CmdCategoryDiffLabel
import com.apx6.chipmunk.app.ext.convertDateLabel
import com.apx6.chipmunk.app.ext.getDfFromToday
import com.apx6.chipmunk.app.ext.millisToFormedDate
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.visibilityExt
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
            /* 제목*/
            tvChecklistTitle.text = cl.title

            /* Diff Days : `%d일전`, `%d일지남`, `오늘`*/
            tvDay.text = cl.exeDate.convertDateLabel(true)

            /* Diff Days : 백그라운드 작업*/
            val dfDays = cl.exeDate.getDfFromToday()
            val dayColor = context.getColor(CmdCategoryDiffLabel.getColorByDiffDays(dfDays).color)
            tvDay.backgroundTintList = ColorStateList.valueOf(dayColor)

            /* 수행일 : yy.mm.dd*/
            tvChecklistExeDate.text = cl.exeDate.millisToFormedDate()

            /* 메모요약*/
            val memoView = cl.memo?.isNotBlank()
            clChecklistMemo.visibilityExt(memoView?: false)
            tvChecklistDesc.text = cl.memo

            /* 카테고리이름*/
            tvChecklistCategory.text = cl.checkListName
        }

        binding.root.setOnSingleClickListener {
            selectCheckList.invoke(cl)
        }
    }
}

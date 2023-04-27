package com.apx6.chipmunk.app.ui.picker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.R
import com.apx6.chipmunk.databinding.ItemRangePickerDayBinding
import com.apx6.chipmunk.databinding.ItemRangePickerMonthBinding

/**
 * RangePickerViewHolder
 */

internal abstract class RangePickerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(item: RangePickerEntity, actionListener: (RangePickerEntity, Int) -> Unit)
}

/**
 * ViewHolder (월)
 */
internal class MonthViewHolder(view: View, parent: ViewGroup) : RangePickerViewHolder(view) {
    val binding = ItemRangePickerMonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    override fun onBind(item: RangePickerEntity, actionListener: (RangePickerEntity, Int) -> Unit) {
        if (item is RangePickerEntity.Month) {
            println("probe :: picker :: month viewholder label : ${item.label}")
//            monthName.text = item.label
//            val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            println("probe :: picker :: month viewholder label : ${item.label}, binding : $binding")

//            binding.tvMonthName.text = item.label
            binding.tvMonthName.text = "hamster!"
        }
    }
}

/**
 * ViewHolder (주)
 */
internal open class WeekViewHolder(view: View, parent: ViewGroup) : RangePickerViewHolder(view) {
    override fun onBind(item: RangePickerEntity, actionListener: (RangePickerEntity, Int) -> Unit) {
        println("probe :: picker :: this empty??")
    }
}

/**
 * ViewHolder (일)
 */
internal class DayViewHolder(view: View, parent: ViewGroup) : RangePickerViewHolder(view) {
//    val binding = ItemRangePickerDayBinding.inflate(LayoutInflater.from(view.context))
    val binding = ItemRangePickerDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    private val dayName by lazy { binding.tvDayName }
    private val leftBackGround by lazy { binding.vwLeftBg }
    private val rightBackGround by lazy { binding.vwRightBg }

    override fun onBind(item: RangePickerEntity, actionListener: (RangePickerEntity, Int) -> Unit) {
        if (item is RangePickerEntity.Day) {
            dayName.text = item.label
            when (item.selection) {
                SelectionType.START -> {
                    dayName.select()
                    leftBackGround.dehighlight()
                    if (item.isRange) rightBackGround.highlight()
                    else rightBackGround.dehighlight()
                }
                SelectionType.END -> {
                    dayName.select()
                    leftBackGround.highlight()
                    rightBackGround.dehighlight()
                }
                SelectionType.BETWEEN -> {
                    dayName.deselect()

                    /* Edge 음영처리*/
                    if ((item.dayOfEdge == DayOfEdge.FIRST && item.weekDay == WeekDays.SAT) ||
                        (item.dayOfEdge == DayOfEdge.LAST && item.weekDay == WeekDays.SUN)) {
                        /* 월초, 월말이 주말인경우 : 좌,우측 모두 둥근 음영*/
                        leftBackGround.leftEdgeSelect()
                        rightBackGround.rightEdgeSelect()
                    } else if (item.dayOfEdge == DayOfEdge.FIRST ||
                        item.weekDay == WeekDays.SUN
                    ) {
                        /* 월 시작일이고, 일요일인경우 : 좌측 둥근 음영*/
                        rightBackGround.highlight()
                        leftBackGround.leftEdgeSelect()
                    } else if (item.dayOfEdge == DayOfEdge.LAST ||
                        item.weekDay == WeekDays.SAT
                    ) {
                        /* 월 마지막일이고, 토요일인경우 : 우측 둥근 음영*/
                        rightBackGround.rightEdgeSelect()
                        leftBackGround.highlight()
                    } else {
                        /* 그외 : 일반음영*/
                        rightBackGround.highlight()
                        leftBackGround.highlight()
                    }
                }
                SelectionType.NONE -> {
                    leftBackGround.dehighlight()
                    rightBackGround.dehighlight()
                    dayName.deselect()
                }
            }

            dayName.setTextColor(getFontColor(item))

            if (item.state != DateState.DISABLED) {
                itemView.setOnClickListener {
                    actionListener.invoke(
                        item,
                        adapterPosition
                    )
                }
            } else {
                itemView.setOnClickListener(null)
            }
        }
    }

    /**
     * 글자 색상
     */
    private fun getFontColor(item: RangePickerEntity.Day): Int {
        return if (item.selection == SelectionType.START || item.selection == SelectionType.END) {
            ContextCompat.getColor(itemView.context, R.color.white)
        } else {
            val color = when (item.state) {
                DateState.DISABLED -> R.color.material_gray_400
                DateState.WEEKEND -> R.color.black_h0
                else -> R.color.black_h0
            }
            ContextCompat.getColor(itemView.context, color)
        }
    }

    /**
     * 선택 Style (일반)
     */
    private fun View.select() {
        val drawable = ContextCompat.getDrawable(context, R.drawable.bg_range_picker_selected_day)
        background = drawable
    }

    /**
     * 선택 Range Edge Style Right (토요일 및 월말일)
     */
    private fun View.rightEdgeSelect() {
        val drawable = ContextCompat.getDrawable(context, R.drawable.bg_range_picker_selected_right_edge)
        background = drawable
    }

    /**
     * 선택 Range Edge Style Left (일요일 및 월시작일)
     */
    private fun View.leftEdgeSelect() {
        val drawable = ContextCompat.getDrawable(context, R.drawable.bg_range_picker_selected_left_edge)
        background = drawable
    }

    /**
     * 선택해제 Style
     */
    private fun View.deselect() {
        background = null
    }

    private fun View.dehighlight() {
        val color = ContextCompat.getColor(context, R.color.transparent)
        setBackgroundColor(color)
    }

    private fun View.highlight() {
        val color = ContextCompat.getColor(context, R.color.material_indigo_50)
        setBackgroundColor(color)
    }
}

internal class EmptyViewHolder(view: View, parent: ViewGroup) : WeekViewHolder(view, parent)
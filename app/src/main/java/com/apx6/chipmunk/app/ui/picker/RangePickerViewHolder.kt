package com.apx6.chipmunk.app.ui.picker

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.R
import kotlinx.android.synthetic.main.item_range_picker_day.view.*
import kotlinx.android.synthetic.main.item_range_picker_month.view.*

/**
 * RangePickerViewHolder
 */

internal abstract class RangePickerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(item: RangePickerEntity, actionListener: (RangePickerEntity, Int) -> Unit)
}

/**
 * ViewHolder (월)
 */
internal class MonthViewHolder(private val view: View) : RangePickerViewHolder(view) {
    private val monthName by lazy { view.tv_month_name }
    override fun onBind(item: RangePickerEntity, actionListener: (RangePickerEntity, Int) -> Unit) {
        if (item is RangePickerEntity.Month) {
            monthName.text = item.label
        }
    }
}

/**
 * ViewHolder (주)
 */
internal open class WeekViewHolder(view: View) : RangePickerViewHolder(view) {
    override fun onBind(item: RangePickerEntity, actionListener: (RangePickerEntity, Int) -> Unit) {
    }
}

/**
 * ViewHolder (일)
 */
internal class DayViewHolder(view: View) : RangePickerViewHolder(view) {
    private val dayName by lazy { view.tv_day_name }
    private val leftBackGround by lazy { view.vw_left_bg }
    private val rightBackGround by lazy { view.vw_right_bg }

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

internal class EmptyViewHolder(view: View) : WeekViewHolder(view)
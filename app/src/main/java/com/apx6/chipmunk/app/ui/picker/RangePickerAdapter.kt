package com.apx6.chipmunk.app.ui.picker

import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ui.picker.*

/**
 * RangePickerAdapter
 */

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachedToRoot: Boolean = false): View =
    from(context).inflate(layoutId, this, attachedToRoot)

internal class RangePickerAdapter : RecyclerView.Adapter<RangePickerViewHolder>() {
    private val data: MutableList<RangePickerEntity> = mutableListOf()
    var onActionListener: (RangePickerEntity, Int) -> Unit = { _, _ -> }

    /**
     * Data 설정
     * @desc 일자 중복으로 DiffCallback 사용
     */
    fun setData(newData: List<RangePickerEntity>) {
        val diffCallback = RangePickerDiffCallback(data, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data.clear()
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    /**
     * ViewHolder 생성
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RangePickerViewHolder {
        return when (viewType) {
            CalendarType.MONTH.ordinal -> MonthViewHolder(parent.inflate(R.layout.item_range_picker_month))
            CalendarType.DAY.ordinal -> DayViewHolder(parent.inflate(R.layout.item_range_picker_day))
            else -> EmptyViewHolder(parent.inflate(R.layout.item_range_picker_empty))
        }
    }

    /**
     * Item Count
     */
    override fun getItemCount(): Int = data.size

    /**
     * Binding
     */
    override fun onBindViewHolder(holder: RangePickerViewHolder, position: Int) {
        holder.onBind(data[position], onActionListener)
    }

    /**
     * ViewType
     */
    override fun getItemViewType(position: Int) = data[position].calendarType
}

/**
 * DiffCallback
 */
internal class RangePickerDiffCallback(
    private val oldList: List<RangePickerEntity>,
    private val newList: List<RangePickerEntity>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]::class == newList[newItemPosition]::class
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (oldList[oldItemPosition] is RangePickerEntity.Day && newList[newItemPosition] is RangePickerEntity.Day) {
            val oldDay = oldList[oldItemPosition] as RangePickerEntity.Day
            val newDay = newList[newItemPosition] as RangePickerEntity.Day
            oldDay.selection == newDay.selection && oldDay.isRange == newDay.isRange
        } else {
            oldList[oldItemPosition].selectionType == newList[newItemPosition].selectionType
        }
    }
}

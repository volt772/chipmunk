package com.apx6.chipmunk.app.ui.picker

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.isTheSameDay
import com.apx6.chipmunk.app.ext.withTime
import java.util.*
import java.util.Calendar.*

/**
 * RangePicker
 */

class RangePicker : RecyclerView {
    private val timeZone = TimeZone.getDefault()
    private val locale = Locale.getDefault()

    /* Picker Adapter*/
    private val rangePickerAdapter = RangePickerAdapter()

    /* '월' 표기 다국어*/
    private var labelForMonth: String = ""

    /* '전체' 표기 다국어*/
    private var labelForFullDate: String = ""

    /* 시작 캘린더(월)*/
    private val startCalendar = getInstance(timeZone, locale)

    /* 종료 캘린더(월)*/
    private val endCalendar = getInstance(timeZone, locale)

    /* 오늘기준 캘린더(월)*/
    private val todayIncludedCalendar = getInstance(timeZone, locale)

    /* 캘린더 데이터 리스트*/
    private var calendarData: MutableList<RangePickerEntity> = mutableListOf()

    /* 선택 : 시작일자*/
    private var startDateSelection: SelectedDate? = null

    /* 선택 : 종료일자*/
    private var endDateSelection: SelectedDate? = null

    /* 선택 : 캘린더 생성 후, 기본 일자 선택 (오늘)*/
    private var defaultDateFunc:(RangePickerEntity.Day) -> Unit = { _ -> }

    /* 선택[UI표시] : 단일선택*/
    private var onStartSelectedListener: (startDate: Date, label: String) -> Unit = { _, _ -> }

    /* 선택[UI표시] : 범위선택*/
    private var onRangeSelectedListener: (startDate: Date, endDate: Date, startLabel: String, endLabel: String) -> Unit =
        { _, _, _, _ -> }

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )

    init {
        /* '월' 다국어*/
        labelForMonth = context.resources.getString(R.string.range_picker_month_title)
        /* '전체형식' 다국어*/
        labelForFullDate = context.resources.getString(R.string.range_picker_date_label)

        /* 시간 초기화 : '일자'만 놓고 시간은 전부 '0'으로 지정*/
        todayIncludedCalendar.set(HOUR_OF_DAY, 0)
        todayIncludedCalendar.set(MINUTE, 0)
        todayIncludedCalendar.set(SECOND, 0)
        todayIncludedCalendar.set(MILLISECOND, 0)

        /* 시작 캘린더 (시간)*/
        startCalendar.time = todayIncludedCalendar.time
        /* 종료 캘린더 (시간)*/
        endCalendar.time = todayIncludedCalendar.time

        /* 시작 캘린더 YEAR (현재연도 - 시작연도)*/
        startCalendar.add(YEAR, START_YEAR_FROM_NOW)

        /* 종료 캘린더 YEAR (현재연도 + 시작연도)*/
        endCalendar.add(YEAR, END_YEAR_FROM_NOW)

        setBackgroundColor(ContextCompat.getColor(context, R.color.white))
        initAdapter()
        initListener()
    }

    /**
     * 캘린더 생성 후, 기본 일자 선택 (오늘)
     */
    fun setSelectionDefaultDate(callback:(RangePickerEntity.Day) -> Unit) {
        defaultDateFunc = callback
    }

    /**
     * UI에서 범위 지정
     */
    fun setSelectionDate(startDate: Date, endDate: Date? = null) {
        selectDate(startDate)
        if (endDate != null) selectDate(endDate)
    }

    /**
     * 일자 선택
     */
    private fun selectDate(date: Date) {
        val index = calendarData.indexOfFirst { it is RangePickerEntity.Day && it.date.isTheSameDay(date) }
        onDaySelected(calendarData[index] as RangePickerEntity.Day, index)
    }

    /**
     * 캘린더 해당 월로 스크롤
     */
    private fun scrollToDate(date: Date) {
        val index = calendarData.indexOfFirst { it is RangePickerEntity.Day && it.date.isTheSameDay(date) }
        if (index > -1) {
            scrollToPosition(index)
        }
    }

    /**
     * 단일 Callback Listener
     */
    fun setOnStartSelectedListener(callback: (startDate: Date, label: String) -> Unit) {
        onStartSelectedListener = callback
    }

    /**
     * 범위 Callback Listener
     */
    fun setOnRangeSelectedListener(callback: (startDate: Date, endDate: Date, startLabel: String, endLabel: String) -> Unit) {
        onRangeSelectedListener = callback
    }

    /**
     * 캘린더 Adapter 초기설정
     */
    private fun initListener() {
        rangePickerAdapter.onActionListener = { item, position ->
            if (item is RangePickerEntity.Day) onDaySelected(item, position)
        }
    }

    /**
     * 캘린더 Adapter 생성
     */
    private fun initAdapter() {
        layoutManager = GridLayoutManager(context, TOTAL_COLUMN_COUNT).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return calendarData[position].columnCount
                }
            }
        }

        adapter = rangePickerAdapter
        refreshData()

        /* 생성 후, 현재 '월' 위치로 이동*/
        scrollToDate(todayIncludedCalendar.time)
    }

    /**
     * 캘린더 Data Refresh
     */
    private fun refreshData() {
        calendarData = buildCalendarData()
        rangePickerAdapter.setData(calendarData)
    }

    /**
     * 캘린더 Data 생성
     */
    private fun buildCalendarData(): MutableList<RangePickerEntity> {
        val calendarData = mutableListOf<RangePickerEntity>()
        val cal = getInstance(timeZone, locale)
        cal.withTime(startCalendar.time)

        /**
         * @desc 시작 캘린더 Year 부터 종료 캘린더 Year 까지 생성
         * @desc View Type
         * 1. 월 : 년 및 월을 보여주는 타이틀
         * 2. 요일 : 요일을 보여주는 레이블
         * 3. 일 : 각각 일을 표시
         * 4. 빈칸 : 월의 시작 및 마지막에 비어있는 뷰
         */
        val monthDifference = endCalendar.totalMonthDifference(startCalendar)

        cal.set(DAY_OF_MONTH, 1)
        (0..monthDifference).forEach { _ ->
            val totalDayInAMonth = cal.getActualMaximum(DAY_OF_MONTH)
            (1..totalDayInAMonth).forEach { _ ->
                val day = cal.get(DAY_OF_MONTH)
                val dayOfWeek = cal.get(DAY_OF_WEEK)

                /**
                 * @desc 오늘날짜 기준 미래일 = DISABLED
                 * @desc 오늘날짜 포함 이전일 = WEEKDAY
                 */

                val dateState = DateState.WEEKDAY

                when (day) {
                    /* 월 시작일 (empty 먼저추가)*/
                    1 -> {
                        calendarData.add(RangePickerEntity.Month(cal.toPrettyMonthOnly(labelForMonth)))
                        calendarData.addAll(createStartEmptyView(dayOfWeek))
                        calendarData.add(
                            RangePickerEntity.Day(
                                day.toString(),
                                cal.toPrettyDateString(labelForFullDate),
                                cal.time,
                                state = dateState,
                                weekDay = getWeekByDay(dayOfWeek),
                                dayOfEdge = DayOfEdge.FIRST
                            )
                        )
                    }
                    /* 월 종료일 (empty 나중 추가)*/
                    totalDayInAMonth -> {
                        calendarData.add(
                            RangePickerEntity.Day(
                                day.toString(),
                                cal.toPrettyDateString(labelForFullDate),
                                cal.time,
                                state = dateState,
                                weekDay = getWeekByDay(dayOfWeek),
                                dayOfEdge = DayOfEdge.LAST
                            )
                        )
                        calendarData.addAll(createEndEmptyView(dayOfWeek))
                    }
                    /* 그 외*/
                    else -> {
                        calendarData.add(
                            RangePickerEntity.Day(
                                day.toString(),
                                cal.toPrettyDateString(labelForFullDate),
                                cal.time,
                                state = dateState,
                                weekDay = getWeekByDay(dayOfWeek),
                                dayOfEdge = DayOfEdge.BETWEEN
                            )
                        )
                    }
                }
                cal.add(DATE, 1)
            }
        }

        return calendarData
    }

    /**
     * 시작 Empty View (요일따라 앞에 Empty View 추가)
     */
    private fun createStartEmptyView(dayOfWeek: Int): List<RangePickerEntity.Empty> {
        val numberOfEmptyView = when (dayOfWeek) {
            MONDAY -> 1
            TUESDAY -> 2
            WEDNESDAY -> 3
            THURSDAY -> 4
            FRIDAY -> 5
            SATURDAY -> 6
            else -> 0
        }

        val listEmpty = mutableListOf<RangePickerEntity.Empty>()
        repeat((0 until numberOfEmptyView).count()) { listEmpty.add(RangePickerEntity.Empty) }
        return listEmpty
    }

    /**
     * 종료 Empty View (요일따라 뒤에 Empty View 추가)
     */
    private fun createEndEmptyView(dayOfWeek: Int): List<RangePickerEntity.Empty> {
        val numberOfEmptyView = when (dayOfWeek) {
            SUNDAY -> 6
            MONDAY -> 5
            TUESDAY -> 4
            WEDNESDAY -> 3
            THURSDAY -> 2
            FRIDAY -> 1
            else -> 6
        }

        val listEmpty = mutableListOf<RangePickerEntity.Empty>()
        repeat((0 until numberOfEmptyView).count()) { listEmpty.add(RangePickerEntity.Empty) }
        return listEmpty
    }

    /**
     * 일자 선택
     */
    private fun onDaySelected(item: RangePickerEntity.Day, position: Int) {
        if (item == startDateSelection?.day) return

        when {
            /* '시작일'이 없을경우 : '시작일' 생성 및 '시작일'과 '종료일'을 같은 일자로 표시*/
            startDateSelection == null -> {
                assignAsStartDate(item, position)
                defaultDateFunc(item)
            }
            /* '종료일'이 없을경우 : '시작일'은 이미 존재하므로, 범위지정 시작*/
            endDateSelection == null -> {
                startDateSelection?.let { selection ->
                    if (selection.position > position) {
                        /* 역방향일경우 ['종료일' 먼저찍고 '시작일' 찍을경우 ( << )]*/
                        calendarData[selection.position] =
                            selection.day.copy(selection = SelectionType.NONE)
                        assignAsStartDate(item, position)
                    } else {
                        /* 순방향일경우 ['시작일' 먼저찍고 '종료일' 찍을경우 ( >> )]*/
                        assignAsStartDate(
                            selection.day,
                            selection.position,
                            true
                        )
                        assignAsEndDate(item, position)
                        highlightDateBetween(selection.position, position)
                    }
                }
            }
            /* '시작일', '종료일' 둘다 있을경우 : 범위 재지정*/
            else -> {
                resetSelection()
                assignAsStartDate(item, position)
            }
        }

        rangePickerAdapter.setData(calendarData)
    }

    /**
     * 선택 초기화
     */
    private fun resetSelection() {
        val startDatePosition = startDateSelection?.position
        val endDatePosition = endDateSelection?.position

        if (startDatePosition != null && endDatePosition != null) {
            (startDatePosition..endDatePosition).forEach {
                val entity = calendarData[it]
                if (entity is RangePickerEntity.Day)
                    calendarData[it] = entity.copy(selection = SelectionType.NONE)
            }
        }
        endDateSelection = null
    }


    /**
     * 기간 음영표시
     */
    private fun highlightDateBetween(
        startIndex: Int,
        endIndex: Int
    ) {
        ((startIndex + 1) until endIndex).forEach {
            val entity = calendarData[it]
            if (entity is RangePickerEntity.Day) {
                calendarData[it] = entity.copy(selection = SelectionType.BETWEEN)
            }
        }
    }

    /**
     * 시작일자 할당
     */
    private fun assignAsStartDate(
        item: RangePickerEntity.Day,
        position: Int,
        isRange: Boolean = false
    ) {
        val newItem = item.copy(selection = SelectionType.START, isRange = isRange)
        calendarData[position] = newItem
        startDateSelection = SelectedDate(newItem, position)
        if (!isRange) onStartSelectedListener.invoke(item.date, item.prettyLabel)
    }

    /**
     * 종료일자 할당
     */
    private fun assignAsEndDate(
        item: RangePickerEntity.Day,
        position: Int
    ) {
        val newItem = item.copy(selection = SelectionType.END)
        calendarData[position] = newItem
        endDateSelection = SelectedDate(newItem, position)

        startDateSelection?.let { selection ->
            onRangeSelectedListener.invoke(
                selection.day.date,
                item.date,
                selection.day.prettyLabel,
                item.prettyLabel
            )
        }
    }

    /**
     * 요일계산
     * @desc Round 음영처리하기 위함
     */
    private fun getWeekByDay(weekDay: Int): WeekDays {
        return when (weekDay) {
            1 -> WeekDays.SUN
            2 -> WeekDays.MON
            3 -> WeekDays.TUE
            4 -> WeekDays.WED
            5 -> WeekDays.THU
            6 -> WeekDays.FRI
            7 -> WeekDays.SAT
            else -> WeekDays.ETC
        }
    }

    internal data class SelectedDate(val day: RangePickerEntity.Day, val position: Int)


    companion object {
        /* 시작연도 : 현재연도와의 차이*/
        const val START_YEAR_FROM_NOW = -10

        /* 종료연도 : 현재연도와의 차이*/
        const val END_YEAR_FROM_NOW = 10

    }
}
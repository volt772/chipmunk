package com.apx6.chipmunk.app.ui.picker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.DateExt
import com.apx6.domain.constants.CmdConstants
import kotlinx.android.synthetic.main.activity_range_picker.*
import org.joda.time.DateTime
import java.util.*
import java.util.Calendar.*

/**
 * RangePickerActivity
 * @desc 범위 선택 캘린더
 */
class RangePickerActivity : Activity() {
    private var queryStartDate: DateTime?= null
    private var queryEndDate: DateTime?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_range_picker)

        /* 상태바 색상*/
        setSystemBarColor(this, R.color.material_indigo_A700)

        /* Default : 현재 일자가 시작일로 선택*/
        val dayOfYear = DateExt.getDateOfToday()

        val defaultCalDate = getInstance()

        /* 현재일자 설정*/
        defaultCalDate.set(dayOfYear.year, dayOfYear.month, dayOfYear.day)
        defaultCalDate.add(MONTH, -1)

        /* 시간 초기화*/
        defaultCalDate.set(HOUR_OF_DAY, 0)
        defaultCalDate.set(MINUTE, 0)
        defaultCalDate.set(SECOND, 0)
        defaultCalDate.set(MILLISECOND, 0)

        /* Picker 초기설정*/
        rp_search_date.apply {
            setSelectionDefaultDate(::setDefaultDate)
            setSelectionDate(defaultCalDate.time, defaultCalDate.time)
        }

        /* Picker 범위 선택*/
        rp_search_date.setOnRangeSelectedListener { startDate, endDate, startLabel, endLabel ->
            atv_start_date.text = startLabel
            atv_end_date.text = endLabel
            setDateTimeForQuery(DaySelectType.RANGE, startDate, endDate)
        }

        /* Picker 단일 선택*/
        rp_search_date.setOnStartSelectedListener { startDate, label ->
            atv_start_date.text = label
            atv_end_date.text = "-"
            setDateTimeForQuery(DaySelectType.SINGLE, startDate)
        }

        /* '닫기'*/
        iv_picker_close.setOnClickListener {
            finish()
        }

        /* '기간선택'*/
        iv_picker_confirm.setOnClickListener {
            /**
             * '종료일' 없을경우, '시작일'로 대체 (당일검색으로 판단)
             * '시작일', '종료일'은 null 이 될 수 없음
             */
            val queryEndDate = checkEndDate()

            if (queryEndDate != null) {
                val rData = Intent().apply {
                    putExtra(CmdConstants.Intent.SELECTED_START_DAY, queryStartDate.toString())
                    putExtra(CmdConstants.Intent.SELECTED_END_DAY, queryEndDate.toString())
                }
                setResult(CmdConstants.Intent.Code.CODE_CALENDAR, rData)
                finish()
            }
        }
    }

    /**
     * 종료시간 재정의
     * @desc 종료시간 '23:59:59'
     */
    private fun checkEndDate(): DateTime? {
        if (queryEndDate == null) {
            queryEndDate = queryStartDate
        }

        queryEndDate?.let { endDate ->
            val year = endDate.year
            val month = endDate.monthOfYear
            val day = endDate.dayOfMonth

            val endCalendar = getInstance()
            endCalendar.set(year, month, day)
            endCalendar.add(MONTH, -1)

            endCalendar.set(HOUR_OF_DAY, 23)
            endCalendar.set(MINUTE, 59)
            endCalendar.set(SECOND, 59)
            endCalendar.set(MILLISECOND, 0)

            return DateTime(endCalendar.time)
        }

        return null
    }

    /**
     * 캘린더 생성 후, 기본 일자 선택 (오늘)
     */
    private fun setDefaultDate(defaultDate: RangePickerEntity.Day) {
        atv_start_date.text = defaultDate.prettyLabel
        atv_end_date.text = defaultDate.prettyLabel
        setDateTimeForQuery(DaySelectType.DEFAULT, defaultDate.date)
    }

    /**
     * 쿼리용 데이터 저장
     * @desc Date형식 > DateFormat형식
     */
    private fun setDateTimeForQuery(selectType: DaySelectType, startDate: Date, endDate: Date?= null) {
        when (selectType) {
            /* 기본 (초기 진입상태)*/
            DaySelectType.DEFAULT -> {
                queryStartDate = DateTime(startDate)
                queryEndDate = DateTime(startDate)
            }
            /* 단일 선택 (시작일만 설정)*/
            DaySelectType.SINGLE -> {
                queryStartDate = DateTime(startDate)
                queryEndDate = null
            }
            /* 범위 선택 (시작일 + 종료일 설정)*/
            DaySelectType.RANGE -> {
                queryStartDate = DateTime(startDate)
                queryEndDate = DateTime(endDate)
            }
        }
    }

    fun setSystemBarColor(act: Activity, @ColorRes color: Int) {
        val window = act.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(act, color)
    }

    enum class DaySelectType { DEFAULT, SINGLE, RANGE }
}
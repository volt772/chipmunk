package com.apx6.chipmunk.app.ui.picker

import java.util.*

/**
 * '월' 표시
 */
internal fun Calendar.toPrettyMonthOnly(
    label: String,
    style: Int = Calendar.LONG,
    locale: Locale = Locale.getDefault()
): String {
    val month = getDisplayName(Calendar.MONTH, style, locale)
    val year = get(Calendar.YEAR).toString()
    return label
        .replace("{YEAR}", year)
        .replace("{MONTH}", month)
}

/**
 * '년월일' 표시
 */
internal fun Calendar.toPrettyDateString(label: String): String {
    val year = get(Calendar.YEAR).toString()
    val month = get(Calendar.MONTH) + 1
    val day = get(Calendar.DAY_OF_MONTH)

    return label
        .replace("{YEAR}", year.substring(2, 4))
        .replace("{MONTH}", String.format("%02d", month))
        .replace("{DAY}", String.format("%02d", day))
}

/**
 * 월 Diffs
 */
internal fun Calendar.totalMonthDifference(startCalendar: Calendar): Int {
    val yearDiff = get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)
    val monthDiff = get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH)

    return monthDiff + (yearDiff * 12)
}

/**
 * 현재일 기준, 과거일인지 판단
 * @desc Millisecond 로 판단
 */
internal fun Long.isAfterDate(todayMillis: Long): Boolean {
    return this > todayMillis
}
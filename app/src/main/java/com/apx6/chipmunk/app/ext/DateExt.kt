package com.apx6.chipmunk.app.ext

import android.content.Context
import com.apx6.domain.constants.CmdConstants
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.LocalDate
import org.joda.time.Period
import java.text.SimpleDateFormat
import java.util.*


val currMillis: Long
    get() {
        return System.currentTimeMillis()
    }

/**
 * 잘못된 Joda DateTime 을 위한 기본값.
 */
val INVALID_JODA_DATE_TIME = DateTime(Long.MIN_VALUE)

val BASE_DATE_FORMATE = "yy.MM.dd"

/**
 * Joda DateTime 이 올바른 값인지 검사.
 */
fun DateTime?.isValid() = this?.let { it.millis != Long.MIN_VALUE } ?: false

/**
 * 시간 Formatted
 * @param type
 */
fun DateTime?.convertDateByType(type: Int): String {
    this ?: return ""

    return when (type) {
        1 -> this.toString("YY.MM.dd HH:mm")
        2 -> this.toString("HH:mm")
        3 -> this.toString("YY-MM-dd HH:mm")
        4 -> this.toString("YYYY-MM-dd")
        5 -> this.toString("YY-MM-dd")
        6 -> this.toString("MM-dd (EEE)")
        7 -> this.toString("YYYY-MM")
        8 -> this.toString("YYYY-MM-dd (EEE)")
        9 -> this.toString("YYYY-MM-dd HH:mm")
        10 -> this.toString("YYYY-MM-dd")
        11 -> this.toString("MM-dd")
        else -> this.toString("YY.MM.dd HH:mm")
    }
}

/**
 * Date Convert
 * @desc String to Millis
 */
fun String?.formedDateToMillis(format : String = BASE_DATE_FORMATE): Long {
    val millis = this?.let { formed ->
        val sdf = SimpleDateFormat("yy.MM.dd")
        val date: Date = sdf.parse(formed) as Date
        date.time
    } ?: 0L

    return millis
}

/**
 * Date Convert
 * @desc Millis to String
 */
fun Long?.millisToFormedDate(format : String = BASE_DATE_FORMATE): String {
    val formed = this?.let { millis ->
        val formatter = SimpleDateFormat(format)
        formatter.format(Date(millis))
    } ?: ""

    return formed
}

/**
 * Today Millis
 */
fun getTodayMillis(): Long {
    val d = Date().time
    val offset: Int = TimeZone.getDefault().getOffset(d)
    return (d + offset) / 86400000L * 86400000L - offset
}

/**
 * D-Day
 * @desc "yy.MM.dd".getDfFromToday()
 * @desc "millis".getDfFromToday()
 */
fun Any.getDfFromToday(): Int {
    val today = getTodayMillis()

    val period = when (this) {
        is String -> Period(today, this.formedDateToMillis())
        is Long -> Period(today, this)
        else -> Period()
    }

    return period.weeks * 7 + period.days
}

/**
 * D-Day String Maker
 * @desc "yy.MM.dd (D-NN)"
 */
fun convertDateLabel(_date: Long, onlyDay: Boolean = false): String {
    val dateLabel = _date.millisToFormedDate()
    val dfDays = _date.getDfFromToday()
    val dDayLabel = if (dfDays != 0) {
        if (dfDays < 0) {
            "D+%d".format(dfDays * -1)
        } else {
            "D-%d".format(dfDays)
        }
    } else {
        "D Day"
    }

    val label = if (onlyDay) {
        "(%s)".format(dDayLabel)
    } else {
        "%s (%s)".format(dateLabel, dDayLabel)
    }

    return label
}


fun getTodaySeparate(type: String): Int {
    val localDate = LocalDate()
    val today = localDate.toString()
    val todayArr = today.splitExt("-")

    when (type) {
        CmdConstants.Date.YEAR -> return Integer.parseInt(todayArr[0])
        CmdConstants.Date.MONTH -> return Integer.parseInt(todayArr[1])
        CmdConstants.Date.DAY -> return Integer.parseInt(todayArr[2])
    }

    return 0
}

fun String.getDateToAbbr(divider: String): String {
    if (this.isBlank()) return ""

    val year = this.substring(2, 4)
    val month = this.substring(4, 6)
    val day = this.substring(6, 8)

    return String.format(Locale.getDefault(), "%s%s%s%s%s", year, divider, month, divider, day)
}


/**
 * 시간 Formatted
 * 예) 11:00 AM
 */
fun DateTime?.toTimeFormat(): String {
    this ?: return ""

    val hour = when (hourOfDay) {
        0 -> 12.toString()
        in 1..12 -> String.format("%02d", hourOfDay)
        else -> String.format("%02d", hourOfDay - 12)
    }
    val minute = String.format("%02d", minuteOfHour)

    val meridiem = if (hourOfDay >= 12) "PM" else "AM"

    return String.format("%s:%s %s", hour, minute, meridiem)
}

/**
 * 오전 12:00, 오후 04:00 등의 String 값을 반환
 * @param [time] hour * 60 + minute 값을 가진다. (예: 6시 50분 -> 410)
 */
fun convertTimeToText(time: Int): String {
    val hour = time / DateTimeConstants.MINUTES_PER_HOUR
    val minute = time % DateTimeConstants.MINUTES_PER_HOUR

    return DateTime()
            .withHourOfDay(if (hour == DateTimeConstants.HOURS_PER_DAY) 0 else hour)
            .withMinuteOfHour(minute)
            .toTimeFormat()
}

/**
 * 시간 Formatted (일자 비교에 따른 분기)
 * - 24시간 단위로 시간 표기, 당일 받은 메일까지는 시간으로 표기
 * - 하루 전 메일은 [어제] 로 표기
 * - 일주일 이내의 메일은 [요일]로 표기
 * - 현재 년도는 년도 빼고 MM월 DD일로 표기
 * - 현재 년도 이전의 메일은 YYYY-MM-DD으로 표기
 */
fun DateTime?.compareDateFormedDate(context: Context): String {
    return this?.let { date ->
        when {
            /* 오늘의 경우*/
            date.isToday -> {
                date.convertDateByType(2)
            }
            /* 하루 전*/
            date.isYesterDay -> {
//                context.getString(R.string.date_yesterday)
                ""
            }
            /* 일주일 이내*/
            date.isWithinWeek -> {
                // this.dayOfWeek().get() -> 월: 1, 화: 2, 수: 3, 목: 4, 금: 5, 토: 6, 일: 7
                getDayOfWeekEngAbbreviation(context, if (this.dayOfWeek == 7) 1 else this.dayOfWeek + 1)
            }
            /* 올해 */
            isWithinYear -> {
                date.convertDateByType(11)
            }
            /* 이외 날짜의 경우*/
            else -> {
                date.convertDateByType(10)
            }
        }
    } ?: ""
}

/* 오늘인지 */
val DateTime.isToday: Boolean
    get() {
        val today = DateTime.now().withMillisOfDay(0)
        val target = this.withMillisOfDay(0)

        return today.millis == target.millis
    }

/* 어제인지 */
val DateTime.isYesterDay: Boolean
    get() {
        val yesterday = DateTime.now().minusDays(1).withMillisOfDay(0)
        val target = this.withMillisOfDay(0)

        return yesterday.millis == target.millis
    }

/**
 * 일주일 안에 있는지
 */
val DateTime.isWithinWeek: Boolean
    get() {
        val weekAgo = DateTime.now().minusDays(7).withMillisOfDay(0)
        val target = this.withMillisOfDay(0)

        return weekAgo.millis < target.millis
    }

/**
 * 올해 안에 있는지
 */
val DateTime.isWithinYear: Boolean
    get() {
        val year = DateTime.now().year
        val target = this.year

        return year == target
    }

/**
 * 분 > 시간치환
 */
fun Int.convertMinToHour(context: Context): String {
    return if (this >= 60) {
//        "${this/60}${context.resources.getString(R.string.locksetting_hour)}"
        ""
    } else {
//        "$this${context.resources.getString(R.string.locksetting_mins)}"
        ""
    }
}

/**
 * 일자 다국어 변환
 * @param context Context
 * @param weekDay Week Int Value [ 1=일, 2=월, 3=화, 4=수, 5=목, 6=금, 7=토 ]
 * @desc YYYY MM DD
 */
fun String?.dateFormatByLanguage(context: Context, weekDay: Int = 0): String {
    if (this.isNullOrBlank()) { return "" }

    if (this.length != 8) { return this }

    val dateResource = if (weekDay != 0) {
//        context.resources.getString(R.string.contacts_regist_birth_format_with_weekday)
        ""
    } else {
//        context.resources.getString(R.string.contacts_regist_birth_format)
        ""
    }

    val year = this.substring(0, 4)
    val month = this.substring(4, 6)
    val day = this.substring(6, 8)

    return if (weekDay != 0) {
        dateResource
            .replace("{WEEK}", getDayOfWeekAbbreviation(context, weekDay))
            .replace("{YEAR}", year)
            .replace("{MONTH}", month)
            .replace("{DAY}", day)
    } else {
        dateResource
            .replace("{YEAR}", year)
            .replace("{MONTH}", month)
            .replace("{DAY}", day)
    }
}

/**
 * 요일
 * @param dayValue [ 1=일, 2=월, 3=화, 4=수, 5=목, 6=금, 7=토 ]
 * 요일 줄임말 ex) 일요일 -> 일, Sun
 */
fun getDayOfWeekAbbreviation(context: Context, dayValue: Int): String {
    return context.getString("week_short_${dayValue}")
}

/**
 * 요일
 * @param dayValue [ 1=일, 2=월, 3=화, 4=수, 5=목, 6=금, 7=토 ]
 * 요일 줄임말 ex) 일요일 -> 일요일, Sun
 */
fun getDayOfWeekEngAbbreviation(context: Context, dayValue: Int): String {
    return context.getString("week_eng_short_${dayValue}")
}

/**
 * 요일
 * @param dayValue [ 1=일, 2=월, 3=화, 4=수, 5=목, 6=금, 7=토 ]
 * 요일 ex) 일요일 -> 일요일, Sunday
 */
fun getDayOfWeek(context: Context, dayValue: Int): String {
    return context.getString("week_long_${dayValue}")
}

/**
 * 월 다국어 처리
 */
fun getMonth(context: Context, monthOfYear: Int): String {
    return context.getString("date_month_${monthOfYear}")
}

/**
 * '몇 번째' 다국어 처리 ( 주(Week) 처리 )
 */
fun getWeekOfMonth(context: Context, weekOfMonth: Int): String {
    return context.getString("sequence_$weekOfMonth")
}

/**
 * 해당 달의 몇 번째 주인지 반환한다.
 * 1일~7일 -> 첫 번째 주, 8일~14일 -> 두 번째 주
 */
fun getWeekOfMonthIndex(dateTime: DateTime): Int = ((dateTime.dayOfMonth - 1) / DateTimeConstants.DAYS_PER_WEEK) + 1

/**
 * 같은 날인지 검사
 */
internal fun Date.isTheSameDay(comparedDate: Date): Boolean {
    val calendar = Calendar.getInstance()
    calendar.withTime(this)
    val comparedCalendarDate = Calendar.getInstance()
    comparedCalendarDate.withTime(comparedDate)
    return calendar.get(Calendar.YEAR) == comparedCalendarDate.get(Calendar.YEAR) &&
            calendar.get(Calendar.DAY_OF_YEAR) == comparedCalendarDate.get(Calendar.DAY_OF_YEAR)
}

/**
 * 날자 기준으로만 검사 : 시간은 모두 '0'
 */
internal fun Calendar.withTime(date: Date) {
    clear()
    time = date
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}

class DateExt {
    companion object {
        /**
         * 현재 일자 (분리형)
         * @return DateOfYear
         */
        fun getDateOfToday(): DateOfToday {
            val date = DateTime()
            val converted = date.convertDateByType(4)

            if (converted.equalsExt("")) {
                return DateOfToday(0, 0, 0)
            }

            val formatted = converted.splitExt("-")

            return if (formatted.isEmpty()) {
                return DateOfToday(0, 0, 0)
            } else {
                DateOfToday(
                    year = formatted[0].toInt(),
                    month = formatted[1].toInt(),
                    day = formatted[2].toInt()
                )
            }
        }
    }

    data class DateOfToday(val year: Int, val month: Int, val day: Int)
}

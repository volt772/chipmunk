package com.apx6.chipmunk.app.constants

import com.apx6.chipmunk.R

enum class CmdCategoryDiffLabel (
    val color: Int
) {

    PAST_IN_7(R.color.label_in_7),
    PAST_IN_14(R.color.label_in_14),
    PAST_IN_21(R.color.label_in_21),
    PAST_IN_28(R.color.label_in_28),
    PAST_IN_90(R.color.label_in_90),
    PAST_IN_240(R.color.label_in_240),
    PAST_IN_365(R.color.label_in_365),
    PAST_OVER_365(R.color.label_over_365),
    ON_DAY(R.color.label_today),
    FUTURE(R.color.label_future),
    OTHER(0);

    companion object {
        fun getColorByDiffDays(df: Int): CmdCategoryDiffLabel {
            return when(df) {
                /* 오늘*/
                0 -> ON_DAY
                /* %d일전*/
                in 1..Int.MAX_VALUE -> FUTURE
                /* 0~7일후*/
                in -7 .. 0 -> PAST_IN_7
                /* 7~14일후*/
                in -14 until -7 -> PAST_IN_14
                /* 14~21일후*/
                in -21 until -14 -> PAST_IN_21
                /* 21~28일후*/
                in -28 until -21 -> PAST_IN_28
                /* 28~90일후*/
                in -90 until -28 -> PAST_IN_90
                /* 90~240일후*/
                in -240 until -90 -> PAST_IN_240
                /* 240~365일후*/
                in -365 until -240 -> PAST_IN_365
                /* 365일후*/
                in -100000 .. -365 -> PAST_OVER_365
                /* 그외*/
                else -> OTHER
            }
        }
    }
}
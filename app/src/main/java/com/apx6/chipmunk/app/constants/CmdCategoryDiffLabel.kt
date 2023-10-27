package com.apx6.chipmunk.app.constants

import com.apx6.chipmunk.R

enum class CmdCategoryDiffLabel (
    val color: Int
) {

    PAST(R.color.label_past),
    ON_DAY(R.color.label_today),
    FUTURE(R.color.label_future),
    OTHER(0);

    companion object {
        fun getColorByDiffDays(df: Int): CmdCategoryDiffLabel {
            return if (df == 0) {
                /* ON_DAY*/
                ON_DAY
            } else if (df >= 1) {
                /* FUTURE*/
                FUTURE
            } else if (df < 0) {
                /* PAST*/
                PAST
            } else {
                /* OTHER*/
                OTHER
            }
        }
    }
}
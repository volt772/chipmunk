package com.apx6.chipmunk.app.constants

import com.apx6.chipmunk.R

enum class CmdCategoryDiffLabel (
    val color: Int
) {

    READY(R.color.label_ready),
    ON_DAY(R.color.label_today),
    PAST(R.color.label_past),
    OTHER(0);

    companion object {
        fun getColorByDiffDays(df: Int): CmdCategoryDiffLabel {
            return if (df == 0) {
                /* ON_DAY*/
                ON_DAY
            }
//            else if (df == 1) {
//                /* IN_TOMORROW*/
//                IN_TOMORROW
//            }
            else if (df > 1) {
                /* PAST*/
                PAST
            } else {
                /* READY*/
                READY
            }
        }
    }
}
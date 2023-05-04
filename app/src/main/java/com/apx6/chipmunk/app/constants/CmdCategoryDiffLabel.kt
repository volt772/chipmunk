package com.apx6.chipmunk.app.constants

import com.apx6.chipmunk.R

enum class CmdCategoryDiffLabel (
    val color: Int
) {

    FUTURE(R.color.material_green_700),
    ON_DAY(R.color.material_amber_700),
    PAST(R.color.material_gray_600),
    OTHER(0);

    companion object {
        fun getColorByDiffDays(df: Int): CmdCategoryDiffLabel {
            return if (df == 0) {
                /* ON_DAY*/
                ON_DAY
            } else if (df > 0) {
                /* FUTURE*/
                FUTURE
            } else {
                /* PAST*/
                PAST
            }
        }
    }
}
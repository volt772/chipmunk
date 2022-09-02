package com.apx6.domain.constants

object CmdConstants {

    object DB {
        const val DB_NAME = "chipmunk.db"
    }

    object Intent {
        const val USER_ID = "userId"
        const val QUERY = "query"
        const val LOCATION_NAME = "locationName"
        const val SELECTED_START_DAY = "selectedStartDay"
        const val SELECTED_END_DAY = "selectedEndDay"

        object Code {
            const val CODE_LOCATION = 10000
            const val CODE_CALENDAR = 20000
        }
    }

}
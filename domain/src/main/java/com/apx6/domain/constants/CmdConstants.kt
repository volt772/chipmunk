package com.apx6.domain.constants

object CmdConstants {

    object App {
        const val NAME = "Chipmunk"
        const val AGENT_APP_CHIPMUNK = "APP_CHIPMUNK"
        const val OS = "Android"
        const val DEFAULT_USER_AGENT = "ANDROID/NA"
    }

    object FCM {
        const val CHANNEL_ID = "channel_chipmunk"
        const val GROUP_ID = "chipmunk_3646"
        const val CHIPMUNK = "chipmunk"
    }

    object RemoteConfig {
        const val MAIN_KEY = "up_info"
        const val VERSION = "version"
        const val DESCRIPTIONS = "descriptions"
    }

    object DB {
        const val DB_NAME = "chipmunk.db"

        /* PAGING(LOCAL) PAGE SIZE*/
        const val LOCAL_PAGE_SIZE = 40
    }

    object Intent {
        const val USER_ID = "userId"
        const val QUERY = "query"
        const val LOCATION_NAME = "locationName"
        const val SELECTED_START_DAY = "selectedStartDay"
        const val SELECTED_END_DAY = "selectedEndDay"
        const val CHECKLIST_ID = "checkListId"
        const val REGISTER_MODE = "registerMode"

        object Code {
            const val CODE_LOCATION = 10000
            const val CODE_CALENDAR = 20000
        }
    }

    object Date {
        const val YEAR = "year"
        const val MONTH = "month"
        const val DAY = "day"
    }

}
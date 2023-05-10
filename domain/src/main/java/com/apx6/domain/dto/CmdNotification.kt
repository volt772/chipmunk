package com.apx6.domain.dto

data class CmdNotification (

    val onComingCount: Int,     // 종료될 건수

    val endDay: Int,            // 종료 D-Day

/* 7일이내 : 곧 종료되는 체크리스트가 2개 있습니다. 앱에서 확인해주세요.*/
/* 하루전 : 내일 끝나는 체크리스트가 2개 있습니다. 앱에서 확인해주세요.*/

) {

    companion object {
        fun default(): CmdNotification {
            return CmdNotification(
                onComingCount = 2,
                endDay = 1
            )
        }
    }
}

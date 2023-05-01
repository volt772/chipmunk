package com.apx6.domain.dto

data class CmdCategory (

    val id: Int = 0,

    val name: String,

    val uid: Int

) {
    companion object {
        fun default(): CmdCategory {
            return CmdCategory(
                id = -1,
                name = "default",
                uid = 0,
            )
        }
    }
}
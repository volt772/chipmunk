package com.apx6.domain.constants

import com.google.gson.annotations.SerializedName

enum class CmdSettingType(
    val type: String
) {

    @SerializedName("user")
    USER("user"),

    @SerializedName("notification")
    NOTIFICATION("notification"),

    NONE("");

    companion object {
        fun type(type: String): CmdSettingType {
            return when (type) {
                USER.type -> USER
                NOTIFICATION.type -> NOTIFICATION
                else -> NONE
            }
        }
    }
}

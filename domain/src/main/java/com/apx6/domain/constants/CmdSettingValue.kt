package com.apx6.domain.constants


enum class CmdSettingValue(
    val value: String
) {

    ON("on"),

    OFF("off"),

    NONE("");

    companion object {
        fun valueToBool(value: String): Boolean {
            return when (value) {
                ON.value -> true
                OFF.value -> false
                else -> false
            }
        }

        fun boolToValue(bool: Boolean): CmdSettingValue {
            return when (bool) {
                true -> ON
                false -> OFF
                else -> OFF
            }
        }
    }

}

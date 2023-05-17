package com.apx6.domain.constants

import com.google.gson.annotations.SerializedName


enum class CmdSettingValue(
    val value: String
) {

    ON("on"),

    OFF("off"),

    NONE("");

}

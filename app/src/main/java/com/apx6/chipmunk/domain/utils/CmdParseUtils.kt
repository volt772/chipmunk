package com.apx6.chipmunk.domain.utils

import com.apx6.chipmunk.domain.constants.CmdRemoteConfigParam
import com.google.firebase.remoteconfig.FirebaseRemoteConfig


interface CmdParseUtils {

    fun <T> toModel(
        jsonString: String,
        klass: Class<T>
    ): T

    fun <T> toListModel(
        jsonString: String,
        klass: Class<T>
    ): List<T>

    fun toJsonString(
        obj: Any
    ): String

    fun <T> read(
        frc: FirebaseRemoteConfig,
        param: CmdRemoteConfigParam,
        returnType: Class<T>
    ): T?

}
package com.apx6.domain.utils


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

//    fun <T> read(
//        frc: FirebaseRemoteConfig,
//        param: CmdRemoteConfigParam,
//        returnType: Class<T>
//    ): T?

}
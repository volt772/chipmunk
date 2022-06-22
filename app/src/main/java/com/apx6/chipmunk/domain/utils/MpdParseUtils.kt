package com.apx6.chipmunk.domain.utils


interface MpdParseUtils {

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
//        param: MpdRemoteConfigParam,
//        returnType: Class<T>
//    ): T?
//
//    fun toFcmIds(
//        idsString: String?
//    ): MpdFcmIds

}
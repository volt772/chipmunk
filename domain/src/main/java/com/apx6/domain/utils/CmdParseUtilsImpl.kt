package com.apx6.domain.utils

import com.apx6.domain.constants.CmdRemoteConfigParam
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class CmdParseUtilsImpl @Inject constructor() : CmdParseUtils {

    override fun <T> toModel(
        jsonString: String,
        klass: Class<T>
    ): T {
        val model = Gson().fromJson<T>(jsonString, TypeToken.getParameterized(klass).type)
        return model as T
    }

    override fun <T> toListModel(
        jsonString: String,
        klass: Class<T>
    ): List<T> {
        val parser = JsonParser().parse(jsonString).asJsonArray
        val arrayModel = mutableListOf<T>()

        parser.forEach { element ->
            arrayModel.add(Gson().fromJson(element, TypeToken.getParameterized(klass).type))
        }

        return arrayModel
    }

    override fun toJsonString(
        obj: Any
    ): String = Gson().toJson(obj)

    override fun <T> read(
        frc: FirebaseRemoteConfig,
        param: CmdRemoteConfigParam,
        returnType: Class<T>
    ): T? {

        val value: Any? = when (returnType) {
            String::class.java -> frc.getString(param.key)
            Boolean::class.java -> frc.getBoolean(param.key)
            Long::class.java -> frc.getLong(param.key)
            Int::class.java -> frc.getLong(param.key).toInt()
            Double::class.java -> frc.getDouble(param.key)
            Float::class.java -> frc.getDouble(param.key).toFloat()
            else -> {
                val json = frc.getString(param.key)
                json.takeIf { it.isNotBlank() }?.let { toModel(json, returnType) }
            }
        }

        @Suppress("UNCHECKED_CAST")
        return (value as? T)
    }
}

package com.apx6.domain.utils

import com.apx6.domain.constants.CmdConstants
import com.apx6.domain.constants.CmdRemoteConfigParam
import com.apx6.domain.dto.CmdAppUpdateValue
import com.apx6.domain.dto.CmdRemoteConfigValue
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import org.json.JSONObject
import javax.inject.Inject

class CmdRemoteConfigMgrImpl @Inject constructor(
    applicationInfo: CmdApplicationInfo
): CmdRemoteConfigMgr {

    @Inject lateinit var parseUtils: CmdParseUtils

    /**
     * 현재 버전코드
     */
    private val currentVersion = applicationInfo.getAppVersionCode()

    private lateinit var frc: FirebaseRemoteConfig

    override fun getCurrentVersionName(): String {
        return getVersionNameByCode(currentVersion)
    }

    override fun syncVersionConfigValue(callback: CmdRemoteConfigCallback) {
        try {
            frc = FirebaseRemoteConfig.getInstance().apply {
                val configSettings = FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(DEBUG_INTERVAL)
                    .build()
                setConfigSettingsAsync(configSettings)
            }

            frc.fetchAndActivate().addOnCompleteListener { task ->
                val msg: String
                if (task.isSuccessful) {
                    val updated = task.result

                    msg = if (updated) {
                        "Update Needed : $updated, currentAppVersion : $currentVersion"
                    } else {
                        "Update NOT Needed : $updated, currentAppVersion : $currentVersion"
                    }

                    println("probe :: version :: fetch : $msg")

                    callback.success(updated)
                } else {
                    msg = "Update FetchAndActivate Failed"
                    callback.fail()
                }
            }
        } catch (e: Exception) {
            callback.fail()
        }

    }

    /**
     * RemoteConfig 버전 상세
     */
    override fun versionDetails(): CmdAppUpdateValue {
        val remoteValue = parseConfigs()

        val rVersion = remoteValue.getInt(CmdConstants.RemoteConfig.VERSION)
        val rDescription = remoteValue.getString(CmdConstants.RemoteConfig.DESCRIPTIONS)

        return CmdAppUpdateValue(
            currAppVersionCode = currentVersion,
            currAppVersionName = getVersionNameByCode(currentVersion),
            remoteAppVersionCode = rVersion,
            remoteAppVersionName = getVersionNameByCode(rVersion),
            remoteDescription = rDescription
        )
    }

    private fun parseConfigs(): JSONObject {
        val rc = FirebaseRemoteConfig.getInstance()
        return JSONObject(rc.getString(CmdConstants.RemoteConfig.MAIN_KEY))
    }

    /**
     * 버전코드 -> 버전이름
     */
    private fun getVersionNameByCode(code: Int): String {
        val versionNameFormat = "%09d"
        return String.format(versionNameFormat, code).run {
            try {
                convertToUiCode(this)
            } catch (e: Exception) {
                val versionCode = getLocalConfigVersion()
                convertToUiCode(String.format(versionNameFormat, versionCode))
            }
        }
    }

    /**
     * Code to Name
     */
    private fun convertToUiCode(version: String): String {
        try {
            val release = version.substring(PIN_POSITION_RELEASE, PIN_POSITION_RELEASE + 2).toInt()
            val major = version.substring(PIN_POSITION_MAJOR, PIN_POSITION_MAJOR + 2).toInt()
            val minor = version.substring(PIN_POSITION_MINOR, PIN_POSITION_MINOR + 2).toInt()
            val patch = version.substring(PIN_POSITION_PATCH).toInt()

            return StringBuffer().apply {
                append(release).append(".")
                append(major).append(".")
                append(minor)

                if (patch != 0) {
                    val alphabet = (patch + '`'.code).toChar()
                    append(alphabet)
                }
            }.toString()
        } catch (e: Exception) {
            throw Exception("Convert ERROR")
        }
    }

    /**
     * Local Config Version
     */
    private fun getLocalConfigVersion(): Int {
        val rv = read<CmdRemoteConfigValue>(CmdRemoteConfigParam.ANDROID) ?: ""
        val localConfigValue = rv as CmdRemoteConfigValue

        return localConfigValue.version
    }

    /**
     * RemoteConfig Parse
     */
    private inline fun <reified T> read(param: CmdRemoteConfigParam): T? =
        parseUtils.read(frc, param, T::class.java)

    companion object {
        const val DEBUG_INTERVAL = 86400L * 30

        const val PIN_POSITION_RELEASE = 1
        const val PIN_POSITION_MAJOR = 3
        const val PIN_POSITION_MINOR = 5
        const val PIN_POSITION_PATCH = 7
    }
}
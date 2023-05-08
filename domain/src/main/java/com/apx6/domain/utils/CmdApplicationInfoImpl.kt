package com.apx6.domain.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageInfo
import android.content.res.Resources
import android.os.Build
import com.apx6.domain.constants.CmdConstants
import java.util.*
import javax.inject.Inject

class CmdApplicationInfoImpl @Inject constructor(
    private val context: Context
) : CmdApplicationInfo {

    @SuppressLint("HardwareIds")
    override fun getAndroidID(
        context: Context
    ): String {
        val device: String = Build.DEVICE
        val model: String = Build.MODEL
        val androidId: String = android.provider.Settings.Secure.getString(
            context.contentResolver,
            android.provider.Settings.Secure.ANDROID_ID
        )

        return UUID(
            androidId.hashCode().toLong(),
            device.hashCode().toLong() shl 32 or model.hashCode().toLong()
        ).toString()
    }

    private fun loadPackageInfo(): PackageInfo? {
        return try {
            context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * AppAgent 생성
     * 형식: 제품명/버젼/제조사/모델명/기타정보1(값;...값) 기타정보1(값;...값)
     * Chipmunk/0.5.1a/LGE/LGM-G600S/Android(8.0.0;)
     */
    override fun getAppAgentPhrase(): String {
        loadPackageInfo().run {
            val appAgent = this?.let { _info ->
                StringBuffer().apply {
                    append(CmdConstants.App.NAME).append("/") // 제품명
                    append(_info.versionName).append("/") // 버젼
                    append(Build.MANUFACTURER).append("/") // 제조사
                    append(Build.MODEL).append("/") // 모델명
                    appendExtraAppAgentValue(this) // 기타정보
                }.also { _buffer ->
                    _buffer.toString()
                }
            } ?: APP_AGENT_DUMMY

            return appAgent.toString()
        }
    }

    /**
     * UserAgent 생성
     * @return cf) APP_TASK/ANDROID/NA
     */
    override fun getUserAgentPhrase(): String {
        loadPackageInfo().run {
            val userAgent = this?.let {
                StringBuffer().apply {
                    append(CmdConstants.App.AGENT_APP_CHIPMUNK).append("/")
                    append(CmdConstants.App.DEFAULT_USER_AGENT)
                }.also { _buffer ->
                    _buffer.toString()
                }
            } ?: ""

            return userAgent.toString()
        }
    }

    private fun appendExtraAppAgentValue(
        src: StringBuffer
    ) {
        src.apply {
            append(CmdConstants.App.OS).append("(")
            append(Build.VERSION.RELEASE).append(";")
            append(Build.VERSION.SDK_INT)
            append(")")
        }
    }

    /**
     * getAppVersionName
     */
    override fun getAppVersionName(): String = getPackageInfo().versionName

    /**
     * getAppVersionCode
     */
    override fun getAppVersionCode(): Int {
        return getPackageInfo().longVersionCode.toInt()
    }

    /**
     * getPackageInfo
     */
    private fun getPackageInfo() = context.packageManager.getPackageInfo(context.packageName, 0)

    companion object {
        const val TAG = "AppInfo"
        const val APP_AGENT_DUMMY = CmdConstants.App.NAME + "/" + "0.0.0/Unknown/Unknown/" + CmdConstants.App.OS
    }
}

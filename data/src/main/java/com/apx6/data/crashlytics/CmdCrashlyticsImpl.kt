package com.apx6.data.crashlytics

import com.apx6.domain.crashlytics.CmdCrashlytics
import com.apx6.domain.exception.MpdOperationException
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject


class CmdCrashlyticsImpl @Inject constructor() : CmdCrashlytics {

    /**
     * 로그 수집 활성화 설정
     */
    override fun collectionEnabled(enabled: Boolean) {
        try {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(enabled)
        } catch (ignorable: Exception) {}
    }

    override fun setEmail(emailAddress: String?) {
        try {
            val email = if (emailAddress.isNullOrBlank()) NONE_DOMAIN else emailAddress
            FirebaseCrashlytics.getInstance().setCustomKey(KEY_DOMAIN, email)
        } catch (ignorable: Exception) {}
    }

    /**
     * 에러보고 - 에러 표시용 추가 로그 정보
     * + Crashlytics는 앱의 속도가 느려지지 않도록 로그의 크기를 64KB로 제한하고, 세션의 로그 크기가 한도를 초과하면 이전 로그 항목을 삭제합니다.
     */
    override fun recordLog(str: String) {
        try {
            if (str.isNotBlank()) {
                val truncated = if (str.length > LOG_LIMIT) "(log truncated)\n" else ""
                FirebaseCrashlytics.getInstance().log("$truncated${str.take(LOG_LIMIT)}")
            }
        } catch (ignorable: Exception) {
        }
    }

    /**
     * "심각하지 않음" 에러 보고
     */
    override fun recordException(t: Throwable) {
        try {
            FirebaseCrashlytics.getInstance().recordException(t)
        } catch (ignorable: Exception) {}
    }

    /**
     * "심각하지 않음" 에러 보고
     * + [MpdOperationException] 전용
     */
    override fun recordException(t: MpdOperationException) {
        try {
            FirebaseCrashlytics.getInstance().apply {
                log(t.message?: "")
                recordException(t)
            }
        } catch (ignorable: Exception) {}
    }

    companion object {
        private const val KEY_DOMAIN = "domain"
        private const val NONE_DOMAIN = "none"
        private const val LOG_LIMIT = 1024 * 4
    }
}
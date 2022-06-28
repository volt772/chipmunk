package com.apx6.chipmunk.app.ui.test

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.apx6.chipmunk.BR
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivityTestBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class TestActivity : BaseActivity<ActivityTestBinding>() {

    @Inject lateinit var context: Context

    private val vm: TestViewModel by viewModels()

    override fun getLayoutId() = R.layout.activity_test
    override fun getBindingVariable() = BR._all

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(intent) {
        }

        initKakaoLogin()

        binding().apply {
            btnLogin.setOnClickListener { caseLogin() }
            btnUserInfo.setOnClickListener { caseUserInfo() }
            btnLogout.setOnClickListener { caseLogout() }
            btnDisconnect.setOnClickListener { caseDisconnect() }
        }
    }

    private fun initKakaoLogin() {
        val ak = getString(R.string.kakao_app_key)
        KakaoSdk.init(this, ak)
    }

    private val loginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            println("probe :: auth : callback : 카카오계정으로 로그인 실패 : $error")
        } else if (token != null) {
            println("probe :: auth : callback : 카카오계정으로 로그인 성공 : token : $token, accessToken : ${token.accessToken}")
        }
    }

    private fun caseLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    println("probe :: auth : instances : 카카오톡으로 로그인 실패 : $error")
                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = loginCallback)
                } else if (token != null) {
                    println("probe :: auth : instances : 카카오톡으로 로그인 성공 : ${token.accessToken}")
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = loginCallback)
        }

    }

    private fun caseUserInfo() {
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                println("probe :: auth : 사용자 정보 요청 실패 : $error")
            }
            else if (user != null) {
                println("probe :: auth : 사용자 : ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
//                    println("probe :: auth : 사용자 정보 요청 성공 : user : ${user.kakaoAccount?.email}, name : ${user.kakaoAccount?.profile?.nickname}")
//                    println("probe :: auth : 사용자 정보 요청 성공 : 회원번호: ${user.id}" +
//                        "\n이메일: ${user.kakaoAccount?.email}" +
//                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
//                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
            }
        }
    }

    private fun caseLogout() {
        // 로그아웃
        UserApiClient.instance.logout { error ->
            if (error != null) {
                println("probe :: auth :: logout 실패 : $error")
            }
            else {
                println("probe :: auth :: logout 성공")
            }
        }
    }

    private fun caseDisconnect() {
        // 연결 끊기
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                println("probe :: auth :: disconnect 실패 : $error")
            }
            else {
                println("probe :: auth :: disconnect 성공")
            }
        }
    }
}

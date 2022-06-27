package com.apx6.chipmunk.app.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.apx6.chipmunk.BR
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.databinding.ActivityCoreBinding
import com.google.android.material.snackbar.Snackbar
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashBoardActivity : BaseActivity<ActivityCoreBinding>() {

    private lateinit var binding: ActivityCoreBinding

    override fun getLayoutId() = R.layout.activity_core
    override fun getBindingVariable() = BR._all

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = binding()

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        binding.btnEmail.setOnClickListener {
            // 사용자 정보 요청 (기본)
            UserApiClient.instance.me { user, error ->
                if (error != null) {
                    println("probe :: auth : 사용자 정보 요청 실패 : $error")
                }
                else if (user != null) {
                    println("probe :: auth : 사용자 정보 요청 성공 : user : ${user.kakaoAccount?.email}, name : ${user.kakaoAccount?.profile?.nickname}")
//                    println("probe :: auth : 사용자 정보 요청 성공 : 회원번호: ${user.id}" +
//                        "\n이메일: ${user.kakaoAccount?.email}" +
//                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
//                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                }
            }
        }

        binding.btnLogout.setOnClickListener {
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

        binding.btnDisconn.setOnClickListener {
            // 연결 끊기
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    println("probe :: auth :: disconn 실패 : $error")
                }
                else {
                    println("probe :: auth :: disconn 성공")
                }
            }
        }

        binding.btnKakaoLogin.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        println("probe :: auth : instances : failed : $error")
//                        Log.e(TAG, "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        println("probe :: auth : instances : success : ${token.accessToken}")
//                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            println("probe :: auth : callback : failed : $error")
//            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            println("probe :: auth : callback : success : token : $token, accessToken : ${token.accessToken}")
//            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
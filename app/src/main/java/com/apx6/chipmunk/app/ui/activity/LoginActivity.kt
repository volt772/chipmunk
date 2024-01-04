package com.apx6.chipmunk.app.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.apx6.chipmunk.R
import com.apx6.chipmunk.app.ext.currMillis
import com.apx6.chipmunk.app.ext.openActivity
import com.apx6.chipmunk.app.ext.randomKey
import com.apx6.chipmunk.app.ext.setOnSingleClickListener
import com.apx6.chipmunk.app.ext.showToast
import com.apx6.chipmunk.app.ext.statusBar
import com.apx6.chipmunk.app.fcm.FcmHelper
import com.apx6.chipmunk.app.ui.base.BaseActivity
import com.apx6.chipmunk.app.ui.vms.LoginViewModel
import com.apx6.chipmunk.databinding.ActivityLoginBinding
import com.apx6.domain.State
import com.apx6.domain.dto.CmdUser
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>() {
    override val viewModel: LoginViewModel by viewModels()

    override fun getViewBinding(): ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)

    @Inject lateinit var fcmHelper: FcmHelper

    override fun preLoad() { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscribers()
    }

    override fun initView() {
        this.statusBar(R.color.white)

        binding.btnKakaoLogin.setOnSingleClickListener {
            UserApiClient.instance.apply {
                if (isKakaoTalkLoginAvailable(this@LoginActivity)) {
                    loginWithKakaoTalk(this@LoginActivity) { token, error ->

                        error?.let {
                            registerUser()
                        } ?: run {
                            /**
                             * @see
                             * 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우
                             * 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                             */
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            }

                            /**
                             * @see
                             * 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                             */
                            loginWithKakaoAccount(this@LoginActivity, callback = loginCallBack)
                        }
                    }
                } else {
                    loginWithKakaoAccount(this@LoginActivity, callback = loginCallBack)
                }
            }
        }
    }

    private fun subscribers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.user.collect { state ->
                    when (state) {
                        is State.Loading -> { }
                        is State.Success -> {
                            progress?.stop()
                            moveToDashBoard()
                        }
                        is State.Error -> {
                            progress?.stop()
                            failedLogin(getString(R.string.failed_login))
                        }
                    }
                }
            }
        }
    }

    private val loginCallBack: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            failedLogin(getString(R.string.failed_login))
        } else if (token != null) {
            registerUser()
        }
    }

    private fun getUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                failedLogin(getString(R.string.failed_get_user_info))
            } else if (user != null) {
                progress?.start()

                viewModel.registerUser(
                    CmdUser(
                        account = "${USER_ACCOUNT_PREFIX}_${randomKey()}",
                        nickName = user.kakaoAccount?.profile?.nickname ?: "",
                        email = user.kakaoAccount?.email,
                        regDate = currMillis,
                        profileThumbnail = user.kakaoAccount?.profile?.thumbnailImageUrl,
                        fToken = fcmHelper.fcmToken,
                    ),
                )
            }
        }
    }

    private fun moveToDashBoard() {
        openActivity(DashBoardActivity::class.java)
    }

    private fun registerUser() {
        getUserInfo()
    }

    private fun failedLogin(msg: String) {
        val vw = binding.clLoginRoot
        showToast(msg, false)
    }

    companion object {
        const val USER_ACCOUNT_PREFIX = "chipmunk"
    }
}

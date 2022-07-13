package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.fcm.FcmHelper
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val fcmHelper: FcmHelper
) : BaseViewModel() {

    private val _user: MutableSharedFlow<CmdUser?> = MutableSharedFlow()
    val user: SharedFlow<CmdUser?> = _user

    fun getUser() {
        viewModelScope.launch {
            userUseCase.getUser().collect { _user.emit(it) }
        }
    }

    fun setFcmToken() {
        fcmHelper.setFcmToken()
    }
}
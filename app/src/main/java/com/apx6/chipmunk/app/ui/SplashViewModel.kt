package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.fcm.FcmHelper
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.dto.CmdAppUpdateValue
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.UserRepository
import com.apx6.domain.utils.CmdRemoteConfigCallback
import com.apx6.domain.utils.CmdRemoteConfigMgr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val fcmHelper: FcmHelper,
    private val rcManager: CmdRemoteConfigMgr
) : BaseViewModel() {

    init {
    }

    private val _user: MutableSharedFlow<CmdUser?> = MutableSharedFlow()
    val user: SharedFlow<CmdUser?> = _user

    fun getUser() {
        viewModelScope.launch {
            userRepository.getUser().collectLatest { _user.emit(it) }
        }
    }

    fun setFcmToken() {
        fcmHelper.setFcmToken()
    }

    fun syncVersion(callback: CmdRemoteConfigCallback) {
        rcManager.syncVersionConfigValue(callback)
    }

    fun getUpdateDetails(): CmdAppUpdateValue {
        return rcManager.versionDetails()
    }
}
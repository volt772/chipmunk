package com.apx6.chipmunk.app.ui.vms

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ext.currMillis
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.constants.CmdSettingType
import com.apx6.domain.constants.CmdSettingValue
import com.apx6.domain.dto.CmdSetting
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.CheckListRepository
import com.apx6.domain.repository.SettingRepository
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository,
    private val checkListRepository: CheckListRepository,
    private val settingRepository: SettingRepository
) : BaseViewModel() {

    init {
        getUser()
    }

    private val _checkListCount: MutableStateFlow<State<Int>> = MutableStateFlow(State.loading())
    val checkListCount: StateFlow<State<Int>> = _checkListCount

    private val _user: MutableSharedFlow<CmdUser?> = MutableSharedFlow()
    val user: SharedFlow<CmdUser?> = _user

    private val _userDeleted: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val userDeleted: SharedFlow<Boolean> = _userDeleted

    private val _setting: MutableSharedFlow<CmdSetting?> = MutableSharedFlow()
    val setting: SharedFlow<CmdSetting?> = _setting

    private val _notiPosted: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val notiPosted: SharedFlow<Boolean> = _notiPosted

    private fun getUser() {
        viewModelScope.launch {
            userRepository.getUser().collectLatest { _user.emit(it) }
        }
    }

    fun getCheckListCount(uid: Int) {
        viewModelScope.launch {
            checkListRepository.getCheckListCount(uid)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _checkListCount.value = state }
        }
    }

    fun deleteUser(user: CmdUser) {
        viewModelScope.launch(ioDispatcher) {
            val deleted = userRepository.delUser(user)
            _userDeleted.emit(deleted)
        }
    }

    fun fetchNotificationSetting(uid: Int) {
        viewModelScope.launch(ioDispatcher) {
            settingRepository.fetchSetting(uid, CmdSettingType.NOTIFICATION)
                .collectLatest {
                    _setting.emit(it)
                }
        }
    }

    fun postNotificationSetting(uid: Int, csv: CmdSettingValue) {
        val cs = CmdSetting(
            uid = uid,
            key = CmdSettingType.NOTIFICATION,
            value = csv.value,
            setDate = currMillis
        )

        viewModelScope.launch(ioDispatcher) {
            val posted = settingRepository.postSetting(cs)
            _notiPosted.emit(posted)
        }
    }

}
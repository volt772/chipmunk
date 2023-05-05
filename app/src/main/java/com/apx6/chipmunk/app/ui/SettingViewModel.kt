package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.di.IoDispatcher
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.CheckListRepository
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingViewModel @Inject constructor(
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository,
    private val checkListRepository: CheckListRepository
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

}
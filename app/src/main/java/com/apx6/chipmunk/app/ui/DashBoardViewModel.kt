package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.CheckListRepository
import com.apx6.domain.repository.UserRepository
import com.apx6.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val checkListRepository: CheckListRepository
) : BaseViewModel() {

    private val _user: MutableSharedFlow<CmdUser?> = MutableSharedFlow()
    val user: SharedFlow<CmdUser?> = _user

    private val _checkLists: MutableStateFlow<State<List<CmdCheckList>>> = MutableStateFlow(State.loading())
    val checkLists: StateFlow<State<List<CmdCheckList>>> = _checkLists


    fun getUser() {
        viewModelScope.launch {
            userRepository.getUser().collect{ _user.emit(it) }
        }
    }

    fun getCheckLists(uid: Int) {
        viewModelScope.launch {
            checkListRepository.getCheckLists(uid)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _checkLists.value = state }
        }
    }

}
package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdTask
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.TaskRepository
import com.apx6.domain.repository.UserRepository
import com.apx6.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val taskRepository: TaskRepository,
    private val userUseCase: UserUseCase
) : BaseViewModel() {

    private val _user: MutableSharedFlow<CmdUser?> = MutableSharedFlow()
    val user: SharedFlow<CmdUser?> = _user

    private val _tasks: MutableStateFlow<State<List<CmdTask>>> = MutableStateFlow(State.loading())
    val tasks: StateFlow<State<List<CmdTask>>> = _tasks


    fun getUser() {
        viewModelScope.launch {
            userUseCase.getUser().collect { _user.emit(it) }
        }
    }

    fun getTasks(uid: Int) {
        viewModelScope.launch {
            taskRepository.getTasks(uid)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _tasks.value = state }
        }
    }

}
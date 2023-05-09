package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _user: MutableStateFlow<State<CmdUser?>> = MutableStateFlow(State.loading())

    val user: StateFlow<State<CmdUser?>> = _user

    fun registerUser(user: CmdUser) {
        viewModelScope.launch {
            userRepository.user(user)
                .map { resource -> State.fromResource(resource) }
                .collect { state -> _user.value = state }
        }
    }
}
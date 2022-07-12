package com.apx6.chipmunk.app.ui

import androidx.lifecycle.viewModelScope
import com.apx6.chipmunk.app.ui.base.BaseViewModel
import com.apx6.domain.State
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _user: MutableStateFlow<State<CmdUser?>> = MutableStateFlow(State.loading())

    val user: StateFlow<State<CmdUser?>> = _user

    fun getUser() {
//        val testUser = CmdUser(
//            account = "volt772@naver.com",
//            nickName = "고라니",
//            email = "volt772@naver.com",
//            regDate = 12345678,
//            profileThumbnail = "https://k.kakaocdn.net/dn/kSEsG/btrzMIjo4fj/Yz2likKAtl8kfM5Kn00Hp1/img_110x110.jpg",
//            fToken = "asdfasdf"
//        )

        viewModelScope.launch {
            val user = userRepository.getUser().firstOrNull()
            _user.value = user
//                .map { resource -> State.fromResource(resource) }
//                .collect { state -> _user.value = state }
            println("probe :: user : $user")
        }
    }

}
package com.apx6.domain.usecase

import com.apx6.domain.dto.CmdUser
import com.apx6.domain.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {

    suspend fun user(user: CmdUser) = userRepository.user(user)

    suspend fun getUser() = userRepository.getUser()

}
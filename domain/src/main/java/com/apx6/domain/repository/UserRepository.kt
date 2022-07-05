package com.apx6.domain.repository

import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun saveUser(user: CmdUser)

    suspend fun getUser(): Flow<UserEntity>

}
package com.apx6.domain.repository

import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun postUser(user: CmdUser)

    suspend fun getUser(): Flow<User?>

    suspend fun user(user: CmdUser): Flow<Resource<CmdUser?>>

}
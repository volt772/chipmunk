package com.apx6.domain.repository

import com.apx6.domain.dto.CmdUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun postUser(user: CmdUser)

    suspend fun getUser(): Flow<CmdUser?>

    suspend fun user(user: CmdUser): Flow<Resource<CmdUser?>>

}
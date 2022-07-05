package com.apx6.domain.mapper

import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserMapper {

    suspend fun userToEntity(user: CmdUser): UserEntity

//    suspend fun entityToUserFlow(user: Flow<UserEntity>): Flow<CmdUser>

}
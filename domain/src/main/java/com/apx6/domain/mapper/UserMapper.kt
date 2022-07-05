package com.apx6.domain.mapper

import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.User

interface UserMapper {

    suspend fun userToEntity(user: CmdUser): User

//    suspend fun entityToUserFlow(user: Flow<UserEntity>): Flow<CmdUser>

}
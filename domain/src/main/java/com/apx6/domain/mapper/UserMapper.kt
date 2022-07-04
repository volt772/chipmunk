package com.apx6.domain.mapper

import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.UserEntity

interface UserMapper {

    suspend fun userToEntity(user: CmdUser): UserEntity

}
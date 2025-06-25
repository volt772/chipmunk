package com.apx6.chipmunk.app.domain.mapper

import com.apx6.chipmunk.app.domain.dto.CmdUser
import com.apx6.chipmunk.app.domain.entities.User

interface UserMapper {

    suspend fun userToEntity(user: CmdUser): User

}
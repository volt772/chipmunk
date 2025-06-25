package com.apx6.chipmunk.app.data.mapper

import com.apx6.chipmunk.app.domain.dto.CmdUser
import com.apx6.chipmunk.app.domain.entities.User
import com.apx6.chipmunk.app.domain.mapper.UserMapper
import javax.inject.Inject

class UserMapperImpl @Inject constructor(

): UserMapper {

    override suspend fun userToEntity(
        user: CmdUser
    ): User {
        return User(
            id = user.id,
            account = user.account,
            nickName = user.nickName,
            email = user.email,
            regDate = user.regDate,
            profileThumbnail = user.profileThumbnail,
            fToken = user.fToken
        )
    }
}
package com.apx6.data.mapper

import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.User
import com.apx6.domain.mapper.UserMapper
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
package com.apx6.data.mapper

import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.UserEntity
import com.apx6.domain.mapper.UserMapper
import javax.inject.Inject

class UserMapperImpl @Inject constructor(

): UserMapper {

    override suspend fun userToEntity(
        user: CmdUser
    ): UserEntity {
        return UserEntity(
//            id = 0,
            account = user.account,
            nickName = user.nickName,
            email = user.email,
            regDate = user.regDate,
            profileThumbnail = user.profileThumbnail,
            fToken = user.fToken
        )
    }
}

//override fun mapAccountDbToUi(
//    account: LiveData<AccountEntity>
//): LiveData<AccountDto> {
//
//    return Transformations.map(account) { _a ->
//        AccountDto(
//            id = _a?.id?: 0L,
//            memberId = _a?.memberId?: 0L,
//            emailAddress = _a?.emailAddress?: "",
//            displayName = _a?.displayName?: "",
//            department = _a?.department?: "",
//            position = _a?.position?: "",
//            photoUri = _a?.photoUri?: "",
//            userId = _a?.userId?: "",
//            isActive = _a?.isActive?: true,
//            linkedOrganization = _a?.linkedOrganization
//        )
//    }
//
//}
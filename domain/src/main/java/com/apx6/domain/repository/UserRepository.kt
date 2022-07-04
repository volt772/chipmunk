package com.apx6.domain.repository

import com.apx6.domain.dto.CmdUser

interface UserRepository {

    suspend fun saveUser(user: CmdUser)

}
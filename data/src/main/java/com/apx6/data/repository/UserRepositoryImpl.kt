package com.apx6.data.repository

import com.apx6.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(): UserRepository {

    override suspend fun saveUser() {
        TODO("Not yet implemented")
    }
}
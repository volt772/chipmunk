package com.apx6.data.repository

import com.apx6.data.dao.UserDao
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.UserEntity
import com.apx6.domain.mapper.UserMapper
import com.apx6.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userMapper: UserMapper
): UserRepository {

    override suspend fun saveUser(user: CmdUser) {
        val entity = userMapper.userToEntity(user)
        userDao.insertOrUpdate(entity)
    }

    override suspend fun getUser(): Flow<UserEntity> {
        return userDao.getUser()
    }
}
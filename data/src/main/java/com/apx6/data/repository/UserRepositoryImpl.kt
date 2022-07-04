package com.apx6.data.repository

import com.apx6.data.dao.UserDao
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.mapper.UserMapper
import com.apx6.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userMapper: UserMapper
): UserRepository {

    override suspend fun saveUser(user: CmdUser) {

        val entity = userMapper.userToEntity(user)
        println("probe :: user : entity : $entity")
        userDao.overwrite(entity)

    }
}
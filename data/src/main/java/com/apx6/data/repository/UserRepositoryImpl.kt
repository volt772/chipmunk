package com.apx6.data.repository

import com.apx6.data.dao.UserDao
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.User
import com.apx6.domain.mapper.UserMapper
import com.apx6.domain.repository.boundary.LocalBoundaryRepository
import com.apx6.domain.repository.Resource
import com.apx6.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userMapper: UserMapper
): UserRepository {

    override suspend fun user(user: CmdUser): Flow<Resource<CmdUser?>> {
        return object: LocalBoundaryRepository<CmdUser?, CmdUser>() {
            override suspend fun postToLocal(obj: CmdUser) {
                val entity = userMapper.userToEntity(obj)
                userDao.insertOrUpdate(entity)
            }

            override fun fetchFromLocal(): Flow<CmdUser?> {
                return userDao.getUser()
            }
        }.asFlow(user)
    }

    override suspend fun postUser(user: CmdUser) {
        val entity = userMapper.userToEntity(user)
        userDao.insertOrUpdate(entity)
    }

    override suspend fun getUser(): Flow<CmdUser?> {
        return userDao.getUser()
    }

    override suspend fun getUserId(): Flow<Int?> {
        return userDao.getUserId()
    }

    override suspend fun delUser(user: CmdUser): Boolean {
        val entity = convertToEntity(user)
        return userDao.delete(entity) > 0
    }

    private suspend fun convertToEntity(user: CmdUser): User {
        return userMapper.userToEntity(user)
    }
}
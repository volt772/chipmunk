package com.apx6.data.repository

import com.apx6.data.dao.UserDao
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.User
import com.apx6.domain.mapper.UserMapper
import com.apx6.domain.repository.BoundaryRepository
import com.apx6.domain.repository.Resource
import com.apx6.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userMapper: UserMapper
): UserRepository {

    override suspend fun saveUser(user: CmdUser) {
        val entity = userMapper.userToEntity(user)
        userDao.insertOrUpdate(entity)
    }

    override suspend fun getUser(): Flow<Resource<User>> {

        return object: BoundaryRepository<User, User>() {
            override suspend fun saveRemoteData(response: User) {
                userDao.insertOrUpdate(response)
            }

            override fun fetchFromLocal(): Flow<User> {
                return userDao.getUser()
            }

//            override suspend fun fetchFromRemote(): Response<User> {
//            }
        }.asFlow()

//        return userDao.getUser()
    }
}
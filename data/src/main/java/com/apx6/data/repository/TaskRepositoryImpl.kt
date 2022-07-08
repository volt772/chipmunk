package com.apx6.data.repository

import com.apx6.data.dao.UserDao
import com.apx6.domain.dto.CmdUser
import com.apx6.domain.entities.User
import com.apx6.domain.mapper.UserMapper
import com.apx6.domain.repository.BoundaryRepository
import com.apx6.domain.repository.Resource
import com.apx6.domain.repository.TaskRepository
import com.apx6.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userMapper: UserMapper
): TaskRepository {

//    override suspend fun user(user: CmdUser): Flow<Resource<CmdUser?>> {
//        return object: BoundaryRepository<CmdUser?, CmdUser>() {
//            override suspend fun saveRemoteData(response: CmdUser) {
//                val entity = userMapper.userToEntity(response)
//                userDao.insertOrUpdate(entity)
//            }
//
//            override fun fetchFromLocal(): Flow<CmdUser?> {
//                return userDao.getUser()
//            }
//
////            override suspend fun fetchFromRemote(): Response<User> {
////            }
//        }.asFlow()
//    }
//
//    override suspend fun postUser(user: CmdUser) {
//        val entity = userMapper.userToEntity(user)
//        userDao.insertOrUpdate(entity)
//    }
//
//    override suspend fun getUser(): Flow<CmdUser?> {
//        return userDao.getUser()
//    }
//
//    override suspend fun delUser(user: CmdUser): Boolean {
//        val entity = convertToEntity(user)
//        return userDao.delete(entity) > 0
//    }
//
//    private suspend fun convertToEntity(user: CmdUser): User {
//        return userMapper.userToEntity(user)
//    }
}
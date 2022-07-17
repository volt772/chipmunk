package com.apx6.data.repository

import com.apx6.data.dao.TaskDao
import com.apx6.domain.constants.CmdBoundaryQueryType
import com.apx6.domain.dto.CmdTask
import com.apx6.domain.entities.Task
import com.apx6.domain.mapper.TaskMapper
import com.apx6.domain.repository.Resource
import com.apx6.domain.repository.TaskRepository
import com.apx6.domain.repository.boundary.LocalBoundaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
    private val taskMapper: TaskMapper
): TaskRepository {

    override suspend fun task(task: CmdTask, uid: Int): Flow<Resource<List<CmdTask>>> {
        return object: LocalBoundaryRepository<List<CmdTask>, CmdTask>() {
            override suspend fun postToLocal(obj: CmdTask) {
                val entity = convertToEntity(task)
                taskDao.insertOrUpdate(entity)
            }

            override fun fetchFromLocal(): Flow<List<CmdTask>> {
                return taskDao.getTasks(uid)
            }

        }.asFlow(task)
    }

    override suspend fun postTask(task: CmdTask) {
        val entity = convertToEntity(task)
        taskDao.insertOrUpdate(entity)
    }

    override suspend fun getTasks(uid: Int): Flow<Resource<List<CmdTask>>> {
        val tasks = taskDao.getTasks(uid)
        val result = tasks.map {
            Resource.Success(it)
        }

        return result
    }

    override suspend fun getTask(id: Int): Flow<CmdTask?> {
        return taskDao.getTask(id)
    }

    override suspend fun patchTask(task: CmdTask): Boolean {
        val entity = convertToEntity(task)
        return taskDao.update(entity) > 0
    }

    override suspend fun delTask(task: CmdTask): Boolean {
        val entity = convertToEntity(task)
        return taskDao.delete(entity) > 0
    }

    private suspend fun convertToEntity(task: CmdTask): Task {
        return taskMapper.taskToEntity(task)
    }
}
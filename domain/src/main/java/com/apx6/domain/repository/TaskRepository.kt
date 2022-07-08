package com.apx6.domain.repository

import com.apx6.domain.dto.CmdTask
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    /* Combined*/
    suspend fun task(task: CmdTask): Flow<Resource<List<CmdTask>>>

    suspend fun postTask(task: CmdTask)

    suspend fun getTasks(): Flow<List<CmdTask>>

    suspend fun getTask(id: Int): Flow<CmdTask?>

    suspend fun patchTask(task: CmdTask): Boolean

    suspend fun delTask(task: CmdTask): Boolean

}
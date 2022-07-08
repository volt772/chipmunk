package com.apx6.domain.mapper

import com.apx6.domain.dto.CmdTask
import com.apx6.domain.entities.Task

interface TaskMapper {

    suspend fun taskToEntity(task: CmdTask): Task

//    suspend fun entityToUserFlow(user: Flow<UserEntity>): Flow<CmdUser>

}
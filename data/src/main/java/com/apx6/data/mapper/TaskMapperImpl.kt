package com.apx6.data.mapper

import com.apx6.domain.dto.CmdTask
import com.apx6.domain.entities.Task
import com.apx6.domain.mapper.TaskMapper
import javax.inject.Inject

class TaskMapperImpl @Inject constructor(

): TaskMapper {

    override suspend fun taskToEntity(
        task: CmdTask
    ): Task {
        return Task(
            id = task.id,
            cid = task.cid,
            uid = task.uid,
            title = task.title,
            memo = task.memo,
            startDate = task.startDate,
            endDate = task.endDate
        )
    }
}
package com.apx6.data.mapper

import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.entities.Category
import com.apx6.domain.mapper.CategoryMapper
import com.apx6.domain.mapper.TaskMapper
import javax.inject.Inject

class TaskMapperImpl @Inject constructor(

): TaskMapper {

//    override suspend fun categoryToEntity(
//        category: CmdCategory
//    ): Category {
//        return Category(
//            id = category.id,
//            uid = category.uid,
//            name = category.name
//        )
//    }
}
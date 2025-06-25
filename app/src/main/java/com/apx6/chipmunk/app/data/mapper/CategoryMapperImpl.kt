package com.apx6.chipmunk.app.data.mapper

import com.apx6.chipmunk.app.domain.dto.CmdCategory
import com.apx6.chipmunk.app.domain.entities.Category
import com.apx6.chipmunk.app.domain.mapper.CategoryMapper
import javax.inject.Inject

class CategoryMapperImpl @Inject constructor(

): CategoryMapper {

    override suspend fun categoryToEntity(
        category: CmdCategory
    ): Category {
        return Category(
            id = category.id,
            uid = category.uid,
            name = category.name
        )
    }
}
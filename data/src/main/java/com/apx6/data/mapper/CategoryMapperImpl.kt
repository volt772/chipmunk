package com.apx6.data.mapper

import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.entities.Category
import com.apx6.domain.mapper.CategoryMapper
import javax.inject.Inject

class CategoryMapperImpl @Inject constructor(

): CategoryMapper {

    override suspend fun categoryToEntity(
        category: CmdCategory
    ): Category {
        return Category(
            uid = category.uid,
            name = category.name
        )
    }
}
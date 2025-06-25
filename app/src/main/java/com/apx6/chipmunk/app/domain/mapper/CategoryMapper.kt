package com.apx6.chipmunk.app.domain.mapper

import com.apx6.chipmunk.app.domain.dto.CmdCategory
import com.apx6.chipmunk.app.domain.entities.Category

interface CategoryMapper {

    suspend fun categoryToEntity(category: CmdCategory): Category

}
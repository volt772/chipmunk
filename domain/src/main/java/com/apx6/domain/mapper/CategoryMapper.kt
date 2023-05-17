package com.apx6.domain.mapper

import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.entities.Category

interface CategoryMapper {

    suspend fun categoryToEntity(category: CmdCategory): Category

}
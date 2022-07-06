package com.apx6.domain.repository

import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.entities.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategories(): Flow<List<Category>>

    suspend fun getCategory(id: Long): Category

    suspend fun postCategory(category: CmdCategory): Flow<Resource<List<Category>>>

    suspend fun patchCategory(): Category

    suspend fun delCategory(): Boolean

}
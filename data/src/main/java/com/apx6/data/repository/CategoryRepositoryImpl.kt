package com.apx6.data.repository

import com.apx6.data.dao.CategoryDao
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.entities.Category
import com.apx6.domain.repository.BoundaryRepository
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
): CategoryRepository {

    override suspend fun getCategories(): Flow<List<Category>> {
        return categoryDao.getCategories()
    }

    override suspend fun getCategory(id: Long): Category {
        return categoryDao.getCategory(id)
    }

    override suspend fun postCategory(category: CmdCategory): Flow<Resource<List<Category>>> {
        return object: BoundaryRepository<List<Category>, List<Category>>() {
            override suspend fun saveRemoteData(response: List<Category>) {
                categoryDao.insertOrUpdate(response)
            }

            override fun fetchFromLocal(): Flow<List<Category>> {
                return categoryDao.getCategories()
            }

        }.asFlow()
    }

    override suspend fun patchCategory(): Category {
        TODO("Not yet implemented")
    }

    override suspend fun delCategory(): Boolean {
        TODO("Not yet implemented")
    }

}
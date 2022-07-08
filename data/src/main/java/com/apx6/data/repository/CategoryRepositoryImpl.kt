package com.apx6.data.repository

import com.apx6.data.dao.CategoryDao
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.entities.Category
import com.apx6.domain.mapper.CategoryMapper
import com.apx6.domain.repository.BoundaryRepository
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val categoryMapper: CategoryMapper
): CategoryRepository {

    override suspend fun category(category: CmdCategory): Flow<Resource<List<CmdCategory>>> {
        return object: BoundaryRepository<List<CmdCategory>, CmdCategory>() {
            override suspend fun saveRemoteData(response: CmdCategory) {
                val entity = convertToEntity(category)
                categoryDao.insertOrUpdate(entity)
            }

            override fun fetchFromLocal(): Flow<List<CmdCategory>> {
                return categoryDao.getCategories()
            }

        }.asFlow()
    }

    override suspend fun postCategory(category: CmdCategory) {
        val entity = convertToEntity(category)
        categoryDao.insertOrUpdate(entity)
    }

    override suspend fun getCategories(): Flow<List<CmdCategory>> {
        return categoryDao.getCategories()
    }

    override suspend fun getCategory(id: Int): Flow<CmdCategory?> {
        return categoryDao.getCategory(id)
    }

    override suspend fun patchCategory(category: CmdCategory): Boolean {
        val entity = convertToEntity(category)
        return categoryDao.update(entity) > 0
    }

    override suspend fun delCategory(category: CmdCategory): Boolean {
        val entity = convertToEntity(category)
        return categoryDao.delete(entity) > 0
    }

    private suspend fun convertToEntity(category: CmdCategory): Category {
        return categoryMapper.categoryToEntity(category)
    }

}
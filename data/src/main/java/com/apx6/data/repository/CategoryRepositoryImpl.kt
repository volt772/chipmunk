package com.apx6.data.repository

import androidx.paging.PagingData
import com.apx6.data.dao.CategoryDao
import com.apx6.data.data_source.category.CategoryRemoteDataSource
import com.apx6.domain.dto.CmdCategory
import com.apx6.domain.entities.Category
import com.apx6.domain.mapper.CategoryMapper
import com.apx6.domain.repository.CategoryRepository
import com.apx6.domain.repository.Resource
import com.apx6.domain.repository.boundary.LocalBoundaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val categoryMapper: CategoryMapper,
    private val categoryRemoteDataSource: CategoryRemoteDataSource
): CategoryRepository {

    override suspend fun category(category: CmdCategory, uid: Int): Flow<Resource<List<CmdCategory>>> {
        return object: LocalBoundaryRepository<List<CmdCategory>, CmdCategory>() {
            override suspend fun postToLocal(obj: CmdCategory) {
                val entity = convertToEntity(obj)
                categoryDao.insertOrUpdate(entity)
            }

            override fun fetchFromLocal(): Flow<List<CmdCategory>> {
                return categoryDao.getCategories(uid)
            }

        }.asFlow(category)
    }

    override suspend fun postCategory(category: CmdCategory): Int {
        val entity = convertToEntity(category)
        return categoryDao.insertOrUpdate(entity)
    }

    override suspend fun getCategories(uid: Int): Flow<Resource<List<CmdCategory>>> {
        val categories = categoryDao.getCategories(uid)
        val result = categories.map {
            Resource.Success(it)
        }

        return result
    }

    override fun fetchCategories(uid: Int): Flow<PagingData<CmdCategory>> {
        return categoryRemoteDataSource.category(uid)
    }

    override suspend fun getCategory(id: Int): Flow<CmdCategory?> {
        return categoryDao.getCategory(id)
    }

    override suspend fun patchCategory(category: CmdCategory): Boolean {
        val entity = convertToEntity(category)
        return categoryDao.update(entity) > 0
    }

    override suspend fun delCategory(id: Int): Boolean {
        return categoryDao.delCategory(id) > 0
    }

    private suspend fun convertToEntity(category: CmdCategory): Category {
        return categoryMapper.categoryToEntity(category)
    }

}
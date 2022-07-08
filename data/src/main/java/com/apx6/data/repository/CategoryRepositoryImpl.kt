package com.apx6.data.repository

import com.apx6.data.dao.CategoryDao
import com.apx6.domain.dto.CmdCategory
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
                val entity = categoryMapper.categoryToEntity(response)
                categoryDao.insertOrUpdate(entity)
            }

            override fun fetchFromLocal(): Flow<List<CmdCategory>> {
                return categoryDao.getCategories()
            }

        }.asFlow()
    }

    override suspend fun postCategory(category: CmdCategory) {
        val entity = categoryMapper.categoryToEntity(category)
        categoryDao.insertOrUpdate(entity)
    }


    override suspend fun getCategories(): Flow<List<CmdCategory>> {
        return categoryDao.getCategories()
    }

    override suspend fun getCategory(id: Long): Flow<CmdCategory?> {
        return categoryDao.getCategory(id)
    }
//
//    override suspend fun patchCategory(): CmdCategory {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun delCategory(): Boolean {
//        TODO("Not yet implemented")
//    }

}
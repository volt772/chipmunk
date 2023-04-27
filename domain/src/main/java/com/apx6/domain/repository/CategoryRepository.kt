package com.apx6.domain.repository

import androidx.paging.PagingData
import com.apx6.domain.dto.CmdCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    /* Combined*/
    suspend fun category(category: CmdCategory, uid: Int): Flow<Resource<List<CmdCategory>>>

    suspend fun postCategory(category: CmdCategory)

    suspend fun getCategories(uid: Int): Flow<Resource<List<CmdCategory>>>

    fun fetchCategories(uid: Int): Flow<PagingData<CmdCategory>>

    suspend fun getCategory(id: Int): Flow<CmdCategory?>

    suspend fun patchCategory(category: CmdCategory): Boolean

    suspend fun delCategory(category: CmdCategory): Boolean

}
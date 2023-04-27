package com.apx6.data.data_source.category

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.apx6.data.db.CmdDatabase
import com.apx6.domain.constants.CmdConstants.DB.LOCAL_PAGE_SIZE
import com.apx6.domain.dto.CmdCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRemoteDataSourceImpl @Inject constructor(
    private val cmdDatabase: CmdDatabase,
) : CategoryRemoteDataSource {

    override fun category(
        uid: Int
    ): Flow<PagingData<CmdCategory>> {

        /* RemoteMediator 미사용, 로컬 DB만 사용시 Factory*/
        return {
            cmdDatabase.categoryDao().getPagingCategories(
                uid = uid
            )
        }.run {
            Pager(
                config = PagingConfig(
                    pageSize = LOCAL_PAGE_SIZE,
                    enablePlaceholders = false,
                    initialLoadSize = LOCAL_PAGE_SIZE * 2
                ),
                pagingSourceFactory = this
            ).flow
        }
    }
}

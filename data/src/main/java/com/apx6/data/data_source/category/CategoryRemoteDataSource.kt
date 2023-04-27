package com.apx6.data.data_source.category

import androidx.paging.PagingData
import com.apx6.domain.dto.CmdCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRemoteDataSource {

    fun category(
        uid: Int
    ): Flow<PagingData<CmdCategory>>
}

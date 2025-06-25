package com.apx6.chipmunk.app.data.category

import androidx.paging.PagingData
import com.apx6.chipmunk.app.domain.dto.CmdCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRemoteDataSource {

    fun category(
        uid: Int
    ): Flow<PagingData<CmdCategory>>
}

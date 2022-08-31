package com.apx6.domain.repository

import com.apx6.domain.dto.CmdCheckList
import kotlinx.coroutines.flow.Flow

interface CheckListRepository {

    /* Combined*/
    suspend fun checklists(checkList: CmdCheckList, uid: Int): Flow<Resource<List<CmdCheckList>>>

    suspend fun postCheckList(checkList: CmdCheckList)

    suspend fun getCheckLists(uid: Int): Flow<Resource<List<CmdCheckList>>>

    suspend fun getCheckList(id: Int): Flow<CmdCheckList?>

    suspend fun patchCheckList(checkList: CmdCheckList): Boolean

    suspend fun delCheckList(checkList: CmdCheckList): Boolean

    suspend fun getLocation(query: String)

}
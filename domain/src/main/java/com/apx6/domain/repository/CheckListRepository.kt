package com.apx6.domain.repository

import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.dto.CmdCheckListWithCategory
import com.apx6.domain.dto.CmdLocation
import com.apx6.domain.dto.CmdLocationDoc
import kotlinx.coroutines.flow.Flow

interface CheckListRepository {

    /* Combined*/
    suspend fun checklists(checkList: CmdCheckList, uid: Int): Flow<Resource<List<CmdCheckList>>>

    suspend fun postCheckList(checkList: CmdCheckList): Boolean

    suspend fun getCheckLists(uid: Int, millis: Long): Flow<Resource<List<CmdCheckList>>>

    suspend fun getCheckListsInCategory(uid: Int, cid: Int): Flow<List<CmdCheckList>>

    suspend fun getCheckList(id: Int): Flow<CmdCheckList?>

    suspend fun getCheckListWithCategory(id: Int): Flow<CmdCheckListWithCategory?>

    suspend fun getCheckListCount(uid: Int): Flow<Resource<Int>>

    suspend fun patchCheckList(checkList: CmdCheckList): Boolean

    suspend fun delCheckList(checkList: CmdCheckList): Boolean

//    suspend fun getLocation(query: String): Flow<Resource<CmdLocation>>

}
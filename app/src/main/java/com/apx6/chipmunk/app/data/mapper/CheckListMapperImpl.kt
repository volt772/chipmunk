package com.apx6.chipmunk.app.data.mapper

import com.apx6.chipmunk.app.domain.dto.CmdCheckList
import com.apx6.chipmunk.app.domain.entities.CheckList
import com.apx6.chipmunk.app.domain.mapper.CheckListMapper
import javax.inject.Inject

class CheckListMapperImpl @Inject constructor(

): CheckListMapper {

    override suspend fun checkListToEntity(
        checkList: CmdCheckList
    ): CheckList {
        return CheckList(
            id = checkList.id,
            cid = checkList.cid,
            uid = checkList.uid,
            title = checkList.title,
            memo = checkList.memo,
            exeDate = checkList.exeDate
        )
    }
}
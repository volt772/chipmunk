package com.apx6.data.mapper

import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.entities.CheckList
import com.apx6.domain.mapper.CheckListMapper
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
            startDate = checkList.startDate,
            endDate = checkList.endDate
        )
    }
}
package com.apx6.chipmunk.app.domain.mapper

import com.apx6.chipmunk.app.domain.dto.CmdCheckList
import com.apx6.chipmunk.app.domain.entities.CheckList

interface CheckListMapper {

    suspend fun checkListToEntity(checkList: CmdCheckList): CheckList

}
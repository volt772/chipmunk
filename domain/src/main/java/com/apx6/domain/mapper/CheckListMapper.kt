package com.apx6.domain.mapper

import com.apx6.domain.dto.CmdCheckList
import com.apx6.domain.entities.CheckList

interface CheckListMapper {

    suspend fun checkListToEntity(checkList: CmdCheckList): CheckList

}
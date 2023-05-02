package com.apx6.domain.dto

data class CmdCheckListDetail (

    val checkList: CmdCheckList,

    val categoryName: String

) {
    companion object {
        fun default(): CmdCheckListDetail {
            return CmdCheckListDetail(
                checkList = CmdCheckList.default(),
                categoryName = ""
            )
        }
    }
}

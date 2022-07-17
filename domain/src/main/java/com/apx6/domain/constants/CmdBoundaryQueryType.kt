package com.apx6.domain.constants

enum class CmdBoundaryQueryType {

    /* 이벤트 Insert or Update 발생포함*/
    UPSERT,

    /* 단순 리스트 쿼리*/
    LIST

}
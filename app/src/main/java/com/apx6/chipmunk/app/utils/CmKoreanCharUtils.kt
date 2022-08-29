package com.apx6.chipmunk.app.utils

interface CmKoreanCharUtils {

    fun isChoseong(c: Char): Boolean

    fun isSyllable(c: Char): Boolean

    fun getCompatChoseong(value: Char): Char

}

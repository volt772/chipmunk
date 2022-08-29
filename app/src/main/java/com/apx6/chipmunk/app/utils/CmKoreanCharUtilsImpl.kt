package com.apx6.chipmunk.app.utils

import javax.inject.Inject

/**
 * 한글 문자 관련 util
 */
class CmKoreanCharUtilsImpl @Inject constructor() : CmKoreanCharUtils {
    private val choseongCount = 19
    private val jungseongCount = 21
    private val jongseongCount = 28
    private val hangulSyllableCount = choseongCount * jungseongCount * jongseongCount
    private val hangulSyllablesBase = 0xAC00 // 가
    private val hangulSyllablesEnd = hangulSyllablesBase + hangulSyllableCount - 1 // 힣
    private val compatChoseongMap = intArrayOf(
        0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145,
        0x3146, 0x3147, 0x3148, 0x3149, 0x314A, 0x314B, 0x314C, 0x314D, 0x314E
    )

    /**
     * 초성인지 아닌지
     * ㄱ,ㄴ,ㄷ,ㄹ,...
     */
    override fun isChoseong(c: Char): Boolean {
        return compatChoseongMap.contains(c.code)
    }

    /**
     * 음절인지 아닌지
     * 가,나,다,라,...
     */
    override fun isSyllable(c: Char): Boolean {
        return c.code in hangulSyllablesBase..hangulSyllablesEnd
    }

    /**
     * 문자값이 한글이면 해당 값의 초성을 반환한다.
     * 한글이 아니면 해당 값을 반환한다.
     * 예) 정 -> ㅈ
     */
    override fun getCompatChoseong(value: Char): Char {
        if (isChoseong(value) || !isSyllable(value)) return value

        val choseongIndex = getChoseongIndex(value)
        return compatChoseongMap[choseongIndex].toChar()
    }

    /**
     * 문자값 중에 한글이 포함된 경우 해당 값의 초성으로 변환한다.
     * 한글이 아닌 다른 문자가 들어간 경우 해당 값을 반환한다.
     * 예) 정상현 -> ㅈㅅㅎ
     * 예) 영어(English)가 포함 -> ㅇㅇ(English)ㄱ ㅍㅎ
     */
    private fun getChoseongIndex(syllable: Char): Int {
        val syllableIndex = syllable.code - hangulSyllablesBase
        return syllableIndex / (jungseongCount * jongseongCount)
    }
}

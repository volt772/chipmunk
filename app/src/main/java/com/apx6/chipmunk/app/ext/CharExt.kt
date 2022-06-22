package com.apx6.chipmunk.app.ext

/**
 * 알파벳인가?
 */
val Char.isAlphabet: Boolean
    get() = this in 'A'..'Z' || this in 'a'..'z'

/**
 * 한글인가?
 */
//val Char.isHangul: Boolean
//    get() = this.toInt() in 0xAC00..0xD7A3 || this.toInt() in 0x3131..0x314E // 가~힣 or ㄱ~ㅎ

//val Char.isHangul: Boolean
//    get() = KoreanCharUtils.isChoseong(this) || KoreanCharUtils.isSyllable(this)

/**
 * 숫자인가?
 */
val Char.isNumber: Boolean
    get() = this.isDigit()

/**
 * 특수기호인가?
 */
val Char.isSpecial: Boolean
    get() {
        return (this.code >= '!'.code && this.code <= '/'.code // !"#$%&'()*+,-./
                || this.code >= ':'.code && this.code <= '@'.code //:;<=>?@
                || this.code >= '['.code && this.code <= '`'.code //[\]^_`
                || this.code >= '{'.code && this.code <= '~'.code) //{|}~
    }
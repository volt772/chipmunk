package com.apx6.chipmunk.app.ext

/**
 * 실수 값 Cast String Value
 * ex) value 1.5일때 Return 1.5 (Float 형태 String 값)
 *     value 1.0일때 Return 1 (Int 형태 String 값)
 */
fun Float?.toStringExt(): String {
    if (this == null) {
        return ""
    }
    val intValue = this.toInt()
    val decimalValue = this - intValue.toFloat()

    return if (decimalValue == 0f) intValue.toString() else this.toString()
}
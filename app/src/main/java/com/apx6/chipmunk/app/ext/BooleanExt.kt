package com.apx6.chipmunk.app.ext

/**
 * Boolean
 * true = 1 / false = 0
 */
fun Boolean?.toInt(): Int {
    return if (this != null && this) 1 else 0
}
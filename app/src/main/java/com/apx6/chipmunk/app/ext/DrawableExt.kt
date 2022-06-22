package com.mailplug.aeolos.ext

/**
 * Color : HSV to HEX
 */
fun Int.colorHsvToHex() = "#" + Integer.toHexString(this).substring(2)

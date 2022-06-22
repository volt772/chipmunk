package com.apx6.utils

/**
 * TestUtils
 */
object TestUtils {

    /**
     * Time Wait
     */
    fun waitInSec(sec: Long) {
        val waitSec = if (sec > 0) sec else 1
        val interval = 500L
        val loopCount = waitSec * 1000L / interval
        for (i in 0 until loopCount) {
            try {
                println("!!!!! Waiting : $i !!!!!")
                Thread.yield()
                Thread.sleep(interval)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}
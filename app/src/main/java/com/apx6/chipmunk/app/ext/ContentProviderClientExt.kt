package com.apx6.chipmunk.app.ext

import android.content.ContentProviderClient
import android.os.Build

inline fun <T: ContentProviderClient, R> T.useSafe(block: (T) -> R) : R {
    try {
        return block.invoke(this)
    } catch (e: Throwable) {
        throw e
    } finally {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.close()
        } else {
            this.release()
        }
    }
}
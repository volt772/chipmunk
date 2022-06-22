package com.apx6.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class LiveDataTestUtils {
    companion object {

        @Throws(InterruptedException::class)
        fun <T> getValue(liveData: LiveData<T>, waitSec: Long = 5, tag: String = ""): T? {
            val latch = CountDownLatch(1)
            val data = mutableListOf<T>()
            val observer = Observer<T> {
                println("[$tag] LiveData changed !")

                if (it != null) {
                    data.add(it)
                    latch.countDown()
                }
            }

            liveData.observeForever(observer)
            latch.await(waitSec, TimeUnit.SECONDS)
            liveData.removeObserver(observer)

            return if (data.isEmpty()) null else data.last()
        }

        fun <T> LiveData<T>.getValueBlocking(): T? {
            var value: T? = null
            val latch = CountDownLatch(1)

            val observer = Observer<T> { t ->
                value = t
                latch.countDown()
            }

            observeForever(observer)

            latch.await(2, TimeUnit.SECONDS)
            return value
        }

        fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
            val observer = OneTimeObserver(handler = onChangeHandler)
            observe(observer, observer)
        }
    }

}
package com.apx6.chipmunk.app.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * Observe Ext
 * @param liveData Observer
 * @param action 데이터가 null 이 아닐 때의 action
 */
inline fun <T> LifecycleOwner.observeExt(liveData: LiveData<T>, crossinline action: (t: T) -> Unit) {
    liveData.observe(this) { it?.let { t -> action(t) } }
}

/**
 * Observe Ext
 * @param liveData Observer
 * @param action 데이터가 null 이 아닐 때의 action
 * @param nullAction 데이터가 null 일 때의 action
 */
inline fun <T> LifecycleOwner.observeExt(liveData: LiveData<T>, crossinline action: (t: T) -> Unit, crossinline nullAction: () -> Unit) {
    liveData.observe(this) {
        it?.let { t -> action(t) } ?: nullAction()
    }
}
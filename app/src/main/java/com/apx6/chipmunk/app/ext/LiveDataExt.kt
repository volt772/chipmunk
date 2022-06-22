package com.apx6.chipmunk.app.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

class NonNullMediatorLiveData<T> : MediatorLiveData<T>() {

    inline fun observe(owner: LifecycleOwner, crossinline observer: (t: T) -> Unit) {
        this.observe(owner, Observer {
            it?.let(observer)
        })
    }
}

fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
    val mediator = NonNullMediatorLiveData<T>()
    mediator.addSource(this) { it?.let { mediator.value = it } }
    return mediator
}

fun <T> MediatorLiveData<T>.addSources(vararg sources: LiveData<*>, onChanged: () -> T) {
    sources.forEach {
        this.addSource(it) { value = onChanged() }
    }
}
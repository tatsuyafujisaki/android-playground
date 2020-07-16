package com.github.tatsuyafujisaki.androidplayground.util

import androidx.lifecycle.*

object LiveDataUtil {
    fun <T> MutableLiveData<T>.notifyObserver() {
        value = value
    }

    fun <A, B, C> combine(
        liveData1: LiveData<A>,
        liveData2: LiveData<B>,
        onChanged: (A, B) -> C
    ) =
        liveData1.switchMap { a ->
            liveData2.map { b ->
                onChanged(a, b)
            }
        }

    fun <A, B, C> mediate(
        liveData1: LiveData<A>,
        liveData2: LiveData<B>,
        onChanged: (A?, B?) -> C
    ) = MediatorLiveData<C>().apply {
        addSource(liveData1) {
            value = onChanged(liveData1.value, liveData2.value)
        }
        addSource(liveData2) {
            value = onChanged(liveData1.value, liveData2.value)
        }
    }
}

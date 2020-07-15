package com.github.tatsuyafujisaki.androidplayground.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

object LiveDataUtil {
    fun <T> MutableLiveData<T>.notifyObserver() {
        value = value
    }

    fun <T1, T2, R> mediate(
        liveData1: LiveData<T1>,
        liveData2: LiveData<T2>,
        onChange: (T1?, T2?) -> R
    ) = MediatorLiveData<R>().apply {
        addSource(liveData1) {
            value = onChange(liveData1.value, liveData2.value)
        }
        addSource(liveData2) {
            value = onChange(liveData1.value, liveData2.value)
        }
    }

    fun <T1, T2, R> mediateNoNull(
        liveData1: LiveData<T1>,
        liveData2: LiveData<T2>,
        default: R,
        onChange: (T1, T2) -> R
    ) = MediatorLiveData<R>().apply {
        fun onChange() = if(liveData1.value == null || liveData2.value == null) {
            default
        } else {
            onChange(liveData1.value!!, liveData2.value!!)
        }

        addSource(liveData1) {
            value = onChange()
        }
        addSource(liveData2) {
            value = onChange()
        }
    }

    fun <T1, T2, R> mediateSets(
        liveData1: LiveData<T1>,
        liveData2: LiveData<T2>,
        onChange: (T1, T2) -> Set<R>
    ) = MediatorLiveData<Set<R>>().apply {
        fun onChange() = if(liveData1.value == null || liveData2.value == null) {
            emptySet()
        } else {
            onChange(liveData1.value!!, liveData2.value!!)
        }

        addSource(liveData1) {
            value = onChange()
        }
        addSource(liveData2) {
            value = onChange()
        }
    }
}

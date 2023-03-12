package com.github.tatsuyafujisaki.androidplayground.util

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.toPublisher
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

object LiveDataUtil {
    fun <T> MutableLiveData<T>.notifyObserver() {
        value = value
    }

    fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, onChanged: (T) -> Unit) {
        observe(
            owner,
            object : Observer<T> {
                override fun onChanged(value: T) {
                    onChanged(value)
                    removeObserver(this)
                }
            }
        )
    }

    /**
     * NB: observeForever(...) observes even after LifecycleOwner, which contains this LiveData, gets destroyed.
     */
    fun <T> LiveData<T>.observeOnce(onChanged: (T) -> Unit) {
        observeForever(object : Observer<T> {
            override fun onChanged(value: T) {
                onChanged(value)
                removeObserver(this)
            }
        })
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

    private fun <A, B, C> mediateNonNull(
        liveData1: LiveData<A>,
        liveData2: LiveData<B>,
        onChanged: (A, B) -> C
    ) = MediatorLiveData<C>().apply {
        addSource(liveData1) {
            if (liveData1.value != null && liveData2.value != null) {
                value = onChanged(liveData1.value!!, liveData2.value!!)
            }
        }
        addSource(liveData2) {
            if (liveData1.value != null && liveData2.value != null) {
                value = onChanged(liveData1.value!!, liveData2.value!!)
            }
        }
    }

    /**
     * Instead, use MediatorLiveData directly.
     * [Transformations] is a wrapper of [MediatorLiveData].
     * Nested [Transformations]s are nested [MediatorLiveData]s and create extra overhead.
     */
    @Deprecated("Instead, use MediatorLiveData directly")
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

    fun sampleUsage(lifecycleOwner: LifecycleOwner) {
        val a = MutableLiveData(1)
        val b = MutableLiveData(2)

        mediateNonNull(a, b) { c, d ->
            c + d
        }.observe(lifecycleOwner) {
            Log.d(object {}.javaClass.enclosingMethod?.name, it.toString())
        }

        a.value = 10
        b.value = 100
    }

    /**
     * Converts LiveData to Observable using LiveDataReactiveStreams in androidx.lifecycle:lifecycle-reactivestreams-ktx.
     */
    fun <T : Any> LiveData<T>.toObservable1(owner: LifecycleOwner): Observable<T> =
        Observable.fromPublisher(toPublisher(owner))

    /**
     * Converts LiveData to Observable without LiveDataReactiveStreams in androidx.lifecycle:lifecycle-reactivestreams-ktx.
     */
    fun <T : Any> LiveData<T>.toObservable2(owner: LifecycleOwner): Observable<T> =
        PublishSubject.create<T>().apply {
            observe(owner) {
                onNext(it)
            }
        }.hide()
}

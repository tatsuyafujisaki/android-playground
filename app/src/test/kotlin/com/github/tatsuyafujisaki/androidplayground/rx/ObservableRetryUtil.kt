package com.github.tatsuyafujisaki.androidplayground.rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleEmitter
import io.reactivex.rxjava3.core.SingleOnSubscribe

/**
 * Observables that fail once. They are useful in a unit test for [Observable.retry].
 */
object ObservableRetryUtil {
    fun <T : Any> errorOnceObservable(value: T, error: Throwable = Throwable()): Observable<T> {
        var firstTest = true

        return Observable.create {
            if (firstTest) {
                firstTest = false
                it.onError(error)
            } else {
                it.onNext(value)
            }
        }
    }

    fun <T : Any> errorOnceSingle(t: T): Single<T> = Single.create(
        object : SingleOnSubscribe<T> {
            private var firstTime = true

            override fun subscribe(emitter: SingleEmitter<T>) {
                if (firstTime) {
                    firstTime = false
                    emitter.onError(Throwable())
                } else {
                    emitter.onSuccess(t)
                }
            }
        }
    )
}

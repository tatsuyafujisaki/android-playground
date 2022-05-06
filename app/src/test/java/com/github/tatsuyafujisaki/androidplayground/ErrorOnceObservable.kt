package com.github.tatsuyafujisaki.androidplayground

import io.reactivex.rxjava3.core.Observable

/**
 * Observable that fails once. It is useful in a unit test for [Observable.retry].
 */
class ErrorOnceObservable {
    var firstRun = true

    fun <T : Any> create(value: T, error: Throwable = Throwable()): Observable<T> =
        Observable.create {
            if (firstRun) {
                firstRun = false
                it.onError(error)
            } else {
                it.onNext(value)
            }
        }
}

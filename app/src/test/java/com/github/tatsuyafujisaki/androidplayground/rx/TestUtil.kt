package com.github.tatsuyafujisaki.androidplayground.rx

import io.reactivex.rxjava3.core.*

object ObservableRetryUtil {
    fun <T : Any> createErrorOnceObservable(value: T): Observable<T> = Observable.create(
        object : ObservableOnSubscribe<T> {
            private var firstTime = true

            override fun subscribe(emitter: ObservableEmitter<T>) {
                if (firstTime) {
                    firstTime = false
                    emitter.onError(Throwable())
                } else {
                    emitter.onNext(value)
                }
            }
        }
    )


    fun <T : Any> createErrorOnceSingle(t: T): Single<T> = Single.create(
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

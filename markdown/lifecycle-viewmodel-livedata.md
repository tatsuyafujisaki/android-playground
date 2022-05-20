# ViewModel
> Warning: Never expose mutable data fields from your ViewModel—make sure this data can't be modified from another class. Mutable data inside the ViewModel should always be private.

https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel#4

> Data in a LiveData object can be read, but not changed. From outside the ViewModel, data should be readable, but not editable, so the data should be exposed as LiveData.

https://developer.android.com/codelabs/kotlin-android-training-live-data#5

# LiveData versus Flow
&nbsp;|LiveData|Flow
--|--|--
contains ...|only the lastest value|all the past values
readable and writable only in|the main thread (*1)|any thread
when there is no active observer (*2)|canceled|not canceled
suitable for|the main thread|except for the main thread

* (*1) `postValue()` moves the write to the main thread. Moreover, `observe()` can be called only from the main thread.
* (*2) i.e. when `Activity` goes `STOPPED`.

> Note: LiveData is retained in this example for simplicity. In general, it's recommended to use Flow with repositories as it's independent of the lifecycle.

https://developer.android.com/codelabs/basic-android-kotlin-training-repository-pattern#4


# LifecycleCoroutineScope.launchWhenStarted
* pauses when not STARTED, and cancels when Activity is destroyed.
* https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleCoroutineScope#launchwhenstarted

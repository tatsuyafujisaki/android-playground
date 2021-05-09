# ViewModel
* > Warning: Never expose mutable data fields from your ViewModel—make sure this data can't be modified from another class. Mutable data inside the ViewModel should always be private.
  * https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel#4
* > Data in a LiveData object can be read, but not changed. From outside the ViewModel, data should be readable, but not editable, so the data should be exposed as LiveData.
  * https://developer.android.com/codelabs/kotlin-android-training-live-data#5

# LiveData and Flow

&nbsp;|LiveData|Flow
--|--|--
contains ...|only the lastest value|all the past values
callable from|only the main thread|any thread
when there is no active observer (*)|canceled|not canceled
suitable for|the main thread|except for the main thread

(*) i.e. Activity goes in the stopped state.

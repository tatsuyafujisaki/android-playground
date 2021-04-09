# ViewModel
* > Warning: Never expose mutable data fields from your ViewModel—make sure this data can't be modified from another class. Mutable data inside the ViewModel should always be private.
  * https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel#4
* > Data in a LiveData object can be read, but not changed. From outside the ViewModel, data should be readable, but not editable, so the data should be exposed as LiveData.
  * https://developer.android.com/codelabs/kotlin-android-training-live-data#5

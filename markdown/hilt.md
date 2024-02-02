- https://developer.android.com/training/dependency-injection/hilt-android
  - > If you annotate an Android class with @AndroidEntryPoint, then you also must annotate Android classes that depend on it. For example, if you annotate a fragment, then you must also annotate any activities where you use that fragment.

- https://developer.android.com/codelabs/android-hilt#8
  - > Since the different implementations of LoggerDataSource are scoped to different containers, we cannot use the same module: LoggerInMemoryDataSource is scoped to the Activity container and LoggerLocalDataSource to the Application container.
  - > We need to define a qualifier per implementation since each qualifier will be used to identify a binding.

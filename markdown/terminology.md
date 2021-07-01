# Known direct subclass
* is a direct subclass in the same library.
* For example, FileProvider is a direct subclass of ContentProvider, but not a "known" direct subclass of ContentProvider because FileProvider as is part of the AndroidX Core Library, while ContentProvider is not.
https://developer.android.com/jetpack/androidx/releases/core#declaring_dependencies

# Main-safe function
* means that the function is safe to call from the main thread. The function does not block the main thread because it runs on the background thread.
* For example, suspend functions are main-safe.

# @Volatile
> Annotate INSTANCE with @Volatile. The value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory. This helps make sure the value of INSTANCE is always up-to-date and the same for all execution threads. It means that changes made by one thread to INSTANCE are visible to all other threads immediately.

https://developer.android.com/codelabs/basic-android-kotlin-training-persisting-data-room#6

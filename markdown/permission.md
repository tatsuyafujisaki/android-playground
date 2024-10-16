# Scoped storage
> In Android 11 or higher, apps targeting API 30 or higher must use scoped storage.

https://source.android.com/docs/core/storage/scoped

# Manifest.permission
## [READ_EXTERNAL_STORAGE](https://developer.android.com/reference/kotlin/android/Manifest.permission#read_external_storage)
> Note: Starting in API level 33, this permission has no effect. 

https://developer.android.com/reference/kotlin/android/Manifest.permission#read_external_storage

## [WRITE_EXTERNAL_STORAGE](https://developer.android.com/reference/kotlin/android/Manifest.permission#write_external_storage)
> Note: If your app targets android.os.Build.VERSION_CODES#R (= Android 11 (API level 10)) or higher, this permission has no effect.

https://developer.android.com/reference/kotlin/android/Manifest.permission#write_external_storage

The above is probably why the following specifies `android:maxSdkVersion="30"`.

```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
                 android:maxSdkVersion="29" />
```
https://developer.android.com/training/data-storage/shared/media#access-other-apps-files

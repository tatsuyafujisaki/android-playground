# Meanings of onStart and onResume
* onStart ... Activity is visible.
* onResume ... Activity has focus. 

# Activity lifecycle
* onCreate()
* onStart()
* onRestoreInstanceState() (API 21+)
  * called only if savedInstanceState is not null.
  * > Most of the time, you restore the activity state in onCreate(). But because onRestoreInstanceState() is called after onStart(), if you ever need to restore some state after onCreate() is called, you can use onRestoreInstanceState().
    * https://developer.android.com/codelabs/basic-android-kotlin-training-activity-lifecycle#4
* onResume()
* onPause()
* onStop()
* onSaveInstanceState()
  * called after onStop() in Android P or higher.
  * > If called, this method will occur after onStop for applications targeting platforms starting with android.os.Build.VERSION_CODES#P. For applications targeting earlier platform versions this method will occur before onStop and there are no guarantees about whether it will occur before or after onPause.
    * https://developer.android.com/reference/kotlin/android/app/Activity#onsaveinstancestate_1
* onDestroy()

# Activity.finish()
  * calls these in order: onPause > onStop > onDestroy

# Activity.isFinishing()
  * is false if the system is temporarily destroying the activity to save space.

# FragmentActivity
is an Activity that can get FragmentManager.

# `android:configChanges`
1. Open https://developer.android.com/guide/topics/manifest/activity-element
2. Search for `Normal launches for most activities`, and you will find the great table of the list of configuration changes.

# `android:launchMode`
1. Open https://developer.android.com/guide/topics/manifest/activity-element
2. Search for `Normal launches for most activities`, and you will find the great table of the differences between `standard`, `singleTop`, `singleTask`, and `singleInstance`.

## Examples
Each of A, B, C, and D below represents an instance of an Activity.

### Example of `android:launchMode="standard"` (default)
Suppose that B is `android:launchMode="standard"`.
1. Stack
    * B
    * A
2. Start B
3. Stack
    * B (new)
    * B
    * A
4. Start A
5. Stack
    * A (new)
    * B
    * B
    * A

### Example of `android:launchMode="singleTop"`
Suppose that B is `android:launchMode="singleTop"`.
1. Stack
    * B
    * A
2. Start B
3. Stack
    * B (reused with the new intent)
    * A
4. Start A
5. Stack
    * A (new)
    * B
    * A

When you repeat searching, `singleTop` prevents recreating `SearchActivity` and `SearchResultActivity`.

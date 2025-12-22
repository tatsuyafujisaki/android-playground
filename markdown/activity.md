# Activity lifecycle

* `onCreate()`
* `onStart()`
* `onResume()`
* `onPause()`
* `onStop()`
* `onRestart()`
* `onDestroy()`
* `onSaveInstanceState()`
  * Called to save UI state. As you noted, the timing of this call relative to `onStop()` changed in Android P.
* `onRestoreInstanceState()`
  * Called after `onStart()` to restore UI state, only if there is a saved state.
* `onNewIntent()`
  * Called when an activity is re-launched while at the top of the activity stack.

# Activity.finish()
* calls these in order: onPause > onStop > onDestroy


# FragmentActivity
is an Activity that can get FragmentManager.

# `android:configChanges`
1. Open https://developer.android.com/guide/topics/manifest/activity-element
1. Search for `Normal launches for most activities`, and you will find the great table of the list of configuration changes.

# `android:launchMode`
* https://developer.android.com/guide/components/activities/tasks-and-back-stack#ManifestForTasks
* Do the following
  1. Open https://developer.android.com/guide/topics/manifest/activity-element
  1. Search for `Normal launches for most activities`, and you will find the great table of the differences between `standard`, `singleTop`, `singleTask`, and `singleInstance`.

## Examples
Each of A, B, C, and D below represents an instance of an Activity.

### Example of `android:launchMode="standard"` (default)
Suppose that B is `android:launchMode="standard"`.
1. Stack
    * B
    * A
1. Start B
1. Stack
    * B (new)
    * B
    * A
1. Start A
1. Stack
    * A (new)
    * B
    * B
    * A

### Example of `android:launchMode="singleTop"`
Suppose that B is `android:launchMode="singleTop"`.
1. Stack
    * B
    * A
1. Start B
1. Stack
    * B (reused with the new intent)
    * A
1. Start A
1. Stack
    * A (new)
    * B
    * A

When you repeat searching, `singleTop` prevents recreating `SearchActivity` and `SearchResultActivity`.

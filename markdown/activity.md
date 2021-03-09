# Meanings of onStart and onResume
* onStart ... Activity is visible.
* onResume ... Activity has focus. 

# Activity lifecycle
* onCreate()
* onStart()
* onRestoreInstanceState() (API 21+)
  * called only if savedInstanceState is not null.
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

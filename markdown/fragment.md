# DialogFragment/Fragment lifecycle
* onAttach()
* onCreate()
* onCreateDialog()
  * DialogFragment only
* onCreateView()
* onViewStateRestored()
* onStart()
* onResume()
* onDismiss()
  * DialogFragment only
* onPause()
* onSaveInstanceState()
* onStop()
  * When going to sleep, up to onStop() is called, but onDestroyView() is not.
* onDestroyView()
* onDestroy()
* onDetach()

Note that onRestoreInstanceState() does not exist in Fragment(s).

# FragmentContainerView
is a container to which multiple fragments can be added.

# View in Fragment
* is the root of a fragment's layout.
* is created by onCreateView().
* is detached from a fragment  by onDestroyView().

# FragmentManager
## FragmentManager.findFragmentById(...)
* gets fragments that provide a UI in the activity layout.
## FragmentManager.findFragmentByTag(...)
* gets fragments whether or not they provide a UI in the activity layout.
* Despite [the documentation](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentManager#findfragmentbytag), I have found that findFragmentByTag() can get fragments that are detached and not in the back stack.
## FragmentManager.getFragments()
* does not include fragments that are detached.
* https://developer.android.com/guide/components/fragments#Managing

## FragmentActivity.getSupportFragmentManager()
* returns a FragmentManager associated with the activity.
  * which can place a fragment in `<androidx.fragment.app.FragmentContainerView>` in the activity's layout file.
* FragmentActivity is namely an Activity.

## Fragment.getParentFragmentManager()
* returns a FragmentManager associated with the fragment's activity.
  * which can place a child fragment in `<androidx.fragment.app.FragmentContainerView>` in the activity's layout file.

## Fragment.getChildFragmentManager()
* returns a private FragmentManager associated with the fragment.
  * which can place a child fragment in `<androidx.fragment.app.FragmentContainerView>` in the fragment's layout file.

# Fragment back stack or simply back stack
* is NOT a collection of Fragment(s) but a collection of FragmentTransaction(s).
* is managed by the parent activity.
  * https://developer.android.com/guide/components/fragments#Transactions
* Fragment = childFragmentManager = fragment back stack = 1 : 1 : 1
* In a Fragment, the following removes all the existing fragments, and then add a FragmentA
```kotlin
fragmentManager.commit {
    replace(FragmentA)
}
```

# FragmentManager
* When `FragmentManager.popBackStack()` is called, the following happens in sequence.
  1. `FragmentManager.primaryNavigationFragment.childFragmentManager.popBackStack()` is called.
  2. If the above returns false, `FragmentActivity.supportFragmentManager.popBackStack()` is called.
  3. If the above returns false, the app quits.

# FragmentTransaction
## FragmentTransaction.remove(...)
* calls `Fragment.onDestroy()` and `Fragment.onDetach()` if `FragmentTransaction.addToBackStack(...)` is NOT called in the same transaction.
* calls up to `Fragment.onDestroyView()` if `FragmentTransaction.addToBackStack(...)` is called in the same transaction.

## FragmentTransaction.addToBackStack()
* adds a FragmentTransaction in the back stack instead of adding an Fragment in the back stack.
* will run the sequence of the operations of the transaction in the reverse order when the Up or Back button is pressed.

## FragmentTransaction.attach(...)
* attaches a fragment to the UI.
* calls `Fragment.onCreateView()`.

## FragmentTransaction.detach(...)
* detaches a fragment to the UI.
* calls `Fragment.onDestroyView()`.

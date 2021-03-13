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

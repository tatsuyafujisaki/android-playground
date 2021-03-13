# FragmentManager
* When FragmentManager.popBackStack() is called, the following happens in order.
  1. primaryNavigationFragment.childFragmentManager.popBackStack() is called.
  2. If the above returns false, FragmentActivity.supportFragmentManager.popBackStack() is called.
  3. If the above returns false, the app quits.

# FragmentTransaction
## FragmentTransaction.remove(...)
* calls onDestroy() and onDetach() if addBackStack() is NOT called in the same transaction.
* calls up to onDestroyView() if addBackStack() is called in the same transaction.

## FragmentTransaction.addToBackStack()
* adds a FragmentTransaction in the back stack instead of adding an Fragment in the back stack.
* will run the sequence of the operations of the transaction in the reverse order when the Up or Back button is pressed.

## FragmentTransaction.attach(...)
* attaches a fragment to the UI.
* calls onCreateView() to create the view in the fragment.

## FragmentTransaction.detach(...)
* detaches a fragment to the UI.
* calls onDestroyView() to destroy the view in the fragment.

# Navigation
## Analogies
* NavHostFragment … a TV
* NavController … a remote control
* NavDestination … a television channel
* NavigationView ... Menu for DrawerLayout, which exists 
  * is not part of the Navigation component and exists before the Navigation component.
* NavigationUI … outside a TV (e.g. NavigationView, BottomNavBar)
  * https://www.youtube.com/watch?v=xITkfPIaStU&t=573s

# Up button versus back button
* Both navigate in reverse-chronological order through the history of where you have been.
* Both behave identically except:
  * The Up button navigates only within the app.
  * The Up button must not be shown if the user is at an app's start destination.
* > When your app is launched using a deep link on another app's task, Up transitions users back to your app’s task and through a simulated back stack and not to the app that triggered the deep link. The Back button, however, does take you back to the other app.
  * https://developer.android.com/guide/navigation/navigation-principles
* https://developer.android.com/codelabs/kotlin-android-training-add-navigation#7

# Navigation
## Analogies
* NavHostFragment … a TV
* NavController … a remote control
* NavDestination … a television channel
* NavigationView ... Menu for DrawerLayout, which exists 
  * is not part of the Navigation component and exists before the Navigation component.
* NavigationUI … outside a TV
  * e.g. NavigationView, BottomNavBar
  * https://www.youtube.com/watch?v=xITkfPIaStU&t=573s

## NavHostFragment
* is contained in the layout of an Activity as follows.
```xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/fragment_container_view"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:defaultNavHost="true"
    app:navGraph="@navigation/nav_graph" />
```
* is like a window to swap in and out different fragment destinations.
* has a NavController.
* If there are more than two NavHostFragment in a layout, only one NavHostFragment must have "app:defaultNavHost="true"", which intercepts the Back button.
  * https://developer.android.com/guide/navigation/navigation-getting-started

## NavController
* is in a NavHostFragment.
* shows different destinations in a NavHostFragment.
* There are three ways to get a NavController
  * Fragment.findNavController()
  * View.findNavController()
  * Activity.findNavController(viewId: Int)
* Sample usages:
```kotlin
findNavController().navigate(OriginatingFragment1Directions.myAction1(argKey1 = argValue1))
findNavController().navigate(R.id.action1 or R.id.destination1 or R.id.nav_graph1)
findNavController().navigate(deepLink: Uri)
```
  * Navigation graph IDs are usable because NavGraph inherits NavDestination.
    * > When navigating using IDs, we strongly recommend using actions where possible.
      * https://developer.android.com/guide/navigation/navigation-navigate#id
* When `NavController.popBackStack()` returns false, …
  * `NavController.getCurrentDestination()` returns null.
  * You should call `Activity.finish()`.

## NavDestination
* is either `<fragment>` or `<activity>`.
  * https://developer.android.com/codelabs/android-navigation#2
* If you don't specify a list of top-level destinations, then the only top-level destination is your start destination
  * https://developer.android.com/codelabs/android-navigation#8
* You move from an originating destination to a receiving destination.

## NavGraph
* is equivalent of `<navigation>` in XML.
* inherits the NavDestination class.
* is a collection of NavDestination.
https://developer.android.com/guide/navigation/navigation-getting-started

### Nested graph
* is a nested `<navigation>`.
```xml
<!-- parent graph -->
<navigation>
    <include app:graph="@navigation/child_nav" … />
    <fragment>
        <action app:destination="@id/child_nav" />
    </fragment>
</navigation>
```
```xml
<!-- @navigatoin/child_nav -->
<navigation android:id="@+id/child_nav" … >...</navigation>
```

### `<action>`
* is represented as an arrow in a visual navigation graph.
* is a connection from one destination to another.
```xml
<action
    android:id="@+id/action1"
    app:destination="@id/destination1" or "@id/nestedGraph1"
    app:popUpToInclusive="true" /> <!-- Default is false -->
```

## Global action
* is available from any destination in the navigation graph.
* can be accessed in a type-safe way as `<NavigationId>Directions.globalAction1`.

## Navigation drawer
* > Caution: Avoid using a navigation drawer with other primary navigation components, such as a bottom navigation bar.
  * https://material.io/components/navigation-drawer#usage

## SafeArgs plugin
* generates ...
  * `<OriginatingDestination>Directions`.
  * `<ReceivingDestination>Args`.
  * `<NavigationId>Directions.globalAction1`.

## Terminology
* "Simulated back stack" and "Synthetic back stack" are the same thing.

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

# First ...
* Refer to [deep-link.md](deep-link.md) for deep linking.
* Refer to [fragment.md](fragment.md) for fragments except the Navigation component.

# Up button versus back button
* Both navigate in reverse-chronological order through the history of where you have been.
* Both behave identically except:
  * The Up button navigates only within the app.
  * The Up button must not be shown if the user is at an app's start destination.
* > When your app is launched using a deep link on another app's task, Up transitions users back to your app’s task and through a simulated back stack and not to the app that triggered the deep link. The Back button, however, does take you back to the other app.
  * https://developer.android.com/guide/navigation/navigation-principles
* https://developer.android.com/codelabs/kotlin-android-training-add-navigation#7

# Analogies
* NavHostFragment … a TV
* NavController … a remote control
* NavDestination … a television channel
* NavigationView ... Menu for DrawerLayout, which exists
  * is not part of the Navigation component and exists before the Navigation component.
* NavigationUI … outside a TV
  * e.g. NavigationView, BottomNavBar
  * https://www.youtube.com/watch?v=xITkfPIaStU&t=573s

# NavHostFragment
* is configured via a FragmentContainerView in the layout of an Activity as follows.
  * https://developer.android.com/guide/navigation/navigation-migrate#create_a_navigation_graph
```xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/fragment_container_view"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:defaultNavHost="true"
    app:navGraph="@navigation/nav_graph" />
```
* has a NavController.
* is like a window to swap in and out different fragment destinations.
* Activity : FragmentContainerView : NavHostFragment : NavGraph = 1 : 1 : 1
* If there are more than two NavHostFragment in a layout, only one NavHostFragment must have "app:defaultNavHost="true"", which intercepts the Back button.
  * https://developer.android.com/guide/navigation/navigation-getting-started
* How to connect NavHostFragment with BottomNavigationView
```kotlin
val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.setupWithNavController(navController)
```
* Don't use `<fragment>`. Use `<androidx.fragment.app.FragmentContainerView>`.
  * > Caution: Avoid using the <fragment> tag to add a fragment using XML, as the <fragment> tag allows a fragment to move beyond the state of its FragmentManager. Instead, always use FragmentContainerView for adding a fragment using XML.
    * https://developer.android.com/guide/fragments/lifecycle#states

# NavController
* is in a NavHostFragment.
* shows different destinations in a NavHostFragment.
* There are three ways to get a NavController
  * Fragment.findNavController()
  * View.findNavController()
  * Activity.findNavController(viewId: Int)
* Sample usages:
```kotlin
findNavController().navigate(MyOriginatingFragmentDirections.myAction1(key1 = value1))
findNavController().navigate(R.id.action1 or R.id.destination1 or R.id.nav_graph1)
findNavController().navigate(deepLink: Uri)
```
  * Navigation graph IDs are usable because NavGraph inherits NavDestination.
    * > When navigating using IDs, we strongly recommend using actions where possible.
      * https://developer.android.com/guide/navigation/navigation-navigate#id
* When `NavController.popBackStack()` returns false, …
  * `NavController.getCurrentDestination()` returns null.
  * You should call `Activity.finish()`.
  * https://developer.android.com/guide/navigation/navigation-navigate#back-stack

# NavDestination
* is either `<fragment>`, `<dialog>`, or `<activity>`.
  * https://developer.android.com/codelabs/android-navigation#2
* If you don't specify a list of top-level destinations, then the only top-level destination is your start destination
  * https://developer.android.com/codelabs/android-navigation#8
* You move from an originating destination to a receiving destination.

# Navigator
* There are three navigators.
  * `<fragment>` (FragmentNavigator)
  * `<dialog>` (DialogFragmentNavigator)
  * `<activity>` (ActivityNavigator)
* can contain three types of XML elements.
  * `<action>`
    * `<activity>` cannot contain `<action>` because activities are considered terminal destinations.
      * https://developer.android.com/guide/navigation/navigation-migrate#create_a_navigation_graph
  * `<argument>`
  * `<deepLink>`
* A template of a navigator
```xml
<fragment
   android:id="@+id/destination1"
   android:name="com.example.Fragment1"
   android:label="Page title on a top app bar" or "{argName}" for automatic population. (https://developer.android.com/guide/navigation/navigation-ui)
   tools:layout="@layout/fragment1" />
```

# NavGraph
* is equivalent of `<navigation>` in XML.
* inherits the NavDestination class.
* is a collection of NavDestination.
* > each activity has its own navigation graph.
  * https://developer.android.com/guide/navigation/navigation-getting-started

## Nested graph
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

## `<action>`
* is represented as an arrow in a visual navigation graph.
* is a connection from one destination to another.
```xml
<action
    android:id="@+id/action1"
    app:destination="@id/destination1" or "@id/nestedGraph1"
    app:popUpTo="@id/start_fragment"
    app:popUpToInclusive="true" /> <!-- Default is false -->
```

### `app:popUpTo`
is used when you want to pop more than one destination.

# Global action
* is available from any destination in the navigation graph.
* can be accessed in a type-safe way as `<NavigationId>Directions.globalAction1`.

# BottomNavigationView
## BottomNavigationView.setOnNavigationItemReselectedListener(...)
* is called when the currently selected bottom navigation destination is selected again.

## BottomNavigationView.setOnNavigationItemSelectedListener(...)
* is called when any bottom navigation destination is selected, if `BottomNavigationView.setOnNavigationItemReselectedListener` is NOT set.
* is called only when a not-currently-selected bottom navigation is selected, if `BottomNavigationView.setOnNavigationItemReselectedListener` is set.

## How BottomNavigationView works
1. Suppose there are three items in a Bottom Navigation.
    * A (startDestination), B, C
2. When you touch A
    * fragmentManager's fragments == [A]
    * fragmentManager's backStackEntries == (empty)
3. When you touch B
    * If A was selected previously, A will call onDestroyView().
    * If C was selected previously, C will call onDestroy().
    * fragmentManager's fragments == [B]
    * fragmentManager's backStackEntries == [A]
4. When you touch C
    * If A was selected previously, A will call onDestroyView().
    * If B was selected previously, B will call onDestroy().
    *  fragmentManager's fragments == [C]
    * fragmentManager's backStackEntries == [A]
5. When you navigate from C to "C-Detail" for example.
    * fragmentManager's fragments == [C-Detail]
    * B will call onDestroyView().
    * fragmentManager's backStackEntries == [A, C]

# Navigation drawer
* > Caution: Avoid using a navigation drawer with other primary navigation components, such as a bottom navigation bar.
  * https://material.io/components/navigation-drawer#usage

# SafeArgs plugin
* generates ...
  * `<OriginatingDestination>Directions`.
  * `<ReceivingDestination>Args`.
  * `<NavigationId>Directions.globalAction1`.

## How to forcibly generate `<OriginatingDestination>Directions` classes
Android Studio's toolbar > `View` > `Tool Windows` > `Gradle` > `<app name>` > `Tasks` > `build` > (again) `build`

# AppCompatActivity.onSupportNavigateUp()
* Override `onSupportNavigateUp()` if the app bar is used instead of a Toolbar. The following URLs show the code snippets of overriding `onSupportNavigateUp()`.
  * https://developer.android.com/codelabs/basic-android-kotlin-training-fragments-navigation-component#7
  * https://developer.android.com/codelabs/basic-android-kotlin-training-navigation-backstack#2
* > Note: When using a Toolbar, Navigation automatically handles click events for the Navigation button, so you do not need to override onSupportNavigateUp().
  * https://developer.android.com/guide/navigation/navigation-ui#create_a_toolbar
* If `Activity.onOptionsItemSelected(...)` is overridden, `AppCompatActivity.onSupportNavigateUp()` will not be called.
  * https://stackoverflow.com/a/40626742

# Animation
```xml
<fragment
    android:id="@+id/fragment1"
    android:name="com.example.Fragment1"
    tools:layout="@layout/fragment_a">
    <action
        android:id="@+id/action_fragment1_to_fragment2"
        app:destination="@id/fragment2"
        app:enterAnim="@anim/slide_in_right"
        app:exitAnim="@anim/slide_out_left"
        app:popEnterAnim="@anim/slide_in_left"
        app:popExitAnim="@anim/slide_out_right" />
</fragment>
```
* `app:enterAnim` … How Fragment2 appears when you move from Fragment1 to Fragment2.
  * If `app:enterAnim="@anim/slide_in_right"` is specified, Fragment2 slides in from the left.
  * If `app:enterAnim=...` is not specified, Fragment2 shows up without animation.
* `app:exitAnim` … How Fragment1 disappears when you move from Fragment1 to Fragment2.
  * If `app:exitAnim="@anim/slide_out_left"` is specified, Fragment1 slides out to the right.
  * If `app:exitAnim=...` is not specified, Fragment1 stays still and Fragment2 shows up over the Fragment1, with the animation `app:enterAnim`.
* `app:popEnterAnim` … How Fragment1 appears when you move back from Fragment2 to Fragment1, by the pop action.
* `app:popExitAnim` … How Fragment2 disappears when you move back from Fragment2 to Fragment1, by the pop action.

# Misc
* "Simulated back stack" and "Synthetic back stack" are the same thing.

# Task
* is a stack of activities.
  * Fragments are stacked inside an activity.
  * https://www.youtube.com/watch?v=MvIlVsXxXmY
     * Hitting the Back button ...
       * pops off the top fragment if any fragment transaction exists
       * pops off the top activity if no fragment transaction exists.
  * The following illustraitons are useful:
    * https://developer.android.com/codelabs/basic-android-kotlin-training-navigation-backstack#3
* https://developer.android.com/guide/navigation/navigation-principles
  * > The back stack always has the start destination of the app at the bottom of the stack
  * > The top of the stack is the current screen, and the previous destinations in the stack represent the history of where you've been.
  * > Navigating to a destination pushes that destination on top of the stack.

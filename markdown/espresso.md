# Espresso
## How to interact with an Activity
```kotlin
activityScenario.onActivity {
    // Do something with an Activity.
}
```

## How to interact with a Fragment
```kotlin
fragmentScenario.onFragment {
    // Do something with a Fragment.
}
```

## How to move to a state
Unless specified, it moves to RESUMED.
```kotlin
// scenario is ActivityScenario or FragmentScenario.
scenario.moveToState(State.STARTED)
```

## How to recreate an Activity or a Fragment
```kotlin
// scenario is ActivityScenario or FragmentScenario.
scenario.recreate()
```

## How to click a button
```kotlin
onView(withId(R.id.my_button)).perform(click())

// scrollTo() is necessary only if the view is not displayed without scrolling.
onView(withId(R.id.my_button)).perform(scrollTo(), click())
```

## How to check the visibility or existence of a view
```kotlin
onView(withId(R.id.my_view)).check(matches(isDisplayed())) // VISIBLE
onView(withId(R.id.my_view)).check(matches(not(isDisplayed()))) // INVISIBLE
onView(withId(R.id.my_view)).check(doesNotExist()) // GONE or does not exist.
```

## How to check if a view has exactly the same string
```kotlin
onView(withId(R.id.my_view))
.perform(scrollTo()) // Necessary only if the view is not displayed without scrolling.
.check(matches(withText("foo")))
```

## How to check if a view contains a string
```kotlin
onView(withId(R.id.my_view))
.perform(scrollTo()) // Necessary only if the view is not displayed without scrolling.
.check(matches(withText(containsString("foo"))))
```

## How to troubleshoot
* If you see the following error when trying to run an instrumented unit test:
  * `java.lang.TypeNotPresentException: Type http://android.support.test.runner.AndroidJUnit4 not present`
* Solution
  * Go to Android Studio's menu bar > `Run` > `Edit Configurations` > delete all the tests.

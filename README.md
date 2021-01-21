# Official style guides
* https://developer.android.com/kotlin/style-guide
* https://kotlinlang.org/docs/reference/coding-conventions.html

# Material Design typography
## TextAppearance from [Material Components for Android](https://material.io/develop/android/docs/getting-started/)
Style|Scalable pixels (sp)
--|--
@style/TextAppearance.MaterialComponents.Headline1|96sp
@style/TextAppearance.MaterialComponents.Headline2|60sp
@style/TextAppearance.MaterialComponents.Headline3|48sp
@style/TextAppearance.MaterialComponents.Headline4|34sp
@style/TextAppearance.MaterialComponents.Headline5|24sp
@style/TextAppearance.MaterialComponents.Headline6|20sp
@style/TextAppearance.MaterialComponents.Subtitle1|16sp
@style/TextAppearance.MaterialComponents.Body1|16sp
@style/TextAppearance.MaterialComponents.Subtitle2|14sp
@style/TextAppearance.MaterialComponents.Body2|14sp
@style/TextAppearance.MaterialComponents.Button|14sp
@style/TextAppearance.MaterialComponents.Chip|14sp
@style/TextAppearance.Design.Tab|14sp
@style/TextAppearance.Design.Snackbar.Message|14sp
@style/TextAppearance.MaterialComponents.Overline|12sp
@style/TextAppearance.MaterialComponents.Caption|12sp

## TextAppearance from Android SDK
Style|Scalable pixels (sp)
--|--
@android:style/TextAppearance.Material.Display4|112sp
@android:style/TextAppearance.Material.Display3|56sp
@android:style/TextAppearance.Material.Display2|45sp
@android:style/TextAppearance.Material.Display1|34sp
@android:style/TextAppearance.Material.Headline|24sp
@android:style/TextAppearance.Material.Title|20sp
@android:style/TextAppearance.Material.Subhead|16sp
@android:style/TextAppearance.Material.Menu|16sp
@android:style/TextAppearance.Material.Body1|14sp
@android:style/TextAppearance.Material.Body2|14sp
@android:style/TextAppearance.Material.Button|14sp
@android:style/TextAppearance.Material.Caption|12sp

## Usage
```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello, World!"
    android:textAppearance="@style/TextAppearance.MaterialComponents.Headline6" />
```

Or if you want to create a style:

```xml
<!-- res/values/styles.xml -->
<resources>
    <style name="MyStyle">
        <item name="android:textAppearance">@style/TextAppearance.MaterialComponents.Headline6</item>
    </style>
</resources>
```

## References
* https://material.io/develop/android/theming/typography/
* https://material.io/design/typography/the-type-system.html
* https://github.com/material-components/material-components-android
* https://developer.android.com/guide/topics/ui/look-and-feel/themes#textappearance

# Colors predefined on Android SDK
* black
* darker_gray
* red
  * not documented in https://d.android.com/reference/kotlin/android/R.color but available.
* transparent
* white

## Usage
```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello, World!"
    android:textColor="@android:color/white" />
```

## References
https://developer.android.com/reference/kotlin/android/R.color

# Japanese strings.xml on Android SDK
https://cs.android.com/android/platform/superproject/+/master:frameworks/base/core/res/res/values-ja/strings.xml

# How to create a Bundle
```kotlin
val bundle: Bundle = bundleOf("apple" to 123, "orange" to 456) // Core KTX
```

# How to encode and decode HTML entities
```kotlin
val s = "<>&'\""
val encoded: String = s.htmlEncode() // &lt;&gt;&amp;&#39;&quot;
val decoded: String = Html.fromHtml(encoded, Html.FROM_HTML_MODE_COMPACT).toString() // <>&'"
```
[String.htmlEncode](https://developer.android.com/reference/kotlin/androidx/core/text/package-summary#htmlencode) is a part of the Core KTX library and is syntactic sugar for [TextUtils.htmlEncode](https://developer.android.com/reference/kotlin/android/text/TextUtils#htmlEncode(kotlin.String)).

# How to get a query parameter
```kotlin
val uri: Uri = "https://example.com?key1=value1&key2=value2".toUri()
val value: String = uri.getQueryParameter("key2") // value2
```

# How to convert AARRGGBB as Int to Color
```kotlin
val color: Color = Color.valueOf(0x11223344)
```

# How to change the color of a menu item
No need to use `SpannableString`.
```kotlin
R.id.my_menu_item -> {
    findViewById<TextView>(R.id.my_menu_item)?.setTextColor(Color.RED)
}
```

# Gradle
## How to list all the source sets (subdirectories under the `src` directory)
```shell
./gradlew sourceSets
```

## How to assemble, lint, and test
```shell
./gradlew build

# In case you want to exclude lint and test
./gradlew build --exclude-task lint --exclude-task test
```

## How to skip lint and tests
Add the following to the beginning of the project-level `build.gradle`.
```shell
tasks.whenTaskAdded { task ->
    if (task.name == "lint" || task.name.endsWith("Test")) {
        task.enabled false
    }
}
```

# How to show an app's dependencies
## Option 1
Android Studio's toolbar > `View` > `Tool Windows` > `Gradle` > `<app name>` > `Tasks` > `android` > `androidDependencies`

The preceding shows dependencies, as a list, declared in the project `app`.

## Option 2
```shell
./gradlew app:dependencies
```
shows dependencies, as a tree, declared in the project `app`.

## Option 3
```shell
./gradlew app:androidDependencies
```
shows dependencies, as a list, declared in the project `app`.


# How to run a unit test or an instrumented unit test from Terminal
## How to run a local unit test
```shell
./gradlew test
```

## How to run an instrumented unit test
```shell
./gradlew connectedAndroidTest

# Alternatively
# https://docs.gradle.org/current/userguide/command_line_interface.html#task_name_abbreviation
./gradlew cAT
```

## How to run a local unit test for a build variant and a module
```shell
./gradlew <module>:test<build-variant>UnitTest
```

## How to run an instrumented unit test for a build variant and a module
```shell
./gradlew <module>:connected<build-variant>AndroidTest
```

# How to find what class causes an error by `minifyenabled true`
Add the following in `proguard-rule.pro`.
```
-keepattributes SourceFile,LineNumberTable
```

# Predefined string resources
```kotlin
val a: String = resources.getString(android.R.string.ok) // OK
val d: String = resources.getString(android.R.string.cancel) // Cancel
val b: String = resources.getString(android.R.string.unknownName) // Unknown
val c: String = resources.getString(android.R.string.untitled) // <Untitled>
```

* https://developer.android.com/reference/kotlin/android/R.string
* (Japanese) https://cs.android.com/android/platform/superproject/+/master:frameworks/base/core/res/res/values-ja/strings.xml

# OnBackPressedDispatcher
`OnBackPressedDispatcher.hasEnabledCallbacks()` returns true if both of the following are met.

1. There is at least one callback registered with this dispatcher.
2. The Activity's lifecycle state is after `onStart()` and before `onStop()`. If you override `onStart()` and/or `onStop()`, it is between `super.onStart()` and `super.onStop()`.
    1. Even if you pass a Fragment to `OnBackPressedDispatcher.addCallback(...)`, the Fragment's lifecycle does nothing to do with `OnBackPressedDispatcher.hasEnabledCallbacks()`.

# Template
## Child in ConstraintLayout
```xml
<View
    android:id="@+id/view"
    android:layout_width="0dp"
    android:layout_height="0dp"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

## LinearLayout
```xml
<LinearLayout
    android:id="@+id/linear_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:showDividers="middle"
    android:dividerPadding="16dp"
    android:divider="?android:listDivider" or "?android:dividerHorizontal"
    android:orientation="vertical">
```

## RecyclerView
```xml
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recycler_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    tools:listitem="@layout/view_holder" />
```

```kotlin
with(recyclerView) {
    adapter = myAdapter
    addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
    itemAnimator = null
    registerAdapterDataObserver(
    object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            scrollToPosition(0) // RecyclerView#scrollToPosition(0)
        }
    })
    myLiveData.observe(viewLifecycleOwner) {
        with(adapter as MyAdapter) {
            submitList(it)
            // notifyDataSetChanged() // Uncomment this only if new data is not reflected on UI after submitList(...).
        }
    }
}
```

## EditText
`android:hint`, `android:importantForAutofill`, and `android:inputType` are to suppress a warning.
```xml
<EditText
    android:id="@+id/edit_text"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@null"
    android:importantForAutofill="no"
    android:inputType="text"
    android:text="Hello, World!" />
```

## HorizontalScrollView and ChipGroup
```xml
<HorizontalScrollView
    android:id="@+id/horizontal_scroll_view"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:scrollbars="none"
    android:requiresFadingEdge="horizontal"
    android:fadingEdgeLength="80dp">

    <com.google.android.material.chip.ChipGroup
        android:id="@+id/chip_group"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:singleSelection="true"
        app:chipSpacingHorizontal="0dp" />
</HorizontalScrollView>
```
* `app:singleSelection="true"` makes [choice chips](https://material.io/components/chips#choice-chips) and enables [`ChipGroup.setOnCheckedChangeListener()`](https://developer.android.com/reference/com/google/android/material/chip/ChipGroup#setoncheckedchangelistener).
* If `app:singleSelection="true"` is not set, you have to add an `View.OnClickListener` on each chip.
* `ChipGroup.setOnCheckedChangeListener()` returns -1 if the currently selected chip is re-selected.

```kotlin
chipGroup.setOnCheckedChangeListener { group, checkedId ->
    group.children.filterIsInstance<Chip>().firstOrNull { it.id == checkedId }?.run {
        /*
         * Prevents re-selecting the selected chip because it will unselect the selected chip and leave all the chips unselected.
         * app:selectionRequired="true" in Material Components 1.2.0-alpha02 or higher dispenses with this workaround.
         * https://github.com/material-components/material-components-android/issues/651
         */
        isClickable = false
        group.children.filterIsInstance<Chip>().filterNot { it.id == id }.forEach {
            it.isClickable = true
        }
    }
}
```

# How to use CookieManager
```kotlin
val cookieManager = CookieManager.getInstance()

val url1 = "example.com"
val url2 = "example.com/foo"
val url3 = "sub.example.com"
val url4 = "https://example.com"
val url5 = "http://example.com"

cookieManager.setCookie(url1, "a=1")
cookieManager.setCookie(url2, "b=2")
cookieManager.setCookie(url3, "c=3")
cookieManager.setCookie(url4, "d=4")
cookieManager.setCookie(url5, "e=5")
cookieManager.setCookie(url5, "e=5!")

val cookie1: String = cookieManager.getCookie(url1) // a = 1; b = 2; d = 4; e = 5!
val cookie2: String = cookieManager.getCookie(url2) // a = 1; b = 2; d = 4; e = 5!
val cookie3: String = cookieManager.getCookie(url3) // c = 3
val cookie4: String = cookieManager.getCookie(url4) // a = 1; b = 2; d = 4; e = 5!
val cookie5: String = cookieManager.getCookie(url5) // a = 1; b = 2; d = 4; e = 5!
```

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
* Issue
  * `java.lang.TypeNotPresentException: Type http://android.support.test.runner.AndroidJUnit4 not present (when trying to run an instrumented unit test)`
* Solution
  * Go to Android Studio's menu bar > `Run` > `Edit Configurations` > delete all the tests.

# Emulators
# How to list emulators
```shell
~/Library/Android/sdk/emulator/emulator -list-avds
```

# How to launch an emulator specifying a DNS server
```shell
~/Library/Android/sdk/emulator/emulator -avd <emulator> -dns-server 1.1.1.1,8.8.8.8,8.8.4.4

# You don't have to specify an emulator if you have only one emulator.
~/Library/Android/sdk/emulator/emulator -avd $(~/Library/Android/sdk/emulator/emulator -list-avds) -dns-server 1.1.1.1,8.8.8.8,8.8.4.4
```

# Charles
## How to enable Charles Proxy in debug build
https://www.charlesproxy.com/documentation/using-charles/ssl-certificates/

## How to enable Charles Proxy in release build
1. Connect your Android to Charles. Otherwise, Charles Root Certificate will NOT be automatically downloaded in the next step.
2. Access http://www.charlesproxy.com/getssl/ or http://chls.pro/ssl from Android. Then, Charles Root Certificate should be automatically downloaded.
3. Open Charles and go to the menu bar > `Help` > `SSL Proxying` > `Save Charles Root Certificate`
4. Add the downloaded PEM file as `res/raw/charles_certificate.pem`.
5. Add `ref/xml/network_security_config.xml` as follows.
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config>
        <trust-anchors>
            <certificates src="system" />
            <certificates src="@raw/charles_certificate"/>
            <!-- The following in <base-config> has no effect. -->
            <!-- <certificates src="user" /> -->
        </trust-anchors>
    </base-config>
</network-security-config>
```
5. Reference `ref/xml/network_security_config.xml` in `AndroidManifest.xml` as follows.
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest>
    <application android:networkSecurityConfig="@xml/network_security_config" />
</manifest>
```

### Note
If you have installed the Charles Root Certificate on Android and still see the following error, you may be doing the release build. If so, the above steps will solve the error.
```
SSL handshake with client failed: An unknown issue occurred processing the certificate (certificate_unknown)
You may need to configure your browser or application to trust the Charles Root Certificate. See SSL Proxying in the Help menu.
certificate_unknown (46) - An unknown issue occurred processing the certificate.
```

# Best practices
* Use `String.toUri()` rather than `Uri.parse(...)` for simplicity.
* Use `CharSequence.isDigitsOnly()` to check if a string contains only digits.
* Use `Context.withStyledAttributes(...)` rather than `obtainStyledAttributes(...)` for simplicity.

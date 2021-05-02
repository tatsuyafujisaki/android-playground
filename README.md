# Official style guides and best practices
* https://developer.android.com/kotlin/style-guide
* https://kotlinlang.org/docs/reference/coding-conventions.html
* https://developer.android.com/kotlin/coroutines/coroutines-best-practices
* https://github.com/androidx/androidx/blob/androidx-main/compose/docs/compose-api-guidelines.md

# Gradle
## How to show the root project's dependencies as a tree
```shell
./gradlew dependencies
```

## How to show the app module's dependencies as a tree
```shell
./gradlew app:dependencies
```

## How to show modules the given module depends on
```shell
# -q is to suppress non-error logs.
# --configuration is to filter only "implementation".
# https://docs.gradle.org/current/userguide/viewing_debugging_dependencies.html
./gradlew -q <module>:dependencies --configuration implementation | grep '+--- project' | sort
```

## Testing
### How to run a local unit test
```shell
./gradlew test
```

### How to run an instrumented unit test
```shell
./gradlew connectedAndroidTest

# Alternatively
# https://docs.gradle.org/current/userguide/command_line_interface.html#task_name_abbreviation
./gradlew cAT
```

### How to run a local unit test for a build variant and a module
```shell
./gradlew <module>:test<build-variant>UnitTest
```

## How to run an instrumented unit test for a build variant and a module
```shell
./gradlew <module>:connected<build-variant>AndroidTest
```

## Meaning of `task clean` in project-level `build.gradle`
The following custom task in the project-level `build.gradle` is to delete the project-level `build` directory when clicking the menu bar > `Build` > `Clean Project`.
```gradle
task clean(type: Delete) {
    delete rootProject.buildDir
}
```

# Android Debug Bridge (adb)
## Activity/Fragment
### How to show the current activity
```shell
adb shell "dumpsys activity activities | grep mResumedActivity
```

### How to show fragments
```shell
adb shell dumpsys activity top | grep 'Added Fragments' -A 5
```

## Screenshot/Video
### How to take a screenshot and save it to desktop
```shell
filepath=~/Desktop/$(date +%Y%m%d-%H%M%S).png
adb exec-out screencap -p > ${filepath} && open ${filepath}
```

### How to record a video
```shell
adb shell screenrecord /sdcard/video.mp4
```

### How to save the recorded video to desktop
```shell
# Use a subshell to restore the current directory in the end.
(cd ~/Desktop && adb pull /sdcard/video.mp4 && adb shell rm /sdcard/video.mp4 && open video.mp4)
```

## Emulator
## How to list emulators
```shell
~/Library/Android/sdk/emulator/emulator -list-avds
```

### How to kill the emulator
```shell
adb -s emulator-5554 emu kill
```

### How to start the emulator specifying DNS servers
```shell
~/Library/Android/sdk/emulator/emulator -avd <emulator> -dns-server 1.1.1.1,1.0.0.1
```

### How to start the emulator and close the shell
```shell
start_emulator() {
  adb -s emulator-5554 emu kill # kills the emulator.

  # "&|" is to keep an emulator running even after Zsh is closed.
  # http://zsh.sourceforge.net/Doc/Release/Shell-Builtin-Commands.html
  ~/Library/Android/sdk/emulator/emulator -avd <emulator> &|

  exit # closes Zsh.
}

start_emulator()
```

## Enable/Disable
### How to toggle `Don't keep activities`
```shell
adb shell settings put global always_finish_activities 1 # enable
adb shell settings put global always_finish_activities 0 # disable
```

### How to toggle `Enable demo mode`
```shell
adb shell settings put global sysui_demo_allowed 1 # enable
adb shell settings put global sysui_demo_allowed 0 # disable
```

However, there seems to be no way to toggle `Show demo mode`.

# Predefined colors from Android SDK
```kotlin
val black: Int = ContextCompat.getColor(this, android.R.color.black)
val white: Int = ContextCompat.getColor(this, android.R.color.white)
val transparent: Int = ContextCompat.getColor(this, android.R.color.transparent)
```
```xml
<TextView android:textColor="@android:color/black" />
<TextView android:textColor="@android:color/white" />
<TextView android:textColor="@android:color/transparent" />
```

https://developer.android.com/reference/kotlin/android/R.color

# Predefined string resources from Android SDK
```kotlin
val ok: String = resources.getString(android.R.string.ok) // OK
val cancel: String = resources.getString(android.R.string.cancel) // Cancel
val unknown: String = resources.getString(android.R.string.unknownName) // Unknown
val untitled: String = resources.getString(android.R.string.untitled) // <Untitled>
```
```xml
<TextView android:text="@android:string/ok" /> <!-- OK -->
<TextView android:text="@android:string/cancel" /> <!-- Cancel -->
<TextView android:text="@android:string/unknownName" /> <!-- Unknown -->
<TextView android:text="@android:string/untitled" /> <!-- <Untitled> -->
```

* https://developer.android.com/reference/kotlin/android/R.string
* (Japanese) https://cs.android.com/android/platform/superproject/+/master:frameworks/base/core/res/res/values-ja/strings.xml

# String
## How to convert a String to a Uri and enrich it if necessary
```kotlin
val uri1: Uri = "example.com".toUri() // simpler than Uri.parse(String)
val uri2: Uri = uri1
    .buildUpon()
    .scheme("https")
    .appendPath("path")
    .appendQueryParameter("key1", "value1")
    .build() // https:/example.com/path?key1=value1
```
## How to check if a string contains only digits
```kotlin
val isDigitsOnly: Boolean = "123".isDigitsOnly() // true
```

# How to create a Bundle
```kotlin
val bundle: Bundle = bundleOf("apple" to 100, "orange" to 200) // simpler than Bundle().apply { putInt("apple", 100) ... }
```

# How to encode HTML entities
```kotlin
val encoded: String = "<>&'\"".htmlEncode() // &lt;&gt;&amp;&#39;&quot;
```

# How to decode HTML entities
```kotlin
val decoded: String = String = Html.fromHtml("&lt;&gt;&amp;&#39;&quot;", Html.FROM_HTML_MODE_COMPACT).toString() // <>&'"
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

# How to identify the class that acts up by `minifyenabled true`
Add the following in `proguard-rule.pro`.
```
-keepattributes SourceFile,LineNumberTable
```

# How to close DrawerLayout when the user taps the Back button
```kotlin
/** isOpen and close() require "androidx.drawerlayout:drawerlayout:1.1.0" or higher. */
override fun onBackPressed() {
    if(drawerLayout.isOpen) {
        drawerLayout.close()
        return
    }
    super.onBackPressed()
}
```

# OnBackPressedDispatcher
* `OnBackPressedDispatcher.hasEnabledCallbacks()` returns true if both of the following are met.
  1. There is at least one callback registered with this dispatcher.
  2. Your Activity is being between `onStart()` and `onStop()` (both inclusive). If you override `onStart()` and/or `onStop()`, it is between `super.onStart()` and `super.onStop()`.
    1. Even if you pass a Fragment to `OnBackPressedDispatcher.addCallback(...)`, the Fragment's lifecycle does not affect `OnBackPressedDispatcher.hasEnabledCallbacks()`.
* If your Activitiy overrides `onBackPressed()` but you forget to call `super.onBackPressed()` in it, your callback will never be called.

# How to define a custom view
```kotlin
class MyCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {
    init {
        context.withStyledAttributes(attrs, R.styleable.MyCustomView, defStyle, 0) { // simpler than Theme.obtainStyledAttributes(...)
            // ...
        }
    }
}
```

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
    android:clipToPadding="false"
    android:orientation="horizontal"
    app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    tools:listitem="@layout/item" />
```

```kotlin
with(recyclerView) {
    adapter = myAdapter
    addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
    setHasFixedSize(true) // only if the size is fixed.
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

# CoordinatorLayout
* shares scrolling information between its children.

# ContentProvider
* You work with content providers in one of two scenarios
  * You may want to implement code to access an existing content provider in another application.
  * You may want to create a new content provider in your application to share data with other applications.

# CookieManager
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

# Misc
## Density / dpi / dp / px
* density [(# of 160px) / inch] = dpi / 160
* dp [(1 / 160) inch] = px * (160 / dpi) = px / density

## How a lancher icon's density bucket (mdpi, hdpi,  xhdpi, ...) is chosen
> Android will select the resource at the closest larger density bucket and then scale down.

https://developer.android.com/codelabs/basic-android-kotlin-training-change-app-icon#2

## Round image
Both `RoundedBitmapDrawable` and `ImageFilterView` can draw a round image but neither of them can draw a border around it.

# Activity
[activity.md](markdown/activity.md)

# Build variant / Build type / Flavor dimension
[build-variant.md](markdown/build-variant.md)

# Charles
[charles.md](markdown/charles.md)

# Constriant Layout
[constraint-layout.md](markdown/constraint-layout.md)

# Dagger 2
[dagger2.md](markdown/dagger2.md)

# Deep link
[deep-link.md](markdown/deep-link.md)

# Emulator
[emulator.md](markdown/emulator.md)

# Espresso
[espresso.md](markdown/espresso.md)

# Firebase
[firebase.md](markdown/firebase.md)

# Fragment
[fragment.md](markdown/fragment.md)

# Jetpack Compose
[jetpack-compose.md](markdown/jetpack-compose.md)

# Material Design typography
[material-design-typography.md](markdown/material-design-typography.md)

# Navigation
[navigation.md](markdown/navigation.md)

# RecyclerView
[recyclerview.md](markdown/recyclerview.md)

# Resources
[resources.md](markdown/resources.md)

# ViewModel and LiveData
[viewmodel-livedata.md](markdown/viewmodel-livedata.md)

# ViewPager
[viewpager.md](markdown/viewpager.md)

# Use A rather than B for simplicity or clarity
A|B|Note
--|--|--
`String.toUri()`|`Uri.parse(...)`
`bundleOf(...)`|`Bundle().apply { ... }`
`CharSequence.isDigitsOnly()`|(old-school ways to check if a string contains only digits)
`Context.withStyledAttributes(...)`|`obtainStyledAttributes(...)`
`fragment.parentFragmentManager`|`fragment.requireActivity().supportFragmentManager`
`intent.getStringExtra("foo")`|`intent.extras?.getString("foo")`|The same goes for other types.
`requireViewById(...)`|`findViewById(...)`|in case you are not authorized to use view binding

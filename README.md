# Official style guides
* https://developer.android.com/kotlin/style-guide
* https://kotlinlang.org/docs/reference/coding-conventions.html

# Official best practices
* https://developer.android.com/kotlin/coroutines/coroutines-best-practices
* https://developer.android.com/training/data-storage/use-cases
* https://github.com/androidx/androidx/blob/androidx-main/compose/docs/compose-api-guidelines.md

# Gradle
https://docs.gradle.org/current/userguide/viewing_debugging_dependencies.html

## How to show the root project's dependencies
```shell
# shows as a tree
./gradlew dependencies

# shows as a list
./gradlew androidDependencies

# e.g. shows Jetpack Compose dependencies.
./gradlew androidDependencies | grep compose | sort | uniq

# Run the following for more details.
./gradlew help --task dependencies
./gradlew help --task androidDependencies
```

## How to show `buildSrc`'s dependencies
```shell
# shows as a tree
cd buildSrc
../gradlew dependencies

# Run the following for more details.
./gradlew help --task dependencies
```

## How to show the project `app`'s dependencies
```shell
# shows as a tree
./gradlew app:dependencies --configuration <buildVariant>CompileClasspath
./gradlew app:dependencies --configuration debugCompileClasspath

# shows as a list
./gradlew app:androidDependencies
./gradlew app:dependencies --configuration <buildVariant>CompileClasspath | grep "^+---" | sort
./gradlew app:dependencies --configuration debugCompileClasspath | grep "^+---" | sort

# for more details
./gradlew help --task app:dependencies
./gradlew help --task app:androidDependencies
```

## How to show projects on which the project `app` depends
```shell
./gradlew app:dependencies --configuration implementation | grep '+--- project' | sort
```

## How to show where a dependency in the project `app` comes from
```shell
./gradlew app:dependencyInsight --configuration <buildVariant>CompileClasspath --dependency <group>:<name>
./gradlew app:dependencyInsight --configuration debugCompileClasspath --dependency org.jetbrains.kotlin:kotlin-stdlib

# for more details
./gradlew help --task app:dependencyInsight
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
./gradlew <module>:test<buildVariant>UnitTest
```

## How to run an instrumented unit test for a build variant and a module
```shell
./gradlew <module>:connected<buildVariant>AndroidTest
```

## Meaning of `task clean` in project-level `build.gradle`
The following custom task in the project-level `build.gradle` is to delete the project-level `build` directory when clicking the menu bar > `Build` > `Clean Project`.
```gradle
task clean(type: Delete) {
    delete rootProject.buildDir
}
```

# Predefined Color
## Predefined ColorInt
https://developer.android.com/reference/kotlin/android/graphics/Color

## Predefined ColorRes from Android SDK
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

# Release build
## How to debug a release build
You can debug a release build only if you set both `minifyenabled false` and `debuggable false`.
```gradle
// app/build.gradle
android {
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'

            debuggable true
            signingConfig signingConfigs.debug
        }
    }
}
```
```gradle
// app/build.gradle.kts
android {
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")

            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}
```

## How to identify the class that acts up by `minifyenabled true`
Add the following to `proguard-rule.pro`.
```shell
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
## ViewModel and LiveData
```kotlin
class MyViewModel : ViewModel() {
    private val _something = MutableLiveData<Something>()    
    // Google codelabs tend to use a getter as below but it is redundant.
    // val something: LiveData<Something> get() = _something
    val something: LiveData<Something> = _something

    fun setSomething(something: Something) {
        _something.value = something
    }
}
```

## xmlns
```xlm
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
```

## ConstraintLayout
```xml
<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="0dp"
    android:layout_height="0dp"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

### How to programmatically update constraints
```kotlin
myContentLayout.updateLayoutParams<ConstraintLayout.LayoutParams> {
    height = ConstraintSet.WRAP_CONTENT
    height = ConstraintSet.MATCH_CONSTRAINT // equivalent to 0dp
    height = ViewGroup.LayoutParams.MATCH_PARENT

    topToTop  = ConstraintSet.UNSET
    topToTop = ConstraintSet.PARENT_ID
    topToBottom = myToolbar.id // safer than R.id.my_toolbar
}
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
<!-- android:orientation is unnecessary if you want it vertical. -->
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
    adapter = myAdapter.apply {
        registerAdapterDataObserver(
            object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    scrollToPosition(0) // RecyclerView#scrollToPosition(0)
                }
            }
        )
    }
    addItemDecoration(DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation))
    setHasFixedSize(true) // only if the size is fixed.
    itemAnimator = null
    myLiveData.observe(viewLifecycleOwner) {
        with(adapter as MyAdapter) {
            submitList(it)
            // notifyDataSetChanged() // Uncomment this only if new data is not reflected on UI after submitList(...).
        }
    }
}
```

## TextView
```xml
<!-- "android:gravity" is unnecessary if you don't use app:drawableStartCompat and so on. -->
<TextView
    android:id="@+id/text_view"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:ellipsize="end"
    android:gravity="center"
    android:singleLine="true"
    tools:drawableEndCompat="@tools:sample/avatars"
    tools:drawableStartCompat="@tools:sample/avatars"
    tools:text="@tools:sample/lorem/random" />
```

## ImageView
```xml
<ImageView
    android:id="@+id/image_view"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:contentDescription="@null"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    tools:src="@tools:sample/avatars" />
```

## Divider
```xml
<View
    android:id="@+id/divider"
    android:layout_width="0dp"
    android:layout_height="1dp"
    android:background="@android:color/darker_gray"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent" />
```
Instead, use [MaterialDivider](https://material.io/components/dividers/android) when it becomes available.

## EditText
`android:hint`, `android:importantForAutofill`, and `android:inputType` are to suppress a warning.
```xml
<EditText
    android:id="@+id/edit_text"
    android:layout_width="wrap_content"
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
    android:layout_width="0dp"
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

# Activity
[activity.md](markdown/activity.md)

# Android Studio
[android-studio.md](markdown/android-studio.md)

# Build variant / Build type / Flavor dimension
[build-variant.md](markdown/build-variant.md)

# Charles
[charles.md](markdown/charles.md)

# ConstriantLayout
[constraint-layout.md](markdown/constraint-layout.md)

# Dagger
[dagger.md](markdown/dagger.md)

# Deep link
[deep-link.md](markdown/deep-link.md)

# Android Debug Bridge (adb) and emulators
[adb-emulator.md](markdown/adb-emulator.md)

# Espresso
[espresso.md](markdown/espresso.md)

# Firebase Cloud Messaging
[fcm.md](markdown/fcm.md)

# Fragment
[fragment.md](markdown/fragment.md)

# Jetpack Compose
[jetpack-compose.md](markdown/jetpack-compose.md)

# Material Design
[material-design.md](markdown/material-design.md)

# Misc
[misc.md](markdown/misc.md)

# Navigation
[navigation.md](markdown/navigation.md)

# RecyclerView
[recyclerview.md](markdown/recyclerview.md)

# Resources
[resources.md](markdown/resources.md)

# Glossary / Vocabulary / Terminology
[terminology.md](markdown/terminology.md)

# Lifecycle/ViewModel/LiveData
[lifecycle-viewmodel-livedata.md](markdown/lifecycle-viewmodel-livedata.md)

# ViewPager
[viewpager.md](markdown/viewpager.md)

# Use A rather than B for simplicity or clarity
A|B|Note
--|--|--
`String.toUri()`|`Uri.parse(...)`
`bundleOf(...)`|`Bundle().apply { ... }`
`CharSequence.isDigitsOnly()`|(old-school ways to check if a string contains only digits)
`Context.withStyledAttributes(...)`|`obtainStyledAttributes(...)`
`intent.getStringExtra("foo")`|`intent.extras?.getString("foo")`|The same goes for other types.
`requireViewById(...)`|`findViewById(...)`|in case you are not authorized to use view binding

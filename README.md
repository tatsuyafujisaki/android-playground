# Style guides
- https://developer.android.com/kotlin/style-guide
- https://kotlinlang.org/docs/coding-conventions.html
- Use case naming conventions (in a guide)
  - https://developer.android.com/topic/architecture/domain-layer#conventions

# Best practices
- https://developer.android.com/jetpack/compose/performance/bestpractices
- https://developer.android.com/kotlin/coroutines/coroutines-best-practices
- https://developer.android.com/topic/architecture/recommendations
- https://developer.android.com/topic/libraries/architecture/viewmodel#best-practices
- https://developer.android.com/training/data-storage/use-cases

## How to show the root project's dependencies

```shell
# shows as a tree
./gradlew dependencies

# shows as a list
./gradlew androidDependencies
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

## How to build a debug APK
```shell
# Build with the product flavor "foo" and the build type "debug"
./gradlew assembleFooDebug
```
```shell
# Build with all the build variants
./gradlew assemble
```
https://developer.android.com/build/building-cmdline#DebugMode

## How to install a debug APK
```shell
# Install with the product flavor "foo" and the build type "debug"
./gradlew installFooDebug
```
```shell
# Install with all the build variants
./gradlew install
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
# https://docs.gradle.org/current/userguide/command_line_interface.html#sec:name_abbreviation
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

The following custom task in the project-level `build.gradle` is to delete the project-level `build`
directory when clicking the menu bar > `Build` > `Clean Project`.

```gradle
task clean(type: Delete) {
    delete rootProject.buildDir
}
```

# Predefined color resources in Android SDK

https://developer.android.com/reference/kotlin/android/R.color

# Predefined string resources in Android SDK

https://developer.android.com/reference/kotlin/android/R.string

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

# WebViewClient
## How to get `WebViewClient.onReceivedSslError()` called
Open https://httpforever.com

## How to get `WebViewClient.onReceivedHttpError()` called
Open https://httpstat.us/404

# How to encode HTML entities

```kotlin
val encoded: String = "<>&'\"".htmlEncode() // &lt;&gt;&amp;&#39;&quot;
```

# How to decode HTML entities

```kotlin
val decoded: String = String =
    Html.fromHtml("&lt;&gt;&amp;&#39;&quot;", Html.FROM_HTML_MODE_COMPACT).toString() // <>&'"
```

[String.htmlEncode](https://developer.android.com/reference/kotlin/androidx/core/text/package-summary#htmlencode)
is a part of the Core KTX library and is syntactic sugar
for [TextUtils.htmlEncode](https://developer.android.com/reference/kotlin/android/text/TextUtils#htmlEncode(kotlin.String))
.

# How to get a query parameter

```kotlin
val uri: Uri = "https://example.com?key1=value1&key2=value2".toUri()
val value: String = uri.getQueryParameter("key2") // value2
```

# How to convert AARRGGBB as Int to Color

```kotlin
val color: Color = Color.valueOf(0x11223344)
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
    if (drawerLayout.isOpen) {
        drawerLayout.close()
        return
    }
    super.onBackPressed()
}
```

# OnBackPressedDispatcher

* `OnBackPressedDispatcher.hasEnabledCallbacks()` returns true if both of the following are met.
    1. There is at least one callback registered with this dispatcher.
    2. Your Activity is being between `onStart()` and `onStop()` (both inclusive). If you
       override `onStart()` and/or `onStop()`, it is between `super.onStart()` and `super.onStop()`.
    1. Even if you pass a Fragment to `OnBackPressedDispatcher.addCallback(...)`, the Fragment's
       lifecycle does not affect `OnBackPressedDispatcher.hasEnabledCallbacks()`.
* If your Activitiy overrides `onBackPressed()` but you forget to call `super.onBackPressed()` in
  it, your callback will never be called.

# How to define a custom view

```kotlin
class MyCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {
    init {
        context.withStyledAttributes(
            attrs,
            R.styleable.MyCustomView,
            defStyle,
            0
        ) { // simpler than Theme.obtainStyledAttributes(...)
            // ...
        }
    }
}
```

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

# `adb` and `emulator` commands

[adb-emulator.md](markdown/adb-emulator.md)

# Espresso

[espresso.md](markdown/espresso.md)

# Firebase Cloud Messaging

[fcm.md](markdown/fcm.md)

# Fragment

[fragment.md](markdown/fragment.md)

# Hilt

[hilt.md](markdown/hilt.md)

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

# Retrofit

[retrofit.md](markdown/retrofit.md)

# Glossary / Vocabulary / Terminology

[terminology.md](markdown/terminology.md)

# Lifecycle/ViewModel/LiveData

[lifecycle-viewmodel-livedata.md](markdown/lifecycle-viewmodel-livedata.md)

# ViewPager

[viewpager.md](markdown/viewpager.md)

# UI template

[ui-template.md](markdown/ui-template.md)

# Use A rather than B for simplicity (except for Jetpack Compose)

A|B|Note
--|--|--
`Activity.resources`<br>`Fragment.resources`<br>`View.resources`
|`Activity.context.resources`<br>`Fragment.requireContext().resources`<br>`View.context.resources`
`Activity.getString(...)`<br>`Fragment.getString(...)`
|`Activity.resources.getString(...)`<br>`Fragment.requireContext().getString(...)`<br>`Fragment.resources.getString(...)`
`String.toUri()`|`Uri.parse(...)`
`bundleOf(...)`|`Bundle().apply { ... }`
`CharSequence.isDigitsOnly()`|(old-school ways to check if a string contains only digits)
`Context.withStyledAttributes(...)`|`obtainStyledAttributes(...)`
`Intent.getStringExtra("foo")`|`Intent.extras?.getString("foo")`|The same goes for other types.
`view` in `fun onViewCreated(view: View)`|`requireView()` in `fun onViewCreated(view: View)`
`requireViewById(...)`|`findViewById(...)`|in case you are not authorized to use view binding

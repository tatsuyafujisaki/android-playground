# Style guides
- https://developer.android.com/kotlin/style-guide
- https://kotlinlang.org/docs/coding-conventions.html

# Best practices
- https://developer.android.com/develop/ui/compose/performance/bestpractices
- https://developer.android.com/develop/ui/compose/state-hoisting#best-practice
- https://developer.android.com/guide/components/activities/parcelables-and-bundles (says "This page provides recommendations and best practices for using Parcelable and Bundle objects.")
- https://developer.android.com/guide/navigation/custom-back/predictive-back-gesture#best-practices
- https://developer.android.com/kotlin/coroutines/coroutines-best-practices
- https://developer.android.com/topic/architecture/recommendations
- https://developer.android.com/topic/libraries/architecture/lifecycle#lc-bp
- https://developer.android.com/topic/libraries/architecture/viewmodel#best-practices
- https://developer.android.com/training/data-storage/use-cases

## How to show the root project's dependencies

```shell
# shows as a tree
./gradlew dependencies

# shows as a list
./gradlew androidDependencies
```

## How to show the `app` module (aka project)'s dependencies

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

## How to show modules (aka projects) on which the `app` module (aka project) depends

```shell
./gradlew app:dependencies --configuration implementation | grep '+--- project' | sort
```

## How to show where a dependency in the `app` module (aka project) comes from

```shell
./gradlew app:dependencyInsight --configuration <buildVariant>CompileClasspath --dependency <group>:<name>
./gradlew app:dependencyInsight --configuration debugCompileClasspath --dependency org.jetbrains.kotlin:kotlin-stdlib

# for more details
./gradlew help --task app:dependencyInsight
```

## How to build a debug APK
```shell
# Build the app module with the product flavor "foo" and the build type "debug"
./gradlew app:assembleFooDebug
```
```shell
# Build all the modules with all the build variants
./gradlew assemble
```
https://developer.android.com/build/building-cmdline#DebugMode

## How to install a debug APK
```shell
# Install the app module with the product flavor "foo" and the build type "debug"
./gradlew app:installFooDebug
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
    1. Your Activity is being between `onStart()` and `onStop()` (both inclusive). If you
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

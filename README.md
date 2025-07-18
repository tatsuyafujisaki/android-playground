# Style guides
- https://developer.android.com/kotlin/style-guide
- https://kotlinlang.org/docs/coding-conventions.html

# How to show a dependency tree
```shell
./gradlew [module:]dependencies
```

# How to build an Android app
```shell
./gradlew assemble[buildVariant]
```

# How to install an Android app
```shell
./gradlew install[buildVariant]
```

# Testing
## How to run a local unit test
```shell
./gradlew [module:]test[buildVariant]UnitTest
```

## How to run an instrumented test
```shell
./gradlew [module:]connected[buildVariant]AndroidTest
```

# Meaning of `task clean` in project-level `build.gradle`

The following custom task in the project-level `build.gradle` is to delete the project-level `build`
directory when clicking the menu bar > `Build` > `Clean Project`.

```gradle
task clean(type: Delete) {
    delete rootProject.buildDir
}
```

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
* If your Activity overrides `onBackPressed()` but you forget to call `super.onBackPressed()` in
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

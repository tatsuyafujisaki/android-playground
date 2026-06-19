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

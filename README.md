# Shortcuts
* Quick documentation ... `Control`+`J`
* Show type info ... `Control`+`Shift`+`P`

# Best practices
* Use `bundleOf()` in Core KTX to create a Bundle.
* Use `CharSequence.isDigitsOnly()` in Core KTX to check if a string contains only digits.
  * FYI, `CharSequence.isDigitsOnly()` returns true for an empty string.
* Use `String.toUri()` in Core KTX rather than `Uri.parse(...)` for simplicity.

# How to decode HTML entities
```kotlin
val decoded = Html.fromHtml("&amp;&gt;&lt;&nbsp;&quot;", Html.FROM_HTML_MODE_COMPACT).toString() // "&>< ""
```

# How to get a query parameter
```kotlin
val url = "https://example.com?key1=value1&key2=value2"

// String.toUri() is from Core KTX.
val value2 = url.toUri().getQueryParameter("key2") // value2
```

# How to run a unit test or an instrumented unit test from Terminal
## How to run a local unit test
```shell
./gradlew test
```

## How to run an instrumented unit test
```shell
./gradlew connectedAndroidTest
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

# How to remove comments
1. Command+Shift+F in Android Studio.
2. Check `Regex`.
3. Put one of the following regexes.
4. Click `Replace All`.

## Regex to match all the comments (/* \*/ including /** \*/)
```
/\*([\S\s]+?)\*/
```

## Regex to match copyright comments (/* \*/ including /** \*/)
```
/\*([\S\s]+?Copyright[\S\s]+?)\*/
```

## Regex to match copyright comments in XML
```
<!--([\S\s]+?Copyright[\S\s]+?)-->
```

## References
https://developer.android.com/studio/test/command-line

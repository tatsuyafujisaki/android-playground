# Note
* density [(# of 160px) / inch] = dpi / 160
* dp [(1 / 160) inch] = px * (160 / dpi) = px / density

# Shortcuts
* Quick documentation ... `Control`+`J`
* Show type info ... `Control`+`Shift`+`P`

# Best practices
* Use `<androidx.fragment.app.FragmentContainerView>` rather than `<FrameLayout>` or `<fragment>`.
  * https://www.youtube.com/watch?v=RS1IACnZLy4&t=537

## Core KTX
* Use `bundleOf()` to create a Bundle.
* Use `CharSequence.isDigitsOnly()` to check if a string contains only digits.
  * Note that `CharSequence.isDigitsOnly()` returns true for an empty string.
* Use `String.toUri()` rather than `Uri.parse(...)` for simplicity.

### References
https://android.github.io/android-ktx/core-ktx/

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
keepattributes SourceFile,LineNumberTable
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

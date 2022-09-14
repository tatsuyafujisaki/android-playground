# How to integarate Compose with the view-based UI
* If you want to have Compose at the root level in an Activity, you can use `setContent` in Kotlin and don't need XML.
  * https://youtu.be/PjQdFmiDgwk?t=89
* If you want to have Compose NOT at the root level in a Fragment, you can use `<ComposeView>` in XML.
  * https://youtu.be/PjQdFmiDgwk?t=103
* If you want to have Compose at the root level in a Fragment, you can use `ComposeView` in Kotlin and don't need XML.
  * https://youtu.be/PjQdFmiDgwk?t=147
  * If you use multiple `ComposeView`, don't forget to assign a unique ID to each `ComposeView`.

# Row / Column / Box
* Row = Horizontal LinearLayout
* Column = Vertical LinearLayout
* Box = FrameLayout

# Stateful compose / Stateless compose / State hosting
By state hosting, a stateful compose becomes a stateless compose.

# LaunchedEffect
LaunchedEffect will be canceled when either of the two is met:
* LaunchedEffect leaves the composition.
* LaunchedEffect is recomposed with different keys.

# Difference between `assertExists()` and `assertIsDisplayed()`
&nbsp;|`assertExists()`|`assertIsNotDisplayed()`
--|--|--
Modifier.size(0.dp)|true|false
Modifier.size(1.dp)|true|true
Modifier.alpha(0.dp)|true|true
The nodes exists below the screen and you need to scroll down to show it|true|false

# Illustration of `Modifier.firstBaselineToTop(...)`
![](https://user-images.githubusercontent.com/1838962/128594836-c9f2f627-4749-46f0-97db-c71d084e4620.png)

https://developer.android.com/codelabs/jetpack-compose-layouts#6

# How to have Compose in the View system
* If you want to have Compose at the root level in an Activity, you can use `setContent` in Kotlin and don't need XML.
https://youtu.be/PjQdFmiDgwk?t=89

* If you want to have Compose NOT at the root level in a Fragment, you can use `<ComposeView>` in XML.
https://youtu.be/PjQdFmiDgwk?t=103

* If you want to have Compose at the root level in a Fragment, you can use `ComposeView` in Kotlin and don't need XML.
https://youtu.be/PjQdFmiDgwk?t=147

# Row / Column / Box
* Row = Horizontal LinearLayout
* Column = Vertical LinearLayout
* Box = FrameLayout

# Stateful compose / Stateless compose / State hosting
By state hosting, a stateful compose becomes a stateless compose.

# Misc
* > Note: By convention, the modifier is specified as the first optional parameter of a function. This enables you to specify a modifier on a composable without having to name all parameters.
  * https://developer.android.com/codelabs/jetpack-compose-layouts#2

## Illustration of `Modifier.firstBaselineToTop(...)`
![](https://user-images.githubusercontent.com/1838962/128594836-c9f2f627-4749-46f0-97db-c71d084e4620.png)

https://developer.android.com/codelabs/jetpack-compose-layouts#6

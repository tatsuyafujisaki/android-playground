# Modifier
> Composables that accept a Modifier as a parameter to be applied to the whole component represented by the composable function should name the parameter modifier and assign the parameter a default value of Modifier. It should appear as the first optional parameter in the parameter list
- https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier
- https://twitter.github.io/compose-rules/rules/#modifiers-should-have-default-parameters

> Note: It is a best practice to pass a modifier to every composable and set it to a default value.

- https://developer.android.com/codelabs/basic-android-kotlin-compose-training-add-scrollable-list#3

# Content padding
- is a padding for an entire list. The padding also scrolls along with the content.
- https://developer.android.com/jetpack/compose/lists
- https://youtu.be/1ANt65eoNhQ?t=166

# How to integarate Compose with the view-based UI
* If you want to have Compose at the root level in an Activity, you can use `setContent` in Kotlin and don't need XML.
  * https://youtu.be/PjQdFmiDgwk?t=89
* If you want to have Compose NOT at the root level in a Fragment, you can use `<ComposeView>` in XML.
  * https://youtu.be/PjQdFmiDgwk?t=103
* If you want to have Compose at the root level in a Fragment, you can use `ComposeView` in Kotlin and don't need XML.
  * https://youtu.be/PjQdFmiDgwk?t=147
  * If you use multiple `ComposeView`, don't forget to assign a unique ID to each `ComposeView`.

# Difference between `assertExists()` and `assertIsDisplayed()`
&nbsp;|`assertExists()`|`assertIsDisplayed()`
--|--|--
Modifier.size(0.dp)|true|false
Modifier.size(1.dp)|true|true
Modifier.alpha(0.dp)|true|true
The nodes exists below the screen and you need to scroll down to show it|true|false

# Recommended to specify a `key` in `items` of `LazyColumn`
Benefits of specifying a `key` in `items` of `LazyColumn` are below.
- You can keep the scroll position when the Activity is recreated, or even when you scroll away and scroll back.
- Jetpack Compose avoids unnecessarily recomposing the entire list when an item is removed.
- https://developer.android.com/jetpack/compose/lists#item-keys
- https://youtu.be/PMMY23F0CFg?t=2061

# Illustration of `Modifier.firstBaselineToTop(...)`
![](https://user-images.githubusercontent.com/1838962/128594836-c9f2f627-4749-46f0-97db-c71d084e4620.png)

https://developer.android.com/codelabs/jetpack-compose-layouts#6

# Use A rather than B for simplicity
- Use content spacing between items rather than each item having padding.
    - https://developer.android.com/jetpack/compose/lists#content-spacing

# Modifier
> Composables that accept a Modifier as a parameter to be applied to the whole component represented by the composable function should name the parameter modifier and assign the parameter a default value of Modifier. It should appear as the first optional parameter in the parameter list
- https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier
- https://twitter.github.io/compose-rules/rules/#modifiers-should-have-default-parameters

> Note: It is a best practice to pass a modifier to every composable and set it to a default value.

- https://developer.android.com/codelabs/basic-android-kotlin-compose-training-add-scrollable-list#3

> In Compose, it is best practice to pass a modifier parameter to composable functions.

- https://developer.android.com/codelabs/basic-android-kotlin-compose-material-theming#3

# Content padding
- is a padding for an entire list. The padding also scrolls along with the content.
- https://developer.android.com/jetpack/compose/lists
- https://youtu.be/1ANt65eoNhQ?t=166

# LazyColumn
Google recommends specifying a `key` in `items` in LazyColumn. The benefits are listed below.
- You can keep the scroll position when the Activity is recreated, or even when you scroll away and scroll back.
- Jetpack Compose avoids unnecessarily recomposing the entire list when an item is removed.
- https://developer.android.com/jetpack/compose/lists#item-keys
- https://youtu.be/PMMY23F0CFg?t=2061

# Pager in Compose
## How to apply scroll effects
Here is the explanation of the code snippet inside the following URL.

```kotlin
val pagerState = rememberPagerState()
HorizontalPager(pageCount = 4, state = pagerState) { page ->
    Card(
        Modifier
            .size(200.dp)
            .graphicsLayer {
                // Calculate the absolute offset for the current page from the
                // scroll position. We use the absolute value which allows us to mirror
                // any effects for both directions
                val pageOffset = (
                    (pagerState.currentPage - page) + pagerState
                        .currentPageOffsetFraction
                    ).absoluteValue

                // We animate the alpha, between 50% and 100%
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
    ) {
        // Card content
    }
}
```

- [PagerState.currentPage](https://developer.android.com/reference/kotlin/androidx/compose/foundation/pager/PagerState#currentPage())
  - is the page that sits closest to the snapped position.
  - may or may not be the current page.
- [PagerState.currentPageOffsetFraction](https://developer.android.com/reference/kotlin/androidx/compose/foundation/pager/PagerState#currentPageOffsetFraction())
  - indicates how far the current page is to the snapped position.
    - is between -0.0 and -0.5 if the current page has space in the left.
    - is between 0.0 and 0.5 if the current page has space in the right.
    - never goes beyond -0.5 or 0.5, because if it does, the page is too far from the snapped position to be called the current page.
- `page` in `pageContent` of `HorizontalPager`
  - is the page being processed.
  - may or may not be the current page.

https://developer.android.com/jetpack/compose/layouts/pager#scroll-effects

# Unit test
## Difference between `assertExists()` and `assertIsDisplayed()`
&nbsp;|`assertExists()`|`assertIsDisplayed()`
--|--|--
Modifier.size(0.dp)|true|false
Modifier.size(1.dp)|true|true
Modifier.alpha(0.dp)|true|true
The nodes exists below the screen and you need to scroll down to show it|true|false

# Use A rather than B for simplicity
- Use content spacing between items rather than each item having padding.
    - https://developer.android.com/jetpack/compose/lists#content-spacing

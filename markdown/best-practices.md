# Official best practices
- https://developer.android.com/develop/ui/compose/performance/bestpractices
- https://developer.android.com/develop/ui/compose/state-hoisting#best-practice
- https://developer.android.com/guide/components/activities/parcelables-and-bundles
  - > This page provides recommendations and best practices for using Parcelable and Bundle objects.
- https://developer.android.com/guide/navigation/custom-back/predictive-back-gesture#best-practices
- https://developer.android.com/kotlin/coroutines/coroutines-best-practices
- https://developer.android.com/topic/architecture/recommendations
- https://developer.android.com/topic/libraries/architecture/lifecycle#lc-bp
- https://developer.android.com/topic/libraries/architecture/viewmodel#best-practices
- https://developer.android.com/training/data-storage/use-cases

## Miscellaneous

> Do not intercept back at the root (for example, MainActivity.kt)

https://developer.android.com/codelabs/predictive-back#4

# Personal best practices
## Personal best practices for Gradle and JVM

[agp-and-gradle-and-jdk-and-buil-error.md](agp-and-gradle-and-jdk-and-buil-error.md)

## Personal best practices for Jetpack Compose

[jetpack-compose.md](jetpack-compose.md)

## Use A rather than B for simplicity (except for Jetpack Compose)

A|B|Note
--|--|--
`Activity.resources`<br>`Fragment.resources`<br>`View.resources`|`Activity.context.resources`<br>`Fragment.requireContext().resources`<br>`View.context.resources`
`Activity.getString(...)`<br>`Fragment.getString(...)`|`Activity.resources.getString(...)`<br>`Fragment.requireContext().getString(...)`<br>`Fragment.resources.getString(...)`
`String.toUri()`|`Uri.parse(...)`
`bundleOf(...)`|`Bundle().apply { ... }`
`CharSequence.isDigitsOnly()`|(old-school ways to check if a string contains only digits)
`Context.withStyledAttributes(...)`|`obtainStyledAttributes(...)`
`Intent.getStringExtra("foo")`|`Intent.extras?.getString("foo")`|The same goes for other types.
`view` in `fun onViewCreated(view: View)`|`requireView()` in `fun onViewCreated(view: View)`
`Uri.encode(url)`|`URLEncoder.encode(url, Charsets.UTF_8.name())`

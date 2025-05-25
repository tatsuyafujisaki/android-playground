# Miscellaneous

> Do not intercept back at the root (for example, MainActivity.kt)

https://developer.android.com/codelabs/predictive-back#4

# Use A rather than B for simplicity (except for Jetpack Compose)

A|B|Note
--|--|--
`Activity.resources`<br>`Fragment.resources`<br>`View.resources`
|`Activity.context.resources`<br>`Fragment.requireContext().resources`<br>`View.context.resources`
`Activity.getString(...)`<br>`Fragment.getString(...)`
|`Activity.resources.getString(...)`<br>`Fragment.requireContext().getString(...)`<br>`Fragment.resources.getString(...)`
`String.toUri()`|`Uri.parse(...)`
`bundleOf(...)`|`Bundle().apply { ... }`
`CharSequence.isDigitsOnly()`|(old-school ways to check if a string contains only digits)
`Context.withStyledAttributes(...)`|`obtainStyledAttributes(...)`
`Intent.getStringExtra("foo")`|`Intent.extras?.getString("foo")`|The same goes for other types.
`view` in `fun onViewCreated(view: View)`|`requireView()` in `fun onViewCreated(view: View)`
`Uri.encode(url)`|`URLEncoder.encode(url, Charsets.UTF_8.name())`

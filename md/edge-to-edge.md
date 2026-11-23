- This app sets `minSdk` to 37, so edge-to-edge is always enforced. You don't need to call [enableEdgeToEdge()](https://developer.android.com/reference/kotlin/androidx/activity/package-summary#(androidx.activity.ComponentActivity).enableEdgeToEdge(androidx.activity.SystemBarStyle,androidx.activity.SystemBarStyle)) to turn it on.
- You can't opt out of edge-to-edge with `R.attr#windowOptOutEdgeToEdgeEnforcement`.

> Important: Edge-to-edge is enforced on Android 15 (API level 35) and higher once your app targets SDK 35.

https://developer.android.com/develop/ui/views/layout/edge-to-edge

> - If your app targets Android 16 (API level 36) and is running on an Android 16 device, `R.attr#windowOptOutEdgeToEdgeEnforcement` is disabled.

https://developer.android.com/about/versions/16/behavior-changes-16#edge-to-edge

> By default, Scaffold provides insets as parameter paddingValues for you to consume and use. Scaffold does not apply the insets to content; this responsibility is yours.

https://developer.android.com/develop/ui/compose/system/material-insets#scaffold

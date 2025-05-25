# Edge-to-edge
- If `targetSdk = 35` or later, edge-to-edge is enabled by default on Android 15 or later without calling [enableEdgeToEdge()](https://developer.android.com/reference/kotlin/androidx/activity/package-summary#(androidx.activity.ComponentActivity).enableEdgeToEdge(androidx.activity.SystemBarStyle,androidx.activity.SystemBarStyle)).
- In Android 14 or earlier, `enableEdgeToEdge()` must be called in each Activity to enable edge-to-edge.

> - If your app targets Android 16 (API level 36) and is running on an Android 15 device, `R.attr#windowOptOutEdgeToEdgeEnforcement` continues to work.
> - If your app targets Android 16 (API level 36) and is running on an Android 16 device, `R.attr#windowOptOutEdgeToEdgeEnforcement` is disabled.

https://developer.android.com/about/versions/16/behavior-changes-16#edge-to-edge

> By default, Scaffold provides insets as parameter paddingValues for you to consume and use. Scaffold does not apply the insets to content; this responsibility is yours.

https://developer.android.com/develop/ui/compose/system/material-insets#scaffold

> Important: Edge-to-edge is enforced on Android 15 (API level 35) and higher once your app targets SDK 35.

https://developer.android.com/develop/ui/views/layout/edge-to-edge

> If your app targets SDK 35 or later, edge-to-edge is automatically enabled for Android 15 devices or later.
> To enable edge-to-edge on previous Android versions, do the following:
>
> ...
>
> Manually enable edge-to-edge by calling enableEdgeToEdge in onCreate of your Activity. It should be called before setContentView.

https://developer.android.com/develop/ui/views/layout/edge-to-edge

> Note: SociaLite has one activity. If your app has more than one activity, enableEdgeToEdge should be called on each activity.

https://developer.android.com/codelabs/edge-to-edge#3

# Predictable back gesture

&nbsp;|Android 12|Android 13|Android 14|Android 15|Note
--|--|--|--|--|--
(Application-level) Predictive back gesture|Unavailable|Available but disabled by default|(same as ⬅️)|(same as ⬅️)
Activity-level predictive back gsture|Unavailable|Unavailable|Available|Available|> Starting with Android 14, the `android:enableOnBackInvokedCallback` flag lets you opt-in to predictive system animations at the activity level.<br>https://developer.android.com/guide/navigation/custom-back/predictive-back-gesture#opt-activity-level
Settings > System > Developer options > Predictive back animations|Does not exist|Exists|Exists|Does not exist|> Starting with Android 15, the developer option for predictive back animations is no longer available.<br>https://developer.android.com/guide/navigation/custom-back/predictive-back-gesture#dev-option<br><br>> With Android 15, system animations such as back-to-home, cross-task, and cross-activity are no longer behind the developer option.<br>https://developer.android.com/guide/navigation/custom-back/predictive-back-gesture#dev-option

# Deep link
* is an intent filter
  * https://developer.android.com/training/app-links/verify-site-associations#the-difference
* associate a URL with the activity you want to open.
  * https://developer.android.com/codelabs/android-navigation#10

## Explicit deep link
* specifies a destination ID.
* uses a PendingIntent.
  * PendingIntent is a wrapper of an Intent, which lets another application run the Intent.
* clears the back stack, and pushes the following destinations in sequence:
  * the startDestination of the navigation graph
  * parent activity destinations
    * https://developer.android.com/codelabs/android-navigation#9
  * other startDestination(s) if navigation graphs are nested
  * the specified destination (goal)
* is a notification or an app widget, for example.

## Implicit deep link
* specifies a URI.
* A URI without a scheme matches both http and https.
  * e.g. `www.example.com/foo` matches both `https://www.example.com/foo` and `http://www.example.com/foo`.
  * `.*` matches 0 or more characters.
  * Placeholders like `http://www.example.com/users/{id}?myarg={myarg}` are available.
  * `{id}` can be got as `arguments?.getString("id")`, or as `arguments?.getInt("id")` if SafeArgs is used as follows.
```xml
<fragment>
  <deepLink
    android:id="@+id/deepLink"
    app:uri="example.com/users/{id}" />
    <argument
      android:name="id"
      app:argType="integer" />
</fragment>
```
* clears the back stack like explicit deep linking if the implicit Intent was launched with the Intent.FLAG_ACTIVITY_NEW_TASK flag.
* does NOT clear the back stack unlike explicit deep linking unless the implicit Intent was launched with the `Intent.FLAG_ACTIVITY_NEW_TASK` flag.
`<nav-graph>` in `<activity>` in `AndroidManifest.xml` is required to enable implicit deep linking from outside the app.
  * At compile time, `<nav-graph>` generates `<intent-filter>` elements for all the deep links.

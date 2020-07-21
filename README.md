# Best practices
## Core KTX
* Use `String.toUri()` rather than `Uri.parse(...)` for simplicity.
* Use `CharSequence.isDigitsOnly()` to check if a string contains only digits.
* Use `Context.withStyledAttributes(...)` rather than `obtainStyledAttributes(...)` for simplicity.

# How to create a Bundle
```kotlin
val bundle: Bundle = bundleOf("apple" to 123, "orange" to 456)
```

# How to get a query parameter
```kotlin
val uri: Uri = "https://example.com?key1=value1&key2=value2".toUri()
val value: String = uri.getQueryParameter("key2") // value2
```

# How to convert AARRGGBB as Int to Color
```kotlin
val color: Color = Color.valueOf(0x11223344)
```

# How to change the color of a menu item
No need to use `SpannableString`.
```kotlin
R.id.my_menu_item -> {
    findViewById<TextView>(R.id.my_menu_item)?.setTextColor(Color.RED)
}
```

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
-keepattributes SourceFile,LineNumberTable
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

# Template
## Child in ConstraintLayout
```xml
<View
    android:id="@+id/view"
    android:layout_width="0dp"
    android:layout_height="0dp"
    app:layout_constraintBottom_toTopOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

## LinearLayout
```xml
<LinearLayout
    android:id="@+id/linear_layout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:showDividers="middle"
    android:dividerPadding="16dp"
    android:divider="?android:listDivider" or "?android:dividerHorizontal"
    android:orientation="vertical">
```

## RecyclerView
```xml
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recycler_view"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    tools:listitem="@layout/view_holder" />
```

```kotlin
with(recyclerView) {
    adapter = myAdapter
    itemAnimator = null
    registerAdapterDataObserver(
    object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            scrollToPosition(0) // RecyclerView#scrollToPosition(0)
        }
    })
    myLiveData.observe(viewLifecycleOwner) {
        with(adapter as MyAdapter) {
            submitList(it)
            // notifyDataSetChanged() // Uncomment this only if new data is not reflected on UI after submitList(...).
        }
    }
}
```

## HorizontalScrollView and ChipGroup
```xml
<HorizontalScrollView
    android:id="@+id/horizontal_scroll_view"
    android:layout_width="0dp"
    android:layout_height="wrap_content"
    android:scrollbars="none"
    android:requiresFadingEdge="horizontal"
    android:fadingEdgeLength="80dp">

    <com.google.android.material.chip.ChipGroup
        android:id="@+id/chip_group"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:singleSelection="true"
        app:chipSpacingHorizontal="0dp" />
</HorizontalScrollView>
```
* `app:singleSelection="true"` makes [choice chips](https://material.io/components/chips#choice-chips) and enables [`ChipGroup.setOnCheckedChangeListener()`](https://developer.android.com/reference/com/google/android/material/chip/ChipGroup#setoncheckedchangelistener).
* If `app:singleSelection="true"` is not set, you have to add an `View.OnClickListener` on each chip.
* `ChipGroup.setOnCheckedChangeListener()` returns -1 if the currently selected chip is re-selected.

```kotlin
chipGroup.setOnCheckedChangeListener { group, checkedId ->
    group.children.filterIsInstance<Chip>().firstOrNull { it.id == checkedId }?.run {
        /*
         * Prevents re-selecting the selected chip because it will unselect the selected chip and leave all the chips unselected.
         * app:selectionRequired="true" in Material Components 1.2.0-alpha02 or higher dispenses with this workaround.
         * https://github.com/material-components/material-components-android/issues/651
         */
        isClickable = false
        group.children.filterIsInstance<Chip>().filterNot { it.id == id }.forEach {
            it.isClickable = true
        }
    }
}
```

# Charles
## How to enable Charles Proxy in debug build
https://www.charlesproxy.com/documentation/using-charles/ssl-certificates/

## How to enable Charles Proxy in release build
1. Connect your Android to Charles. Otherwise, Charles Root Certificate will NOT be automatically downloaded in the next step.
2. Access http://www.charlesproxy.com/getssl/ or http://chls.pro/ssl from Android. Then, Charles Root Certificate should be automatically downloaded.
3. Open Charles and go to the menu bar > `Help` > `SSL Proxying` > `Save Charles Root Certificate`
4. Add the downloaded PEM file as `res/raw/charles_certificate.pem`.
5. Add `ref/xml/network_security_config.xml` as follows.
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config>
        <trust-anchors>
            <certificates src="system" />
            <certificates src="@raw/charles_certificate"/>
            <!-- The following in <base-config> has no effect. -->
            <!-- <certificates src="user" /> -->
        </trust-anchors>
    </base-config>
</network-security-config>
```
5. Reference `ref/xml/network_security_config.xml` in `AndroidManifest.xml` as follows.
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest>
    <application android:networkSecurityConfig="@xml/network_security_config" />
</manifest>
```
### Note
If you have installed the Charles Root Certificate on Android and still see the following error, you may be doing the release build. If so, the above steps will solve the error.
```
SSL handshake with client failed: An unknown issue occurred processing the certificate (certificate_unknown)
You may need to configure your browser or application to trust the Charles Root Certificate. See SSL Proxying in the Help menu.
certificate_unknown (46) - An unknown issue occurred processing the certificate.
```

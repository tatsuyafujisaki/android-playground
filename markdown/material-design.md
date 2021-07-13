# Material Design typography
## TextAppearance from [Material Components for Android](https://material.io/develop/android/docs/getting-started)
Attr|Style|Scalable pixels (sp)
--|--|--
?attr/textAppearanceHeadline1|@style/TextAppearance.MaterialComponents.Headline1|96sp
?attr/textAppearanceHeadline2|@style/TextAppearance.MaterialComponents.Headline2|60sp
?attr/textAppearanceHeadline3|@style/TextAppearance.MaterialComponents.Headline3|48sp
?attr/textAppearanceHeadline4|@style/TextAppearance.MaterialComponents.Headline4|34sp
?attr/textAppearanceHeadline5|@style/TextAppearance.MaterialComponents.Headline5|24sp
?attr/textAppearanceHeadline6|@style/TextAppearance.MaterialComponents.Headline6|20sp
?attr/textAppearanceSubtitle1|@style/TextAppearance.MaterialComponents.Subtitle1|16sp
?attr/textAppearanceBody1|@style/TextAppearance.MaterialComponents.Body1|16sp
?attr/textAppearanceSubtitle2|@style/TextAppearance.MaterialComponents.Subtitle2|14sp
?attr/textAppearanceBody2|@style/TextAppearance.MaterialComponents.Body2|14sp
?attr/textAppearanceButton|@style/TextAppearance.MaterialComponents.Button|14sp
N/A|@style/TextAppearance.MaterialComponents.Tooltip|14sp
?attr/textAppearanceOverline|@style/TextAppearance.MaterialComponents.Overline|12sp
?attr/textAppearanceCaption|@style/TextAppearance.MaterialComponents.Caption|12sp

## TextAppearance from Android SDK
Style|Scalable pixels (sp)
--|--
@android:style/TextAppearance.Material.Display4|112sp
@android:style/TextAppearance.Material.Display3|56sp
@android:style/TextAppearance.Material.Display2|45sp
@android:style/TextAppearance.Material.Display1|34sp
@android:style/TextAppearance.Material.Headline|24sp
@android:style/TextAppearance.Material.Title|20sp
@android:style/TextAppearance.Material.Subhead|16sp
@android:style/TextAppearance.Material.Menu|16sp
@android:style/TextAppearance.Material.Body1|14sp
@android:style/TextAppearance.Material.Body2|14sp
@android:style/TextAppearance.Material.Button|14sp
@android:style/TextAppearance.Material.Caption|12sp

## Usage
```xml
<TextView android:textAppearance="?attr/textAppearanceHeadline1" />
<!-- or -->
<TextView android:textAppearance="@style/TextAppearance.MaterialComponents.Headline1" />
```

Or if you want to create a style:

```xml
<!-- res/values/styles.xml -->
<resources>
    <style name="MyStyle">
        <item name="android:textAppearance">@style/TextAppearance.MaterialComponents.Headline6</item>
    </style>
</resources>
```

# Inset
is a margin that increases the area that responds to touch events.

# CircularProgressIndicator
An example of a determinate circular progress indicator
```xml
<com.google.android.material.progressindicator.CircularProgressIndicator
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:progress="75"
  app:indicatorColor="#FF0000"
  app:indicatorSize="100dp"
  app:trackColor="#D3D3D3"
  app:trackThickness="10dp" />
```

<img src="https://user-images.githubusercontent.com/1838962/121907165-c4614780-cd66-11eb-8ac5-c4b08a636d2d.png" width="100" />

## References
* https://material.io/components/progress-indicators/android
* https://developer.android.com/reference/com/google/android/material/progressindicator/CircularProgressIndicator
* https://github.com/material-components/material-components-android

## References
* https://material.io/develop/android/theming/typography/
* https://material.io/design/typography/the-type-system.html
* https://github.com/material-components/material-components-android
* https://developer.android.com/guide/topics/ui/look-and-feel/themes#textappearance

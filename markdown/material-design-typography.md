# Material Design typography
## TextAppearance from [Material Components for Android](https://material.io/develop/android/docs/getting-started)
Style|Scalable pixels (sp)
--|--
@style/TextAppearance.MaterialComponents.Headline1|96sp
@style/TextAppearance.MaterialComponents.Headline2|60sp
@style/TextAppearance.MaterialComponents.Headline3|48sp
@style/TextAppearance.MaterialComponents.Headline4|34sp
@style/TextAppearance.MaterialComponents.Headline5|24sp
@style/TextAppearance.MaterialComponents.Headline6|20sp
@style/TextAppearance.MaterialComponents.Subtitle1|16sp
@style/TextAppearance.MaterialComponents.Body1|16sp
@style/TextAppearance.MaterialComponents.Subtitle2|14sp
@style/TextAppearance.MaterialComponents.Body2|14sp
@style/TextAppearance.MaterialComponents.Button|14sp
@style/TextAppearance.MaterialComponents.Chip|14sp
@style/TextAppearance.Design.Tab|14sp
@style/TextAppearance.Design.Snackbar.Message|14sp
@style/TextAppearance.MaterialComponents.Overline|12sp
@style/TextAppearance.MaterialComponents.Caption|12sp

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
<TextView android:textAppearance="@style/TextAppearance.MaterialComponents.Headline6" />
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

## References
* https://material.io/develop/android/theming/typography/
* https://material.io/design/typography/the-type-system.html
* https://github.com/material-components/material-components-android
* https://developer.android.com/guide/topics/ui/look-and-feel/themes#textappearance

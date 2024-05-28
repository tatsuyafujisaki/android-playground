# Examples of configuration changes
- Rotate the device
- Change the language
- Switch between dark and light mode

# Density / dpi / dp / px
* density [(# of 160px) / inch] = dpi / 160
* dp [(1 / 160) inch] = px * (160 / dpi) = px / density

# How a launcher icon's density bucket (mdpi, hdpi,  xhdpi, ...) is chosen
> Android will select the resource at the closest larger density bucket and then scale down.

https://developer.android.com/codelabs/basic-android-kotlin-training-change-app-icon#2

# How to draw a round image not in Jetpack Compose
Use either `RoundedBitmapDrawable` or `ImageFilterView`. Note that neither of them can draw a border around it.

# How to view a DataStore file
```shell
protoc --decode_raw < input.preferences_pb
```

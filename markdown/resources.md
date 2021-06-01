# How to convert alpha (0.0 ~ 1.0) to hexadecimal (0x00 ~ 0xFF)
1. Calculate 255 * alpha (0.0~1.0)
2. Convert the above to hexadecimal.

For example:
* alpha 1.0 = 255 * 1.0 = 0xFF
* alpha 0.5 = 255 * 0.5 = 0x80
* alpha 0.0 = 255 * 0.0 = 0x00

# `<selector>` / ColorStateList / DrawableStateList
* A state list, i.e. `<selector>`, can be used only for colors or drawables.
  * https://developer.android.com/guide/topics/resources/color-list-resource
  * https://developer.android.com/guide/topics/resources/drawable-resource#StateList
* ColorStateList
  * https://developer.android.com/reference/kotlin/android/content/res/ColorStateList
* DrawableStateList
  * https://developer.android.com/reference/kotlin/android/graphics/drawable/StateListDrawable

# `<plural>`
* is used to express quantity strings.
  * https://developer.android.com/guide/topics/resources/string-resource#Plurals

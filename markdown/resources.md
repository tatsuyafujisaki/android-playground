# How to convert alpha (0.0 ~ 1.0) to hexadecimal (0x00 ~ 0xFF)
1. Calculate 255 * alpha (0.0~1.0)
2. Convert the result to hexadecimal.

For example:
* alpha 1.0 = 255 * 1.0 = 0xFF
* alpha 0.5 = 255 * 0.5 = 0x80
* alpha 0.0 = 255 * 0.0 = 0x00

# Alpha (in RGB) mapping among float, decimal, and hexadecimal
Alpha (0.0 ~ 1.0)|Alpha in decimal|Alpha in hexadecimal
--|--|--
0.0|0|0x00
0.1|26|0x1A
0.2|51|0x33
0.3|77|0x4D
0.4|102|0x66
0.5|128|0x80
0.6|153|0x99
0.7|179|0xB3
0.8|204|0xCC
0.9|230|0xE6
1.0|255|0xFF

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

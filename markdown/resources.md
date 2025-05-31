# Drawable and Mipmap
## Difference between `drawable/` or `mipmap/`
- `mipmap/` is for app icons.
  - https://developer.android.com/studio/write/create-app-icons
  - https://developer.android.com/training/multiscreen/screendensities#mipmap
- `drawable/` is not for app icons.
    - > A vector drawable is a vector graphic defined in an XML file as a set of points, lines, and curves along with its associated color information.
      - https://developer.android.com/develop/ui/views/graphics/vector-drawable-resources
   - > Supported file types are PNG (preferred), JPG (acceptable), and GIF (discouraged).
       - https://developer.android.com/develop/ui/views/graphics/drawables#drawables-from-images

## How to create notification icons
> A notification is a message that you can display to the user outside of the normal UI of your app. Image Asset Studio places notifications icons in the proper locations in the `res/drawable-density/` directories:

https://developer.android.com/studio/write/create-app-icons#notification

# How to convert alpha (0.0 .. 1.0) to hexadecimal (0x00 ... 0xFF)
1. Calculate 255 * alpha (0.0 ... 1.0)
1. Convert the result to hexadecimal.

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

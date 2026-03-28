# How to take a screenshot
```shell
adb exec-out screencap -p > ~/Desktop/screenshot.png
```

# How to send a file from macOS to an Android device
```shell
adb push filename.extension /sdcard/
```

That said, use drag-and-drop instead of `adb` if you want to send a file from MacOS to an emulator, not to a physical Android device, as this is the easiest way.

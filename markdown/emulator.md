# Emulator
## How to share the clipboard between an emulator and macOS
### macOS -> emulator
1. Copy text on macOS.
2. Long-tap in a text box of an app on an emulator.

### emulator -> macOS
1. Copy text on an emulator.
2. Paste in an editor on macOS.

## How to transfer a file or a folder between an emulator and macOS
### macOS -> emulator
1. Drag a file or a folder on macOS.
2. Drop it on an emulator.

### emulator -> macOS
Run the following.
```shell
adb pull /sdcard/<path-to-file>
```
Alternatively, use Device File Explorer of Android Studio.

## Recommended settings in a Pixel 4 emulator
* `Network & internet` > `Private DNS` > `Off`
* `System` > `Languages & input` > `Autofill service > None`
* `Location` > off (if your app does not depend on location.)
* `Developer options` > `Show taps`

## Settings useful at times
* `Developer options` > `Show layout bounds`

## OEM unlocking
Android emulators don't have the `OEM unlocking` option in `Developer options`.

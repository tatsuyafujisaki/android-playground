# How to share the clipboard between an emulator and macOS

## macOS ➡️ emulator

1. Copy text on macOS.
1. Long-tap in a text box of an app on an emulator.

## emulator ➡️ macOS

Copy the text from the emulator and paste it into a text editor on your Mac.

# How to transfer a file or a folder between an emulator and macOS

## macOS ➡️ emulator

Drag a file or folder from your Mac and drop it onto the emulator.

## emulator ➡️ macOS

```shell
adb pull /sdcard/<path-to-file>
```

Alternatively, you can use Android Studio > View > Tool Windows > Device Explorer.

# How to enable a microphone in an emulator

Android
emulator > [Extended controls](https://developer.android.com/studio/run/emulator-extended-controls) >
`Microphone` > `Virtual microphone uses host audio input`

# OEM unlocking

Android emulators don't have the `OEM unlocking` option in `Developer options`.

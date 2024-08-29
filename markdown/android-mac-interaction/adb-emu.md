# How to kill emulators, which simulates a system-initiated process death
```shell
adb emu kill
```

# How to record a WebM
```shell
adb emu screenrecord start ~/Desktop/screencast.webm
adb emu screenrecord stop
```

# How to set the location
```shell
adb emu geo fix <longitude> <latitude>
```

## `emulator` command
### How to list emulators
```shell
emulator -list-avds
```

### How to start an emulator
```shell
# "&|" is to keep an emulator running even after Zsh is closed.
# http://zsh.sourceforge.net/Doc/Release/Shell-Builtin-Commands.html
emulator @<android-virtual-device> &|
```

### How to start an emulator using specific DNS servers
```shell
emulator @<android-virtual-device> -dns-server 1.1.1.1,1.0.0.1,2606:4700:4700::1111,2606:4700:4700::1001
```

# How to share the clipboard between an emulator and macOS
## macOS ➡️ emulator
1. Copy text on macOS.
2. Long-tap in a text box of an app on an emulator.

## emulator ➡️ macOS
1. Copy text on an emulator.
2. Paste in an editor on macOS.

# How to transfer a file or a folder between an emulator and macOS
## macOS ➡️ emulator
1. Drag a file or a folder on macOS.
2. Drop it on an emulator.

## emulator ➡️ macOS
```shell
adb pull /sdcard/<path-to-file>
```

Alternatively, use Device File Explorer of Android Studio.

# How to enable a microphone in an emulator
Android emulator > [Extended controls](https://developer.android.com/studio/run/emulator-extended-controls)  > `Microphone` > `Virtual microphone uses host audio input`

# OEM unlocking
Android emulators don't have the `OEM unlocking` option in `Developer options`.

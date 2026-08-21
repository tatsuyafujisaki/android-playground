## How to record a WebM

```shell
adb emu screenrecord start ~/Desktop/screencast.webm
adb emu screenrecord stop
```

## How to set the location

```shell
adb emu geo fix <longitude> <latitude>
```

## How to share the clipboard between an emulator and macOS

### macOS ➡️ emulator

1. Copy text on macOS.
2. Long-tap in a text box of an app on an emulator.

### emulator ➡️ macOS

1. Copy text on an emulator.
2. Paste in an editor on macOS.

## How to transfer a file or a folder between an emulator and macOS

### macOS ➡️ emulator

1. Drag a file or a folder on macOS.
   2Drop it on an emulator.

### emulator ➡️ macOS

```shell
adb pull /sdcard/<path-to-file>
```

Alternatively, use the Device Explorer in Android Studio.

# Android Debug Bridge (adb)
# How to kill emulators or simulate a system-initiated process death
```shell
adb emu kill
```

# How to display third-party packages
```shell
# -3 is to show only third-party packages.
adb shell pm list package -3 | sort
```

## How to uninstall third-party packages
```shell
# https://stackoverflow.com/a/30390647
adb shell pm list packages -3 | cut -d: -f2 | tr '\r' ' ' | xargs -n1 -r -t adb uninstall
```

## How to show the current activity
```shell
adb shell "dumpsys activity activities | grep mResumedActivity
```

## How to show fragments
```shell
adb shell dumpsys activity top | grep 'Added Fragments' -A 5
```

## Screenshot / Screencast
### How to take a screenshot
```shell
filepath=~/Desktop/$(date +%Y%m%d-%H%M%S).png
adb exec-out screencap -p > ${filepath} && open ${filepath}
```

### How to record an MP4 or WebM
```shell
# Start
adb shell screenrecord /sdcard/screencast.mp4

# You can stop recording by pressing Command+'.'.
# The following uses a subshell to restore the current directory at the end.
(cd ~/Desktop && adb pull /sdcard/screencast.mp4 && adb shell rm /sdcard/screencast.mp4 && open screencast.mp4)
```

Replace `.mp4` with `.webm` if desired.

### How to record a WebM or an animated GIF on an emulator
```shell
# Start
adb emu screenrecord start ~/Desktop/screencast.webm

# Stop
adb emu screenrecord stop
open ~/Desktop/output.webm
```

## Toggle (Enable/Disable)
### How to toggle `Developer options`
```shell
adb shell settings get global development_settings_enabled
adb shell settings put global development_settings_enabled 1 # on
adb shell settings put global development_settings_enabled 0 # off
```

### How to toggle `Don't keep activities`
```shell
adb shell settings get global always_finish_activities
adb shell settings put global always_finish_activities 1 # on
adb shell settings put global always_finish_activities 0 # off
```

### How to toggle `Enable demo mode`
```shell
adb shell settings get global sysui_demo_allowed
adb shell settings put global sysui_demo_allowed 1 # on
adb shell settings put global sysui_demo_allowed 0 # off
```

### How to toggle `Show demo mode`
```shell
adb shell settings get global sysui_tuner_demo_on
adb shell settings put global sysui_tuner_demo_on 1 # on
adb shell settings put global sysui_tuner_demo_on 0 # off
```

### How to toggle `Show layout bounds`
```shell
adb shell getprop debug.layout
adb shell setprop debug.layout true # on
adb shell setprop debug.layout false # off
```

### How to toggle `Show taps`
```shell
adb shell settings get system show_touches
adb shell settings put system show_touches 1 # on
adb shell settings put system show_touches 0 # off
```

## How to set the location
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

# Third-party packages
## How to show third-party packages
```shell
# -3 is to show only third-party packages.
adb shell pm list package -3 | sort
```

## How to uninstall third-party packages
```shell
# https://stackoverflow.com/a/30390647
adb shell pm list packages -3 | cut -d: -f2 | tr '\r' ' ' | xargs -n1 -r -t adb uninstall
```

# How to list emulators
```shell
emulator -list-avds
```

# How to kill emulators
```shell
adb emu kill
```

# How to start an emulator specifying DNS servers
```shell
emulator @<android-virtual-device> -dns-server 1.1.1.1,1.0.0.1,2606:4700:4700::1111,2606:4700:4700::1001
```

# How to start an emulator
```shell
# Kill running emulators
adb emu kill

# "&|" is to keep an emulator running even after Zsh is closed.
# http://zsh.sourceforge.net/Doc/Release/Shell-Builtin-Commands.html
emulator @<android-virtual-device> &|
```

# Activity/Fragment
## How to show the current activity
```shell
adb shell "dumpsys activity activities | grep mResumedActivity
```

## How to show fragments
```shell
adb shell dumpsys activity top | grep 'Added Fragments' -A 5
```

# Screenrecord/Screenshot
## How to record an MP4
```shell
# Start
adb shell screenrecord /sdcard/video.mp4

# Stop recording by pressing Command+'.'.
# The following uses a subshell to restore the current directory in the end.
(cd ~/Desktop && adb pull /sdcard/video.mp4 && adb shell rm /sdcard/video.mp4 && open video.mp4)
```

## How to record a WebM or an animated GIF
```shell
# Start
adb emu screenrecord start ~/Desktop/output.webm

# Stop
adb emu screenrecord stop
open ~/Desktop/output.webm
```

## How to take a screenshot
```shell
filepath=~/Desktop/$(date +%Y%m%d-%H%M%S).png
adb exec-out screencap -p > ${filepath} && open ${filepath}
```

# Toggle (Enable/Disable)
## How to toggle `Developer options`
```shell
# Get
adb shell settings get global development_settings_enabled

# Enable
adb shell settings put global development_settings_enabled 1

# Disable
adb shell settings put global development_settings_enabled 0
```

## How to toggle `Don't keep activities`
```shell
# Get
adb shell settings get global always_finish_activities

# Enable
adb shell settings put global always_finish_activities 1

# Disable
adb shell settings put global always_finish_activities 0
```

## How to toggle `Enable demo mode`
```shell
# Get
adb shell settings get global sysui_demo_allowed

# Enable
adb shell settings put global sysui_demo_allowed 1

# Disable
adb shell settings put global sysui_demo_allowed 0
```

## How to toggle `Show demo mode`
```shell
# Get
adb shell settings get global sysui_tuner_demo_on

# Enable
adb shell settings put global sysui_tuner_demo_on 1

# Disable
adb shell settings put global sysui_tuner_demo_on 0
```

## How to toggle `Show taps`
```shell
# Get
adb shell settings get system show_touches

# Enable
adb shell settings put system show_touches 1

# Disable
adb shell settings put system show_touches 0
```

# How to slow down the device's animations by a factor of 10
```shell
adb shell settings put global window_animation_scale 10
adb shell settings put global transition_animation_scale 10
adb shell settings put global animator_duration_scale 10

# Reset
adb shell settings put global window_animation_scale 1
adb shell settings put global transition_animation_scale 1
adb shell settings put global animator_duration_scale 1
```

# How to share the clipboard between an emulator and macOS
## macOS -> emulator
1. Copy text on macOS.
2. Long-tap in a text box of an app on an emulator.

## emulator -> macOS
1. Copy text on an emulator.
2. Paste in an editor on macOS.

# How to transfer a file or a folder between an emulator and macOS
## macOS -> emulator
1. Drag a file or a folder on macOS.
2. Drop it on an emulator.

## emulator -> macOS
Run the following.
```shell
adb pull /sdcard/<path-to-file>
```
Alternatively, use Device File Explorer of Android Studio.

# OEM unlocking
Android emulators don't have the `OEM unlocking` option in `Developer options`.

# Recommended settings in an emulator
## Android Virtual Device (AVD) configuration
- `Cold boot`
- `No SD Card`
- `Internal Storage` > 2GB
- `Enable Device Frame` > clear

## Settings
- `Display` > `Auto-rotate screen`
- `Location` > off
- `Google` > `Ads` > `Opt out of Ads Personalization`
- `Privacy` > `Personalize using app data` > off
- `System` > `Languages & input` > `On-screen keyboard` > `Google voice typing` > off

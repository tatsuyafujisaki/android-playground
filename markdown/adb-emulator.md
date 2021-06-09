# How to show third-party packages
```shell
# -3 is to show only third-party packages.
adb shell pm list package -3 | sort
```

# How to list emulators
```shell
~/Library/Android/sdk/emulator/emulator -list-avds
```

# How to kill the emulator
```shell
adb -s emulator-5554 emu kill
```

# How to start the emulator specifying DNS servers
```shell
~/Library/Android/sdk/emulator/emulator -avd <emulator> -dns-server 1.1.1.1,1.0.0.1
```

# How to start the emulator and close the shell
```shell
start_emulator() {
  adb -s emulator-5554 emu kill # kills the emulator.

  # "&|" is to keep an emulator running even after Zsh is closed.
  # http://zsh.sourceforge.net/Doc/Release/Shell-Builtin-Commands.html
  ~/Library/Android/sdk/emulator/emulator -avd <emulator> &|

  exit # closes Zsh.
}

start_emulator()
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
# On
adb shell settings put global development_settings_enabled 1

# Off
adb shell settings put global development_settings_enabled 0

# Get
adb shell settings get global development_settings_enabled
```

## How to toggle `Don't keep activities`
```shell
# On
adb shell settings put global always_finish_activities 1

# Off
adb shell settings put global always_finish_activities 0

# Get
adb shell settings get global always_finish_activities
```

## How to toggle `Enable demo mode`
```shell
# On
adb shell settings put global sysui_demo_allowed 1

# Off
adb shell settings put global sysui_demo_allowed 0

# Get
adb shell settings get global sysui_demo_allowed
```

## How to toggle `Show demo mode`
```shell
# On
adb shell settings put global sysui_tuner_demo_on 1

# Off
adb shell settings put global sysui_tuner_demo_on 0

# Get
adb shell settings get global sysui_tuner_demo_on
```

## How to toggle `Show taps`
```shell
# On
adb shell settings put system show_touches 1

# Off
adb shell settings put system show_touches 0

# Get
adb shell settings get system show_touches
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

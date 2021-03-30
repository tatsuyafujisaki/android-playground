# Android Debug Bridge (adb)
## Activity/Fragment
### How to show the current activity
```shell
adb shell "dumpsys activity activities | grep mResumedActivity
```

### How to show fragments
```shell
adb shell dumpsys activity top | grep 'Added Fragments' -A 5
```

## Screenshot/Video
### How to take a screenshot and save it to desktop
```shell
adb_screenshot() {
  filepath=~/Desktop/$(date +%Y%m%d-%H%M%S).png
  adb exec-out screencap -p > ${filepath} && open ${filepath}
}
```

### How to record a video
```shell
adb shell screenrecord /sdcard/video.mp4 # records a video.
```

### How to save the recorded video to desktop
```shell
# Use a subshell to restore the current directory in the end.
(cd ~/Desktop && adb pull /sdcard/video.mp4 && adb shell rm /sdcard/video.mp4 && open video.mp4) # saves the video to Desktop.
```

## Emulator
### How to kill the emulator
```shell
adb -s emulator-5554 emu kill
```

### How to start the emulator from Zsh
```shell
start_emulator() {
  adb -s emulator-5554 emu kill # kills the emulator.

  # "&|" is to keep an emulator running even after Zsh is closed.
  # http://zsh.sourceforge.net/Doc/Release/Shell-Builtin-Commands.html
  ~/Library/Android/sdk/emulator/emulator -avd $(~/Library/Android/sdk/emulator/emulator -list-avds) &|

  exit # closes Zsh.
}
```

## Demo mode
### How to enable demo mode in developer options via adb
```shell
adb shell settings put global sysui_demo_allowed 1
```

### How to disable demo mode in developer options via adb
```shell
adb shell settings put global sysui_demo_allowed 0
```

### How to show demo mode in developer options via adb
The following work ONLY if you use an emulator without Google Play Store, which means you can get root.
```shell
adb shell am broadcast -a com.android.systemui.demo -e command enter
```

### How to hide demo mode in developer options via adb
The following work ONLY if you use an emulator without Google Play Store, which means you can get root.
```shell
adb shell am broadcast -a com.android.systemui.demo -e command exit
```

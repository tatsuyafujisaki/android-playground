# Android Debug Bridge (adb)
## How to kill the emulator
```shell
adb -s emulator-5554 emu kill
```

## How to start the emulator from Zsh
```shell
start_emulator() {
  adb -s emulator-5554 emu kill # kills the emulator.

  # "&|" is to keep an emulator running even after Zsh is closed.
  # http://zsh.sourceforge.net/Doc/Release/Shell-Builtin-Commands.html
  ~/Library/Android/sdk/emulator/emulator -avd $(~/Library/Android/sdk/emulator/emulator -list-avds) &|

  exit # closes Zsh.
}
```

## How to 

## How to take a screenshot
```shell
adb exec-out screencap -p > screenshot.png
```

## How to shoot a screencast and save it to Desktop.
```shell
(cd ~/Desktop && adb pull /sdcard/video.mp4 && adb shell rm /sdcard/video.mp4 && open video.mp4)
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

### References
https://developer.android.com/studio/debug/dev-options#general

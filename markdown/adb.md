# How to take a screenshot
```shell
adb exec-out screencap -p > screenshot.png
```

# Demo mode
## How to enable demo mode in developer options via adb
```shell
adb shell settings put global sysui_demo_allowed 1
```

## How to disable demo mode in developer options via adb
```shell
adb shell settings put global sysui_demo_allowed 0
```

## How to show demo mode in developer options via adb
The following work ONLY if you use an emulator without Google Play Store, which means you can get root.
```shell
adb shell am broadcast -a com.android.systemui.demo -e command enter
```

## How to hide demo mode in developer options via adb
The following work ONLY if you use an emulator without Google Play Store, which means you can get root.
```shell
adb shell am broadcast -a com.android.systemui.demo -e command exit
```

# References
* https://developer.android.com/studio/debug/dev-options#general
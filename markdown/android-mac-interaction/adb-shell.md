# How to record an MP4 or WebM
```shell
# Start
adb shell screenrecord /sdcard/screencast.mp4

# You can stop recording by pressing Command+'.'.
# The following uses a subshell to restore the current directory at the end.
(cd ~/Desktop && adb pull /sdcard/screencast.mp4 && adb shell rm /sdcard/screencast.mp4 && open screencast.mp4)
```

# How to display third-party packages
```shell
# -3 is to show only third-party packages.
adb shell pm list package -3 | sort
```

# How to uninstall third-party packages
```shell
# https://stackoverflow.com/a/30390647
adb shell pm list packages -3 | cut -d: -f2 | tr '\r' ' ' | xargs -n1 -r -t adb uninstall
```

# Toggle (Enable/Disable)
## How to toggle `Developer options`
```shell
adb shell settings get global development_settings_enabled
adb shell settings put global development_settings_enabled 1 # on
adb shell settings put global development_settings_enabled 0 # off
```

## How to toggle `Don't keep activities`
```shell
adb shell settings get global always_finish_activities
adb shell settings put global always_finish_activities 1 # on
adb shell settings put global always_finish_activities 0 # off
```

## How to toggle `Enable demo mode`
```shell
adb shell settings get global sysui_demo_allowed
adb shell settings put global sysui_demo_allowed 1 # on
adb shell settings put global sysui_demo_allowed 0 # off
```

## How to toggle `Show demo mode`
```shell
adb shell settings get global sysui_tuner_demo_on
adb shell settings put global sysui_tuner_demo_on 1 # on
adb shell settings put global sysui_tuner_demo_on 0 # off
```

## How to toggle `Show layout bounds`
```shell
adb shell getprop debug.layout
adb shell setprop debug.layout true # on
adb shell setprop debug.layout false # off
```

## How to toggle `Show taps`
```shell
adb shell settings get system show_touches
adb shell settings put system show_touches 1 # on
adb shell settings put system show_touches 0 # off
```

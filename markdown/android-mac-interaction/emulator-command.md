# How to list emulators
```shell
emulator -list-avds
```

# How to start an emulator
```shell
# "&|" is to keep an emulator running even after Zsh is closed.
# http://zsh.sourceforge.net/Doc/Release/Shell-Builtin-Commands.html
emulator @<android-virtual-device> &|
```

# How to start one of the installed emulators without specifying which one
```shell
emulator -list-avds | tail -1 | xargs emulator -avd
```

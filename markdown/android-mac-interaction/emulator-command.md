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

# How to start an emulator using specific DNS servers
```shell
emulator @<android-virtual-device> -dns-server 1.1.1.1,1.0.0.1,2606:4700:4700::1111,2606:4700:4700::1001 &|
```
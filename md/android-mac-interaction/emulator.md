## How to list emulators
```shell
emulator -list-avds
```

## How to start an emulator
```shell
# "&|" is to disown the process in Zsh, keeping it running after the terminal closes.
# https://zsh.sourceforge.net/Doc/Release/Shell-Builtin-Commands.html
emulator @<avd-name> &|
```

## How to start one of the installed emulators without specifying which one
```shell
emulator -list-avds | tail -1 | xargs emulator -avd &|
```

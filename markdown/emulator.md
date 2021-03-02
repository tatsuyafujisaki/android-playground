# Emulator
## How to list emulators
```shell
~/Library/Android/sdk/emulator/emulator -list-avds
```

## How to launch an emulator specifying a DNS server
```shell
~/Library/Android/sdk/emulator/emulator -avd <emulator> -dns-server 1.1.1.1,1.0.0.1

# You don't have to specify an emulator if you have only one emulator.
~/Library/Android/sdk/emulator/emulator -avd $(~/Library/Android/sdk/emulator/emulator -list-avds) -dns-server 1.1.1.1,8.8.8.8,8.8.4.4
```

## Useful settings in a Pixel 4 emulator
* System > Languages & input > Autofill service > None
* Location > off (if your app does not depend on location.)
* Developer options > Show taps
# Wireless ADB
This app allows rooted Android smartphones to connect to ADB (Android Debug Bridge) via wifi networks.

## Screenshots


## How to use?
After installing the application, you can go to the platform-tools folder and enter the following commands in the terminal:
```sh
cd C:\Users\Leon\AppData\Local\Android\Sdk\platform-tools
adb devices
adb shell ifconfig wlan0
adb connect <IP>:<PORT>
adb devices
```
For disconnect, you could use:
```sh
adb devices
adb disconnect <IP>:<PORT>
adb usb
adb devices
```

## Licence
GLPv3, [read more](LICENCE)
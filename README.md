# Winterize

A compiled APK of this app to install on an Android device can be downloaded here:
http://geoffledak.com/winterize/winterize.apk

The authorization token that is entered on first launch is retained in a shared preference so that if the app is hard closed and re-opened, the user will immediately be taken to the Overview screen instead of having to re-enter a token. The preference can be cleared and the user returned to the login screen by opening the slideout navigation drawer and selecting 'Sign Out'.

All devices and associated zones are shown on the Overview screen in a scrollable list. Tapping a device displays a details screen which allows the user to enable and disable Standby Mode. Tapping a zone displays a details screen which allows the user to water that zone for a specified duration, and stop the watering.

The device / zone list can be refreshed by scrolling to the top and continuing to swipe down.

Voice commands can be issued by tapping the red voice command button in the upper right portion of the Overview screen. Currently only two voice commands are supported: "turn device on" and "turn device off". These commands disable and enable Standby Mode respectively. Both commands will currently only affect the first device associated with a particular account.


### Screenshots

![Login Screen](/screenshots/screenshot1.png "Login Screen") ![Overview](/screenshots/screenshot2.png "Overview")
![Device Detail](/screenshots/screenshot3.png "Device Detail") ![Navigation Drawer](/screenshots/screenshot5.png "Navigation Drawer")
![Voice Command](/screenshots/screenshot6.png "Voice Command") ![Zone Detail](/screenshots/screenshot4.png "Zone Detail")
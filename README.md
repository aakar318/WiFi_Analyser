# WiFi_Analyser
A simple android app to find the signal strength(and other parameters) and SSID of the WiFi connection and store it locally on the device memory along with the timestamp of the scan.

Parameters which are shown in this app:

->SSID(WiFi name)

->Strength(in dBm)

->Signal Level(out of 5)

->Signal Strength

->Speed

->IP Address

->MAC Address{see note below}

->MAC Address of AP(Access Point)

->Frequency

->Hidden SSID(true/false)

NOTE: MAC Address functionality only works on Android versions <6.0(Marshmallow), the app will work on Android Versions >6.0(Tested on Android 8.1.0), but the MAC Address field will have the default value of 02:00:00:00:00:00
See this link for more info: https://stackoverflow.com/questions/33159224/getting-mac-address-in-android-6-0

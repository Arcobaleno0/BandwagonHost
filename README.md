# BandwagonHost For Android

BandwagonHost for android is a material design style android client for [BandwagonHost](https://bandwagonhost.com).  
I used the BandwagonHost control panel provide RESTful styled API to develop it. The architecture is MVP, it's simplify to the codes.

## Features

1. Look up the hosts details and usaged etc.
2. Control operations such as restarting the host switch.
3. Reinstall system for your hosts.
4. Switch the location for your hosts, and you can look up the location on the maps.
5. Backup your host system images.
6. Add or delete IPv6 address by your hosts.
7. Send command to your hosts.
8. Reset your hosts the root password.

## How to use it ?

You must provide the `APIKey` and `VEID`. But it is worth noting that, this project can not by any way to steal yours `APIkey` and `VEID`.  
If you unbelieve it, you can look the fucking source code.

1. Get the `APIKey` and `VEID` from the hosts control panel.
2. Click your screen bottom right corner "+" to add the host, finished.
3. Enjoy it!

## Build

### Build environment

* JDK 1.8+
* Android SDK
  * Android Build Tools 24.0.1+
  * Android Support Repository 24+ 和 Google Repository 32+
* Android Studio 2.1+
* Gradle 2.10+

### How to build ?

1. Use `git` to `clone` this project to your local disk.
2. Use Android Studio import the project.
3. Click Android Studio navigation bar, and click `Build -> Build APK`.

Of course you also use by command line way, Connect step 1, then:

```bash
cd /path/to/this/project
gradle assembleDebug
```

If you need build Release version, you can use `gradle assembleRelease` command line, **but make sure the signature issue.**

## Screenshots

// TODO

## Downloads

Maybe added to Google Play in the future, Current you can go to this project [Release](https://github.com/pexcn/BandwagonHost/releases) page to download it.

## TODO

* [ ] Day night mode

## Depends

* [Android Support Appcompat/Cardview/Design/Recyclerview](https://developer.android.com/topic/libraries/support-library/index.html)
* [Google Play Service Maps](https://developers.google.com/android/guides/setup)
* [Google Gson](https://github.com/google/gson)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [Retrofit](https://github.com/square/retrofit)
* [ormlite](https://github.com/j256/ormlite-android)
* [Floating Action Button](https://github.com/Clans/FloatingActionButton)

## Contributors

* Xingyu Chen
  * E-mail [pexcn97@gmail.com](mailto:pexcn97@gmail.com)
  * Blog [https://pexcn.me](https://pexcn.me)

## License

![GPL v3](https://www.gnu.org/graphics/gplv3-127x51.png)

```
BandwagonHost - A bandwagonhost.com client for Android
Copyright (C) 2016 Xingyu Chen <pexcn97@gmail.com>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
```

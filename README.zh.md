# BandwagonHost For Android

BandwagonHost 是一个 Material 风格的 [BandwagonHost](https://bandwagonhost.com) 客户端。  
使用主机后台控制面板提供的 RESTful 风格的 API 进行开发。开发模式为 MVP 模式，降低耦合度，使代码更简洁。

## 特性

1. 查看主机详细信息以及使用情况等
2. 控制主机开关机重启等操作
3. 重装系统
4. 切换机房，可以在地图显示机房的地理位置
5. 备份系统镜像
6. 添加或删除 IPv6 地址
7. 向主机发送命令行执行
8. 重置 Root 密码

## 使用

需要使用者提供主机的 `APIKey` 和 `VEID`。不过值得注意的是，本项目不会通过任何方式盗取你的 `APIkey` 和 `VEID` 。  
如果不放心，可以阅读它的源代码。

1. 从主机后台面板获取 `APIKey` 和 `VEID`
2. 点击右下角 + 号来添加主机，完成
3. Enjoy it!

## 编译

### 编译环境

* JDK 1.8+
* Android SDK
  * Android Build Tools 24.0.1+
  * Android Support Repository 24+ 和 Google Repository 32+
* Android Studio 2.1+
* Gradle 2.10+

### 如何编译

1. 使用 `git` 把本项目 `clone` 到本地
2. 使用 Android Studio 导入本项目
3. 点击 Android Studio 导航栏，`Build -> Build APK`

当然，也可以使用命令行的方式编译，接着以上第 1 步，然后：

```bash
cd /path/to/this/project
gradle assembleDebug
```

如果需要编译 Release 版本，可以使用 `gradle assembleRelease` ，**但是请务必注意签名问题**

## 截图

// TODO

## 下载

以后可能会上架 Google Play, 目前可以到本项目 [Release](https://github.com/pexcn/BandwagonHost/releases) 页面下载

## TODO

* [ ] 夜间模式

## 依赖项目

* [Android Support Appcompat/Cardview/Design/Recyclerview](https://developer.android.com/topic/libraries/support-library/index.html)
* [Google Play Service Maps](https://developers.google.com/android/guides/setup)
* [Google Gson](https://github.com/google/gson)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [Retrofit](https://github.com/square/retrofit)
* [ormlite](https://github.com/j256/ormlite-android)
* [Floating Action Button](https://github.com/Clans/FloatingActionButton)

## 贡献者

* Xingyu Chen
  * E-mail [pexcn97@gmail.com](mailto:pexcn97@gmail.com)
  * Blog [https://pexcn.me](https://pexcn.me)

## 开源许可证

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

# Rx Receivers

[![JitPack](https://img.shields.io/github/tag/yongjhih/rx-receivers.svg?label=JitPack)](https://jitpack.io/#yongjhih/rx-receivers)
[![javadoc](https://img.shields.io/github/tag/yongjhih/rx-receivers.svg?label=javadoc)](https://jitpack.io/com/github/yongjhih/rx-receivers/-SNAPSHOT/javadoc/)
[![Build Status](https://travis-ci.org/yongjhih/rx-receivers.svg)](https://travis-ci.org/yongjhih/rx-receivers)
[![Coverage Status](https://coveralls.io/repos/github/yongjhih/rx-receivers/badge.svg)](https://coveralls.io/github/yongjhih/rx-receivers)

Reactive `BroadcastReceiver` implementations for Android.

*Note*: work in progress

### Usage

Register a `BroadcastReceiver` with the given `IntentFilter`, which emits `Intent` values:

```java
RxBroadcastReceiver.create(context, WifiManager.WIFI_STATE_CHANGED_ACTION)
  .subscribe((Action1) (intent) -> { textView.setText(intent.toString()); });
```

Or use the pre-defined factory methods.
```java
RxTelephonyManager.phoneStateChanges(context).subscribe();
RxWifiManager.wifiStateChanges(context).subscribe();
RxWifiManager.networkStateChanges(context).subscribe();
RxWifiManager.supplicantConnectionChanges(context).subscribe();
RxWifiManager.supplicantStateChanges(context).subscribe();
```

### Download

The library is available as a snapshot release.

```groovy
repositories {
  jcenter()
  maven { url 'https://oss.sonatype.org/content/repositories/snapshots/' }
}
```

```groovy
compile 'com.f2prateek.rx.receivers:rx-receivers:0.1.0-SNAPSHOT'
```

or via jitpack:

```gradle
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.yongjhih:rx-receivers:-SNAPSHOT'
}
```

### License

```
Copyright 2016 Andrew Chen
Copyright 2015 Prateek Srivastava

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

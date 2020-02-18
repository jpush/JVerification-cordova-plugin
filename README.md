# JVerification-cordova-plugin
极光认证官方支持的 cordova 插件（Android &amp; iOS）


## Install

- 通过 cordova plugin 安装：

```shell
cordova plugin add jg-jverification-cordova-plugin --variable APP_KEY=极光appKey
```

- 或直接通过 url 安装：

```shell
cordova plugin add https://github.com/jpush/JVerification-cordova-plugin.git --variable APP_KEY=极光appKey
```

- 或下载到本地安装：

```shell
cordova plugin add <plugin_local_path> --variable APP_KEY=极光appKey
```


***说明 ***：
+ android 如果你的应用的targetSdkVersion >=28，设备在Android P 上是默认限制使用http请求的，开发者需要做如下配置：

+ 在res文件夹下创建一个xml文件夹，然后创建一个network_security_config.xml文件，文件内容如下：

~~~
        <?xml version="1.0" encoding="utf-8"?>
        <network-security-config>
            <base-config cleartextTrafficPermitted="true">
                <trust-anchors>
                    <certificates src="system" />
                </trust-anchors>
            </base-config>
        </network-security-config>
~~~

+ 在AndroidManifest.xml文件下的application标签增加以下属性：

~~~
    <application
        ...
        android:networkSecurityConfig="@xml/network_security_config"
        ...
        />
~~~


### 窗口模式样式设置

####1、setCustomUIWithConfig
JVerifyUIConfig配置元素说明   
+ 授权页弹窗模式

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setDialogTheme|int,int,int,int,boolean|设置授权页为弹窗模式(窗口宽度，窗口高度，窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口是否居屏幕底部。设置后offsetY将失效)，单位dp。|

####2、在manifest中为授权页activity设置窗口样式style

AndroidManifest.xml

~~~
<activity android:name="cn.jiguang.verifysdk.CtLoginActivity"
            android:configChanges="orientation|keyboardHidden|screenSize"
            android:theme="@style/ActivityDialogStyle"   <!-- 设置自定义style -->
            android:screenOrientation="unspecified"
            android:launchMode="singleTop">
</activity>
~~~

####style中增加具体弹窗样式

res/values/styles.xml

~~~
<style name="ActivityDialogStyle">
		 <!--隐藏action bar和title bar-->
        <item name="android:windowActionBar">false</item>
        <item name="android:windowNoTitle">true</item>
        <!--背景透明-->
        <item name="android:windowIsTranslucent">true</item>
        <!--dialog圆角-->
        <item name="android:windowBackground">@drawable/dialog_bg</item>
    </style>
~~~

####定义窗口圆角属性

res/drawable/dialog_bg.xml

~~~
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <corners android:radius="5dp"/>
</shape>
~~~




## API

可直接参考 [JG-JVerification-cordova-plugin.js](/www/JG-JVerification-cordova-plugin.js) 文件。

## 参考资料

[Andorid doc](/doc/android.md)

[Ios doc](/doc/ios.md)

[官方文档](https://docs.jiguang.cn/jverification/guideline/intro/)

## Support

- [极光社区](http://community.jiguang.cn/)

## License

MIT © [JiGuang](/license)

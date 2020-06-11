# JVerification-cordova-plugin
极光认证官方支持的 cordova 插件（Android &amp; iOS）

>注意：插件不支持 cordova-android 7.0.0之前版本。
>
>如果需要在cordova-android 7.0.0之前版本集成插件，参照[这篇文章](https://www.jianshu.com/p/23b117ca27a6)
>
>[Cordova Android版本与原生版本对应表](http://cordova.apache.org/docs/en/latest/guide/platforms/android/index.html#requirements-and-support)

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

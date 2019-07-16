# JVerification-cordova-plugin
极光认证官方支持的 cordova 插件（Android &amp; iOS）


## Install

- 通过 cordova plugin 安装：

```shell
cordova plugin add JGJVerification-cordova-plugin --variable APP_KEY=极光appKey
```

- 或直接通过 url 安装：

```shell
cordova plugin add https://github.com/jpush/JVerification-cordova-plugin.git --variable APP_KEY=极光appKey
```

- 或下载到本地安装：

```shell
cordova plugin add <plugin_local_path> --variable APP_KEY=极光appKey
```

## API

可直接参考 [JAnalytics.js](/www/JG-JVerification-cordova-plugin.js) 文件。

## 参考资料

### Andorid 

### JVerifyUIConfig配置元素说明

+ 授权页背景
+ 开始支持的版本：2.1.1
+ 说明：
+ 图片会默认拉伸铺满整个屏幕，建议使用 .9.png 格式的图片来解决不同尺寸屏幕的适配问题 

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setAuthBGImgPath|String|设置背景图片|

+ 授权页导航栏

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setNavColor|int|设置导航栏颜色|
|setNavText|String|设置导航栏标题文字|
|setNavTextColor|int|设置导航栏标题文字颜色|
|setNavReturnImgPath|String|设置导航栏返回按钮图标|
|setNavTransparent|boolean|设置导航栏背景是否隐藏，默认不透明。since 2.3.2|


+ 授权页logo

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setLogoWidth|int|设置logo宽度（单位：dp）|
|setLogoHeight|int|设置logo高度（单位：dp）|
|setLogoHidden|boolean|隐藏logo|
|setLogoOffsetY|int|设置logo相对于标题栏下边缘y偏移|
|setLogoImgPath|String|设置logo图片|

+ 授权页号码栏

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setNumberColor|int|设置手机号码字体颜色|
|setNumberSize|Number|设置手机号码字体大小（单位：sp）。since 2.3.2|
|setNumFieldOffsetY|int|设置号码栏相对于标题栏下边缘y偏移|

+ 授权页登录按钮

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setLogBtnText|String|设置登录按钮文字|
|setLogBtnTextColor|int|设置登录按钮文字颜色|       
|setLogBtnImgPath|String|设置授权登录按钮图片|
|setLogBtnOffsetY|int|设置登录按钮相对于标题栏下边缘y偏移|

+ 授权页隐私栏

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setAppPrivacyOne|String,String|设置开发者隐私条款1名称和URL(名称，url)|
|setAppPrivacyTwo|String,String|设置开发者隐私条款2名称和URL(名称，url)|       
|setAppPrivacyColor|int,int|设置隐私条款名称颜色(基础文字颜色，协议文字颜色)|
|setPrivacyOffsetY|int|设置隐私条款相对于授权页面底部下边缘y偏移|       
|setCheckedImgPath|String|设置复选框选中时图片|
|setUncheckedImgPath|String|设置复选框未选中时图片|  
|setPrivacyState|boolean|设置隐私条款默认选中状态，默认不选中。since 2.3.2|

+ 授权页slogan 

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setSloganTextColor|int|设置移动slogan文字颜色|
|setSloganOffsetY|int|设置slogan相对于标题栏下边缘y偏移|

+ 开发者自定义控件

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|addCustomView|见以上方法定义|在授权页空白处添加自定义控件以及点击监听|

![JVerification](../image/cutomeUI_description_android.png)

### 错误码

|code|message|备注|
|:-----:|:----:|:-----:|
|1000|verify consistent|手机号验证一致|
|1001|verify not consistent|手机号验证不一致|
|1002|unknown result|未知结果|
|1003|token expired|token失效|
|1004|sdk verify has been closed|SDK发起认证未开启|
|1005|包名和 AppKey 不匹配|请检查客户端配置的包名与官网对应 Appkey 应用下配置的包名是否一致|
|1006|frequency of verifying single number is beyond the maximum limit|同一号码自然日内认证消耗超过限制|
|1007|beyond daily frequency limit|appKey自然日认证消耗超过限制|
|1008|AppKey 非法|请到官网检查此应用信息中的 appkey，确认无误|
|1009||请到官网检查此应用的应用详情；更新应用中集成的极光SDK至最新|
|1010|verify interval is less than the minimum limit|同一号码连续两次提交认证间隔过短|
|1011|appSign invalid|应用签名错误，检查签名与Portal设置的是否一致|
|2000|内容为token|获取token成功|
|2001|fetch token failed|获取token失败|
|2002|init failed|SDK初始化失败|
|2003|network not reachable|网络连接不通|
|2004|get uid failed|极光服务注册失败|
|2005|request timeout|请求超时|
|2006|fetch config failed|获取应用配置失败|
|2007|内容为异常信息|验证遇到代码异常|
|2008|Token requesting, please try again later|正在获取token中，稍后再试|
|2009|verifying, please try again later|正在认证中，稍后再试 |
|2010|don't have READ_PHONE_STATE permission|未开启读取手机状态权限|
|2011|内容为异常信息|获取配置时代码异常|
|2012|内容为异常信息|获取token时代码异常|
|2013|内容为具体错误原因|网络发生异常|
|2014|internal error while requesting token|请求token时发生内部错误|
|2016|network type not supported|当前网络环境不支持认证|
|4001|parameter invalid|参数错误。请检查参数，比如是否手机号格式不对|
|4018||没有足够的余额|
|4031||不是认证SDK用户|
|4032||获取不到用户配置|
|4033|appkey is not support login|不是一键登录用户|
|5000|bad server|服务器未知错误|
|6000|内容为token|获取loginToken成功|
|6001|fetch loginToken failed|获取loginToken失败|
|6002|fetch loginToken canceled|用户取消获取loginToken|
|6003|UI 资源加载异常|未正常添加sdk所需的资源文件|
|6004|authorization requesting, please try again later|正在登录中，稍后再试|
|7000|preLogin success|sdk 预取号成功|
|7001|preLogin failed|sdk 预取号失败|
|7002|preLogin requesting, please try again later|正在预取号中，稍后再试|
|-994|网络连接超时|   |
|-996|网络连接断开|   |
|-997|注册失败/登录失败|（一般是由于没有网络造成的）如果确保设备网络正常，还是一直遇到此问题，则还有另外一个原因：JPush 服务器端拒绝注册。而这个的原因一般是：你当前 App 的 Android 包名以及 AppKey，与你在 Portal 上注册的应用的 Android 包名与 AppKey 不相同。|


### IOS

### JVUIConfig类

登录界面UI配置基类。以下是属性说明：

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|authPageBackgroundImage|UIImage|授权页面背景图片|
|navColor|UIColor|导航栏颜色|
|barStyle|UIBarStyle|状态栏着色样式|
|navText|NSAttributedString|导航栏标题|
|navReturnImg|UIImage|导航返回图标|
|navControl|UIBarButtonItem|导航栏右侧自定义控件|
|navCustom|BOOL|导航栏是否隐藏|
|logoImg|UIImage|LOGO图片|
|logoWidth|CGFloat|LOGO图片宽度|
|logoHeight|CGFloat|LOGO图片高度|
|logoOffsetY|CGFloat|LOGO图片偏移量|
|logoHidden|BOOL|LOGO图片隐藏|
|logBtnText|NSString|登录按钮文本|
|logBtnOffsetY|CGFloat|登录按钮Y偏移量|
|logBtnTextColor|UIColor|登录按钮文本颜色|
|logBtnImgs|NSArray|登录按钮背景图片添加到数组(顺序如下) @[激活状态的图片,失效状态的图片,高亮状态的图片]|
|numberColor|UIColor|手机号码字体颜色|
|numberSize|CGFloat|手机号码字体大小|
|numFieldOffsetY|CGFloat|号码栏Y偏移量|
|uncheckedImg|UIImage|复选框未选中时图片|
|checkedImg|UIImage|复选框选中时图片|
|appPrivacyOne|NSArray|隐私条款一:数组（务必按顺序）@[条款名称,条款链接]|
|appPrivacyTwo|NSArray|隐私条款二:数组（务必按顺序）@[条款名称,条款链接]|
|appPrivacyColor|NSArray|隐私条款名称颜色 @[基础文字颜色,条款颜色]|
|privacyState|BOOL|隐私条款check框默认状态 默认:NO|
|privacyOffsetY |CGFloat|隐私条款Y偏移量(注:此属性为与屏幕底部的距离)|
|sloganOffsetY|CGFloat|slogan偏移量Y|
|sloganTextColor|UIColor|slogan文字颜色|

![JVerification](../image/cutomeUI_description.png)


### 错误码列表

|code|描述|备注|
|:-----:|:----:|:-----:|
|1000 | verify consistent|手机号验证一致|
|1001 | verify not consistent|手机号验证不一致|
|1002 | unknown result|未知结果|
|1003 | token expired|token失效|
|1004 | sdk verify has been closed|SDK发起认证未开启|
|1005|AppKey 不存在|请到官网检查 Appkey 对应的应用是否已被删除|
|1006|frequency of verifying single number is beyond the maximum limit|同一号码自然日内认证消耗超过限制|
|1007|beyond daily frequency limit|appKey自然日认证消耗超过限制|
|1008|AppKey 非法|请到官网检查此应用详情中的 Appkey，确认无误|
|1009|当前的 Appkey 下没有创建 iOS 应用；你所使用的 JCore 版本过低|请到官网检查此应用的应用详情；更新应用中集成的 JCore 至最新。|
|1010|verify interval is less than the minimum limit|同一号码连续两次提交认证间隔过短|
|2000 | token request success |获取token 成功|
|2001 | fetch token error |获取token失败|
|2002 | sdk init failed |sdk初始化失败|
|2003 | netwrok not reachable |网络连接不通 |
|2004 | get uid failed |极光服务注册失败 |
|2005 | request timeout|请求超时|
|2006 | fetch config failed |获取配置失败|
|2008 | Token requesting, please wait|正在获取token中，稍后再试|
|2009 | verifying, please try again later|正在认证中，稍后再试 |
|2014 | internal error while requesting token|请求token时发生内部错误 |
|2015 | rsa encode failed|rsa加密失败 |
|2016|network type not supported|当前网络环境不支持认证|
|4001 ||参数错误。请检查参数，比如是否手机号格式不对|
|4009 ||解密rsa失败|
|4018 ||没有足够的余额|
|4031 ||不是认证用户|
|4032 ||获取不到用户配置|
|4033|appkey is not support login|不是一键登录用户|
|5000|bad server|服务器未知错误|
|6000|loginToken request success|获取loginToken成功|
|6001|fetch loginToken failed|获取loginToken失败|
|6002|login cancel|用户取消登录|
|6003|UI load error|UI加载异常|
|6004|authorization requesting, please try again later|正在登录中，稍候再试|
|7000|preLogin success|预取号成功|
|7001|preLogin failed|预取号失败|
|7002|preLogin requesting, please try again later|取号中|


[官方文档](https://docs.jiguang.cn/jverification/guideline/intro/)

## Support

- [极光社区](http://community.jiguang.cn/)

## License

MIT © [JiGuang](/license)

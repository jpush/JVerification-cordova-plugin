## 参考资料

### Andorid 

###JVerifyUIConfig配置元素说明

***以下设置图片名都为drawable下的图片名。（图片名不要后缀，图片自行放到res下的drawable文件夹内）***

***x轴未设置偏移则所有组件默认横向居中***

+ 设置授权页背景
+ 支持的版本 ：2.1.1
+ 说明：
+ 图片会默认拉伸铺满整个屏幕，适配不同尺寸手机，建议使用 .9.png 图片来解决适配问题。

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setAuthBGImgPath|String|设置背景图片|
|setAuthBGGifPath|String|设置本地gif背景图片，需要放置到drawable文件中，传入图片名称即可|
|setAuthBGVideoPath|String,String|设置背景Video文件路径:(支持本地路径如：需把文件放入到raw文件夹中，传入参数为， "raw:"+文件名称，如：raw:testvideo  支持网络路径(建议下载到本地后使用本地路径，网络路径会出现卡顿等网络问题)如："https://xxx")，设置默认第一频图片:(需要放置到drawable文件中，传入图片名称即可,如果Video文件路径为本地，则可以填null)|


+ 授权页状态栏

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setStatusBarColorWithNav|boolean|设置状态栏与导航栏同色。仅在android 5.0以上设备生效。 since 2.4.1|
|setStatusBarDarkMode|boolean|设置状态栏暗色模式。仅在android 6.0以上设备生效。 since 2.4.8|
|setStatusBarTransparent|boolean|设置状态栏是否透明。仅在android 4.4以上设备生效。 since 2.4.8|
|setStatusBarHidden|boolean|设置状态栏是否隐藏。since 2.4.8|
|setVirtualButtonTransparent|boolean|设置虚拟按键栏背景是否透明。since 2.5.2|


+ 授权页导航栏

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setNavColor|int|设置导航栏颜色|
|setNavText|String|设置导航栏标题文字|
|setNavTextColor|int|设置导航栏标题文字颜色|
|setNavReturnImgPath|String|设置导航栏返回按钮图标|
|setNavTransparent|boolean|设置导航栏背景是否透明。默认不透明。since 2.3.2|
|setNavTextSize|int|设置导航栏标题文字字体大小（单位：sp）。since 2.4.1|
|setNavReturnBtnHidden|boolean|设置导航栏返回按钮是否隐藏。默认不隐藏。since 2.4.1|
|setNavReturnBtnWidth|int|设置导航栏返回按钮宽度。since 2.4.8|
|setNavReturnBtnHeight|int|设置导航栏返回按钮高度。since 2.4.8|
|setNavReturnBtnOffsetX|int|设置导航栏返回按钮距屏幕左侧偏移。since 2.4.8|
|setNavReturnBtnRightOffsetX|int|设置导航栏返回按钮距屏幕右侧偏移。since 2.4.8|
|setNavReturnBtnOffsetY|int|设置导航栏返回按钮距上端偏移。since 2.4.8|
|setNavHidden|boolean|设置导航栏是否隐藏。since 2.4.8|
|setNavTextBold|boolean|设置导航栏标题字体是否加粗。since 2.5.4|

+ 授权页logo

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setLogoWidth|int|设置logo宽度（单位：dp）|
|setLogoHeight|int|设置logo高度（单位：dp）|
|setLogoHidden|boolean|隐藏logo|
|setLogoOffsetY|int|设置logo相对于标题栏下边缘y偏移|
|setLogoImgPath|String|设置logo图片|
|setLogoOffsetX|int|设置logo相对于屏幕左边x轴偏移。since 2.3.8|
|setLogoOffsetBottomY|int|设置logo相对于屏幕底部y轴偏移。since 2.4.8|

+ 授权页号码栏

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setNumberColor|int|设置手机号码字体颜色|
|setNumberSize|Number|设置手机号码字体大小（单位：sp）。since 2.3.2|
|setNumFieldOffsetY|int|设置号码栏相对于标题栏下边缘y偏移|
|setNumFieldOffsetX|int|设置号码栏相对于屏幕左边x轴偏移。since 2.3.8|
|setNumberFieldOffsetBottomY|int|设置号码栏相对于屏幕底部y轴偏移。since 2.4.8|
|setNumberFieldWidth|int|设置号码栏宽度。since 2.4.8|
|setNumberFieldHeight|int|设置号码栏高度。since 2.4.8|
|setNumberTextBold|boolean|设置手机号码字体是否加粗。since 2.5.4|

+ 授权页登录按钮
 
|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setLogBtnText|String|设置登录按钮文字|
|setLogBtnTextColor|int|设置登录按钮文字颜色|
|setLogBtnImgPath|String|设置授权登录按钮图片|
|setLogBtnOffsetY|int|设置登录按钮相对于标题栏下边缘y偏移|
|setLogBtnOffsetX|int|设置登录按钮相对于屏幕左边x轴偏移。since 2.3.8|
|setLogBtnWidth|int|设置登录按钮宽度。since 2.3.8|
|setLogBtnHeight|int|设置登录按钮高度。since 2.3.8|
|setLogBtnTextSize|int|设置登录按钮字体大小。since 2.3.8|
|setLogBtnBottomOffsetY|int|设置登录按钮相对屏幕底部y轴偏移。since 2.4.8|
|setLogBtnTextBold|boolean|设置登录按钮字体是否加粗。since 2.5.4|

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
|setPrivacyOffsetX|int|设置隐私条款相对于屏幕左边x轴偏移。since 2.3.8|
|setPrivacyTextCenterGravity|boolean|设置隐私条款文字是否居中对齐（默认左对齐）。since 2.3.8|
|setPrivacyText|String,String,String,String|设置隐私条款名称外的文字。<br>如：登录即同意...和...、...并使用本机号码登录<br>参数1为："登录即同意"。<br>参数2为："和"。<br>参数3为："、"。<br>参数4为："并使用本机号码登录"。<br>since 2.3.8|
|setPrivacyTextSize|int|设置隐私条款文字字体大小（单位：sp）。since 2.4.1|
|setPrivacyTopOffsetY|int|设置隐私条款相对导航栏下端y轴偏移。since 2.4.8|
|setPrivacyCheckboxHidden|boolean|设置隐私条款checkbox是否隐藏。since 2.4.8|
|setPrivacyCheckboxSize|int|设置隐私条款checkbox尺寸。since 2.4.8|
|setPrivacyWithBookTitleMark|boolean|设置隐私条款运营商协议名是否加书名号。since 2.4.8|
|setPrivacyCheckboxInCenter|boolean|设置隐私条款checkbox是否相对协议文字纵向居中。默认居顶。since 2.4.8|
|setPrivacyTextWidth|int|设置隐私条款文字栏宽度。since 2.5.0|
|setPrivacyTextBold|boolean|设置隐私条款文字字体是否加粗。since 2.5.4|
|setPrivacyUnderlineText|boolean|设置隐私条款文字字体是否加下划线。since 2.5.4|

+ 授权页隐私协议web页面

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setPrivacyNavColor|int|设置协议展示web页面导航栏背景颜色。since 2.4.8|
|setPrivacyNavTitleTextColor|int|设置协议展示web页面导航栏标题文字颜色。since 2.4.8|
|setPrivacyNavTitleTextSize|int|设置协议展示web页面导航栏标题文字大小（sp）。since 2.4.8|
|setPrivacyNavTitleTextBold|boolean|设置协议展示web页面导航栏字体是否加粗。since 2.5.4|
|setAppPrivacyNavTitle1|String|设置自定义协议1对应web页面导航栏文字内容。since 2.5.2|
|setAppPrivacyNavTitle2|String|设置自定义协议2对应web页面导航栏文字内容。since 2.5.2|
|setPrivacyStatusBarColorWithNav|boolean|设置授权协议web页面状态栏与导航栏同色。仅在android 5.0以上设备生效。since 2.5.2|
|setPrivacyStatusBarDarkMode|boolean|设置授权协议web页面状态栏暗色模式。仅在android 6.0以上设备生效。since 2.5.2|
|setPrivacyStatusBarTransparent|boolean|设置授权协议web页面状态栏是否透明。仅在android 4.4以上设备生效。since 2.5.2|
|setPrivacyStatusBarHidden|boolean|设置授权协议web页面状态栏是否隐藏。since 2.5.2|
|setPrivacyVirtualButtonTransparent|boolean|设置授权协议web页面虚拟按键栏背景是否透明。since 2.5.2|

+ 授权页slogan

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setSloganTextColor|int|设置移动slogan文字颜色|
|setSloganOffsetY|int|设置slogan相对于标题栏下边缘y偏移|
|setSloganOffsetX|int|设置slogan相对于屏幕左边x轴偏移。since 2.3.8|
|setSloganBottomOffsetY|int|设置slogan相对于屏幕底部下边缘y轴偏移。since 2.3.8|
|setSloganTextSize|int|设置slogan字体大小。since 2.4.8|
|setSloganHidden|boolean|设置slogan是否隐藏。since 2.4.8|
|setSloganTextBold|boolean|设置slogan字体是否加粗。since 2.5.4|
              
+ 授权页动画

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setNeedStartAnim|boolean|设置拉起授权页时是否需要显示默认动画。默认展示。since 2.5.2|
|setNeedCloseAnim|boolean|设置关闭授权页时是否需要显示默认动画。默认展示。since 2.5.2|
               
+ 授权页弹窗模式

|方法|参数类型|说明|
|:-----:|:----:|:----:|
|setDialogTheme|int,int,int,int,boolean|设置授权页为弹窗模式(窗口宽度，窗口高度，窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口是否居屏幕底部。设置后offsetY将失效)，单位dp。注：窗口不支持导航栏|

![JVerification](https://docs.jiguang.cn/jverification/image/cutomeUI_description_android.png)

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
|2017|carrier config invalid|运营商配置错误|
|2018|Local unsupported operator|本地不支持的运营商|
|3000|获取短信验证码成功|获取短信验证码成功|
|3001|没有初始化|没有初始化|
|3002|无效电话号码|无效电话号码|
|3003|前后两次请求少于设定时间|前后两次请求少于设定时间|
|3004|未知错误|未知错误,主要看错误信息|
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
|6006|prelogin scrip expired.|预取号结果超时，需要重新预取号|
|7000|preLogin success|sdk 预取号成功|
|7001|preLogin failed|sdk 预取号失败|
|7002|preLogin requesting, please try again later|正在预取号中，稍后再试|
|8000|init success|初始化成功|
|8004|init failed|初始化失败，详见日志|
|8005|init timeout|初始化超时，稍后再试|
|-994|网络连接超时|   |
|-996|网络连接断开|   |
|-997|注册失败/登录失败|（一般是由于没有网络造成的）如果确保设备网络正常，还是一直遇到此问题，则还有另外一个原因：JPush 服务器端拒绝注册。而这个的原因一般是：你当前 App 的 Android 包名以及 AppKey，与你在 Portal 上注册的应用的 Android 包名与 AppKey 不相同。|

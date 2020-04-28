### IOS

### JVerifyUIConfig配置元素说明

注意：参数为图片的值都为IOS图片资源名字。


授权界面UI配置基类。以下是属性说明：

+ 授权页面设置

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|authPageBackgroundImage|String|授权界面背景图片|
|authPageGifImagePath|String|授权界面背景gif资源路径，与authPageBackgroundImage属性互斥|
|setVideoBackgroudResource|String,String|视频路径支持在线url或者本地视频路径，视频未准备好播放时的占位图片名称|
|autoLayout|BOOL|是否使用autoLayout，默认YES，|
|shouldAutorotate|BOOL|是否支持自动旋转 默认YES|
|dismissAnimationFlag|BOOL|关闭授权页是否有动画。默认YES,有动画。参数仅作用于以下两种情况：1、一键登录接口设置登录完成后，自动关闭授权页 2、用户点击授权页关闭按钮，关闭授权页|


+ 导航栏

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|navCustom|BOOL|是否隐藏导航栏（适配全屏图片）|
|navColor|int|导航栏颜色|
|navText|String,int,int|导航栏标题[文字,文字颜色,文字大小]|
|navReturnImg|String|导航返回图标|
|prefersStatusBarHidden|BOOL|*竖屏情况下，是否隐藏状态栏。默认NO.在项目的Info.plist文件里设置UIViewControllerBasedStatusBarAppearance为YES.注意：弹窗模式下无效，是否隐藏由外部控制器控制|
|navTransparent|BOOL|导航栏是否透明，默认不透明。此参数和navBarBackGroundImage冲突，应避免同时使用|
|navReturnHidden|BOOL|导航栏默认返回按钮隐藏，默认不隐藏|
|navDividingLineHidden|BOOL|导航栏分割线是否隐藏，默认隐藏|
|navBarBackGroundImage|String|导航栏背景图片.此参数和navTransparent冲突，应避免同时使用|

+ LOGO

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|logoImg|String|LOGO图片|
|logoWidth|float|LOGO图片宽度|
|logoHeight|float|LOGO图片高度|
|logoOffsetY|float|LOGO图片偏移量|
|logoHidden|BOOL|LOGO图片隐藏|
|logoConstraints|[float,float,float,float]|LOGO图片布局对象(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)|
|logoHorizontalConstraints|[float,float,float,float]|LOGO图片 横屏布局(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)，横屏时优先级高于logoConstraints|

+ 登录按钮

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|logBtnText|String|登录按钮文本|
|logBtnOffsetY|float|登录按钮Y偏移量|
|logBtnTextColor|int|登录按钮文本颜色|
|logBtnImgs|String,String,String|登录按钮背景图片添加到数组(顺序如下) @[激活状态的图片,失效状态的图片,高亮状态的图片]|
|logBtnConstraints|[float,float,float,float]|登录按钮布局对象(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)|
|logBtnHorizontalConstraints|[float,float,float,float]|登录按钮 横屏布局(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)，横屏时优先级高于logBtnConstraints|

+ 手机号码

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|numberColor|int|手机号码字体颜色|
|numberSize|float|手机号码字体大小|
|numFieldOffsetY|float|号码栏Y偏移量|
|numberConstraints|[float,float,float,float]|号码栏布局对象(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)|
|numberHorizontalConstraints|[float,float,float,float]|号码栏 横屏布局(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度),横屏时优先级高于numberConstraints|

+ checkBox

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|uncheckedImg|String|checkBox未选中时图片|
|checkedImg|String|checkBox选中时图片|
|checkViewHidden|BOOL|checkBox是否隐藏，默认不隐藏|
|privacyState|BOOL|隐私条款check框默认状态 默认:NO|
|checkViewConstraints|[float,float,float,float]|checkBox布局对象(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)|
|checkViewHorizontalConstraints|[float,float,float,float]|checkBox横屏布局(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)，横屏优先级高于checkViewConstraints|

+ 隐私协议栏

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|appPrivacyOne|String,String|隐私条款一:数组（务必按顺序）@[条款名称,条款链接]|
|appPrivacyTwo|String,String|隐私条款二:数组（务必按顺序）@[条款名称,条款链接]|
|appPrivacyColor|int,int|隐私条款名称颜色 @[基础文字颜色,条款颜色]|
|privacyTextFontSize|float|隐私条款字体大小，默认12|
|privacyOffsetY |float|隐私条款Y偏移量(注:此属性为与屏幕底部的距离)|
|privacyComponents|String,String|隐私条款拼接文本数组|
|privacyShowBookSymbol|BOOL|隐私条款是否显示书名号，默认不显示|
|privacyLineSpacing|float|隐私条款行距，默认跟随系统|
|privacyConstraints|[float,float,float,float]|隐私条款布局对象(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)|
|privacyHorizontalConstraints|[float,float,float,float]|隐私条款 横屏布局(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)，横屏下优先级高于privacyConstraints|


+ 隐私协议页面

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|agreementNavBackgroundColor|int|协议页导航栏背景颜色|
|agreementNavText|String,int,int|运营商协议的协议页面导航栏标题[文字,文字颜色,文字大小]|
|firstPrivacyAgreementNavText|String,int,int|自定义协议1的协议页面导航栏标题[文字,文字颜色,文字大小]|
|secondPrivacyAgreementNavText|String,int,int|自定义协议2的协议页面导航栏标题[文字,文字颜色,文字大小]|
|agreementNavReturnImage|String|协议页导航栏返回按钮图片|


+ slogan

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|sloganOffsetY|float|slogan偏移量Y|
|sloganTextColor|int|slogan文字颜色|
|sloganConstraints|[float,float,float,float]|slogan布局对象(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)|
|sloganHorizontalConstraints|[float,float,float,float]|slogan 横屏布局(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度),横屏下优先级高于sloganConstraints|


+ 弹窗

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|showWindow|BOOL|是否弹窗，默认no|
|windowBackgroundImage|String|弹框内部背景图片|
|windowBackgroundAlpha|float|弹窗外侧 透明度  0~1.0|
|windowCornerRadius|float|弹窗圆角数值|
|windowConstraints|[float,float,float,float]|弹窗布局对象(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)|
|windowHorizontalConstraints|[float,float,float,float]|弹窗横屏布局(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)，横屏下优先级高于windowConstraints|
|windowCloseBtnImgs|[String,String]|弹窗close按钮图片 @[普通状态图片，高亮状态图片]
|windowCloseBtnConstraints|[float,float,float,float]|弹窗close按钮布局(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度)
|windowCloseBtnHorizontalConstraints|[float,float,float,float]|弹窗close按钮 横屏布局(窗口相对屏幕中心的x轴偏移量，窗口相对屏幕中心的y轴偏移量，窗口宽度，窗口高度),横屏下优先级高于windowCloseBtnConstraints|

![JVerification](https://docs.jiguang.cn/jverification/image/cutomeUI_description.png)

##JVMobileUIConfig类

移动登录界面UI配置类，JVUIConfig的子类。

##JVUnicomUIConfig类

联通登录界面UI配置类，JVUIConfig的子类。

##JVTelecomUIConfig类

电信登录界面UI配置类，JVUIConfig的子类。


##错误码列表

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
|2016 | network type not supported  |当前网络环境不支持认证 |
|2017|carrier config invalid|运营商配置无效|
|4001 ||参数错误。请检查参数，比如是否手机号格式不对|
|4009 ||解密rsa失败|
|4014 |appkey is blocked|功能被禁用|
|4018 ||没有足够的余额|
|4031 ||不是认证用户|
|4032 ||获取不到用户配置|
|4033|Login feature is not available |未开启一键登录|
|6000|loginToken request success|获取loginToken成功|
|6001|fetch loginToken failed|获取loginToken失败|
|6002|login cancel|用户取消登录|
|6003|UI load error|UI加载异常|
|6004|authorization requesting, please try again later|正在登录中，稍候再试|
|6006|prelogin scrip expired|预取号信息过期，请重新预取号|
|7000|preLogin success|预取号成功|
|7001|preLogin failed|预取号失败|
|7002|preLogin requesting, please try again later|取号中|
|8000|init success|初始化成功|
|8004|init failed|初始化失败|
|8005|init timeout|初始化超时|

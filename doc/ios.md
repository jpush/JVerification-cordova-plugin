### IOS

### JVerifyUIConfig配置元素说明

注意：参数为图片的值都为IOS图片资源名字。

|参数名称|参数类型|参数说明|
|:-----:|:----:|:-----:|
|authPageBackgroundImage|String|授权页面背景图片|
|navColor|int|导航栏颜色|
|barStyle|int|状态栏着色样式|
|navText|String,int|导航栏标题[标题文字,标题颜色]|
|navReturnImg|String|导航返回图标|
|navCustom|BOOL|导航栏是否隐藏|
|logoImg|String|LOGO图片|
|logoWidth|float|LOGO图片宽度|
|logoHeight|float|LOGO图片高度|
|logoOffsetY|float|LOGO图片偏移量|
|logoHidden|BOOL|LOGO图片隐藏|
|logBtnText|String|登录按钮文本|
|logBtnOffsetY|float|登录按钮Y偏移量|
|logBtnTextColor|int|登录按钮文本颜色|
|logBtnImgs|String,String,String|登录按钮背景图片添加到数组(顺序如下) [激活状态的图片,失效状态的图片,高亮状态的图片]|
|numberColor|int|手机号码字体颜色|
|numberSize|float|手机号码字体大小|
|numFieldOffsetY|float|号码栏Y偏移量|
|uncheckedImg|String|复选框未选中时图片|
|checkedImg|String|复选框选中时图片|
|appPrivacyOne|String,String|隐私条款一:数组（务必按顺序）[条款名称,条款链接]|
|appPrivacyTwo|String,String|隐私条款二:数组（务必按顺序）[条款名称,条款链接]|
|appPrivacyColor|int,int|隐私条款名称颜色 [基础文字颜色,条款颜色]|
|privacyState|BOOL|隐私条款check框默认状态 默认:NO|
|privacyOffsetY |float|隐私条款Y偏移量(注:此属性为与屏幕底部的距离)|
|sloganOffsetY|float|slogan偏移量Y|
|sloganTextColor|int|slogan文字颜色|

![JVerification](https://docs.jiguang.cn/jverification/image/cutomeUI_description.png)


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
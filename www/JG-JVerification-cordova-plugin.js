var exec = require('cordova/exec');
var PLUGIN_NAME = 'JGJVerificationPlugin'


var JMessagePlugin = {
    /**
     * 初始化接口。
     * @param {function} listener = function (String){}
     * {"code":8000,"msg":"ok""}
     * code: 返回码，8000代表初始化成功，其他为失败，详见错误码描述
     * msg：结果描述
     */
    init: function (listener) {
        exec(listener, null, PLUGIN_NAME, 'init', []);
    },
    /**
     * 初始化接口。
     * @param  {int} timOut =  超时时间（毫秒）,有效取值范围(0,30000],若小于等于0或大于30000则取默认值10000.推荐设置为5000-10000.
     * @param {function} listener = function (String){}
     * {"code":8000,"msg":"ok""}
     * code: 返回码，8000代表初始化成功，其他为失败，详见错误码描述
     * msg：结果描述
     */
    initTimeOut: function (timOut, listener) {
        exec(listener, null, PLUGIN_NAME, 'initTimeOut', [timOut]);
    },
    /**
     * 设置是否开启debug模式。true则会打印更多的日志信息。建议在init接口之前调用。
     * @param {Boolean} enable =  debug开关
     *
     */
    setDebugMode: function (enable) {
        exec(null, null, PLUGIN_NAME, 'setDebugMode', [enable]);
    },
    /**
     * 获取sdk是否整体初始化成功的标识
     * @param {function} listener = function (Boolean) {}
     *  true - 成功，false - 失败
     */
    isInitSuccess: function (listener) {
        exec(listener, null, PLUGIN_NAME, 'isInitSuccess', []);
    },
    /**
     * 判断当前的手机网络环境是否可以使用认证。
     * @param {function} listener = function (Boolean) {}
     * 返回true代表可以使用；返回false建议使用其他验证方式。
     */
    checkVerifyEnable: function (listener) {
        exec(listener, null, PLUGIN_NAME, 'checkVerifyEnable', []);
    },

    /**
     * 在预定时间内获取当前在线的sim卡所在运营商及token，如果超过所设时间，接口回调返回超时。如果获取成功代表可以用来验证手机号，获取失败则建议做短信验证。
     * @param {int} timeOut 超时时间（毫秒）,有效取值范围(0,10000],若小于等于0则取默认值5000.大于10000则取10000.为保证获取token的成功率，建议设置为3000-5000ms.
     * @param {function} listener = function (String){}
     *
     * {"code":2000,"content":"ok","operator":"CM"}
     * code: 返回码，2000代表获取成功，其他为失败，详见错误码描述
     * content：成功时为token，可用于调用验证手机号接口。token有效期为1分钟，超过时效需要重新获取才能使用。失败时为失败信息
     * operator：成功时为对应运营商，CM代表中国移动，CU代表中国联通，CT代表中国电信。失败时可能为null
     */
    getToken: function (timeOut, listener) {
        exec(listener, null, PLUGIN_NAME, 'getToken', [timeOut]);
    },

    /**
     * 验证当前运营商网络是否可以进行一键登录操作，该方法会缓存取号信息，提高一键登录效率。建议发起一键登录前先调用此方法。
     * @param {int} timeOut 超时时间（毫秒）,有效取值范围(0,10000],若小于等于0则取默认值5000.大于10000则取10000, 为保证预取号的成功率，建议设置为3000-5000ms.
     * @param {function} listener =function (String){}
     *
     * {"code":7000,"content":"ok"}
     * code: 返回码，7000代表获取成功，其他为失败，详见错误码描述
     * content：调用结果信息描述
     */
    preLogin: function (timeOut, listener) {
        exec(listener, null, PLUGIN_NAME, 'preLogin', [timeOut]);
    },
    /**
     * 调起一键登录授权页面，在用户授权后获取loginToken，同时支持授权页事件监听
     * @param autoFinish 是否自动关闭授权页，true - 是，false - 否
     *
     * @param {function} listener =function (String){} 登录授权结果回调
     * {"code":6000,"content":"ok","operator":"CM"}
     * code: 返回码，6000代表loginToken获取成功，6001代表loginToken获取失败，其他返回码详见描述
     * content：返回码的解释信息，若获取成功，内容信息代表loginToken。
     * operator：成功时为对应运营商，CM代表中国移动，CU代表中国联通，CT代表中国电信。失败时可能为null
     *
     * @param {function} authPageEventListener =function (String){} 授权页事件回调
     * {"code":1,"content":"login activity closed."}
     * code: 返回码，具体见事件返回码表。
     * content：内容描述。
     */
    loginAuth: function (autoFinish, listener, authPageEventListener) {
        exec(listener, authPageEventListener, PLUGIN_NAME, 'loginAuth', [autoFinish]);
    },
    /**
     * 关闭登录授权页，如果当前授权正在进行，则loginAuth接口会立即触发6002取消回调。
     */
    dismissLoginAuth: function () {
        exec(null, null, PLUGIN_NAME, 'dismissLoginAuth', []);
    },
    /**
     * 关闭登录授权页，如果当前授权正在进行，则loginAuth接口会立即触发6002取消回调。
     * @param {Boolean} needCloseAnim  是否需要展示默认授权页关闭的动画（如果有）。true - 需要，false - 不需要
     */
    dismissLoginAuthFinish: function (needCloseAnim) {
        exec(null, null, PLUGIN_NAME, 'dismissLoginAuthFinish', [needCloseAnim]);
    },
    /**
     *
     * @param {String} phonenum 电话号码
     * @param {String} signId 短信签名id，如果为null，则为默认短信签名id
     * @param {String} tempId 短信模板id，如果为null，则为默认短信模板id
     * @param {function} listener =function (String){}  回调接口
     * {"code":3000,"msg":"1213134e132432"}
     * code: 返回码，3000代表获取验证码成功，msg为此次获取的唯一标识码(uuid)，其他为失败，详见错误码描述
     * msg：结果描述
     */
    getSmsCode: function (phonenum, signId, tempId, listener) {
        exec(listener, null, PLUGIN_NAME, 'getSmsCode', [phonenum, signId, tempId]);
    },
    /**
     *设置前后两次获取验证码的时间间隔，默认 30000ms，有效范围(0,300000)
     * @param {int}intervalTime 时间间隔，单位是毫秒(ms)。
     */
    setSmsIntervalTime: function (intervalTime) {
        exec(null, null, PLUGIN_NAME, 'setSmsIntervalTime', [intervalTime]);
    },
    /**
     * 修改授权页面主题，开发者可以通过 setCustomUIWithConfig 方法修改授权页面主题，需在 loginAuth 接口之前调用
     * @param {String} jVerifyUIConfig = {"key1":"value1","key2":["value1","value2"]}
     * 传入json格式的字符串
     * {"setAuthBGImgPath":"path","setNavColor":255,"setAppPrivacyColor":[10,30]}
     *
     * 参考：分别查看andorid doc和ios doc中的JVerifyUIConfig配置元素说明
     * key ----为JVerifyUIConfig配置元素说明表中的方法字段
     * value ----value的类型为JVerifyUIConfig配置元素说明表中的参数类型，如果是多类型时用数组类型顺序填充。
     *
     *
     */
    setCustomUIWithConfig: function (jVerifyUIConfig) {
        exec(null, null, PLUGIN_NAME, 'setCustomUIWithConfig', [jVerifyUIConfig]);
    },
    /**
     * 注：这个方法只有android有
     *
     * 修改授权页面主题，支持传入竖屏和横屏两套config。sdk会根据当前横竖屏状态动态切换。需在每次调用 loginAuth 接口之前调用。
     * @param {String}  uiConfigPortrait = {"key1":"value1","key2":["value1","value2"]}   竖屏config
     * @param {String} uiConfigLandscape = {"key1":"value1","key2":["value1","value2"]} 横屏config  注：这个方法只有android有
     *
     * 传入json格式的字符串
     * {"setAuthBGImgPath":"path","setNavColor":255,"setAppPrivacyColor":[10,30]}
     *
     * 参考：分别查看andorid doc和ios doc中的JVerifyUIConfig配置元素说明
     * key ----为JVerifyUIConfig配置元素说明表中的方法字段
     * value ----value的类型为JVerifyUIConfig配置元素说明表中的参数类型，如果是多类型时用数组类型顺序填充。
     *
     * 注：这个方法只有android有
     */
    setCustomUIWithConfigAndroid: function (uiConfigPortrait, uiConfigLandscape) {
        exec(null, null, PLUGIN_NAME, 'setCustomUIWithConfigAndroid', [uiConfigPortrait, uiConfigLandscape]);
    },
    /**
     * 清除sdk当前预取号结果缓存。
     */
    clearPreLoginCache: function () {
        exec(null, null, PLUGIN_NAME, 'clearPreLoginCache', [])
    }
};

module.exports = JMessagePlugin



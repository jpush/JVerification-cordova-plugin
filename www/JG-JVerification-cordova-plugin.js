
var exec = require('cordova/exec');
var PLUGIN_NAME = 'JGJVerificationPlugin'


var JMessagePlugin = {
  /**
   * 初始化接口。
   */
  init: function () {
    exec(null, null, PLUGIN_NAME, 'init', []);
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
   * @param {int} timeOut 超时时间（毫秒）,有效取值范围[3000,10000]
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
   * 验证手机号是否是当前在线的sim卡的手机号
   * @param {String} token 选填，getToken接口返回的token。如果传空，将自动调用getToken方法再执行手机号验证
   * @param {String} phone 必填，需要验证的手机号。如果传空会报4001参数错误
   * @param {function} listener = function (String){}
   * 
   * {"code":1000,"content":"ok","operator":"CM"}
   * code: 返回码，1000代表验证一致，1001代表验证不一致，其他为失败，详见错误码描述
   * content：返回码的解释信息
   * operator：成功时为对应运营商，CM代表中国移动，CU代表中国联通，CT代表中国电信。失败时可能为null
   */
  verifyNumber: function (token, phone, listener) {
    exec(listener, null, PLUGIN_NAME, 'verifyNumber', [token, phone]);
  },

  /**
   * 验证当前运营商网络是否可以进行一键登录操作，该方法会缓存取号信息，提高一键登录效率。建议发起一键登录前先调用此方法。
   * @param {int} timeOut 超时时间（毫秒）,有效取值范围[3000,10000]
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
   * 调起一键登录授权页面，在用户授权后获取loginToken
   * @param {int} autoFinish 是否自动关闭授权页，true - 是，false - 否；若此字段设置为false，请在收到一键登录回调后调用SDK提供的关闭授权页面方法。
   * @param {function} listener =function (String){}
   * 
   * {"code":6000,"content":"ok","operator":"CM"}
   * code: 返回码，6000代表loginToken获取成功，6001代表loginToken获取失败，其他返回码详见描述
   * content：返回码的解释信息，若获取成功，内容信息代表loginToken。
   * operator：成功时为对应运营商，CM代表中国移动，CU代表中国联通，CT代表中国电信。失败时可能为null
   */
  loginAuth: function (autoFinish, listener) {
    exec(listener, null, PLUGIN_NAME, 'loginAuth', [autoFinish]);
  },

  /**
   * 关闭登录授权页，如果当前授权正在进行，则loginAuth接口会立即触发6002取消回调。
   */
  dismissLoginAuthActivity: function () {
    exec(null, null, PLUGIN_NAME, 'dismissLoginAuthActivity', []);
  },

  /**
   * 修改授权页面主题，开发者可以通过 setCustomUIWithConfig 方法修改授权页面主题，需在 loginAuth 接口之前调用
   * @param {String} jVerifyUIConfig = {"key":"value","key":"value"}
   * 传入json格式的字符串
   * {"setAuthBGImgPath":"path","setNavColor":255,"setAppPrivacyColor":[10,30]}
   * 
   * 参考：JVerifyUIConfig配置元素说明
   * key为方法名 ----可使用全局变量JVerifyUIConfig_XXX
   * 
   * 
   */
  setCustomUIWithConfig: function (jVerifyUIConfig) {
    exec(null, null, PLUGIN_NAME, 'setCustomUIWithConfig', [jVerifyUIConfig]);
  }
};

var JVerifyUIConfig_setAuthBGImgPath = "setAuthBGImgPath";
var JVerifyUIConfig_setNavColor = "setNavColor";
var JVerifyUIConfig_setNavText = "setNavText";
var JVerifyUIConfig_setNavTextColor = "setNavTextColor";
var JVerifyUIConfig_setNavReturnImgPath = "setNavReturnImgPath";
var JVerifyUIConfig_setLogoWidth = "setLogoWidth";
var JVerifyUIConfig_setLogoHeight = "setLogoHeight";
var JVerifyUIConfig_setLogoHidden = "setLogoHidden";
var JVerifyUIConfig_setLogoOffsetY = "setLogoOffsetY";
var JVerifyUIConfig_setLogoImgPath = "setLogoImgPath";
var JVerifyUIConfig_setNumberColor = "setNumberColor";
var JVerifyUIConfig_setNumFieldOffsetY = "setNumFieldOffsetY";
var JVerifyUIConfig_setLogBtnText = "setLogBtnText";
var JVerifyUIConfig_setLogBtnTextColor = "setLogBtnTextColor";
var JVerifyUIConfig_setLogBtnImgPath = "setLogBtnImgPath";
var JVerifyUIConfig_setLogBtnOffsetY = "setLogBtnOffsetY";
var JVerifyUIConfig_setAppPrivacyOne = "setAppPrivacyOne";
var JVerifyUIConfig_setAppPrivacyTwo = "setAppPrivacyTwo";
var JVerifyUIConfig_setAppPrivacyColor = "setAppPrivacyColor";
var JVerifyUIConfig_setPrivacyOffsetY = "setPrivacyOffsetY";
var JVerifyUIConfig_setCheckedImgPath = "setCheckedImgPath";
var JVerifyUIConfig_setUncheckedImgPath = "setUncheckedImgPath";
var JVerifyUIConfig_setSloganTextColor = "setSloganTextColor";
var JVerifyUIConfig_setSloganOffsetY = "setSloganOffsetY";

module.exports = JMessagePlugin



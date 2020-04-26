package cn.jiguang.cordova.verification;

import android.content.Context;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Iterator;

import cn.jiguang.verifysdk.api.AuthPageEventListener;
import cn.jiguang.verifysdk.api.JVerificationInterface;
import cn.jiguang.verifysdk.api.JVerifyUIConfig;
import cn.jiguang.verifysdk.api.PreLoginListener;
import cn.jiguang.verifysdk.api.RequestCallback;
import cn.jiguang.verifysdk.api.VerifyListener;

public class JVerificationPlugin extends CordovaPlugin {
    private static final String TAG = "JVerificationPlugin";
    private Context mContext;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        mContext = cordova.getActivity().getApplicationContext();
    }

    @Override
    public boolean execute(final String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "execute:" + action);
        cordova.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Method method = JVerificationPlugin.class.getDeclaredMethod(action, JSONArray.class,
                            CallbackContext.class);
                    method.invoke(JVerificationPlugin.this, args, callbackContext);
                } catch (Throwable e) {
                    e.printStackTrace();
                    Log.e(TAG, e.toString());
                }
            }
        });
        return true;
    }


    void init(JSONArray data, final CallbackContext callbackContext) {
        JVerificationInterface.init(mContext, new RequestCallback<String>() {
            @Override
            public void onResult(int code, String msg) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("code", code);
                    jsonObject.put("msg", msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendJsonObject(jsonObject, callbackContext);
            }
        });
    }


    void initTimeOut(JSONArray data, final CallbackContext callbackContext) throws JSONException {
        int timeOut = data.getInt(0);
        JVerificationInterface.init(mContext, timeOut, new RequestCallback<String>() {
            @Override
            public void onResult(int code, String msg) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("code", code);
                    jsonObject.put("msg", msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendJsonObject(jsonObject, callbackContext);
            }
        });
    }

    void setDebugMode(JSONArray data, CallbackContext callbackContext) throws JSONException {
        boolean debug = data.getBoolean(0);
        JVerificationInterface.setDebugMode(debug);
    }

    void isInitSuccess(JSONArray data, CallbackContext callbackContext) {
        boolean initSuccess = JVerificationInterface.isInitSuccess();
        callbackContext.success(initSuccess + "");
    }

    void checkVerifyEnable(JSONArray data, CallbackContext callbackContext) {
        boolean enable = JVerificationInterface.checkVerifyEnable(mContext);
        callbackContext.success(enable + "");
    }

    void getToken(JSONArray data, final CallbackContext callbackContext) throws JSONException {
        int timeOut = data.getInt(0);
        JVerificationInterface.getToken(mContext, timeOut, new VerifyListener() {
            @Override
            public void onResult(int code, String content, String operator) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("code", code);
                    jsonObject.put("content", content);
                    jsonObject.put("operator", operator);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                sendJsonObject(jsonObject, callbackContext);

            }
        });
    }

    void clearPreLoginCache(JSONArray data, final CallbackContext callbackContext) {
        JVerificationInterface.clearPreLoginCache();
    }

    void preLogin(JSONArray data, final CallbackContext callbackContext) throws JSONException {
        int timeOut = data.getInt(0);
        JVerificationInterface.preLogin(mContext, timeOut, new PreLoginListener() {
            @Override
            public void onResult(int code, String content) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("code", code);
                    jsonObject.put("content", content);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                sendJsonObject(jsonObject, callbackContext);

            }
        });
    }


//    void loginAuth(JSONArray data, final CallbackContext callbackContext) throws JSONException {
//        boolean autoFinish = data.getBoolean(0);
//        JVerificationInterface.loginAuth(mContext, autoFinish, new VerifyListener() {
//            @Override
//            public void onResult(int code, String content, String operator) {
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put("code", code);
//                    jsonObject.put("content", content);
//                    jsonObject.put("operator", operator);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                sendJsonObject(jsonObject, callbackContext);
//
//            }
//        });
//    }

    void loginAuth(JSONArray data, final CallbackContext callbackContext) throws JSONException {

        boolean autoFinish = data.getBoolean(0);
        JVerificationInterface.loginAuth(mContext, autoFinish, new VerifyListener() {
            @Override
            public void onResult(int code, String content, String operator) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("code", code);
                    jsonObject.put("content", content);
                    jsonObject.put("operator", operator);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, jsonObject.toString());
                pluginResult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginResult);

//                sendJsonObject(jsonObject, callbackContext);

            }
        }, new AuthPageEventListener() {
            @Override
            public void onEvent(int code, String content) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("code", code);
                    jsonObject.put("content", content);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                PluginResult pluginResult = new PluginResult(PluginResult.Status.ERROR, jsonObject.toString());
                pluginResult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginResult);

//                callbackContext.error(jsonObject.toString());
            }
        });
    }

    void dismissLoginAuth(JSONArray data, CallbackContext callbackContext) throws JSONException {
        JVerificationInterface.dismissLoginAuthActivity();
    }

    void dismissLoginAuthFinish(JSONArray data, final CallbackContext callbackContext) throws JSONException {
        boolean autoFinish = data.getBoolean(0);
        JVerificationInterface.dismissLoginAuthActivity(autoFinish, new RequestCallback<String>() {
            @Override
            public void onResult(int code, String desc) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("code", code);
                    jsonObject.put("desc", desc);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendJsonObject(jsonObject, callbackContext);
            }
        });
    }


    void getSmsCode(JSONArray data, CallbackContext callbackContext) {
        Context context = mContext;
        String phonenum = data.optString(0);
        String sign_id = data.optString(1);
        String temp_id = data.optString(2);
        JVerificationInterface.getSmsCode(context, phonenum, sign_id, temp_id, new RequestCallback<String>() {
            @Override
            public void onResult(int code, String msg) {

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("code", code);
                    jsonObject.put("msg", msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendJsonObject(jsonObject, callbackContext);
            }
        });
    }

    void setSmsIntervalTime(JSONArray data, CallbackContext callbackContext) {
        long intervalTime = data.optLong(0);
        JVerificationInterface.setSmsIntervalTime(intervalTime);
    }

    //设置授权页背景
    private static final String setAuthBGImgPath = "setAuthBGImgPath";
    //    状态栏
    private static final String setStatusBarColorWithNav = "setStatusBarColorWithNav";
    private static final String setStatusBarDarkMode = "setStatusBarDarkMode";
    private static final String setStatusBarTransparent = "setStatusBarTransparent";
    private static final String setStatusBarHidden = "setStatusBarHidden";
    private static final String setVirtualButtonTransparent = "setVirtualButtonTransparent";
    //    授权页导航栏
    private static final String setNavColor = "setNavColor";
    private static final String setNavText = "setNavText";
    private static final String setNavTextColor = "setNavTextColor";
    private static final String setNavReturnImgPath = "setNavReturnImgPath";
    private static final String setNavTransparent = "setNavTransparent";
    private static final String setNavTextSize = "setNavTextSize";
    private static final String setNavReturnBtnHidden = "setNavReturnBtnHidden";
    private static final String setNavReturnBtnWidth = "setNavReturnBtnWidth";
    private static final String setNavReturnBtnHeight = "setNavReturnBtnHeight";
    private static final String setNavReturnBtnOffsetX = "setNavReturnBtnOffsetX";
    private static final String setNavReturnBtnRightOffsetX = "setNavReturnBtnRightOffsetX";
    private static final String setNavReturnBtnOffsetY = "setNavReturnBtnOffsetY";
    private static final String setNavHidden = "setNavHidden";
    //    授权页logo
    private static final String setLogoWidth = "setLogoWidth";
    private static final String setLogoHeight = "setLogoHeight";
    private static final String setLogoHidden = "setLogoHidden";
    private static final String setLogoOffsetY = "setLogoOffsetY";
    private static final String setLogoImgPath = "setLogoImgPath";
    private static final String setLogoOffsetX = "setLogoOffsetX";
    private static final String setLogoOffsetBottomY = "setLogoOffsetBottomY";
    //    授权页号码栏
    private static final String setNumberColor = "setNumberColor";
    private static final String setNumberSize = "setNumberSize";
    private static final String setNumFieldOffsetY = "setNumFieldOffsetY";
    private static final String setNumFieldOffsetX = "setNumFieldOffsetX";
    private static final String setNumberFieldOffsetBottomY = "setNumberFieldOffsetBottomY";
    private static final String setNumberFieldWidth = "setNumberFieldWidth";
    private static final String setNumberFieldHeight = "setNumberFieldHeight";
    //    授权页登录按钮
    private static final String setLogBtnText = "setLogBtnText";
    private static final String setLogBtnTextColor = "setLogBtnTextColor";
    private static final String setLogBtnImgPath = "setLogBtnImgPath";
    private static final String setLogBtnOffsetY = "setLogBtnOffsetY";
    private static final String setLogBtnOffsetX = "setLogBtnOffsetX";
    private static final String setLogBtnWidth = "setLogBtnWidth";
    private static final String setLogBtnHeight = "setLogBtnHeight";
    private static final String setLogBtnTextSize = "setLogBtnTextSize";
    private static final String setLogBtnBottomOffsetY = "setLogBtnBottomOffsetY";
    //    授权页隐私栏
    private static final String setAppPrivacyOne = "setAppPrivacyOne";
    private static final String setAppPrivacyTwo = "setAppPrivacyTwo";
    private static final String setAppPrivacyColor = "setAppPrivacyColor";
    private static final String setPrivacyOffsetY = "setPrivacyOffsetY";
    private static final String setCheckedImgPath = "setCheckedImgPath";
    private static final String setUncheckedImgPath = "setUncheckedImgPath";
    private static final String setPrivacyState = "setPrivacyState";
    private static final String setPrivacyOffsetX = "setPrivacyOffsetX";
    private static final String setPrivacyTextCenterGravity = "setPrivacyTextCenterGravity";
    private static final String setPrivacyText = "setPrivacyText";
    private static final String setPrivacyTextSize = "setPrivacyTextSize";
    private static final String setPrivacyTopOffsetY = "setPrivacyTopOffsetY";
    private static final String setPrivacyCheckboxHidden = "setPrivacyCheckboxHidden";
    private static final String setPrivacyCheckboxSize = "setPrivacyCheckboxSize";
    private static final String setPrivacyWithBookTitleMark = "setPrivacyWithBookTitleMark";
    private static final String setPrivacyCheckboxInCenter = "setPrivacyCheckboxInCenter";
    private static final String setPrivacyTextWidth = "setPrivacyTextWidth";
    //    private static final String enableHintToast = "enableHintToast";
    //    授权页隐私协议web页面
    private static final String setPrivacyNavColor = "setPrivacyNavColor";
    private static final String setPrivacyNavTitleTextColor = "setPrivacyNavTitleTextColor";
    private static final String setPrivacyNavTitleTextSize = "setPrivacyNavTitleTextSize";
    private static final String setAppPrivacyNavTitle1 = "setAppPrivacyNavTitle1";
    private static final String setAppPrivacyNavTitle2 = "setAppPrivacyNavTitle2";
    private static final String setPrivacyStatusBarColorWithNav = "setPrivacyStatusBarColorWithNav";
    private static final String setPrivacyStatusBarDarkMode = "setPrivacyStatusBarDarkMode";
    private static final String setPrivacyStatusBarTransparent = "setPrivacyStatusBarTransparent";
    private static final String setPrivacyStatusBarHidden = "setPrivacyStatusBarHidden";
    private static final String setPrivacyVirtualButtonTransparent = "setPrivacyVirtualButtonTransparent";
    //    授权页slogan
    private static final String setSloganTextColor = "setSloganTextColor";
    private static final String setSloganOffsetY = "setSloganOffsetY";
    private static final String setSloganOffsetX = "setSloganOffsetX";
    private static final String setSloganBottomOffsetY = "setSloganBottomOffsetY";
    private static final String setSloganTextSize = "setSloganTextSize";
    private static final String setSloganHidden = "setSloganHidden";
    //    授权页动画
    private static final String setNeedStartAnim = "setNeedStartAnim";
    private static final String setNeedCloseAnim = "setNeedCloseAnim";
    //   授权页弹窗模式 setDialogTheme
    private static final String setDialogTheme = "setDialogTheme";

    void setCustomUIWithConfig(JSONArray data, CallbackContext callbackContext) throws JSONException {
        JSONObject jsonObjectPortrait = new JSONObject(data.getString(0));
        JVerifyUIConfig.Builder uiConfigPortrait = getBuilder(jsonObjectPortrait);
        JVerificationInterface.setCustomUIWithConfig(uiConfigPortrait.build());


    }

    void setCustomUIWithConfigAndroid(JSONArray data, CallbackContext callbackContext) throws JSONException {
        JSONObject jsonObjectPortrait = new JSONObject(data.getString(0));
        JVerifyUIConfig.Builder uiConfigPortrait = getBuilder(jsonObjectPortrait);
        JSONObject jsonObjectLandscape = new JSONObject(data.getString(1));
        JVerifyUIConfig.Builder uiConfigLandscape = getBuilder(jsonObjectLandscape);
        JVerificationInterface.setCustomUIWithConfig(uiConfigPortrait.build(), uiConfigLandscape.build());
    }


    private void sendJsonObject(JSONObject jsonObject, CallbackContext callbackContext) {
        callbackContext.success(jsonObject.toString());
    }

    private JVerifyUIConfig.Builder getBuilder(JSONObject jsonObject) throws JSONException {
        Iterator<String> keys = jsonObject.keys();

        JVerifyUIConfig.Builder uiConfigBuilder = new JVerifyUIConfig.Builder();

        while (keys.hasNext()) {
            String key = keys.next();
            setUiConfig(uiConfigBuilder, jsonObject, key);
        }
        return uiConfigBuilder;
    }

    private void setUiConfig(JVerifyUIConfig.Builder uiConfigBuilder, JSONObject jsonObject, String key) throws JSONException {
        //设置授权页背景
        if (setAuthBGImgPath.equals(key)) {
            uiConfigBuilder.setAuthBGImgPath(jsonObject.getString(key));
        }
        //    授权页导航栏
        else if (setNavColor.equals(key)) {
            uiConfigBuilder.setNavColor(jsonObject.getInt(key));
        } else if (setNavText.equals(key)) {
            uiConfigBuilder.setNavText(jsonObject.getString(key));
        } else if (setNavTextColor.equals(key)) {
            uiConfigBuilder.setNavTextColor(jsonObject.getInt(key));
        } else if (setNavReturnImgPath.equals(key)) {
            uiConfigBuilder.setNavReturnImgPath(jsonObject.getString(key));
        } else if (setNavTransparent.equals(key)) {
            uiConfigBuilder.setNavTransparent(jsonObject.getBoolean(key));
        } else if (setNavTextSize.equals(key)) {
            uiConfigBuilder.setNavTextSize(jsonObject.getInt(key));
        } else if (setNavReturnBtnHidden.equals(key)) {
            uiConfigBuilder.setNavReturnBtnHidden(jsonObject.getBoolean(key));
        } else if (setNavReturnBtnWidth.equals(key)) {
            uiConfigBuilder.setNavReturnBtnWidth(jsonObject.getInt(key));
        } else if (setNavReturnBtnHeight.equals(key)) {
            uiConfigBuilder.setNavReturnBtnHeight(jsonObject.getInt(key));
        } else if (setNavReturnBtnOffsetX.equals(key)) {
            uiConfigBuilder.setNavReturnBtnOffsetX(jsonObject.getInt(key));
        } else if (setNavReturnBtnRightOffsetX.equals(key)) {
            uiConfigBuilder.setNavReturnBtnRightOffsetX(jsonObject.getInt(key));
        } else if (setNavReturnBtnOffsetY.equals(key)) {
            uiConfigBuilder.setNavReturnBtnOffsetY(jsonObject.getInt(key));
        } else if (setNavHidden.equals(key)) {
            uiConfigBuilder.setNavHidden(jsonObject.getBoolean(key));
        }

        //    授权页logo
        else if (setLogoWidth.equals(key)) {
            uiConfigBuilder.setLogoWidth(jsonObject.getInt(key));
        } else if (setLogoHeight.equals(key)) {
            uiConfigBuilder.setLogoHeight(jsonObject.getInt(key));
        } else if (setLogoHidden.equals(key)) {
            uiConfigBuilder.setLogoHidden(jsonObject.getBoolean(key));
        } else if (setLogoOffsetY.equals(key)) {
            uiConfigBuilder.setLogoOffsetY(jsonObject.getInt(key));
        } else if (setLogoImgPath.equals(key)) {
            uiConfigBuilder.setLogoImgPath(jsonObject.getString(key));
        } else if (setLogoOffsetX.equals(key)) {
            uiConfigBuilder.setLogoOffsetX(jsonObject.getInt(key));
        } else if (setLogoOffsetBottomY.equals(key)) {
            uiConfigBuilder.setLogoOffsetBottomY(jsonObject.getInt(key));
        }

//    授权页号码栏
        else if (setNumberColor.equals(key)) {
            uiConfigBuilder.setNumberColor(jsonObject.getInt(key));
        } else if (setNumberSize.equals(key)) {
            uiConfigBuilder.setNumberSize(jsonObject.getInt(key));
        } else if (setNumFieldOffsetY.equals(key)) {
            uiConfigBuilder.setNumFieldOffsetY(jsonObject.getInt(key));
        } else if (setNumFieldOffsetX.equals(key)) {
            uiConfigBuilder.setNumFieldOffsetX(jsonObject.getInt(key));
        } else if (setNumberFieldOffsetBottomY.equals(key)) {
            uiConfigBuilder.setNumberFieldOffsetBottomY(jsonObject.getInt(key));
        } else if (setNumberFieldWidth.equals(key)) {
            uiConfigBuilder.setNumberFieldWidth(jsonObject.getInt(key));
        } else if (setNumberFieldHeight.equals(key)) {
            uiConfigBuilder.setNumberFieldHeight(jsonObject.getInt(key));
        }


//    授权页登录按钮
        else if (setLogBtnText.equals(key)) {
            uiConfigBuilder.setLogBtnText(jsonObject.getString(key));
        } else if (setLogBtnTextColor.equals(key)) {
            uiConfigBuilder.setLogBtnTextColor(jsonObject.getInt(key));
        } else if (setLogBtnImgPath.equals(key)) {
            uiConfigBuilder.setLogBtnImgPath(jsonObject.getString(key));
        } else if (setLogBtnOffsetY.equals(key)) {
            uiConfigBuilder.setLogBtnOffsetY(jsonObject.getInt(key));
        } else if (setLogBtnOffsetX.equals(key)) {
            uiConfigBuilder.setLogBtnOffsetX(jsonObject.getInt(key));
        } else if (setLogBtnWidth.equals(key)) {
            uiConfigBuilder.setLogBtnWidth(jsonObject.getInt(key));
        } else if (setLogBtnHeight.equals(key)) {
            uiConfigBuilder.setLogBtnHeight(jsonObject.getInt(key));
        } else if (setLogBtnTextSize.equals(key)) {
            uiConfigBuilder.setLogBtnTextSize(jsonObject.getInt(key));
        } else if (setLogBtnBottomOffsetY.equals(key)) {
            uiConfigBuilder.setLogBtnBottomOffsetY(jsonObject.getInt(key));
        }

//    授权页隐私栏
        else if (setAppPrivacyOne.equals(key)) {
            JSONArray jsonArraySetAppPrivacyOne = jsonObject.getJSONArray(key);
            uiConfigBuilder.setAppPrivacyOne(jsonArraySetAppPrivacyOne.getString(0), jsonArraySetAppPrivacyOne.getString(1));
        } else if (setAppPrivacyTwo.equals(key)) {
            JSONArray jsonArraySetAppPrivacyTwo = jsonObject.getJSONArray(key);
            uiConfigBuilder.setAppPrivacyTwo(jsonArraySetAppPrivacyTwo.getString(0), jsonArraySetAppPrivacyTwo.getString(1));
        } else if (setAppPrivacyColor.equals(key)) {
            JSONArray jsonArraySetAppPrivacyColor = jsonObject.getJSONArray(key);
            uiConfigBuilder.setAppPrivacyColor(jsonArraySetAppPrivacyColor.getInt(0), jsonArraySetAppPrivacyColor.getInt(1));
        } else if (setPrivacyOffsetY.equals(key)) {
            uiConfigBuilder.setPrivacyOffsetY(jsonObject.getInt(key));
        } else if (setCheckedImgPath.equals(key)) {
            uiConfigBuilder.setCheckedImgPath(jsonObject.getString(key));
        } else if (setUncheckedImgPath.equals(key)) {
            uiConfigBuilder.setUncheckedImgPath(jsonObject.getString(key));
        } else if (setPrivacyState.equals(key)) {
            uiConfigBuilder.setPrivacyState(jsonObject.getBoolean(key));
        } else if (setPrivacyOffsetX.equals(key)) {
            uiConfigBuilder.setPrivacyOffsetX(jsonObject.getInt(key));
        } else if (setPrivacyTextCenterGravity.equals(key)) {
            uiConfigBuilder.setPrivacyTextCenterGravity(jsonObject.getBoolean(key));
        } else if (setPrivacyText.equals(key)) {
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            uiConfigBuilder.setPrivacyText(jsonArray.getString(0), jsonArray.getString(1), jsonArray.getString(2), jsonArray.getString(3));
        } else if (setPrivacyTextSize.equals(key)) {
            uiConfigBuilder.setPrivacyTextSize(jsonObject.getInt(key));
        } else if (setPrivacyTopOffsetY.equals(key)) {
            uiConfigBuilder.setPrivacyTopOffsetY(jsonObject.getInt(key));
        } else if (setPrivacyCheckboxHidden.equals(key)) {
            uiConfigBuilder.setPrivacyCheckboxHidden(jsonObject.getBoolean(key));
        } else if (setPrivacyCheckboxSize.equals(key)) {
            uiConfigBuilder.setPrivacyCheckboxSize(jsonObject.getInt(key));
        } else if (setPrivacyWithBookTitleMark.equals(key)) {
            uiConfigBuilder.setPrivacyWithBookTitleMark(jsonObject.getBoolean(key));
        } else if (setPrivacyCheckboxInCenter.equals(key)) {
            uiConfigBuilder.setPrivacyCheckboxInCenter(jsonObject.getBoolean(key));
        } else if (setPrivacyTextWidth.equals(key)) {
            uiConfigBuilder.setPrivacyTextWidth(jsonObject.getInt(key));
        }
        //    授权页隐私协议web页面

        else if (setPrivacyNavColor.equals(key)) {
            uiConfigBuilder.setPrivacyNavColor(jsonObject.getInt(key));
        } else if (setPrivacyNavTitleTextColor.equals(key)) {
            uiConfigBuilder.setPrivacyNavTitleTextColor(jsonObject.getInt(key));
        } else if (setPrivacyNavTitleTextSize.equals(key)) {
            uiConfigBuilder.setPrivacyNavTitleTextSize(jsonObject.getInt(key));
        } else if (setAppPrivacyNavTitle1.equals(key)) {
            uiConfigBuilder.setAppPrivacyNavTitle1(jsonObject.getString(key));
        } else if (setAppPrivacyNavTitle2.equals(key)) {
            uiConfigBuilder.setAppPrivacyNavTitle2(jsonObject.getString(key));
        } else if (setPrivacyStatusBarColorWithNav.equals(key)) {
            uiConfigBuilder.setPrivacyStatusBarColorWithNav(jsonObject.getBoolean(key));
        } else if (setPrivacyStatusBarDarkMode.equals(key)) {
            uiConfigBuilder.setPrivacyStatusBarDarkMode(jsonObject.getBoolean(key));
        } else if (setPrivacyStatusBarTransparent.equals(key)) {
            uiConfigBuilder.setPrivacyStatusBarTransparent(jsonObject.getBoolean(key));
        } else if (setPrivacyStatusBarHidden.equals(key)) {
            uiConfigBuilder.setPrivacyStatusBarHidden(jsonObject.getBoolean(key));
        } else if (setPrivacyVirtualButtonTransparent.equals(key)) {
            uiConfigBuilder.setPrivacyVirtualButtonTransparent(jsonObject.getBoolean(key));
        }

//    授权页slogan
        else if (setSloganTextColor.equals(key)) {
            uiConfigBuilder.setSloganTextColor(jsonObject.getInt(key));
        } else if (setSloganOffsetY.equals(key)) {
            uiConfigBuilder.setSloganOffsetY(jsonObject.getInt(key));
        } else if (setSloganOffsetX.equals(key)) {
            uiConfigBuilder.setSloganOffsetX(jsonObject.getInt(key));
        } else if (setSloganBottomOffsetY.equals(key)) {
            uiConfigBuilder.setSloganBottomOffsetY(jsonObject.getInt(key));
        } else if (setSloganTextSize.equals(key)) {
            uiConfigBuilder.setSloganTextSize(jsonObject.getInt(key));
        } else if (setSloganHidden.equals(key)) {
            uiConfigBuilder.setSloganHidden(jsonObject.getBoolean(key));
        }
        //    授权页动画
        else if (setNeedStartAnim.equals(key)) {
            uiConfigBuilder.setNeedStartAnim(jsonObject.getBoolean(key));
        } else if (setNeedCloseAnim.equals(key)) {
            uiConfigBuilder.setNeedCloseAnim(jsonObject.getBoolean(key));
        }
        //   授权页弹窗模式 setDialogTheme
        else if (setDialogTheme.equals(key)) {
            JSONArray jsonArray = jsonObject.getJSONArray(key);
            uiConfigBuilder.setDialogTheme(jsonArray.getInt(0), jsonArray.getInt(1),
                    jsonArray.getInt(2), jsonArray.getInt(3), jsonArray.getBoolean(4));
        }
    }
}
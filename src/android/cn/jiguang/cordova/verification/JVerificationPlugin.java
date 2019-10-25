package cn.jiguang.cordova.verification;

import android.content.Context;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Iterator;

import cn.jiguang.verifysdk.api.JVerificationInterface;
import cn.jiguang.verifysdk.api.JVerifyUIConfig;
import cn.jiguang.verifysdk.api.PreLoginListener;
import cn.jiguang.verifysdk.api.VerifyListener;

public class JVerificationPlugin extends CordovaPlugin {
    private static final String TAG = "JVerificationPlugin";
    private Context mContext;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        mContext = cordova.getContext().getApplicationContext();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "execute:"+action);
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



    void init(JSONArray data, CallbackContext callbackContext) {
        JVerificationInterface.init(mContext);
    }

    void setDebugMode(JSONArray data, CallbackContext callbackContext) throws JSONException {
        boolean debug = data.getBoolean(0);
        JVerificationInterface.setDebugMode(debug);
    }

    void isInitSuccess(JSONArray data, CallbackContext callbackContext) {
        boolean initSuccess = JVerificationInterface.isInitSuccess();
        callbackContext.success(initSuccess+"");
    }

    void checkVerifyEnable(JSONArray data, CallbackContext callbackContext) {
        boolean enable = JVerificationInterface.checkVerifyEnable(mContext);
        callbackContext.success(enable + "");
    }

    void getToken(JSONArray data, CallbackContext callbackContext) throws JSONException {
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

                callbackContext.success(jsonObject.toString());

            }
        });
    }

    void preLogin(JSONArray data, CallbackContext callbackContext) throws JSONException {
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

                callbackContext.success(jsonObject.toString());

            }
        });
    }


    void loginAuth(JSONArray data, CallbackContext callbackContext) throws JSONException {
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
                callbackContext.success(jsonObject.toString());

            }
        });
    }

    void dismissLoginAuth(JSONArray data, CallbackContext callbackContext) {
        JVerificationInterface.dismissLoginAuthActivity();
    }


    private static final String setAuthBGImgPath = "setAuthBGImgPath";
    private static final String setNavColor = "setNavColor";
    private static final String setNavText = "setNavText";
    private static final String setNavTextColor = "setNavTextColor";
    private static final String setNavReturnImgPath = "setNavReturnImgPath";
    private static final String setLogoWidth = "setLogoWidth";
    private static final String setLogoHeight = "setLogoHeight";
    private static final String setLogoHidden = "setLogoHidden";
    private static final String setLogoOffsetY = "setLogoOffsetY";
    private static final String setLogoImgPath = "setLogoImgPath";
    private static final String setNumberColor = "setNumberColor";
    private static final String setNumFieldOffsetY = "setNumFieldOffsetY";
    private static final String setLogBtnText = "setLogBtnText";
    private static final String setLogBtnTextColor = "setLogBtnTextColor";
    private static final String setLogBtnImgPath = "setLogBtnImgPath";
    private static final String setLogBtnOffsetY = "setLogBtnOffsetY";
    private static final String setAppPrivacyOne = "setAppPrivacyOne";
    private static final String setAppPrivacyTwo = "setAppPrivacyTwo";
    private static final String setAppPrivacyColor = "setAppPrivacyColor";
    private static final String setPrivacyOffsetY = "setPrivacyOffsetY";
    private static final String setCheckedImgPath = "setCheckedImgPath";
    private static final String setUncheckedImgPath = "setUncheckedImgPath";
    private static final String setSloganTextColor = "setSloganTextColor";
    private static final String setSloganOffsetY = "setSloganOffsetY";

    void setCustomUIWithConfig(JSONArray data, CallbackContext callbackContext) throws JSONException {
        JSONObject jsonObject = new JSONObject(data.getString(0));
        Iterator<String> keys = jsonObject.keys();

        JVerifyUIConfig.Builder uiConfigBuilder = new JVerifyUIConfig.Builder();

        while (keys.hasNext()) {
            String key = keys.next();
            setUiConfig(uiConfigBuilder, jsonObject, key);
        }

        JVerificationInterface.setCustomUIWithConfig(uiConfigBuilder.build());
    }


    void setUiConfig(JVerifyUIConfig.Builder uiConfigBuilder, JSONObject jsonObject, String key) throws JSONException {
        switch (key) {
            case setAuthBGImgPath:
                uiConfigBuilder.setAuthBGImgPath(jsonObject.getString(key));
                break;// "setAuthBGImgPath";
            case setNavColor:
                uiConfigBuilder.setNavColor(jsonObject.getInt(key));
                break;// "setNavColor";
            case setNavText:
                uiConfigBuilder.setNavText(jsonObject.getString(key));
                break;// "setNavText";
            case setNavTextColor:
                uiConfigBuilder.setNavTextColor(jsonObject.getInt(key));
                break;// "setNavTextColor";
            case setNavReturnImgPath:
                uiConfigBuilder.setNavReturnImgPath(jsonObject.getString(key));
                break;// "setNavReturnImgPath";
            case setLogoWidth:
                uiConfigBuilder.setLogoWidth(jsonObject.getInt(key));
                break;// "setLogoWidth";
            case setLogoHeight:
                uiConfigBuilder.setLogoHeight(jsonObject.getInt(key));
                break;// "setLogoHeight";
            case setLogoHidden:
                uiConfigBuilder.setLogoHidden(jsonObject.getBoolean(key));
                break;// "setLogoHidden";
            case setLogoOffsetY:
                uiConfigBuilder.setLogoOffsetY(jsonObject.getInt(key));
                break;// "setLogoOffsetY";
            case setLogoImgPath:
                uiConfigBuilder.setLogoImgPath(jsonObject.getString(key));
                break;// "setLogoImgPath";
            case setNumberColor:
                uiConfigBuilder.setNumberColor(jsonObject.getInt(key));
                break;// "setNumberColor";
            case setNumFieldOffsetY:
                uiConfigBuilder.setNumFieldOffsetY(jsonObject.getInt(key));
                break;// "setNumFieldOffsetY";
            case setLogBtnText:
                uiConfigBuilder.setLogBtnText(jsonObject.getString(key));
                break;// "setLogBtnText";
            case setLogBtnTextColor:
                uiConfigBuilder.setLogBtnTextColor(jsonObject.getInt(key));
                break;// "setLogBtnTextColor";
            case setLogBtnImgPath:
                uiConfigBuilder.setLogBtnImgPath(jsonObject.getString(key));
                break;// "setLogBtnImgPath";
            case setLogBtnOffsetY:
                uiConfigBuilder.setLogBtnOffsetY(jsonObject.getInt(key));
                break;// "setLogBtnOffsetY";
            case setAppPrivacyOne:
                JSONArray jsonArraySetAppPrivacyOne = jsonObject.getJSONArray(key);
                uiConfigBuilder.setAppPrivacyOne(jsonArraySetAppPrivacyOne.getString(0), jsonArraySetAppPrivacyOne.getString(1));
                break;// "setAppPrivacyOne";
            case setAppPrivacyTwo:
                JSONArray jsonArraySetAppPrivacyTwo = jsonObject.getJSONArray(key);
                uiConfigBuilder.setAppPrivacyTwo(jsonArraySetAppPrivacyTwo.getString(0), jsonArraySetAppPrivacyTwo.getString(1));
                break;// "setAppPrivacyTwo";
            case setAppPrivacyColor:
                JSONArray jsonArraySetAppPrivacyColor = jsonObject.getJSONArray(key);
                uiConfigBuilder.setAppPrivacyColor(jsonArraySetAppPrivacyColor.getInt(0), jsonArraySetAppPrivacyColor.getInt(1));
                break;// "setAppPrivacyColor";
            case setPrivacyOffsetY:
                uiConfigBuilder.setPrivacyOffsetY(jsonObject.getInt(key));
                break;// "setPrivacyOffsetY";
            case setCheckedImgPath:
                uiConfigBuilder.setCheckedImgPath(jsonObject.getString(key));
                break;// "setCheckedImgPath";
            case setUncheckedImgPath:
                uiConfigBuilder.setUncheckedImgPath(jsonObject.getString(key));
                break;// "setUncheckedImgPath";
            case setSloganTextColor:
                uiConfigBuilder.setSloganTextColor(jsonObject.getInt(key));
                break;// "setSloganTextColor";
            case setSloganOffsetY:
                uiConfigBuilder.setSloganOffsetY(jsonObject.getInt(key));
                break;// "setSloganOffsetY";
        }
    }
}
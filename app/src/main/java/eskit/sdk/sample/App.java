package eskit.sdk.sample;

import androidx.multidex.MultiDexApplication;

import com.extscreen.runtime.EsKitInitHelper;

import eskit.sdk.core.InitConfig;

/**
 * 说明
 * <p>
 * Create by weipeng on 2022/07/11 11:47
 */
public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化SDK
        EsKitInitHelper.init(this, InitConfig.getDefault()
                .setAppId("74f156bd9d7ad3e9") // 申请的APPID,每个apk包名对应一个id
                .setChannel("sample")
                .setDebug(BuildConfig.DEBUG)
                .addFlags(InitConfig.FLAG_DYNAMIC_SO));
    }
}

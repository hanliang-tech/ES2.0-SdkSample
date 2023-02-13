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
                .setDebug(BuildConfig.DEBUG)
                .addFlags(InitConfig.FLAG_DYNAMIC_SO));
    }
}

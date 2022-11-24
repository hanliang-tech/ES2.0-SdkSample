package eskit.sdk.sample;

import androidx.multidex.MultiDexApplication;

/**
 * 说明
 * <p>
 * Create by weipeng on 2022/07/11 11:47
 */
public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        new ESKitInitHelper().init(this);
    }
}

package eskit.sdk.sample;

import androidx.multidex.MultiDexApplication;

import eskit.sdk.core.EsManager;
import eskit.sdk.core.InitConfig;
import eskit.sdk.support.imageloader.GlideImageLoader;
import eskit.sdk.support.player.ijk.component.IJKPlayerComponent;
import eskit.sdk.support.player.ijk.module.ESPlayerIJKModule;

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
        EsManager.get().init(this, InitConfig.getDefault()
                .appId("后台申请KEY")
                .channel("集成渠道")
                .debug(BuildConfig.DEBUG));

        // 注册图片组件
        EsManager.get().setImageLoader(GlideImageLoader.class.getName());
        // 注册Vue组件
        // 视频组件
        EsManager.get().registerComponent(IJKPlayerComponent.class.getName());
        // 注册Module
        EsManager.get().registerModule(ESPlayerIJKModule.class.getName());
    }
}

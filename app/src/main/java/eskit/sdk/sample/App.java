package eskit.sdk.sample;

import androidx.multidex.MultiDexApplication;

import eskit.sdk.core.EsManager;
import eskit.sdk.core.InitConfig;
import eskit.sdk.support.asyncplayer.ESAsyncPlayerModule;
import eskit.sdk.support.chart.ESBarChartViewComponent;
import eskit.sdk.support.chart.ESLineChartViewComponent;
import eskit.sdk.support.imageloader.GlideImageLoader;
import eskit.sdk.support.player.audio.android.ESAndroidAudioPlayerModule;
import eskit.sdk.support.player.audio.android.ESAndroidAudioPlayerServiceModule;
import eskit.sdk.support.player.audio.ijk.ESIJKAudioPlayerModule;
import eskit.sdk.support.player.audio.ijk.ESIJKAudioPlayerServiceModule;
import eskit.sdk.support.player.audio.soundpool.ESSoundPoolAudioPlayerModule;
import eskit.sdk.support.player.ijk.component.IJKPlayerComponent;
import eskit.sdk.support.player.ijk.module.ESPlayerIJKModule;
import eskit.sdk.support.webview.ESWebViewComponent;
import eskit.sdk.support.x5webview.ESX5WebViewComponent;

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
                .customServer("http://172.27.2.13:8888")
                .debug(BuildConfig.DEBUG));

        // 注册图片组件
        EsManager.get().setImageLoader(GlideImageLoader.class.getName());
        // 注册Vue组件
        // 视频组件
        EsManager.get().registerComponent(IJKPlayerComponent.class.getName());

        // 音频播放组件
        EsManager.get().registerModule(ESIJKAudioPlayerModule.class.getName());
        EsManager.get().registerModule(ESIJKAudioPlayerServiceModule.class.getName());
        EsManager.get().registerModule(ESAndroidAudioPlayerModule.class.getName());
        EsManager.get().registerModule(ESAndroidAudioPlayerServiceModule.class.getName());
        EsManager.get().registerModule(ESSoundPoolAudioPlayerModule.class.getName());
        EsManager.get().registerModule(ESAsyncPlayerModule.class.getName());

        // 注册Module
        EsManager.get().registerModule(ESPlayerIJKModule.class.getName());
        //图表
        EsManager.get().registerComponent(ESBarChartViewComponent.class.getName());
        EsManager.get().registerComponent(ESLineChartViewComponent.class.getName());

        // 注册网页组件
        EsManager.get().registerComponent(ESWebViewComponent.class.getName());
        EsManager.get().registerComponent(ESX5WebViewComponent.class.getName());
    }
}

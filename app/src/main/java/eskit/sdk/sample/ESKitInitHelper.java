package eskit.sdk.sample;

import android.app.Application;

import com.tencent.mtt.hippy.HippyAPIProvider;
import com.tencent.mtt.hippy.HippyEngineContext;
import com.tencent.mtt.hippy.common.Provider;
import com.tencent.mtt.hippy.modules.javascriptmodules.HippyJavaScriptModule;
import com.tencent.mtt.hippy.modules.nativemodules.HippyNativeModuleBase;
import com.tencent.mtt.hippy.uimanager.HippyViewController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eskit.sdk.core.EsKitInitCallback;
import eskit.sdk.core.EsManager;
import eskit.sdk.core.InitConfig;
import eskit.sdk.core.pm.EsPageController;
import eskit.sdk.core.pm.EsRouterController;
import eskit.sdk.core.pm.PageModule;
import eskit.sdk.support.ad.component.ADPlayerComponent;
import eskit.sdk.support.asyncplayer.ESAsyncPlayerModule;
import eskit.sdk.support.canvas.ESCanvasController;
import eskit.sdk.support.chart.ESBarChartViewComponent;
import eskit.sdk.support.chart.ESLineChartViewComponent;
import eskit.sdk.support.download.ESDownloadModule;
import eskit.sdk.support.gif.ESGifViewComponent;
import eskit.sdk.support.icon.ESAppIconViewComponent;
import eskit.sdk.support.image.crop.ESCroppedImageViewComponent;
import eskit.sdk.support.imageloader.GlideImageLoader;
import eskit.sdk.support.module.develop.AndroidDevelopManager;
import eskit.sdk.support.module.develop.Develop;
import eskit.sdk.support.player.audio.android.ESAndroidAudioPlayerModule;
import eskit.sdk.support.player.audio.android.ESAndroidAudioPlayerServiceModule;
import eskit.sdk.support.player.audio.ijk.ESIJKAudioPlayerModule;
import eskit.sdk.support.player.audio.ijk.ESIJKAudioPlayerServiceModule;
import eskit.sdk.support.player.audio.soundpool.ESSoundPoolAudioPlayerModule;
import eskit.sdk.support.player.ijk.component.IJKPlayerComponent;
import eskit.sdk.support.player.ijk.module.ESPlayerIJKModule;
import eskit.sdk.support.playmarkview.ESPlayMarkViewComponent;
import eskit.sdk.support.record.ESAudioRecordModule;
import eskit.sdk.support.rippleview.ESRippleViewComponent;
import eskit.sdk.support.runtime.device.DeviceIdManager;
import eskit.sdk.support.runtime.device.DeviceTypeManager;
import eskit.sdk.support.runtime.device.RuntimeDeviceModule;
import eskit.sdk.support.swiper.ESSwiperSlideViewComponent;
import eskit.sdk.support.ui.item.ItemFrameComponent;
import eskit.sdk.support.ui.item.ItemViewComponent;
import eskit.sdk.support.ui.item.RippleViewComponent;
import eskit.sdk.support.ui.item.TitleFocusComponent;
import eskit.sdk.support.ui.item.TitleNormalComponent;
import eskit.sdk.support.ui.largelist.LargeListComponent;
import eskit.sdk.support.ui.swiftlist.SwiftListComponent;
import eskit.sdk.support.upload.ESUploadModule;
import eskit.sdk.support.viewpager.tabs.ESRecyclerViewPagerController;
import eskit.sdk.support.viewpager.tabs.ESTabsController;
import eskit.sdk.support.voice.wave.ESVoiceWaveViewComponent;
import eskit.sdk.support.webview.ESWebViewComponent;
import eskit.sdk.support.x5webview.ESX5WebViewComponent;

/**
 * 初始化扩展屏
 * 测试命令: am start -d 'esapp://action/start?pkg=es.skill.weather.tv&from=cmd'
 **/
public class ESKitInitHelper implements EsKitInitCallback {

    /**
     * 初始化sdk
     **/
    public void init(Application context) {

        EsManager.get().init(context, InitConfig.getDefault()
                .setAppId("74f156bd9d7ad3e9") // 申请的APPID,每个apk包名对应一个id
                .setChannel("sample")      // APK渠道
                .addApiProvider(new CustomAPIProvider())
                .setDebug(BuildConfig.DEBUG)
                .setInitCallback(this));  // 初始化回调

        // 注册图片解析
        EsManager.get().setImageLoader(GlideImageLoader.class.getName());

        DeviceIdManager.getInstance().init(context);
        DeviceTypeManager.getInstance().init(context);

        // 注册组件
        EsManager.get().registerModule(
                RuntimeDeviceModule.class.getName(),
                ESAudioRecordModule.class.getName(),
                ESDownloadModule.class.getName(),
                ESUploadModule.class.getName()
        );
        //-----------------------------------------------------------------
        //播放视频
        EsManager.get().registerComponent(IJKPlayerComponent.class.getName());
        EsManager.get().registerModule(ESPlayerIJKModule.class.getName());
        EsManager.get().registerComponent(ADPlayerComponent.class.getName());
        //-----------------------------------------------------------------
        //播放音频
        EsManager.get().registerModule(ESIJKAudioPlayerModule.class.getName());
        EsManager.get().registerModule(ESIJKAudioPlayerServiceModule.class.getName());

        EsManager.get().registerModule(ESAndroidAudioPlayerModule.class.getName());
        EsManager.get().registerModule(ESAndroidAudioPlayerServiceModule.class.getName());

        EsManager.get().registerModule(ESSoundPoolAudioPlayerModule.class.getName());

        EsManager.get().registerModule(ESAsyncPlayerModule.class.getName());
        EsManager.get().registerComponent(ESVoiceWaveViewComponent.class.getName());
        EsManager.get().registerComponent(ESRippleViewComponent.class.getName());
        EsManager.get().registerComponent(ESPlayMarkViewComponent.class.getName());
        //
        EsManager.get().registerComponent(ItemViewComponent.class.getName());
        EsManager.get().registerComponent(TitleNormalComponent.class.getName());
        EsManager.get().registerComponent(TitleFocusComponent.class.getName());
        EsManager.get().registerComponent(RippleViewComponent.class.getName());
        EsManager.get().registerComponent(ItemFrameComponent.class.getName());

        EsManager.get().registerComponent(LargeListComponent.class.getName());
        EsManager.get().registerComponent(SwiftListComponent.class.getName());
        EsManager.get().registerComponent(ESSwiperSlideViewComponent.class.getName());
        EsManager.get().registerComponent(ESGifViewComponent.class.getName());
        //
        EsManager.get().registerComponent(ESWebViewComponent.class.getName());
        EsManager.get().registerComponent(ESX5WebViewComponent.class.getName());

        //图表
        EsManager.get().registerComponent(ESBarChartViewComponent.class.getName());
        EsManager.get().registerComponent(ESLineChartViewComponent.class.getName());

        //
        EsManager.get().registerComponent(ESAppIconViewComponent.class.getName());
        // 拼图组件
        EsManager.get().registerComponent(ESCroppedImageViewComponent.class.getName());

    }

    /**
     * SDK 初始化成功
     **/
    @Override
    public void onEsKitInitSuccess() {
        try {
            Develop develop = AndroidDevelopManager.getInstance().getDevelop();
            if (develop != null) {
                develop.setChannel("APP渠道");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    private class CustomAPIProvider implements HippyAPIProvider {
        @Override
        public List<Class<? extends HippyViewController>> getControllers() {
            List<Class<? extends HippyViewController>> components = new ArrayList<>();
            components.add(ESRecyclerViewPagerController.class);
            components.add(ESTabsController.class);
            components.add(ESCanvasController.class);
            components.add(EsPageController.class);
            components.add(EsRouterController.class);
            return components;
        }

        @Override
        public Map<Class<? extends HippyNativeModuleBase>, Provider<? extends HippyNativeModuleBase>> getNativeModules(HippyEngineContext context) {
            Map<Class<? extends HippyNativeModuleBase>, Provider<? extends HippyNativeModuleBase>> modules = new HashMap<>();
            modules.put(PageModule.class, (Provider<PageModule>) () -> new PageModule(context));
            return modules;
        }

        @Override
        public List<Class<? extends HippyJavaScriptModule>> getJavaScriptModules() {
            return null;
        }
    }

}

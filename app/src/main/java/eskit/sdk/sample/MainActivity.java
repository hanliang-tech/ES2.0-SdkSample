package eskit.sdk.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import eskit.sdk.core.EsData;
import eskit.sdk.core.EsManager;

/**
 * 说明
 * <p>
 * Create by weipeng on 2022/07/11 12:57
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startEsApp();
    }

    private void startEsApp() {
        EsData data = new EsData();
        data.setAppPackage("es.hello.world");
        // 自定义下载链接
//        data.setLoadType(LoadType.CUSTOM_SERVER);
//        data.setAppDownloadUrl(getDownloadUrl());

        EsManager.get().start(data);
    }

    private String getDownloadUrl() {
        return "https://www.eskit.tv/download/eskit-sdk-sample.es";
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}

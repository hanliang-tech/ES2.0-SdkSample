package eskit.sdk.sample;

import android.os.Bundle;

import androidx.annotation.Nullable;

import eskit.sdk.core.EsData;
import eskit.sdk.core.ui.BrowserStandardActivity;

/**
 * 说明
 * <p>
 * Create by weipeng on 2022/07/11 12:57
 */
public class MainActivity extends BrowserStandardActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        EsData data = new EsData();

        // 设置要启动的应用包名
        data.setAppPackage("es.hello.world");

        getIntent().putExtra("data", data);
        super.onCreate(savedInstanceState);
    }

}

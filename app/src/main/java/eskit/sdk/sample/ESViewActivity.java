package eskit.sdk.sample;

import static eskit.sdk.core.protocol.EsProtocolDispatcher.K_FROM;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import eskit.sdk.core.EsData;
import eskit.sdk.core.EsManager;
import eskit.sdk.core.count.BaseEvent;
import eskit.sdk.core.internal.IEsActivityProxy;
import eskit.sdk.support.EsException;
import eskit.sdk.support.args.EsMap;

public class ESViewActivity extends AppCompatActivity {

    private final String ES_APP_PACKAGE = "es.hello.world";

    private IEsActivityProxy mActivityProxy;

    private FrameLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_es_view);

        rootView = this.findViewById(R.id.es_root_view);

        initES();
    }

    private void initES() {
        EsData data = new EsData();
        data.setAppPackage(ES_APP_PACKAGE);
        EsMap exp = new EsMap();
        EsMap from = new EsMap();
        from.pushObject(BaseEvent.ES_REFERER, BaseEvent.FROM_OUTER);
        from.pushObject(BaseEvent.ES_REFERER1, getPackageName());
        from.pushObject(BaseEvent.ES_REFERER2, "ZeroPageActivity");
        exp.pushMap(K_FROM, from);
        data.setExp(exp);
        data.isHomePage(true);

        //
        loadFromNetwork(data);
    }

    private void loadFromNetwork(EsData data) {
        EsManager.get().load(this, data, new IEsActivityProxy.LoadCallback() {
            @Override
            public void onStartLoad(IEsActivityProxy proxy) {
                mActivityProxy = proxy;
            }

            @Override
            public void onEsViewCreated(View view) {
                rootView.addView(view);
            }

            @Override
            public void onError(EsException e) {
            }

            @Override
            public void requestFinish() {
                finish();
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (mActivityProxy != null)
            mActivityProxy.dispatchKeyEvent(event);
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onBackPressed() {
        if (mActivityProxy != null) {
            if (mActivityProxy.onBackPressed(super::onBackPressed)) {
                return;
            }
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        if (mActivityProxy != null) mActivityProxy.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mActivityProxy != null) mActivityProxy.onPause();
        super.onPause();
    }

    @Override
    protected void onStart() {
        if (mActivityProxy != null) mActivityProxy.onStart();
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (mActivityProxy != null) mActivityProxy.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mActivityProxy != null) mActivityProxy.onDestroy();
        mActivityProxy = null;
        super.onDestroy();
    }
}
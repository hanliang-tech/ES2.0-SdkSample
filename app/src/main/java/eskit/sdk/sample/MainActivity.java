package eskit.sdk.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    private Button activityModeButton;
    private Button viewModeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        activityModeButton = this.findViewById(R.id.activity_mode_button);
        activityModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEsApp();
            }
        });

        //
        viewModeButton = this.findViewById(R.id.view_mode_button);
        viewModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ESViewActivity.class);
                startActivity(intent);
            }
        });

    }

    private void startEsApp() {
        EsData data = new EsData();
        data.setAppPackage("es.hello.world");
        EsManager.get().start(data);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

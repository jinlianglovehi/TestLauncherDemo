package com.huami.watch.testlauncherdemo;

import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_test_get_apps).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testGetAPPData();
            }
        });
    }


    private void testGetAPPData(){


        new Thread(new Runnable() {
            @Override
            public void run() {

                List<ResolveInfo> list = AppDataHelper.getInstance().getAppDataInPhone(MainActivity.this);

                Log.i(TAG,"listSize:"+list.size());


            }
        }).start();



    }
}

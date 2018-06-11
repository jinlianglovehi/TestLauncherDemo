
package com.huami.watch.testlauncherdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();

    private GridView gridView ;

    // 所有的app 信息
    private List<ResolveInfo> mApps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadApps();
        gridView  =(GridView) findViewById(R.id.apps_list);
        gridView.setAdapter(new AppsAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               jumpToAppByPosition(position);
           }
       });

    }


    /**
     * app 跳转
     * @param position
     */
    private void jumpToAppByPosition(int position){

        ResolveInfo info = mApps.get(position);
        //该应用的包名
        String pkg = info.activityInfo.packageName;
        //应用的主activity类
        String cls = info.activityInfo.name; // main activity 的name

        ComponentName componet = new ComponentName(pkg, cls);
        Intent i = new Intent();
        i.setComponent(componet);
        startActivity(i);

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


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取的是滑动的位置
        Log.i(TAG,"") ;
        event.getX() ;
        event.getY();
        return super.onTouchEvent(event);
    }




    /**
     *  load 所有的app
     */
    public void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
    }

    /**
     * appsAdapter
     */

    public class AppsAdapter extends BaseAdapter {
        public AppsAdapter() {
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i;

            // chuli handler view
            if (convertView == null) {
                i = new ImageView(MainActivity.this);
                i.setScaleType(ImageView.ScaleType.FIT_CENTER);
                i.setLayoutParams(new GridView.LayoutParams(100, 100));
            } else {
                i = (ImageView) convertView;
            }

            ResolveInfo info = mApps.get(position);
            i.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));

            return i;
        }

        public final int getCount() {
            return mApps.size();
        }

        public final Object getItem(int position) {
            return mApps.get(position);
        }

        public final long getItemId(int position) {
            return position;
        }
    }
}

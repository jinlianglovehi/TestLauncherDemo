
package com.huami.watch.testlauncherdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.List;

public class AppDataHelper {

    public static final String TAG = AppDataHelper.class.getSimpleName();


    public static AppDataHelper instance ;


    /**
     *  创建唯一的实例
     */
    public static AppDataHelper getInstance(){
        if(instance==null){
            synchronized (AppDataHelper.class){
                if(instance==null){
                    instance = new AppDataHelper();
                }
            }
        }
        return instance;
    }



    public  List<ResolveInfo> getAppDataInPhone(Context mContext){

        PackageManager mPackageManager =mContext.getPackageManager();

        Intent mMainIntent = new Intent(Intent.ACTION_MAIN);
        mMainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> infoList=mPackageManager.queryIntentActivities(mMainIntent,0);

        /**
         *     packageName:com.tencent.qqpimsecure,name:com.tencent.server.fore.QuickLoadActivity
         packageName:com.tencent.radio,name:com.tencent.radio.common.ui.LaunchActivity
         packageName:com.tencent.reading,name:com.tencent.reading.activity.SplashActivity
         packageName:com.tencent.wifimanager,name:com.tencent.wifimanager.MainActivity
         packageName:com.tw.coffeemade.bleperipheraltool,name:com.tw.coffeemade.bleperipheraltool.HomeActivity
         packageName:com.xiaomi.hm.health,name:com.xiaomi.hm.health.activity.StartUpActivity
         packageName:com.xinzhongxinbletester,name:com.xinzhongxin.bletester.MainActivity
         packageName:com.youdao.note,name:com.youdao.note.activity2.SplashActivity
         packageName:com.yutong.bledemo,name:com.yutong.bledemo.activity.BLEMainActivity
         packageName:com.zhihu.android,name:com.zhihu.android.app.ui.activity.MainActivity
         */
        for (ResolveInfo info : infoList ) {
            // 可以获取的是packagename 和 主要MainActivity 的name 可以通过动态的点击进行跳入到另一个的app 中
            Log.i(TAG,"packageName:"+ info.activityInfo.packageName +",name:"+ info.activityInfo. name);
        }
        return infoList;
    }

    /**
     * 点击其中的一个app ，去打开某一个app 的应用
     * @param info
     */
    public void clickItemApp(Context mContext ,ResolveInfo info){
        //该应用的包名
        String pkg = info.activityInfo.packageName;
        //应用的主activity类
        String cls = info.activityInfo.name;
        ComponentName componet = new ComponentName(pkg, cls);
        Intent i = new Intent();
        i.setComponent(componet);
        mContext.startActivity(i);
    }


    /**
     *
     *
     *  <application...>
        <meta-data android:value="hello my application" android:name="data_Name"></meta-data>
        </application>


     * @param mContext
     * @return
     */
    public ApplicationInfo getActivityInfoByMetaData(Context mContext){
        ApplicationInfo appInfo = null;
        try {
            appInfo = mContext.getPackageManager()
                    .getApplicationInfo(mContext.getPackageName(),
                            PackageManager.GET_META_DATA);
            String msg=appInfo.metaData.getString("data_Name");
            Log.d(TAG, " msg == " + msg );

            return appInfo;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *  load 所有的app
     */
    public List<ResolveInfo> loadApps(Context mContext) {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> mApps = mContext.getPackageManager().queryIntentActivities(mainIntent, 0);
        return mApps;
    }

}

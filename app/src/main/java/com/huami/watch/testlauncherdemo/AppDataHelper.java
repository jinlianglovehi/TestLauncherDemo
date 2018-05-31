
package com.huami.watch.testlauncherdemo;

import android.content.Context;
import android.content.Intent;
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

}

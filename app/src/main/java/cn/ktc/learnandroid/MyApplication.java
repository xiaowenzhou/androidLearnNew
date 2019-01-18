package cn.ktc.learnandroid;

import android.app.Application;
import android.content.Context;

/**
 * @author zhouxw
 */
public class MyApplication extends Application {
    public  static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext= getApplicationContext();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mContext=null;
    }
}

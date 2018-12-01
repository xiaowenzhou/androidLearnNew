package cn.ktc.learnandroid.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    /**
     * 绑定服务时调用这个方法，返回一个IBinder对象
     * @param intent
     * @return IBinder对象
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind");
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.i(TAG,"unbindService");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    public interface  MyIbinder{
        void invokeMethodInMyService();
    }

    public class MyBinder extends Binder implements MyIbinder {
        public void  stopService(ServiceConnection serviceConnection){
            unbindService(serviceConnection);
        }

        @Override
        public void invokeMethodInMyService() {
            Toast.makeText(getApplicationContext(), "服务里的方法执行了。。。",
                    Toast.LENGTH_SHORT).show();
        }
    }
}

package cn.ktc.learnandroid.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.ktc.learnandroid.IMyBinder;
import cn.ktc.learnandroid.R;

public class custom extends AppCompatActivity {

    public Button startService,unbindService,startRemoteService;
    private  MyService.MyBinder mBinder;
    private IMyBinder myBinder;
    private ServiceConnection mServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initView(){
        startService= findViewById(R.id.btn_custom_start);
        unbindService =findViewById(R.id.btn_custom_unbindService);
        startRemoteService=findViewById(R.id.btn_custom_startRemoteService);
        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startService(new Intent(custom.this,MyService.class));
                mServiceConnection = new  MyServiceConnection();
                bindService(new Intent(custom.this,MyService.class),mServiceConnection,Context.BIND_AUTO_CREATE);

            }
        });

        unbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(mServiceConnection);
            }
        });

        startRemoteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("MyService","onServiceConnected");
            mBinder= (MyService.MyBinder) service;
            mBinder.invokeMethodInMyService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        Log.i("MyService","onServiceDisconnected");
        }
    }
}

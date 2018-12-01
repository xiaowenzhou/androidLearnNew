package cn.ktc.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.ktc.learnandroid.service.custom;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnService;
    private String TAG ="MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        btnService = findViewById(R.id.btn_Service);
        btnService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Service:{
                Intent serviceIntent =new Intent(this,custom.class);
                startActivity(serviceIntent);
            }
            break;
            default:
        }
    }
}

package cn.ktc.learnandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.ktc.learnandroid.base.BaseActivity;
import cn.ktc.learnandroid.bean.ArticleBean;
import cn.ktc.learnandroid.presenter.SingleInterfacePresenter;
import cn.ktc.learnandroid.service.custom;
import cn.ktc.learnandroid.view.SingleInterfaceIView;

/**
 * @author zhouxw
 */
public class MainActivity extends BaseActivity<SingleInterfacePresenter> implements View.OnClickListener,SingleInterfaceIView {
    private Button btnService;
    private Button btnClick;
    private TextView mTextView;
    private static final String TAG ="MainActivity";


    private void initView(){
        btnService = findViewById(R.id.btn_Service);
        btnClick = findViewById(R.id.btn_click);
        btnClick.setOnClickListener(this);
        btnService.setOnClickListener(this);
        mTextView = findViewById(R.id.tv_content);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Service:{
                Intent serviceIntent =new Intent(this,custom.class);
                startActivity(serviceIntent);
            }
            break;
            case R.id.btn_click:{
                Log.d(TAG, "onClick: ");
                mPresenter.getData(0);

            }
            default:
        }
    }

    @Override
    protected SingleInterfacePresenter createPresenter() {
        return new SingleInterfacePresenter();
    }

    @Override
    protected void init() {
        Log.i(TAG,"onCreate");
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    public void showArticleSuccess(ArticleBean bean) {
        Log.d(TAG, "showArticleSuccess: "+bean.getData().getDatas().get(0).getTitle());
        mTextView.setText(bean.getData().getDatas().get(0).getTitle());

    }

    @Override
    public void showArticleFail(String errorMsg) {
        mTextView.setText(errorMsg);

    }
}

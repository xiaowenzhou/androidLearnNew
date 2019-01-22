package cn.ktc.learnandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.ktc.learnandroid.presenter.IPresenter;
import cn.ktc.learnandroid.view.IView;

/**
 * @author zhouxw
 */
public abstract class BaseActivity <T extends IPresenter>extends AppCompatActivity implements IView {
    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        init();


    }

    @Override
    protected void onDestroy() {
        if (mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected void initPresenter(){
        mPresenter = createPresenter();
        if (mPresenter!=null){
            mPresenter.attachView(this);
        }
    }

    /**
     *创建一个presenter
     * @return
     */
    protected abstract T createPresenter();


    /**
     *
     * 初始化
     */
    protected  abstract  void init();
}

package cn.ktc.learnandroid.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.ktc.learnandroid.presenter.IPresenter;
import cn.ktc.learnandroid.view.IView;

public abstract class BaseActivity <T extends IPresenter>extends AppCompatActivity implements IView {
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        init();
        initPresenter();
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
    protected  abstract  void init();
}

package cn.ktc.learnandroid.base;

import cn.ktc.learnandroid.presenter.IPresenter;
import cn.ktc.learnandroid.view.IView;

/**
 * @author zhouxw
 */
public abstract class BasePresenter<T extends IView> implements IPresenter<T> {
    private   T mView;

    @Override
    public void attachView(T view) {
        mView =view;
    }

    @Override
    public void detachView() {
    mView =null;
    }

    @Override
    public boolean isViewAttached() {
        return mView !=null;
    }
}

package cn.ktc.learnandroid.presenter;

import cn.ktc.learnandroid.view.IView;

public interface IPresenter<T extends IView> {
    /**
     * 衣服view
     * @param view
     */
    void attachView(T view);

    /**
     * 分离view
     */
    void detachView();

    /**
     * 判断view是否已销毁
     * @return
     */
    boolean isViewAttached();

}

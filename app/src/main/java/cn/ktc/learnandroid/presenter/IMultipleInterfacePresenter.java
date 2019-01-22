package cn.ktc.learnandroid.presenter;

import cn.ktc.learnandroid.view.IView;

/**
 * @author zhouxw
 */
public interface IMultipleInterfacePresenter<T extends IView> extends IPresenter {
    void addPresenter(IPresenter<T> ap);
}

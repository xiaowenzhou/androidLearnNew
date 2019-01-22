package cn.ktc.learnandroid.contract;

import cn.ktc.learnandroid.bean.BannerBean;
import cn.ktc.learnandroid.presenter.IPresenter;
import cn.ktc.learnandroid.view.IView;

/**
 * @author zhouxw
 */
public interface MultipleInterfaceContract {
    interface View extends IView{
        void showMultipleBannerSuccess(BannerBean bean);
        void showMultipleFail(String errorMsg);
    }
    interface Presenter<T extends IView> extends IPresenter<T>{
        void getBanner();
    }
}

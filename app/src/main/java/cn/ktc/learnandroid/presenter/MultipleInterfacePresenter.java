package cn.ktc.learnandroid.presenter;

import cn.ktc.learnandroid.base.BasePresenter;
import cn.ktc.learnandroid.bean.BannerBean;
import cn.ktc.learnandroid.contract.MultipleInterfaceContract;
import cn.ktc.learnandroid.interfaces.Callback;
import cn.ktc.learnandroid.model.MultipleInterfaceModel;
import cn.ktc.learnandroid.view.IView;

/**
 * @author zhouxw
 */
public class MultipleInterfacePresenter<T extends IView> extends BasePresenter<MultipleInterfaceContract.View> implements MultipleInterfaceContract.Presenter<MultipleInterfaceContract.View> {
    private MultipleInterfaceModel mMultipleInterfaceModel;

    public MultipleInterfacePresenter(){
        this.mMultipleInterfaceModel = new MultipleInterfaceModel();
    }

    @Override
    public void getBanner() {
        mMultipleInterfaceModel.getBanner(new Callback<BannerBean,String>() {
            @Override
            public void onSuccess(BannerBean bean) {
                if (isViewAttached()){}
                mView.showMultipleBannerSuccess(bean);
            }

            @Override
            public void onFail(String errorMsg) {
                mView.showMultipleFail(errorMsg);

            }
        });

    }
}

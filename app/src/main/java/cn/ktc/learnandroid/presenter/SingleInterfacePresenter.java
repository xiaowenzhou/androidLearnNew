package cn.ktc.learnandroid.presenter;

import android.util.Log;

import cn.ktc.learnandroid.base.BasePresenter;
import cn.ktc.learnandroid.bean.ArticleBean;
import cn.ktc.learnandroid.contract.SingleInterfaceContract;
import cn.ktc.learnandroid.interfaces.Callback;
import cn.ktc.learnandroid.model.SingleInterfaceModel;

/**
 * @author zhouxw
 */
public class SingleInterfacePresenter extends BasePresenter<SingleInterfaceContract.View> implements SingleInterfaceContract.Presenter<SingleInterfaceContract.View> {
    private  final SingleInterfaceModel mSingleInterfaceModel;
    public SingleInterfacePresenter(){
        this.mSingleInterfaceModel = new SingleInterfaceModel();
    }

    @Override
    public void  getData(int curPage){
        mSingleInterfaceModel.getData(curPage, new Callback<ArticleBean,String>() {
            @Override
            public void onSuccess(ArticleBean bean) {
                // 如果Model层请求数据成功,则此处应执行通知View层的代码
                Log.d("zhouxw", "onSuccess: "+isViewAttached());
                if (isViewAttached()){
                    Log.d("zhouxw", "isViewAttached: ");
                    mView.showArticleSuccess(bean);

                }
            }

            @Override
            public void onFail(String errorMsg) {
                // 如果Model层请求数据失败,则此处应执行通知View层的代码
                Log.d("zhouxw", "onFail: ");
                mView.showArticleFail(errorMsg);
            }
        });
    }
}

package cn.ktc.learnandroid.model;







import android.util.Log;

import cn.ktc.learnandroid.bean.ArticleBean;
import cn.ktc.learnandroid.interfaces.Callback;
import cn.ktc.learnandroid.utils.NetworkUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhouxw
 */
public class SingleInterfaceModel implements ISingleInterfaceModel {

    @Override
    public void getData(int curPage, final Callback callback){
        NetworkUtils.getmInstance()
                .getApi()
                .getData(curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("zhouxw","开始采用subscribe 连接");
                    }

                    @Override
                    public void onNext(ArticleBean articleBean) {
                        if (null==articleBean){
                            callback.onFail("failed");
                        }else if (articleBean.getErrorCode()!=0){
                            callback.onFail(articleBean.getErrorMsg());
                        }else {
                            callback.onSuccess(articleBean);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail("failed");

                    }

                    @Override
                    public void onComplete() {
                        Log.d("zhouxw", "onComplete: ");


                    }
                });

    }
}

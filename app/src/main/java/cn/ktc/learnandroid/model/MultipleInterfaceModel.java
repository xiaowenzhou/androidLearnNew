package cn.ktc.learnandroid.model;

import android.util.Log;

import cn.ktc.learnandroid.bean.BannerBean;
import cn.ktc.learnandroid.interfaces.Callback;
import cn.ktc.learnandroid.utils.NetworkUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author zhouxw
 */
public class MultipleInterfaceModel implements IMultipleInterfaceModel {
    @Override
    public void getBanner(final Callback callback) {
        NetworkUtils.getmInstance()
                .getApi()
                .getBanner()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("zhouxw", "onSubscribe: multiple ");

                    }

                    @Override
                    public void onNext(BannerBean bean) {
                        if (null==bean){
                            callback.onFail("Fail");
                        }else if (bean.getErrorCode()!=0){
                            callback.onFail("Error");
                        }else {
                            callback.onSuccess(bean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail("Error");

                    }

                    @Override
                    public void onComplete() {
                        Log.d("zhouxw", "onComplete: multiple");

                    }
                });
    }
}

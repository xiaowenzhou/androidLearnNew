package cn.ktc.learnandroid.utils;


import java.util.concurrent.TimeUnit;

import cn.ktc.learnandroid.MyApplication;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author zhouxw
 */
public class NetworkUtils {
    private static  NetworkUtils mInstance;
    private static final String BASEURI = "http://www.wanandroid.com";
    private NetworkUtils(){
        final long maxCache=10*10*1024;
        Cache cache = new Cache(MyApplication.mContext.getCacheDir(),maxCache);
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.retryOnConnectionFailure(true);
        builder.connectTimeout(10*1000,TimeUnit.MILLISECONDS);
        builder.cache(cache);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();


    }

    public  static NetworkUtils getmInstance(){
        if (mInstance==null){
            synchronized (NetworkUtils.class){
                if (mInstance==null){
                    mInstance = new NetworkUtils();
                }
            }
        }
        return mInstance;
    }
}

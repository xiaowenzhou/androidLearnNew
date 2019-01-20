package cn.ktc.learnandroid.api;



import cn.ktc.learnandroid.bean.ArticleBean;
import cn.ktc.learnandroid.bean.BannerBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    /**
     *
     * wanAndroid 首页文章列表
     * @param curPage 当前第几页
     * @return
     */
    @GET("article/list/{curPage}/json")
    Observable<ArticleBean> getData(@Path("curPage") int curPage);


    @GET("banner/json")
    Observable<BannerBean> getBanner();
}

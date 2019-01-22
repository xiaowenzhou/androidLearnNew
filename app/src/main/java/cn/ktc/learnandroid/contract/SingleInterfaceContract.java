package cn.ktc.learnandroid.contract;

import cn.ktc.learnandroid.bean.ArticleBean;
import cn.ktc.learnandroid.presenter.IPresenter;
import cn.ktc.learnandroid.view.IView;

/**
 * @author zhouxw
 */
public interface SingleInterfaceContract {
    interface View extends IView{
        /***
         * 显示文章第一页title
         * @param bean 获取到的数据
         */
        void showArticleSuccess(ArticleBean bean);

        /**
         *显示获取数据错误信息
         * @param errorMsg 错误信息
         */
        void showArticleFail(String errorMsg);

    }
    interface Presenter<T extends  IView> extends IPresenter<T> {
        /**
         *获取数据
         * @param curPage 当前第几页
         */
        void getData(int curPage);

    }
}

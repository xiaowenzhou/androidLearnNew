package cn.ktc.learnandroid.view;

import cn.ktc.learnandroid.bean.ArticleBean;

/**
 * @author zhouxw
 */
public interface SingleInterfaceIView extends  IView{
    void showArticleSuccess(ArticleBean bean);
    void showArticleFail(String errorMsg);

}

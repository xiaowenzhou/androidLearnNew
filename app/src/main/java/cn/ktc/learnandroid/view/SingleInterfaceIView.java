package cn.ktc.learnandroid.view;

import cn.ktc.learnandroid.bean.ArticleBean;

public interface SingleInterfaceIView extends  IView{
    void showArticleSuccess(ArticleBean bean);
    void showArticleFail(String errorMsg);

}

package cn.ktc.learnandroid.model;

import cn.ktc.learnandroid.interfaces.Callback;

/**
 * @author zhouxw
 */
public interface ISingleInterfaceModel extends IModel{
    /**
     * 获取文章数据
     * @param curPage 当前页面
     * @param callback 回调
     */
    void getData(int curPage, final Callback callback);
}

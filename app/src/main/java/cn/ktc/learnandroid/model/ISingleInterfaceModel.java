package cn.ktc.learnandroid.model;

import cn.ktc.learnandroid.interfaces.Callback;

/**
 * @author zhouxw
 */
public interface ISingleInterfaceModel extends IModel{
    void getData(int curPage, final Callback callback);
}

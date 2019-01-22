package cn.ktc.learnandroid.model;

import cn.ktc.learnandroid.interfaces.Callback;

/**
 * @author zhouxw
 */
public interface IMultipleInterfaceModel extends IModel{
    void getBanner(final Callback callback);
}

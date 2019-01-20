package cn.ktc.learnandroid.interfaces;

public interface Callback<K,V> {
    void  onSuccess(K data);

    void onFail(V data);
}

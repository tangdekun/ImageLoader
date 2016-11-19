package com.john.mvpdemo.base;

/**
 * Created by John on 2016/11/6.
 */

public abstract class BasePresenter<T> {
    public T mView;

    /**将View和Presenter绑定
     * @param mView  View泛型，一般是Activity，Fragment等
     */
    public void attach(T mView){
        this.mView = mView;
    }

    /**
     * 将View和Presenter解绑
     */
    public void deattach(){
        mView = null;
    }
}

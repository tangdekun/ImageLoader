package com.john.mvpdemo.model;

/**
 * Created by John on 2016/11/6.
 */

public interface OnRequestListener {
    void onSuccess(String string);
    void onError(Throwable ex);
}

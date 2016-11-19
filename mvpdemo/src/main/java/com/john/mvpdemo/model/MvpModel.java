package com.john.mvpdemo.model;

import android.os.AsyncTask;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by John on 2016/11/6.
 */

public class MvpModel implements IMvpModel{

    public MvpModel() {

    }

    @Override
    public void getDatas(final OnRequestListener requestListener) {
        RequestParams params = new RequestParams("https://www.baidu.com/s");
        params.addQueryStringParameter("wd", "xUtils");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                requestListener.onSuccess(result);
            }

            @Override
            public void onFinished() {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                requestListener.onError(ex);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }
        });

    }




}

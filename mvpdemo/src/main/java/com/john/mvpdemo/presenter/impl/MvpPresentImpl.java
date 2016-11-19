package com.john.mvpdemo.presenter.impl;

import android.os.Looper;

import com.john.mvpdemo.base.BasePresenter;
import com.john.mvpdemo.model.IMvpModel;
import com.john.mvpdemo.model.MvpModel;
import com.john.mvpdemo.model.OnRequestListener;
import com.john.mvpdemo.presenter.IMvpPresenter;
import com.john.mvpdemo.view.IMvpView;

import java.util.HashMap;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by John on 2016/11/6.
 */

public class MvpPresentImpl extends BasePresenter<IMvpView> implements IMvpPresenter {
    private IMvpModel mvpModel;
    private Handler mHandler;
//    private IMvpView mvpView;
    public MvpPresentImpl(){
        mvpModel = new MvpModel();
    }

    public void resume(){
        mvpModel.getDatas(new OnRequestListener() {
            @Override
            public void onSuccess(String string) {
                mView.showSuccessOnTextView(string);
            }

            @Override
            public void onError(Throwable ex) {
                mView.showErrorToast(ex.getMessage().toString());
            }
        });
    }

}

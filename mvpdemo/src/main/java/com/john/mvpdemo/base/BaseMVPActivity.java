package com.john.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by John on 2016/11/6.
 */

public abstract class BaseMVPActivity<V,T extends BasePresenter<V>>  extends AppCompatActivity {
    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mPresenter =  initPresenter();
    }

    /**
     * 在onResume()方法中将Activity和Presenter绑定在一起
     */
    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attach((V) this);
    }

    /**
     * 在onDestroy()方法中将Activity和Presenter解绑，Presenter不再拥有Activity引用，
     * 避免了内存泄漏
     */
    @Override
    protected void onDestroy() {

        mPresenter.deattach();
        super.onDestroy();
    }

    /**
     * 初始化Presenter
     */
    public abstract T initPresenter();
}

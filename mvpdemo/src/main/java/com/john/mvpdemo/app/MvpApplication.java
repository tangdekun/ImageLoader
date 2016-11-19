package com.john.mvpdemo.app;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by John on 2016/11/6.
 */

public class MvpApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //xUtils3注入
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

        //Realm注入
        Realm.init(this);
        RealmConfiguration realmConfiguration = new  RealmConfiguration.Builder().
                name("myReam.realm").
                deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);






    }
}

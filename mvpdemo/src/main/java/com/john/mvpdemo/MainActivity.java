package com.john.mvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.john.mvpdemo.base.BaseMVPActivity;
import com.john.mvpdemo.presenter.impl.MvpPresentImpl;
import com.john.mvpdemo.view.IMvpView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;



public class MainActivity extends BaseMVPActivity<IMvpView,MvpPresentImpl>  implements IMvpView, View.OnClickListener {
    @ViewInject(R.id.tv)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);

    }



    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    public MvpPresentImpl initPresenter() {

        return new MvpPresentImpl();
    }

    @Override
    public void showErrorToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccessOnTextView(String msg) {
        mTextView.setText(msg);
    }

    @Override
    public void onClick(View view) {
           switch(view.getId()){
                  case R.id.button :
                      mPresenter.resume();
                  break;
               case R.id.button2:
                       startActivity(new Intent(MainActivity.this,RealmActivity.class));
                   break;

              }

    }
}

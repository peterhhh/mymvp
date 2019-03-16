package com.dingbin.commonmodel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dingbin.common_base.base.BaseActivity;
import com.dingbin.common_base.util.OpenActivity;
import com.dingbin.common_base.util.ToastUtils;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainContract.MainView,MainContract.MainPresenter> implements MainContract.MainView{

    private static final String TAG = "MainActivity";

    @Override
    protected MainContract.MainPresenter createPresenter() {
        return new MyPresenter();
    }

    @Override
    protected MainContract.MainView createView() {
        return this;
    }

    @Override
    protected boolean openIntentInject() {

        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        iPresenter.get("dingbin");
    }

    @Override
    public void setData(String s) {

    }

    @Override
    public void showToast(String s) {
        ToastUtils.showShort(this,s);

    }

    @OnClick(R.id.sample_text)
    public void goMain2Activity(){
        Bundle bundle = new Bundle();
        bundle.putString("name","dingbin");
        bundle.putInt("age",22);
        //bundle.putBoolean("isName",true);
        OpenActivity.open(this,Main2Activity.class,bundle);

    }

}

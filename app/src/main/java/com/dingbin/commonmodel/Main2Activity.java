package com.dingbin.commonmodel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dingbin.common_base.base.BaseActivity;
import com.dingbin.common_base.base.intentioc.IntentInject;
import com.dingbin.common_base.util.ToastUtils;

public class Main2Activity extends BaseActivity<MainContract.MainView,MainContract.MainPresenter> implements MainContract.MainView {

    private static final String TAG = "Main2Activity";
    @IntentInject
    private String name;

    @IntentInject
    private int age;

    @IntentInject
    private boolean isName;


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
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initData() {
        ToastUtils.showShort(this,"收到intent数据 name = "+name+" age = "+age+" isName = "+isName);
    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void setData(String s) {

    }
}

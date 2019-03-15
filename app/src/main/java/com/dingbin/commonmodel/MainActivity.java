package com.dingbin.commonmodel;

import android.widget.Toast;

import com.dingbin.common_base.base.BaseActivity;

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
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();

    }
}

package com.dingbin.commonmodel;


import com.dingbin.common_base.base.BasePresenter;

/**
 * @author dingbin
 * @date 2019/3/15 16:00
 */

public class MyPresenter extends BasePresenter<MainContract.MainView,MainContract.MainModel> implements MainContract.MainPresenter{


    @Override
    public void get(String s) {
        v.get().showToast(m.get(s));
    }


    @Override
    protected MainContract.MainModel createModel() {
        return new MyModel();
    }
}

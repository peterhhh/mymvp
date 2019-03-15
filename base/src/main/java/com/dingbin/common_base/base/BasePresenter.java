package com.dingbin.common_base.base;

import java.lang.ref.WeakReference;

/**
 * 对于P层，因为P层需要持有V层引用，所以我是定义了一个泛型<V>的接口IPresenter，里面定义了绑定和解绑的方法，具体的实现交给基类BasePresenter实现。
 * @author dingbin
 * @date 2019/3/15 15:47
 */

public abstract class BasePresenter<V extends IView,M extends IModel> implements IPresenter<V> {

    protected WeakReference<V> v;
    protected M m;

    @Override
    public void attachView(V v){
        this.v = new WeakReference<>(v);
        this.m = createModel();
    }

    @Override
    public void dettachView(){
        if (this.v!=null){
            this.v.clear();
            this.v = null;
        }
        if (this.m!=null){
            this.m = null;
        }
    }
    protected abstract M createModel();
}

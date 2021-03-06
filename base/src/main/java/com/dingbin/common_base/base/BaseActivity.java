package com.dingbin.common_base.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dingbin.common_base.base.intentioc.IntentManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 对于V层而言，他只关心V和P的关系，就怎么将他们建立一个联系。在我这个MVP框架中，我定义了一个Activity基类，将V、P之间的关系交给基类处理，通过泛型，子类只需要将V、P的具体实例传入即可完成赋值。
 * 具体绑定的工作交给P层去处理，因为P是需要持有V和M引用的
 * @param <V>
 * @param <P>
 */
public abstract class BaseActivity<V extends IView,P extends IPresenter<V>> extends AppCompatActivity {

    private  V iView;
    protected P iPresenter;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //注入butterknife
        bind = ButterKnife.bind(this);
        //注入接收intent的框架
        IntentManager.getInstance().inject(this,openIntentInject());
        bindVP();
        initData();
    }

    private void bindVP(){
        iView = createView();
        iPresenter = createPresenter();
        iPresenter.attachView(iView);
    }

    protected abstract P createPresenter();
    protected abstract V createView();
    protected abstract boolean openIntentInject();
    protected abstract int getLayoutId();
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenter.dettachView();
        bind.unbind();
    }
}

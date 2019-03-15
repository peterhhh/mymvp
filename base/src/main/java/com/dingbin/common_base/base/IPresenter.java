package com.dingbin.common_base.base;

/**
 * @author dingbin
 * @date 2019/3/15 15:40
 */

public interface IPresenter<V extends IView> {
    void attachView(V iView);

    void dettachView();

}

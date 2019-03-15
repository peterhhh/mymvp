package com.dingbin.commonmodel;

import com.dingbin.common_base.base.IModel;
import com.dingbin.common_base.base.IPresenter;
import com.dingbin.common_base.base.IView;

/**
 * 协议类，统一管理
 * @author dingbin
 * @date 2019/3/15 15:59
 */

public class MainContract {

    interface MainView extends IView {
        void setData(String s);

    }

    interface MainModel extends IModel {
        String get(String s);

    }

    interface MainPresenter extends IPresenter<MainView> {
        void get(String s);


    }




}

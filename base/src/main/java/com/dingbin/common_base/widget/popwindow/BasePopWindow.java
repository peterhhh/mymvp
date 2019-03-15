package com.dingbin.common_base.widget.popwindow;

import android.app.Activity;
import android.content.Context;
import android.gesture.Prediction;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.CallSuper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.dingbin.common_base.R;
import com.dingbin.common_base.util.KeyboardUtils;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * Created by dingbin on 2018/11/15.
 */

public abstract class BasePopWindow extends PopupWindow {


    public WeakReference<Context> context;


    protected abstract int getLAyoutId();

    public BasePopWindow(Context context) {
        this.context = new WeakReference<Context>(context);
        init(this.context);
    }

     protected void init(WeakReference<Context> context){
         View view = LayoutInflater.from(this.context.get()).inflate(getLAyoutId(), null);
         setContentView(view);
         ButterKnife.bind(this,view);
         setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
         setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
         setAnimationStyle(R.style.pop_style);
//        /**
//         * 设置背景只有设置了这个才可以点击外边和BACK消失
//         */
         /**
          * 设置背景只有设置了这个才可以点击外边和BACK消失
          */
         setBackgroundDrawable(new ColorDrawable());

         /**
          * 设置可以获取集点
          */
         setFocusable(true);

         /**
          * 设置点击外边可以消失
          */
         setOutsideTouchable(true);

         /**
          *设置可以触摸
          */
         setTouchable(true);

         initChildData();

     }
     public abstract void initChildData();

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        lightOff();
    }

    private void lightUp() {
        WindowManager.LayoutParams layoutParams = ((Activity)context.get()).getWindow().getAttributes();
        layoutParams.alpha=1.0f;
        ((Activity)context.get()).getWindow().setAttributes(layoutParams);
    }

    private void lightOff() {
        WindowManager.LayoutParams layoutParams = ((Activity)context.get()).getWindow().getAttributes();
        layoutParams.alpha=0.5f;
        ((Activity)context.get()).getWindow().setAttributes(layoutParams);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (context.get()!=null)
        KeyboardUtils.hideSoftInput((Activity) context.get());
        lightUp();
    }
}

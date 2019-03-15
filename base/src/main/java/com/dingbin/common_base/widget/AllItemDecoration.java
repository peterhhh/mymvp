package com.dingbin.common_base.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dingbin on 2018/11/14.
 */

public class AllItemDecoration extends RecyclerView.ItemDecoration {
    private int left,right,top,bottom;

    public AllItemDecoration(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.right = right;
        outRect.top = top;
        outRect.left = left;
        outRect.bottom = bottom;
    }
}

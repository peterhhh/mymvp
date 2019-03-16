package com.dingbin.common_base.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author dingbin
 * @date 2019/3/16 17:07
 */

public class OpenActivity {

    public static void open(Context context, Class<?> clz, Bundle bundle){
        context.startActivity(new Intent(context,clz).putExtras(bundle));
    }
}

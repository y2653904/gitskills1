package com.example.zhen.kususuoyin;

import android.content.Context;
import android.widget.Toast;

/**
 * 类名:      Utils
 * 创建者:    yuanzhen
 * 创建时间:  2016/11/06.
 * 描述：     吐司
 */

public class Utils {


    private static Toast toast;

    public static void showToast(Context context, String str) {
        if(toast == null){
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(str);
        toast.show();
    }
}

package com.nq.qbr.utils;

import com.BeeFramework.view.ToastView;

import android.content.Context;
import android.view.Gravity;


public class qbrUtils {

    /**
     * ��Ϣ��ʾ��
     * 
     * @param msg
     */
    public static void showMsgCenter(Context context, String msg) {
        ToastView toast = new ToastView(context, msg);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}

package com.example.xia.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;

/**
 * @User Xiahangli
 * @Date 2018/10/25  11:13
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class KeyBoardUtils {

    /**
     * 隐藏输入法
     * 软键盘参考 <href="https://blog.csdn.net/ccpat/article/details/46717573"></href="">
     */
   public static void hideInputMethod(View v){
       if (v == null) {
           return;
       }


       InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
       if (imm.isActive(v)){//检查是否当前view可以处理输入法，没有就
       //todo 第一个参数，IBinder 第二个参数flag,参考显示输入法，0 current maybe,1 hide_implicit_only,2 hide_not_always三种参数
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
       }
//        im.hideSoftInputFromInputMethod();
   }


    /**
     *
     */
    public static void hideInputMethod(Activity aty){

    }

    /**
     *
     * @param v EditText或者子类
     */
    public static void showInputMethod(View v){
        if (v == null)return;
        InputMethodManager imm= (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        //参考showSoftInput todo 第一个参数 showFrags 0,1 SHOW_IMPLICIT,2 show_force 第二个参数
                imm.toggleSoftInput(0,InputMethod.SHOW_FORCED);//强制显示
//        imm.showSoftInput(v,InputMethodManager.SHOW_IMPLICIT)
    }

}

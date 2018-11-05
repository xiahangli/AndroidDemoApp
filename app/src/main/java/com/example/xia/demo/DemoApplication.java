package com.example.xia.demo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @User Xiahangli
 * @Date 2018/10/25  14:04
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**================第一步初始化Arouter，越早越好==========================*/
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
        /**==================================================================*/
    }
}

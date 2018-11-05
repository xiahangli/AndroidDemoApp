package com.example.xia.demo.bgservice;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @User Xiahangli
 * @Date 2018/10/23  9:41
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class Service  extends android.app.Service {

    MBInder binder = new MBInder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Service", "onBind");
        return binder;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Service", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Service", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e("Service", "onRebind");
        super.onRebind(intent);
    }


    @Override
    public void onDestroy() {
        Log.e("Service", "onDestroy");
        super.onDestroy();
    }

    class MBInder extends Binder {
        public MBInder(){

        }

        public Service getService(){
            return Service.this;
        }
    }
}

package com.example.xia.demo.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;

public class ResourceUtils {

    public static ResourceUtils resourceUtils;
    public Context mContext;

    public ResourceUtils(Context context) {
        this.mContext = context;
    }

    public static   ResourceUtils getInstance(Context context) {
        if (resourceUtils == null) {
            synchronized (  ResourceUtils.class) {
                if (resourceUtils == null) {
                    resourceUtils = new   ResourceUtils(context);
                }
            }
        }
        return resourceUtils;
    }

    /**
     * @param colorResId
     * @removed
     * @deprecated 请用 {@link   ResourceUtils#obtainColorResources(int)} 替代
     */
    @Deprecated
    public ResourceUtils getColorResources(int colorResId) {
        mContext.getResources().getColor(colorResId);
        return this;
    }

    /**
     * @param colorResId
     * @return
     */
    @TargetApi(23)
    public  ResourceUtils obtainColorResources(int colorResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ContextCompat.getColor(mContext,colorResId);
        } else {
            mContext.getResources().getColor(colorResId);
        }
        return this;
    }
}


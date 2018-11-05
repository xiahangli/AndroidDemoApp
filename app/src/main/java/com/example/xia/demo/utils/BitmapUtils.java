package com.example.xia.demo.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

/**
 * @User Xiahangli
 * @Date 2018/11/1  9:55
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class BitmapUtils {
    public static Bitmap getViewBitmap(View view){
        Bitmap bitmap = null;
        Bitmap tmpCacheBitmap = null;
        view.setDrawingCacheEnabled(true);
        tmpCacheBitmap = view.getDrawingCache();//得到缓存的图片
        bitmap = Bitmap.createBitmap(tmpCacheBitmap);//根据缓存图片，创建新的bitmap
        //The new bitmap may
        //     * be the same object as source, or a copy may have been made
        //
        view.destroyDrawingCache();
        tmpCacheBitmap.recycle();
        tmpCacheBitmap = null;
        view.setDrawingCacheEnabled(false);
        return bitmap;//不能直接返回否者报错 Canvas: trying to use a recycled bitmap android.graphics.Bitmap@11b9bba
    }



    public static void glideLoad(File file, int w, final ImageView iv) {
//        if (getContext() != null) {
//            Glide.with(getContext().getApplicationContext()).load(file).asBitmap().transform(new GlideTransform(getContext().getApplicationContext(), w, file.getPath())).into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                    if (getContext() == null) {
//                        return;
//                    }
//                    if (resource.getWidth() > resource.getHeight()) {
//                        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);//把图片按比例扩大/缩小到View的宽度，居中显示
//                    } else {
//                        iv.setScaleType(ImageView.ScaleType.CENTER);//按图片的原来size居中显示，当图片长/宽超过View的长/宽，则截取图片的居中部分显示
//                    }
//                    iv.setImageBitmap(resource);
//                    iv.setEnabled(true);
//                }
//            });
//        }
    }
}

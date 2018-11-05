package com.example.xia.demo.mvp.core;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @User Xiahangli
 * @Date 2018/10/23  10:13
 * @Email xiahangli@qq.com
 * @Descrip 基础Present，P层持有V层,使用弱引用防止未调用onDestroy导致内存泄漏
 */
public abstract class BasePresenter<T> {
    public Reference<T> viewReference;

    public void attach(T view) {
        viewReference = new WeakReference<>(view);
    }

    public boolean isAttach() {
        return viewReference != null && null != viewReference.get();
    }

    public void detach() {
      if (viewReference!=null){
          viewReference.clear();
          viewReference = null;
      }
    }

    public void onResume() {

    }

}

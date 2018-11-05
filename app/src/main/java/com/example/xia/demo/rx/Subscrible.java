package com.example.xia.demo.rx;

/**
 * @User Xiahangli
 * @Date 2018/11/2  14:13
 * @Email xiahangli@qq.com
 * @Descrip 女生,订阅者,观察者
 */
public abstract class Subscrible<T> {
    public abstract void onNext(T t) ;
}

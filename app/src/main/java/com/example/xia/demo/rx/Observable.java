package com.example.xia.demo.rx;


/**
 * @User Xiahangli
 * @Date 2018/11/2  14:05
 * @Email xiahangli@qq.com
 * @Descrip 场景
 */
public class Observable<T> {
    /**
     * 男生
     */
    private OnSubscrible<T> onSubscrible;//男生

    public Observable(OnSubscrible onSubscrible) {
        this.onSubscrible = onSubscrible;
    }


    /**
     * 静态方法
     */
    public static <T>    Observable<T> create(OnSubscrible<T> onSubscrible) {
        return  new    Observable<T>(onSubscrible);
    }

    /**
     * super用于参数类型限定，不用于返回类型限定
     *
     * @param subscribe
     */
    public void subscrible(Subscrible<? super T> subscribe) {
        onSubscrible.call(subscribe);//参数传入
    }

    /**
     * @param func1
     * @param <R>
     * @return
     */
    public <R>    Observable<R> map(Func1<? super T, ? extends R> func1) {
        return new    Observable<>(new OnSubscribleLift<T, R>(onSubscrible, func1));
    }
}

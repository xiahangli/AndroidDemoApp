package com.example.xia.demo.rx;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
/**
 * @User Xiahangli
 * @Date 2018/11/1  15:20
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class HttpRx {

    /**
     * 预处理htttp请求,返回datas或data
     * <R, T extends Result<R>> 代表方法泛型
     * Observable代表
     *
     * @param <T> 预处理之前的请求,所有 Rsult<R>或其子类</>
     * @param <R> Observable中携带的后台中data or datas数据
     * @return
     */
    public static <R, T extends Result<R>> io.reactivex.Observable<R> normal(io.reactivex.Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<T, R>() {
                    @Override
                    public R apply(T t) throws Exception {
                        if (t.isSuccess) {
                            if (t.datas != null) {
                                return t.datas;
                            } else if (t.data != null) {
                                return t.data;
                            }
                            throw new ResultErrorException("result.data or result.datas is null", t);

                        } else {
                            throw new ResultErrorException("Result.isSuccess is false", t);
                        }
                    }
                });
    }
}

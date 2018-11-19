package com.example.xia.demo.retrofit;

import android.util.Log;

import com.example.xia.demo.bean.HttpJuHeResult;
import com.example.xia.demo.bean.JuheDream;
import com.example.xia.demo.retrofit.service.JuHeService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * =====================================
 * API接口
 * @see <a href ="https://blog.csdn.net/c__chao/article/details/78573737"></a>
 * =====================================
 */
public class HttpJuHeMethods {
    public static final String BASE_URL = "http://api.apiopen.top/";
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;

    /**
     *   使用mServices缓存Service的方式
     */
    private JuHeService juheService;
    private Map<Class,Object> mServices = new HashMap<>();
    private String TAG = "HttpJuHeMethods";
    private Disposable disposable;

    //构造方法私有
    private HttpJuHeMethods() {
        //手动创建一个OkHttpClient并设置超时时间

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //添加日志拦截器
        OkHttpClient okHttpClient =httpClientBuilder
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))//logging-interceptro
                .build();       //建造者创建一个okhttp
        //配合rxjava使用，接口返回observable
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())//retrofit converter依赖
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//retrofit adapter依赖
                .baseUrl(BASE_URL)
                .build();

        juheService = retrofit.create(JuHeService.class);// 通过反射创建具体的service服务 todo 后期将相同的的Service做一个缓存

    }


    public Object createService(Class clazz){
        if (clazz!=null){

        }
        return clazz;
    }

    /**
     *缓存所有services
     * @param clz
     * @param <T>
     */
    public synchronized  <T> T createServiceNew(Class<T> clz){
        T t = (T) mServices.get(clz);
        if (t == null){
           t = retrofit.create(clz);
            mServices.put(clz,t);
        }
        return t;
    }

    public void destroy() {
        disposable.dispose();
    }


    //在访问HttpMethods时创建单例,todo why final
    private static class SingletonHolder {
        private static final HttpJuHeMethods INSTANCE = new HttpJuHeMethods();
    }

    //获取单例
    public static HttpJuHeMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * rxjava2换成另外一种形式不使用Func1了。。。。
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
//    @Deprecated
//    private class HttpResultFunc<T> implements Func1<HttpJuHeResult<T>, T> {
//        @Override
//        public T call(HttpJuHeResult<T> httpResult) {
//            if (httpResult.getError_code() != 0) {
//                throw new ApiException(httpResult.getError_code());
//            }
//            return httpResult.getResult();
//        }
//    }
//    private <T> void toSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
//        observable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//    }

    /**
     * 用于获取聚合笑话的数据
     *
     * @param options 访问参数
     */
    public void getJokesByHttpResultMap(Map<String, Object> options) {
//        juheService.getJokesByRxJavaHttpResult(options)
//                .map(new HttpResultFunc<JuHeDream>())
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
//        Observable<List<JuheDream>> observable;
        juheService
                .getDreams()//observable通过map操作符实现;
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//rxandroid-2.1.0
                .subscribe(new Observer<HttpJuHeResult<JuheDream>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpJuHeResult<JuheDream> listHttpJuHeResult) {
                        Log.d("test", listHttpJuHeResult.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("test", e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("test", "");
                    }
                });//最后别忘了订阅
//        toSubscribe(observable, subscriber);
    }

    public void memoryLeakage(){
        Observable observable = Observable.create(new ObservableOnSubscribe<String >() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //emitter 发送消息
                Thread.sleep(10000000);
                emitter.onNext("hello");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               ;
       disposable =   observable.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(TAG,o.toString());
            }
        });

    }


    /**
     *
     */
    public  void showMeTheCode(){
        Observable.just("long", "longer", "longest")

                .map(String::length)//method references are not supported at level ‘7’
                .subscribe(length -> System.out.println("item length " + length));
    }
}
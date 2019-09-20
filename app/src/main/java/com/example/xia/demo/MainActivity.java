package com.example.xia.demo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xia.demo.cusview.AsteriskPasswordTransformationMethod;
import com.example.xia.demo.cusview.WordReplacement;
import com.example.xia.demo.rx.Func1;
import com.example.xia.demo.rx.Observable;
import com.example.xia.demo.rx.OnSubscrible;
import com.example.xia.demo.rx.Subscrible;
import com.example.xia.demo.utils.BitmapUtils;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.disposables.Disposable;

/**
 * @User Xiahangli
 * @Date 2018/11/15  17:18
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class MainActivity extends Activity {

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    String strHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText1);

        strHint = getResources().getString(R.string.app_name);
        //上面创建的实例对象是通过new新建的Obserbable，引用存到第一个observable1场景里面
        Observable<String> observable1 = Observable.create(new OnSubscrible<String>() {
            //这里的call是在OnSubscribleLift的call方法调用的时候回调的，而call方法是在最下面的subscribe方法中调用的
            @Override
            public void call(Subscrible<? super String> subscrible) {
                //男生：被观察者，生产者，发布者，通知女生
                subscrible.onNext("发送事件1");
            }
        });

        //map函数也产生一个observable2实例对象
        //observable1对象中的onSubscrible对象是我们上面的new OnSubscrible对象
        Observable<Bitmap> observable2 = observable1

                //这里可以看出下面observable2指向的Observable对象内部存放的是map中新建的OnSubscribleLift对象
                .map(new Func1<String, Bitmap>() {
                    //这里的回调是OperaChange也就是变换类回调的，主要是将T转成R
                    @Override
                    public Bitmap call(String s) {
                        System.out.println("============执行变换");
//                Bitmap bitmap = BitmapFactory.decodeFile(s);
                        Bitmap bitmap1 = Bitmap.createBitmap(100, 100, Bitmap.Config.ALPHA_8);
                        return bitmap1;
                    }
                });

        observable2
                //开启订阅，先从observable2中的onSubscribleLift对象开始，通过call调用到OnSubscribleList对象
                .subscrible(new Subscrible<Bitmap>() {
            @Override
            public void onNext(Bitmap bitmap) {
                System.out.println("==========sddasdfasdfsdf");
            }
        });


         Observable.create(new OnSubscrible<String>() {
            @Override
            public void call(Subscrible<? super String> subscrible) {
                System.out.println("===========dddd");
                //这里发送通知
                subscrible.onNext("sssssss");
            }
        }).subscrible(new Subscrible<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("========"+s);
                System.out.println("=============s");
            }
        });

        //输入字符星号化
//        et1.setTransformationMethod(new WordReplacement());
//        et1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        et1.setTransformationMethod(new AsteriskPasswordTransformationMethod());
//        Single.create(new SingleOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
//                    emitter.onSuccess(1444444);
//            }
//        }).subscribe(new SingleObserver<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(Integer integer) {
//                System.out.println(integer);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//        });
//        et1.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

    }

    public void btn(View view){
        String s = et1.getText().toString();
        Log.d("test",s);
    }
}





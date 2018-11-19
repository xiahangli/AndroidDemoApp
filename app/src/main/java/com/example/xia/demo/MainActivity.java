package com.example.xia.demo;

import android.app.Activity;
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


        //输入字符星号化
//        et1.setTransformationMethod(new WordReplacement());
//        et1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        et1.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> emitter) throws Exception {
                    emitter.onSuccess(1444444);
            }
        }).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
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





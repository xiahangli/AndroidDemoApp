package com.example.xia.demo.mvp.core;

import android.app.Activity;
import android.os.Bundle;


/**
 * 将通用的绑定和解绑的操作抽离至BaseActivity中去
 *
 * @param <V>
 * @param <T>
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends Activity {

    protected T mPresenter;//具体的present由子类指定

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresener();
        //todo check if right
        if (mPresenter != null && !mPresenter.isAttach()) {
            mPresenter.attach((V) this);//保证只attach一次
        }
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mPresenter.detach();
    }

    protected abstract T createPresener();
}
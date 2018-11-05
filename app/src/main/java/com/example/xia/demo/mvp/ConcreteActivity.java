package com.example.xia.demo.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.xia.demo.bean.JuheDream;
import com.example.xia.demo.mvp.core.BaseActivity;
import com.example.xia.demo.mvp.p.ConcretePresenter;
import com.example.xia.demo.mvp.v.GetDataView;

import router.RouterConfig;

/**
 * @User Xiahangli
 * @Date 2018/10/22  10:50
 * @Email xiahangli@qq.com
 * @Descrip
 */
@Route(path = RouterConfig.HomePath.INDEX)
public class ConcreteActivity extends BaseActivity<GetDataView, ConcretePresenter> implements GetDataView {

    private Fragment[] mFragment;

    @Autowired//注解方式解析携带的参数
    public String name;
    @Autowired(name = "age")
    public int age;

    @Override
    protected ConcretePresenter createPresener() {
        return new ConcretePresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);//添加在onCreate中，ARouter会自动对Autowired注解标记的字段进行赋值，无需通过getIntent主动获取
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
        mPresenter.getDatas();
    }

    @Override
    public void showData(JuheDream juheDream) {

    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "showLoading.....", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {

    }
}

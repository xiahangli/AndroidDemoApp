package com.example.xia.demo.mvp.p;


import com.example.xia.demo.mvp.core.BasePresenter;
import com.example.xia.demo.mvp.m.RequestData;
import com.example.xia.demo.mvp.m.RequestImpl;
import com.example.xia.demo.mvp.v.GetDataView;
import com.example.xia.demo.retrofit.HttpJuHeMethods;

/**
 * @User Xiahangli
 * @Date 2018/10/23  11:50
 * @Email xiahangli@qq.com
 * @Descrip P层持有V和M的引用
 */
public class ConcretePresenter extends BasePresenter<GetDataView> {

    private RequestData mRequestData;

    /**
     * constructor,创建model
     */
    public ConcretePresenter() {
        mRequestData = new RequestImpl();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (viewReference != null && viewReference.get() != null) {
            viewReference.get().showLoading();
        }
    }

    public void getDatas() {
        HttpJuHeMethods.getInstance().getJokesByHttpResultMap(null);
    }
}

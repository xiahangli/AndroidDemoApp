package com.example.xia.demo.mvp.v;

import com.example.xia.demo.bean.JuheDream;
import com.example.xia.demo.mvp.core.BaseView;

/**
 * @User Xiahangli
 * @Date 2018/10/23  11:54
 * @Email xiahangli@qq.com
 * @Descrip 接口继承
 */
public interface GetDataView extends BaseView {
    public void showData(JuheDream juheDream);
}

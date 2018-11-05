package com.example.xia.demo.mvp.m;


import com.example.xia.demo.retrofit.HttpJuHeMethods;

/**
 * @User Xiahangli
 * @Date 2018/10/23  11:49
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class RequestImpl implements RequestData {
    @Override
    public void getDream() {
        HttpJuHeMethods.getInstance().getJokesByHttpResultMap(null);
    }
}

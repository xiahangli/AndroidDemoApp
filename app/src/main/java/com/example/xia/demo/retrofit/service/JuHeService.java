package com.example.xia.demo.retrofit.service;

import com.example.xia.demo.bean.HttpJuHeResult;
import com.example.xia.demo.bean.JuheDream;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 请求示例：
 * http://v.juhe.cn/dream/query
 * q:梦境关键字，如：黄金 需要utf8 urlencode
 * cid:指定分类，默认全部
 * full: 是否显示详细信息，1:是 0:否，默认0
 */
public interface JuHeService {
    @GET("recommendPoetry")
    Observable<HttpJuHeResult<JuheDream>> getDreams();
//    Observable<HttpJuHeResult<JuheDream>> getDreams(@QueryMap HashMap<String,Object> params);
}
package com.example.xia.demo.cusview;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @User Xiahangli
 * @Date 2018/11/19  11:17
 * @Email xiahangli@qq.com
 * @Descrip
 */
public interface ISocketType {
    /**
     * 连接
     */
    public static final int OPEN = 0;
    /**
     * 重新连接
     */
    public static final int REOPEN = 1;
    /**
     * 发送数据
     */
    public static final int SEND = 2;
    /**
     * 刷新
     */
    public static final int REFRESH = 3;
    /**
     * 关闭
     */
    public static final int CLOSE = 4;


    @IntDef({OPEN, REOPEN, SEND, REFRESH, CLOSE})
@Retention(RetentionPolicy.SOURCE)
public @interface OperationType {
}

    /**
     * 操作失败
     * @param type 操作类型 具体看OperationType
     */
    public void onSuccess(@OperationType int type);


    /**
     * 操作失败
     * @param type 操作类型
     */
    public void onFailed(@OperationType int type, Throwable throwable);
}

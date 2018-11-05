package com.example.xia.demo.bean;

/**
 * @User Xiahangli
 * @Date 2018/10/23  10:34
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class MyResult<T> {
    int code;
    T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MyResult{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }
}

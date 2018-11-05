package com.example.xia.demo.rx;


/**
 * @User Xiahangli
 * @Date 2018/11/1  15:31
 * @Email xiahangli@qq.com
 * @Descrip
 */
public class ResultErrorException extends Exception {

    private Result result;

    public ResultErrorException (Result result){

    }

    public ResultErrorException(String message, Result result){
        super(message);
        this.result=result;
    }

    // 用指定的详细信息和原因构造一个新的异常
    public ResultErrorException(String message, Throwable throwable, Result result){
        super(message,throwable);
        this.result= result;
    }

    public ResultErrorException(Throwable throwable, Result result){
        super(throwable);
        this.result=result;
    }

    public Result getResult() {
        return result;
    }
}

package com.daysurprise.mail.api;

import lombok.Data;

import java.io.Serializable;

/**
 * @Class: com.daysurprise.mail.api.Result
 * @Author: daysurprise
 * @Date: 2021/4/7
 * @Mote: 我于生命之中绽放, 犹如黎明中的花朵
 * @Desc: dubbo接口返回实体
 */
@Data
public class Result<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public static final Integer SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGE = "成功";
    public static final Integer FAIL_CODE = 500;
    public static final String FAIL_MESSAGE = "失败";

    public static <T> Result<T> success(){
        Result result = new Result<Object>();
        result.setCode(SUCCESS_CODE);
        result.setMessage(SUCCESS_MESSAGE);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result result = success();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(){
        Result result = new Result<Object>();
        result.setCode(FAIL_CODE);
        result.setMessage(FAIL_MESSAGE);
        return result;
    }

    public static <T> Result<T> fail(String message){
        Result result = new Result<Object>();
        result.setCode(FAIL_CODE);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> fail(Integer code, String message){
        Result result = new Result<Object>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}

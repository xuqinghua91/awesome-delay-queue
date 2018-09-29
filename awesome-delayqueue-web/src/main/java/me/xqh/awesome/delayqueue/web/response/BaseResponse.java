/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:BaseResponse.java
 * Date:2018/09/29
 */

package me.xqh.awesome.delayqueue.web.response;


/**
 * @author qinghua.xu
 * @date 2018/9/29
 **/
public class BaseResponse<T> {
    private final static String SUCCESS_CODE = "0";
    private final static String SUCCESS_DESC = "success";
    protected String resultCode;
    protected String resultDesc;
    protected T data;

    public BaseResponse(String resultCode,String resultDesc,T data){
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.data = data;
    }
    public BaseResponse(T data){
        resultCode = SUCCESS_CODE;
        resultDesc = SUCCESS_DESC;
        this.data = data;
    }
    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

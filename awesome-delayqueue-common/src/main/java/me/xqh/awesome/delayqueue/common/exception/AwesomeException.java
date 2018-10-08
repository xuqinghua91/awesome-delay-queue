/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:AwesomeException.java
 * Date:2018/09/29
 */

package me.xqh.awesome.delayqueue.common.exception;

/**
 * @author qinghua.xu
 * @date 2018/9/29
 **/
public class AwesomeException extends Exception {

    public AwesomeException(){}
    public AwesomeException(AwesomeErrorCode awesomeErrorCode){
        this.errorMsg= awesomeErrorCode.getErrorDesc();
        this.errorCode = awesomeErrorCode.getErrorCode();
    }
    public AwesomeException(String errorCode,String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    private String errorCode;
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:AwesomeErrorCode.java
 * Date:2018/09/30
 */

package me.xqh.awesome.delayqueue.common.exception;

/**
 * @author qinghua.xu
 * @date 2018/9/30
 **/
public enum AwesomeErrorCode {
    PARAM_ERROR("1","参数错误"),
    UNSUPPORT_STORAGE("2","不支持的存储方式"),
    NO_TOPIC("10000","topic不存在"),
    JOB_ALREADY_EXIST("10001","job已存在"),
    EXCEED_TOPIC_LIMIT("10002","job数量已超出topic限制"),
    ;

    private String errorCode;

    private String errorDesc;

    AwesomeErrorCode(String errorCode,String errorDesc){
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}

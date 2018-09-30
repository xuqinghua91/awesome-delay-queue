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
    NO_TOPIC("10100","topic不存在");

    private String errorCode;

    private String errorDesc;

    AwesomeErrorCode(String errorCode,String errorDesc){
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }
}

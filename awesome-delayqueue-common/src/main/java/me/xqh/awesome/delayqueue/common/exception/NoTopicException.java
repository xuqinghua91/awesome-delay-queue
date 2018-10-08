/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:NoTopicException.java
 * Date:2018/09/30
 */

package me.xqh.awesome.delayqueue.common.exception;

/**
 * @author qinghua.xu
 * @date 2018/9/30
 **/
public class NoTopicException extends AwesomeException {
    public NoTopicException(){
        super(AwesomeErrorCode.NO_TOPIC);
    }

}

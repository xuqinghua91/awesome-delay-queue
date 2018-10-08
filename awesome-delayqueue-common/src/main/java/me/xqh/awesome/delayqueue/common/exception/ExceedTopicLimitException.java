/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:ExceedTopicLimitException.java
 * Date:2018/10/08
 */

package me.xqh.awesome.delayqueue.common.exception;

/**
 * @author qinghua.xu
 * @date 2018/10/8
 **/
public class ExceedTopicLimitException extends AwesomeException {
    public ExceedTopicLimitException(){
        super(AwesomeErrorCode.EXCEED_TOPIC_LIMIT);
    }
}

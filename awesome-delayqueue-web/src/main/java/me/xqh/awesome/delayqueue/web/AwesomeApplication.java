/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:AwesomeApplication.java
 * Date:2018/09/20
 */

package me.xqh.awesome.delayqueue.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@Import(PloyConfiguration.class)
@RestController
public class AwesomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwesomeApplication.class,args);
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "hello, awesome delay queue";
    }
}

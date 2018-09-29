/*
 * Copyright (c) 2018
 * User:qinghua.xu
 * File:AwesomeURL.java
 * Date:2018/09/29
 */

package me.xqh.awesome.delayqueue.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinghua.xu
 * @date 2018/9/29
 **/
public class AwesomeURL {

    private final String storage;
    private final String username;

    private final String password;

    // by default, host to registry
    private final String host;

    // by default, port to registry
    private final int port;

    private final String path;

    private final Map<String, String> parameters;


    protected AwesomeURL() {
        this.storage = null;
        this.username = null;
        this.password = null;
        this.host = null;
        this.port = 0;
        this.path = null;
        this.parameters = null;
    }

    public AwesomeURL(String storage,String username, String password, String host, int port, String path, Map<String, String> parameters) {
        if ((username == null || username.length() == 0)
                && password != null && password.length() > 0) {
            throw new IllegalArgumentException("Invalid url, password without username!");
        }
        this.storage = storage;
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = (port < 0 ? 0 : port);
        // trim the beginning "/"
        while (path != null && path.startsWith("/")) {
            path = path.substring(1);
        }
        this.path = path;
        if (parameters == null) {
            parameters = new HashMap<>();
        } else {
            parameters = new HashMap<>(parameters);
        }
        this.parameters = Collections.unmodifiableMap(parameters);
    }

    public int getParameter(String key, int defaultValue) {
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        int i = Integer.parseInt(value);
        return i;
    }
    public boolean getParameter(String key, boolean defaultValue) {
        String value = getParameter(key);
        if (value == null || value.length() == 0) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
    public String getParameter(String key) {
        String value = parameters.get(key);
        if (value == null || value.length() == 0) {
            value = parameters.get(Constants.DEFAULT_KEY_PREFIX + key);
        }
        return value;
    }

    public String getStorage() {
        return storage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }
}

package com.transgrid.api.constants;

import org.apache.commons.lang3.EnumUtils;

public enum Method {
    GET("get"), POST("post"), DELETE("delete"), PUT("put"), PATCH("patch");
    String name;
    Method(String name){
        this.name=name;
    }
    String getMethodName(){
        return name;
    }


    public static Method getMethodUsingName(String name){
        return  EnumUtils.getEnumList(Method.class).stream().filter(method->method.getMethodName().equalsIgnoreCase(name)).findFirst().orElse(GET);
    }
}

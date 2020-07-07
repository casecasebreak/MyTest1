package com.qjl.iot.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EsAnno {
    //模块莫名称
    String moduleName();
    //操作：增删改查
    String operation();
//    //操作数据Id
//    String Id();
}

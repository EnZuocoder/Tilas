package com.hfut.tilaswebmangement.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//基于自定义注解表示aop切入点
//自定义日志注解
//添加原注解
@Target(ElementType.METHOD)//表示该注解用于方法上
@Retention(RetentionPolicy.RUNTIME)//表示该注解在运行时有效
public @interface LogInfo {
}

package com.mvc.commons.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动注入的注解
 * 	业务层需要注入数据层、而控制层又需要注入业务层
 * 
 * */

@Documented
@Target({ElementType.METHOD,ElementType.FIELD})//该注解可以在方法上使用，也可以在属性上使用
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoWired {
	public String name() default "none";//每一个注解的组件，都会存在一个名称
}

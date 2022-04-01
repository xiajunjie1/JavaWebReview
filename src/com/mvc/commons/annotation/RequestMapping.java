package com.mvc.commons.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在一个项目里，一定会包含有很多控制层类（Action类会有无限多），所以为了可以方便每一个Action准确访问，
 * 最佳的做法是为其配置访问路径，就需要设置有一个访问路径的注解。
 * */
@Documented
//该注解可以在类上使用也可以在方法上使用,如果在类上使用就表示配置父路径，在方法上使用就表示配置子路径
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	public String value() default "/";
}

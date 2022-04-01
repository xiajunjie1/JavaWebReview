package com.mvc.commons.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE})//注解需要在类定义上使用
@Retention(RetentionPolicy.RUNTIME)//该注解在运行时生效
public @interface Controller {
	public String value() default "none";//表示控制层的名称
	//由于项目之中会有很多的控制层，最佳的做法是为每个控制类定义不同的名称，那么对于名称可以用户手工设置也可以通过程序逻辑使用类名称的方式进行配置
	
	
}

package com.maker.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maker.bean.SpringBean;
import com.maker.bean.SpringBeanCopy;
import com.maker.bean.User;

public class Spring_test {
	/**
	 * Spring bean标签，scope属性测试
	 * 当scope为singleton时，程序加载Spring的配置文件时，该bean的实例则被创建了，可以通过在该类的无参构造中加一句打印方法进行验证
	 * 当scope为prototype时，程序在执行getBean()方法时就会调用一次构造方法，即创建一个新的实例对象
	 * 对象的声明周期
	 * 	scope为singleton时，只要容器存在，对象就一直存在，当应用卸载，销毁容器的时候，对象被销毁
	 * 	scope为prototype时，只要对象在使用，对象就存在，当对象长时间不使用时，被java的GC回收
	 * */
	@Test
	public void test1(){
		System.out.println(System.currentTimeMillis()+"：程序开始运行");
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(System.currentTimeMillis()+"：开始获取bean实例...");
		SpringBean bean1=(SpringBean) app.getBean("springbean");
		System.out.println(System.currentTimeMillis()+"：已获取到第一个bean实例...");
		SpringBean bean2=(SpringBean)app.getBean("springbean");
		
		System.out.println(System.currentTimeMillis()+"：已获取到第二个bean实例");
		//singleton范围下，输出的两个值是一样的，prototype的话，两个输出值则不同
		System.out.println("bean1为："+bean1);
		System.out.println("bean2为："+bean2);
	}
	
	/**
	 * 测试Spring bean对象声明周期的控制
	 * 配置init-method,初始化对象后，调用配置好的方法
	 * 配置destroy-method，对象销毁之前，调用配置好的方法
	 * */
	@Test
	public void test2(){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringBeanCopy bean=(SpringBeanCopy) app.getBean("springbean2");
		System.out.println(bean);
		//手动销毁容器
		((ClassPathXmlApplicationContext) app).close();
	}
	
	/**
	 * 依赖注入测试
	 * 	包括基本类型、引用类型、集合类型的注入
	 * 	注入方式包括setter和有参构造
	 * */
	@Test
	public void test3(){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringBeanCopy bean=(SpringBeanCopy) app.getBean("springbean3");
		bean.show();
	}
	
	@Test
	public void test4(){
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		User u=(User) app.getBean("user_2");
		System.out.println(u);
	}
}

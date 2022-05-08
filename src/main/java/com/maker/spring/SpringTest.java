package com.maker.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.maker.bean.SpringBean;

/**
 * Spring
 * 	Spring是分层的JavaSE/EE应用full-stack轻量级开源框架，以ioC（反转控制）和
 * 	AOP（面向切面编程）为内核
 * 
 * 	提供展现层SpringMVC和持久层SpringTemplate以及业务层事务管理等众多企业级应用技术
 * 
 * 	Spring的优势
 * 		方便解耦，简化开发
 * 			通过Spring提供IoC容器，可以将对象间的依赖关系交由Spring进行控制，避免硬编码所造成的的过渡耦合。
 * 			用户也不必再为单例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注上层应用
 * 		AOP的编程支持
 * 			通过Spring的AOP功能，方便进行面向切面编程
 * 		声明式事务的支持
 * 			通过声明式灵活的进行事务管理
 * 		方便程序的测试
 * 
 * 		方便集成各种优秀框架
 * 
 * 		降低JavaEE API的使用难度
 * 			Spring对JavaEE API（如JDBC JavaMail 远程调用等）进行了薄薄的封装层
 * 		
 * 		Java源码是经典的学习范例
 * 
 * 	Spring基本操作：
 * 		1.导入Spring的jar包
 * 		2.编写Bean实例对象
 * 		3.创建Spring核心xml文件
 * 		4.在Spring核心xml文件中配置实例对象
 * 		5.通过Spring的API获取实例对象
 * 
 * 
 * 	Spring 依赖注入：
 * 		一个类中成员对象为另一个类，在通过Spring管理该类时，需要将另一个类注入到该类的成员变量中
 * 		为了实现这个目的，可以利用有参构造或者setter方法实现，所以需要保证被注入的类中，存在有setter方法或者存在有参构造
 * 
 * 		注入数据类型：
 * 			基本数据类型
 * 			引用数据类型
 * 			集合数据类型
 * 
 * 	Spring相关API
 * 		ClassPathXmlApplicationContext
 * 			从类的根路径下加载配置文件推荐使用该接口实现对象
 * 			即src（resource）下的内容都是处于类路径下
 * 		FileSystemXmlApplicationContext
 * 			从磁盘上加载配置文件，配置文件可以位于磁盘的任意位置
 * 			参数为磁盘的具体路径
 * 		AnnotationConfigApplicationContext
 * 			当使用注解配置容器对象时，需要使用此类来创建spring容器。它用来读取注解
 * 		
 * 		public Object getBean(String name)throws BeansException
 * 			使用bean id获取实例对象
 * 		public <T> getBean(Class<T> requiredType)throws BeansException
 * 			使用Class获取实例对象
 * 
 * 	Spring配置数据源
 * 		数据源（连接池）是提高程序性能出现的
 * 		常见的数据源：DBCP、C3P0、Druid、BoneCP等
 * 		
 * 		数据源的开发步骤
 * 			1.导入第三方数据源坐标和数据库驱动坐标
 * 			2.创建数据源对象
 * 			3.设置数据源的基本连接数据
 * 			4.使用数据源获取连接资源和归还连接资源
 * 		
 * 		通过Spring创建数据源对象
 * 			1.在Spring配置文件中配置数据源对象
 * 			2.通过依赖注入的方式，将数据库连接信息传到数据源对象中
 * 		
 * 
 * */
public class SpringTest {
	public static void main(String[] args){
		//从Spring容器获取实例化对象
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringBean bean=(SpringBean) app.getBean("springbean");
		System.out.println(bean.getInfo());
	}
}

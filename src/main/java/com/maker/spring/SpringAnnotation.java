package com.maker.spring;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.maker.config.SpringConfig;
import com.maker.view.UserController;

/**
 * Spring注解
 * 	Spring是轻代码重配置的框架，配置比较繁重，影响开发效率。所以注解是一种趋势，注解用来替代xml
 * 	
 * Spring原始注解
 * 	用来替代bean标签的配置
 * 	@Component注解，代替bean标签
 * 	@Autowired注解，标记自动注入，使用注解注入，注入的属性不需要有setter方法
 * 	@Qualifier注解，确定注入的bean id，该注解要和Autowired配合使用
 * 	@Repository注解，与Component功能一致，表示dao层
 * 	@Service注解，与Component功能一致，表示Service层
 * 	@Controller注解，与Component功能一致，表示Web层
 * 	@Resource(name="beanid"),该标签相当于Qualifier+Autowired
 * 	@Value注解，注入普通数据
 * 	@PostConstruct注解，标记在方法上，代表初始化方法，调用构造器后调用该方法
 * 	@PreDestroy注解，标记在方法上，代表销毁方法，容器关闭后，调用该方法
 * 
 * 在需要实例化的类上配好注解以后，需要告诉Spring哪些类下有注解，需要在Spring配置文件中配置组件扫描
 * 
 * Spring新注解
 * 	对于第三方的类（非自定义bean），原始注解没办法在其源码上添加注解、其他诸如包扫描或者是引用其他配置文件也没有对应的原始注解
 * 	@Configuration，使用在类上，标志该类是Spring的核心配置类
 * 	@ComponentScan,作用和context:component-scan标签一样
 * 	@Bean,使用在方法上，标注将该方法的返回值存储到Spring容器中
 * 	@PropertySource 用于加载properties文件中的配置
 * 	@Import 用于导入其他配置类
 * 	
 * 
 * 
 * 
 * */
public class SpringAnnotation {
	public static void main(String[] args){
		//ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//UserController uc=(UserController) app.getBean("usercontroller");
		//uc.add();
		
		ApplicationContext app2=new AnnotationConfigApplicationContext(SpringConfig.class);
		DruidDataSource datasource=(DruidDataSource) app2.getBean("druid_datasource");
		try {
			Connection con=datasource.getConnection();
			System.out.println("【注解获取数据源】"+con);
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

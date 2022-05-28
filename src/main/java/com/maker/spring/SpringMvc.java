package com.maker.spring;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * SpringMVC开发步骤
 * 	1.导入SpringMVC坐标
 * 	2.配置SpringMVC核心控制器DispatcherServlet
 * 	3.创建Controller类和视图页面
 * 	4.使用注解配置Controller类中业务方法的映射地址
 * 	5.配置SpringMVC和核心文件Spring-mvc.xml
 * 	6.客户端发起请求测试
 * 
 * 	SpringMVC组件解析
 * 		1.用户发送请求到DispatcherServlet
 * 		2.DispatcherServlet调用HandlerMapping处理器映射器
 * 		3.处理器映射器找到具体的处理器（可以根据xml，注解进行查找）生成处理器对象及处理器拦截器（如果有则生成）一并返回DispatcherServlet
 * 		4.DispatcherServlet调用HandlerAdapter处理器适配器
 * 		5.HandlerAdapter根据适配调用具体的Controller
 * 		6.Controller执行完毕返回ModelAndView
 * 		7.HandlerAdapter将ModelAndView传给DispatcherServlet
 * 		8.DispatcherServlet将ModelAndView传到ViewResolve视图解析器
 * 		9.视图解析器解析后返回一个具体的view
 * 		10.DispatcherServlet根据返回的view渲染页面，响应用户
 * 		
 * 	SpringMVC注解
 * 		@RequestMapping:用于建立请求url和处理请求方法之间的对应关系
 * 			位置
 * 				类上：在类上使用，要和具体的处理方法上使用的该注解，一起组成一个虚拟地址
 * 				方法上：在方法上使用可以不在类上使用该注解，那么访问地址直接为该注解的value，若类上也有该注解则需要一起组成一个虚拟地址
 * 			指定访问方法
 * 				@RequestMapping(value="xxxx",method=RequestMethod.POST)
 * 			限制参数
 * 				@RequestMapping(value="xxxx",params={"username"}),在请求该路径时，参数必须携带username参数
 * 
 * 	SpringMVC的数据响应
 * 		SpringMVC的数据响应方式
 * 			1.页面跳转
 * 				直接返回字符串
 * 				通过ModelAndView对象返回
 * 			2.回写数据
 * 				返回字符串，相当于response.getWriter().println()
 * 				返回对象或集合
 * 
 * 		Controller中的方法，是由SpringMVC框架进行调用的
 * 		若在Controller方法中，设置某些特定的形参（如ModeAndView，Model，HttpServletRequest...）
 * 		SpringMVC框架会自己传入一个相应的对象
 * 
 * 		@ResponseBody注解，告诉SpringMVC框架，方法返回字符串不是跳转，而是直接在http响应体中返回，即实现回写功能
 * 			配合Jackson，配置相应的处理器适配器，可以实现返回对象，SpringMVC自动转成JSON字符并回写
 * 			<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">

			<property name="messageConverters">
				<list>
				<bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter" />
				</list>
			</property>
				</bean>
		上述配置可以用一条配置替代：
			<mvc:annotation-driven/>
			在SpringMVC各组件中，处理器映射器，处理器适配器，视图解析器称为SpringMVC三大组件。
			使用<mvc:annotation-driven/>自动加载RequestMappingHandlerMapping和RequestMappingHandlerAdapter
			同时使用<mvc:annotation-driven/>默认会对Jackson进行对象或集合的JSON格式字符串转换
			
	SpringMVC请求参数
		SpringMVC可以接收如下参数
			1.基本类型参数
				Controller中的业务方法的参数名称要与请求参数name一致，参数值会自动映射匹配
			2.POJO类型参数
				Controller中的业务方法的POJO参数的属性名要与请求参数name一致，参数值会自动映射匹配
			3.数组类型参数
				Controller中的业务方法的参数名称要与请求参数name一致,且参数类型为数组
			4.集合类型参数
				集合类型无法直接作为形参接收数据，需要将集合封装到一个VO对象当中
				当客户端使用ajax请求时，可以指定ContentType类型为JSON形式，那么在方法参数位置使用@RequestBody可以直接
				接收集合数据无需使用POJO进行封装
				
	SpringMVC静态资源的访问
		方式一：<mvc:resources location="/js/" mapping="/js/**"/>
 * 		方式二：<mvc:default-servlet-handler/>
 * 
 * 	SpringMVC配置全局乱码过滤器
 * 		当POST请求传输参数时，若参数是中文，会出现乱码
 * 		要解决当前问题，需要在web.xml文件中配置全局乱码过滤器
 * 
 * 	SpringMVC请求参数绑定
 * 		当请求参数名和Controller方法中形参的名字不一致时，可以利用@requestParam(value=请求参数名称)注解进行显示绑定
 * 		@requestParam(value=xxxx,required=true/false,defaultValue=xxxx)
 * 			value:请求参数名
 * 			required:是否必须传入该参数，默认为true，为true时，若不传入参数会报错
 * 			defaultValue:默认值，当没有指定请求参数时，则使用该默认值
 * 
 * 	SpringMVC接收Restful风格参数
 * 		@RequestMapping(/user/restful/{xxx})，在方法上设置访问路径，用占位符占住参数
 * 		@PathVariable(value=xxx)，在方法的形参前加上该注解，xxx要与占位符中的内容一致
 * 		请求：/user/restful/zhangsan就会将zhangsan作为参数注入到方法的形参当中
 * 
 * 	SpringMVC自定义类型转换器
 * 		1.定义转换器类实现Convert接口
 * 		2.在配置文件中声明转换器
 * 		3.在annotation-driven中引用转换器
 * 
 * 
 * 	SpringMVC获得Servlet API
 * 		SpringMVC支持使用原始ServletAPI对象作为控制器方法的参数进行注入，常用对象如下
 * 		HttpServletRequest
 * 		HttpServletResponse
 * 		HttpSession
 * 
 * 
 * 	SpringMVC获取请求头信息
 * 		@RequestHeader注解，相当于request.getHeader(name)
 * 		该注解的属性如下：
 * 			value：请求头的名称
 * 			required：是否必须携带此请求头
 * 
 * 		@CookieValue注解，可以获取指定的Cookie值
 * 			value：指定cookie的名称
 * 			required：是否必须携带此cookie
 * 
 * 
 * 	SpringMVC上传文件
 * 		单位件上传步骤
 * 			1.导入FileUpload和io的坐标
 * 			2.配置文件上传解析器(CommonsMultipartResolver)
 * 			3.编写上传文件代码
 * 		多文件上传
 * 			用数组形参进行接收，循环该数组，重复进行文件的保存
 * 		
 *
 * 
 * */
public class SpringMvc {
	DispatcherServlet ds;
	CommonsMultipartResolver cms;
	
}

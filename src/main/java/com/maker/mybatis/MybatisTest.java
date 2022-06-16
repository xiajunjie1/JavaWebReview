package com.maker.mybatis;
/**
 * Mybatis
 * 	原始jdbc操作的分析
 * 	原始jdbc开发存在的问题如下：
 * 		数据库连接创建、释放频繁造成系统资源浪费从而影响系统性能
 * 		sql语句在代码中硬编码，造成代码不易维护，实际应用sql变化的可能性较大，sql变动需要修改java代码
 * 		查询操作时，需要手动将结果集中的数据手动封装到实体中，插入操作时，需要手动将实体的数据设置到sql语句的占位符位置
 * 
 * 	应对上述问题给出的解决方案
 * 		使用数据库连接池初始化连接资源
 * 		将SQL语句抽取到xml配置文件中
 * 		使用反射、内省等底层技术，自动将实体与表进行属性与字段的自动映射
 * 
 * 	mybatis是一个优秀的基于java的持久层框架，它内部封装了jdbc，使开发者只需要关注sql语句本身
 * 	mybatis通过xml或注解的方式将要执行的各种statement配置起来，并通过java对象和statement中的sql的动态参数进行映射生成最终执行的sql语句
 * 	mybatis框架执行SQL并将结果映射为java对象并返回。采用ORM思想解决了实体和数据库映射的问题，对jdbc进行了封装
 * 
 * Mybatis快速入门
 * 	官网：http://www.mybatis.org/mybatis-3/
 * 	1.导入jar包
 * 	2.创建account数据表
 * 	3.编写Account实体类
 * 	4.编写映射文件UserMapper.xml-----主要编写sql语句
 * 	5.编写核心文件SqlMapConfig.xml----mybatis相关的参数配置
 * 	6.编写测试类
 * 
 * 	Mybatis映射文件
 * 		AccountMapper.xml(Mapper.xml)
 * 	Mybatis核心配置文件
 * 		sqlMapConfig.xml
 * 
 * 
 * 	Mybatis API
 * 		SqlSessionFactory build(inputStream)，SQLSession工厂
 * 			inputStream为Mybatis的核心配置文件的io流，可以利用Resources.getResourceAsStream(resource);
 * 			Resources这个类在org.apache.ibatis.io包中
 * 
 * 		SqlSessionFactory创建SqlSession的方法：
 * 			openSession()
 * 			openSession(boolean autoCommit):参数为是否自动提交，如果设置为true，那么不用手动提交事务
 * 			
 * 		SqlSession主要方法
 * 			<T> T selectOne(String statement,Object parameter):查询一个结果
 * 			<E>	List<E> selectList(String statement,Object parameter)：查询多条数据，返回一个集合
 * 			int insert(String statement,Object parameter)
 * 			int update(String statement,Object parameter)
 * 			int delete(String statement,Object parameter)
 * 		操作事务的主要方法
 * 			void commit()
 * 			void rollback()
 * 
 * 
 * 	Mybatis dao层的实现
 * 		代理开发方式
 * 			Mapper接口开发方法只需要程序员编写Mapper接口（dao接口），由Mybatis框架根据接口定义创建接口的动态代理对象，代理接口的方法体通dao接口实现的方法体
 * 		Mapper接口开发要遵循以下开发规则
 * 			1、Mapper.xml文件中的namespace与Mapper接口的全限定名相同
 * 			2、Mapper接口的方法名和Mapper.xml中定义的每个statement的id相同
 * 			3、Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql的parameterType的类型相同
 * 			4、Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同
 * 
 * 
 * 	Mybatis动态SQL
 * 		<where>标签
 * 			用于定条件的标签，将if标签或者foreach标签放在里面
 * 		<if>标签
 * 			判断标签，使用场景，条件查询，当有多个条件时，满足条件就讲对应的sql语句拼接到查询sql语句中
 * 				如：Select * from account where name=xxx and yyy
 * 		<foreach>标签
 * 			循环标签，使用场景，当参数为一个集合或数组时使用
 * 				如：Select * from account where name in (xxx,yyy)
 * 
 * 		<sql>标签
 * 			抽取sql语句，在对应表的查询语句中，一般有变化的是where 后的条件内容，而非条件的内容往往是固定的，可以使用sql标签进行抽取
 * 
 * 
 * 	Mybatis核心配置文件的深入
 * 		typeHandlers标签
 * 			无论是Mybatis在预处理语句中设置一个参数时，还是从结果集中取出一个值时，都会用类型处理器将获取到的值以合适的方式转换成java类型
 * 			如果转换不满足要求，则可以重写转换器或者自定义转换器
 * 
 * 		具体做法：实现org.apache.ibatis.type.TypeHandler接口，或继承一个org.apache.ibatis.type.BaseTypeHandler,然后可以选择性地将它映射到一个JDBC类型。
 * 	
 * 		plugins标签
 * 			mybatis可以使用第三方的插件来对功能进行扩展，分页助手PageHelper是将分页的复杂操作进行封装，使用简单的即可获得分页的相关数据
 * 			开发步骤
 * 				1.导入通用PageHelper的坐标
 * 				2.在mybatis核心配置文件中配置PageHelper插件
 * 				3.测试分页数据的获取
 * 
 * 
 * Mybatis多表操作
 * 	一对一查询模型
 * 		orders----user
 * 		一个用户可以有多个订单，但是一个订单只属于一个用户
 * 		一对一查询需求，查询一个订单，同时查询出该订单所属的用户
 * 	
 * 	一对多查询模型
 * 		一对多查询需求，查询一个用户，同时查出该用户的所有订单
 * 
 * 	多对多查询模型
 * 		多对多查询和一对多查询的配置方式类似，但是多对多查询一般有三张表
 * 		在此处以sys_user,sys_role和sys_user_role作为例子
 * 
 * 	Mybatis注解开发常用注解
 * 		@Insert:实现新增
 * 		@Update:实现更新
 * 		@Delete:实现删除
 * 		@Select:实现查询
 * 		@Result:实现结果集的封装
 * 		@Results:可以与@Result一起使用，封装多个结果集
 * 		@One:实现一对一结果集封装
 * 		@Many:实现一对多结果集封装
 * 		
 * 		
 * */
public class MybatisTest {

}

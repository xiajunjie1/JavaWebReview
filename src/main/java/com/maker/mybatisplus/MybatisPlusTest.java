package com.maker.mybatisplus;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;

/**
 * mybatis-plus是一个Mybatis的增强工具，在mybatis的基础上只做增强，不做改变
 * 	官网：https://baomidou.com/
 * 
 * 对于mybatis-plus的整合一般有三种
 * 	1.mybatis+mp
 * 	2.Spring+mybatis+mp
 * 	3.SpringBoot+mybatis+mp
 * 
 * 
 * 通用的CRUD
 * 	插入操作
 * 		int insert(T entity);
 * 
 * 	更新操作
 * 		int updateById(@Param(Constants.ENTITY) T entity):根据ID来进行更新
 * 		int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper)：根据条件来做更新
 * 
 * 	删除操作
 * 		int deleteById(T entity)：根据Id进行删除
 * 		int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap)：将条件封装到map中传递给该方法
 * 		int delete(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper)：根据条件进行删除
 * 		int deleteBatchIds(@Param(Constants.COLLECTION) Collection<?> idList)：根据id进行批量删除
 * 
 * 	查询操作
 * 		 T selectById(Serializable id)：根据Id进行查询
 * 		 List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList)：根据多条id查询多条结果，类似where id in (1,2,3...)
 * 		 default T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper)：查询一条数据 
 * 
 * 	SQL注入的原理
 * 		MP在启动以后，会将BaseMapper中的一系列方法注册到mappedStatements中，那么究竟是如何注入的呢？
 * 		在MP中，ISqlInjector负责SQL的注入工作，它是一个接口，AbstractSqlInjector是它的实现类
 * 		在AbstractSqlInjector中，主要由inspectInject()方法进行注入
 * 
 * 	DB策略配置
 * 		idType（id生成策略）：一般用auto，自增长。可通过注解实现，也可以通过配置文件实现
 * 			注解实现：在对应的实体类的id属性上加上：@TableId(type=IdType.AUTO)的注解
 * 		tablePrefix：表名前缀，全局配置后可省略@TableName注解
 * 	
 * 	条件构造器（Wrapper）
 * 		AbstractWrapper
 * 			采用传统的java方式编写
 * 		AbstractChainWrapper
 * 			采用Lambda编写
 * 
 * 		QueryWrapper和UpdateWrapper的父类用于生成sql的where条件，entity属性也用于生成sql的where条件
 * 		注意，entity生成的where条件和使用各个api生成的where条件，没有任何的关联性
 * 
 * 		allEq
 * 
 * 		基本比较操作
 * 			eq，等于=
 * 			ne，不等于<>
 * 			gt，大于>
 * 			lt，小于<
 * 			ge，大于等于>=
 * 			le，小于等于<=
 * 			between，BETWEEN 值1 AND 值2
 * 			notBetween，NOT BETWEEN 值1 AND 值2
 * 			in，字段 IN (val1,val2,val3...)
 * 			notin，字段 NOT IN(val1,val2,val3...)
 * 
 * 		模糊查询操作
 * 			like，LIKE %'值'%
 * 			notLike，NOT LIKE %'值'%
 * 			likeLeft，LIKE %'值'
 * 			likeRight，LIKE '值'%
 * 
 * 		排序查询操作
 * 			orderBy，ORDER BY 字段,...
 * 			orderByAsc，ORDER BY 字段,... ASC 	排序升序
 * 			orderByDesc，ORDER BY 字段，... DESC	排序降序
 * 
 * 
 * 		逻辑查询操作
 * 			or()
 * 
 * 		查询特定字段
 * 			select()
 * 
 * 		ActiveRecord
 * 			主要思想：每一个数据库表对应创建一个类，类的每一个对象实例对应于数据库中表的一行记录；通常表的每个字段在类中都有相应的Field
 * 				ActiveRecord同时负责把自己持久化，在ActiveRecord中封装了对应数据库的访问，即CURD；
 * 				ActiveRecord是一种领域模型，封装了部分业务逻辑
 * 
 * 			使用AR，只需要让实体类继承Model<T>即可
 * 			
 * 			
 * 		
 * 		
 * */
public class MybatisPlusTest {

	MybatisPlusInterceptor interceptor=new MybatisPlusInterceptor();
}

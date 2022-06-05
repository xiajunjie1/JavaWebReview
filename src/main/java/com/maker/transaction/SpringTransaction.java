package com.maker.transaction;

import org.springframework.transaction.annotation.Transactional;

/**
 * Spring事务控制
 * 	编程式事务控制
 * 		PlantformTransactionManager接口，平台事务管理器
 * 			它是Spring的事务管理器，它提供如下的方法
 * 			TransactionStatus getTransaction(TransactionDefination defination)：获取事务的状态信息
 * 			void commit(TransactionStatus status)：提交事务
 * 			void rollback(TransactionStatus status)：回滚事务
 * 
 * 		PlantformTransactionManager是接口类型，不同的dao层技术则有不同的实现类，例如：Dao层的技术是JDBC或myBatis
 * 		时：org.springframework.jdbc.datasource.DataSourceTransactionManager,Dao层技术是hibernate时：org.springframework.orm.hibernateTranscationManager
 * 		
 * 		TransactionDefination是事务的定义信息对象
 * 			int getIsolationLevel():获得事务隔离级别
 * 			int	getPropogationBehavior():获得事务的传播行为
 * 			int getTimeout():获得超过时间
 * 			boolean isReadOnly():是否只读
 * 
 * 		设置事务隔离级别，可以解决事务并发产生的问题，如脏读、不可重复读和虚读		
 * 			ISOLATION_DEFAULT：默认的，与数据库保持一致
 * 			ISOLATION_READ_UNCOMMITTED
 * 			ISOLATION_READ_COMMITTED:解决脏读
 * 			ISOLATION_REPEATABLE_READ：解决不可重复读
 * 			ISOLATION_SERIALIZABLE	
 * 
 * 		事务传播行为：当a业务方法调用b业务方法的情景，如果对a方法和b方法都进行了事务控制，那么就会出现重复或统一的问题，这时候就用事务传播行为来解决	
 * 			REQUIRED：如果当前没有事务，就创建一个新的事务，如果存在事务就加入到这个事务，一般的选择（默认值）
 * 			SUPPORTS：支持当前事务，如果当前没有事务，就以非事务的方式运行。
 * 			NOT_SUPPORTS：以非事务的形式运行，如果当前存在事务，则将事务挂起
 * 			MANDATORY：使用当前事务，如果当前没有事务就抛出异常
 * 			RQUERS_NEW：新建事务，如果当前在事务中，则把事务挂起
 * 			NEVER：以非事务的方式运行，如果当前存在事务，则抛出异常
 * 			NESTED：如果当前有事务，则在嵌套事务内执行，如果当前没有事务，则执行REQUIRED类似操作
 * 			超时时间：默认是-1，没有超时限制，如果有，以秒为单位进行设置
 * 			是否只读：建议查询时设置为只读
 * 
 * 		TransactionStatus接口提供的是事务具体的运行状态，方法介绍如下
 * 			boolean hasSavepoint() 是否存储回滚点
 * 			boolean isCompleted()	事务是否完成
 * 			boolean isNewTransaction()	是否是新事务
 * 			boolean isRollbackOnly()	事务是否回滚
 * 
 * 	声明式事务控制
 * 		基于xml
 * 			在配置文件当中声明代替硬编码方式控制事务
 * 			
 * 		基于注解
 * 			需要在xml中，配置事务管理器，以及声明注解驱动
 * 				<tx:annotation-driven transaction-manager="PlantformTransactionManager"/>
 * 			在业务方法上使用@Transactional注解，该注解可以用在目标方法上也可以用在对应的业务类上，若两者都存在则采用就近原则
 * */

public class SpringTransaction {

}

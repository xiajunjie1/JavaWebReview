package com.maker.review;
/**
 * SSM框架整合
 * 	原始整合方式：每种框架各司其职，正常导入坐标编写配置文件使用
 * 	使用原始的整合方式会存在一些问题，主要是存在在service层
 * 	每次调用对应的方法都要产生session然后通过session获得Mapper，同时也要进行事务的提交和关闭
 * 
 * Spring整合mybatis
 * 	通过Spring来控制mybatis相关的Session的生成
 * 	通过Spring的声明式事务控制来控制相应的事务
 * 	需要导入mybatis-spring的jar包
 * 
 * */
public class SSMReview {
	
}

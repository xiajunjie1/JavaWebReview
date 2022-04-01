package com.mvc;
/**
 * 项目的分层设计思想
 * 	例如：某一计算的修改不应该影响到客户端的执行，而按照java多年积累的设计分层结构来讲，一般会认为如下几层
 * 		1.业务客户端
 * 			数据显示（jsp，xml，html,json）、控制层（请求处理和响应）
 * 		2.业务中心
 * 			业务层、数据层、数据服务（JDBC、Jedis）
 * 		3.持久化存储
 * 			MySQL、redis...
 * 
 * 		业务中心是整个项目的核心，几乎所有的业务设计都要围绕数据层的设计展开，没有SQL数据库（或者是NoSQL）业务实现是难以展开的
 * 		
 * 
 * 程序类与数据表映射
 * 	ps：已在mysql数据库中建立了member表
 * 		CREATE TABLE `member` (
  		`mid` int(11) NOT NULL AUTO_INCREMENT,
  		`name` varchar(255) NOT NULL,
  		`age` int(11) DEFAULT NULL,
  		`email` varchar(255) DEFAULT NULL,
  		`sex` varchar(255) NOT NULL,
  		`birthday` date DEFAULT NULL,
  		`note` text,
  		PRIMARY KEY (`mid`)
		) ENGINE=InnoDB DEFAULT CHARSET=gbk;

	既然要进行软件项目的分层，那么不同层之间就一定会存在有数据的传输以及数据的返回操作
	那么既然所有的处理已经被程序代码封装了，所以不同层之间是不应该彼此进行实现暴露的
	以数据增加的操作为例：如果想要增加数据，则一定要有完善的数据信息，而这个数据信息又可以和数据表有所对应，同时还要满足java操作类的特点，此时我们可以想到简单java类
	
	一个项目中有可能会有若干张实体数据表，那么每一张表应该映射为一个简单java类，该类中所提供的的属性名称
	和表中的字段名称对应，类型也需要对应
		所有的简单java类，应该保存在一个vo包当中，考虑到简单java类有传输的需要，应该让其实现序列化接口
	
	数据层：
		数据层是一个独立的数据库操作汇总，既然现在的设计采用了分层结构，那么在分层结构中就需要注意到不同层之间需要有一个“封装”的概念
		这种情况下就应该将所有可能涉及到的SQL操作直接进行标准化的定义，例如：不同层之间依靠接口解耦合和处理操作，所以应该在整个项目之中
		创建有一个具体的数据接口，而后这个接口被数据层的内部实现，同时该接口又可以暴露数据层中所有的操作方法，并且这些方法允许被业务层使用
			DAO接口标准
			AbstractDAO抽象类（公共父类）
				Connection公共属性
				PreparedStatement公共属性
			工厂类
		通过以上结构可以知道，DAO接口是被业务对象所调用的，但是在实际的开发过程中，既然已经通过接口进行分层设计，
		就应该考虑将业务的具体实现子类进行封装，就应该设计有一个专属工厂类，通过工厂类获取接口实例，以达到对外部
		隐藏子类实现细节的功能
		
		既然数据层的操作，都是以SQL操作为主，在jdbc中，如果想要安全的执行SQL操作最佳的做法就是使用PreparedStatement
		接口完成处理，那么此时就需要获取java.sql.connection的实例，既然所有的数据层都需要同样的操作步骤，最佳的做法
		就是进一步的抽象实现。可以考虑利用一个公共的dao抽象类操作工具
		抽象类最大的特点是无法使用关键字new进行对象实例化处理，要想使用就必须被子类继承，被继承可以保证其内部方法被所有子类使用
		
		一旦实现这样的封装，最大的优点在于，所有的SQL操作都被方法所封装，用户调用特定方法即可实现数据库的操作
		
			
	
 * */
public class Mvc_test {

}
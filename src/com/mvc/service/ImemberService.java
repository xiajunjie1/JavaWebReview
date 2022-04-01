package com.mvc.service;

import java.util.List;
import java.util.Map;

import com.mvc.vo.Member;

/**
 * 业务层就需要对外暴露你可能存在的各种的逻辑操作，而这些逻辑操作的集合就称为业务集合，所有的业务集合在业务层中进行配置
 * 	业务层可以将其称为BO或者Service
 * 
 * 业务逻辑处理的时候除了要进行一些基础判断操作之外，也可能要完成一些数据层的调用，最重要的一点
 * 一个业务层有可能需要调用若干个数据层才可以完成最终所需要的开发功能
 * 在进行业务操作的时候一般也建议创建一个抽象类，这些抽象类可以完成一些公共业务逻辑功能，或者定义一些
 * 子类可能使用到的方法
 * 
 * 由于一个业务层接口有可能需要牵扯到多个数据层对象的处理操作，所以最佳的做法是在业务层上实现数据库的连接
 * 关闭处理操作，对于事务也是要在业务层中进行良好的控制，但是如果想要进行合理的事务处理，就需要对业务层中
 * 的方法进行一些限定，例如：只有更新操作可能存在有事务的控制需要，可以将一些更新操作方法的前缀，单独罗列出来
 * 如：addXXX() editXXX() removeXXX() updateXXX(),查询操作可以根据自身的需要进行方法定义，但是
 * 一定都要有意义
 * 
 * */
public interface ImemberService {
	//处理业务接口
	//在实际的开发中，业务层方法抛出的异常应该是业务相关的异常，例如我们可以自定义一些Runtime异常，来进行抛出
	/*
	 * 用户的增加
	 * 1、【程序逻辑】判断输入的年龄是否符合要求
	 * 2、【程序逻辑】判断输入的性别是否合法
	 * 3、【数据层】判断输入的mid是否重复
	 * 4、【数据层】判断输入的email是否重复
	 * 5、【数据层】无任何验证问题，执行插入操作
	 * @param member 需要增加的用户信息
	 * @return 插入成功返回true，插入失败返回false
	 * @throws 业务有关的异常，为简化抛出Exception
	 * */
	public boolean Add(Member member)throws Exception;
	
	/*
	 * 根据mid，更新用户的全部信息
	 * 1、【程序逻辑】判断输入的年龄是否符合要求
	 * 2、【程序逻辑】判断输入的性别是否合法
	 * 3、【数据层】判断更新后的email是否重复
	 * 4、【数据层】无任何验证问题，执行更新操作
	 * @param member 需要增加的用户信息
	 * @return 更新成功返回true，更新失败返回false
	 * @throws 业务有关的异常，为简化抛出Exception
	 * */
	public boolean Edit(Member member)throws Exception;
	/*
	 * 根据mid，删除若干个用户的信息
	 * 1、【程序逻辑】判断输入参数信息是否存在（即id数组的长度是否大于0）
	 * 2、【数据层】通过mid，进行用户信息的删除
	 * @param ids 需要删除的用户mid数组
	 * @return 删除成功返回true，删除失败返回false
	 * @throws 业务有关的异常，为简化抛出Exception
	 * */
	public boolean RemoveByid(String ... ids)throws Exception;
	
	/*
	 * 根据mid，查询用户信息
	 * @param id 要查询的id
	 * @return 数据以vo实例对象的形式返回，若数据不存在，则返回null
	 * @throws 业务有关的异常，为简化抛出Exception
	 * */
	public Member get(String id)throws Exception;
	
	/*
	 * 获取数据表中全部的用户信息
	 * @return 数据以vo列表的形式返回，若数据不存在，则返回长度为0的列表对象（不为空）
	 * @throws 业务有关的异常，为简化抛出Exception
	 * */
	public List<Member> list()throws Exception;
	
	/*
	 * 分页查询Member，同时支持模糊查询
	 * @param currentPage 当前页
	 * @param pagesize 每页显示的行数
	 * @param colum 需要模糊查询的列（字段）
	 * @param keyword 模糊查询的关键字
	 * @return 返回Map集合，该集合中保存如下的内容
	 * 	1、key=allmember value=List<Member> 表示所有的用户列表
	 * 	2、key=allrecords value=Long 表示所有的记录数
	 * @throws 业务有关的异常，为简化抛出Exception
	 * */
	public Map<String,Object> split(int currentPage,int pagesize,String colum,String keyword)throws Exception;
	
	
}

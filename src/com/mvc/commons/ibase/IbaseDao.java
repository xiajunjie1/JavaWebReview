package com.mvc.commons.ibase;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * 数据层接口
 * 	一个项目中不可能只有一张数据表，它往往可能要包含多张表，而这多张表当中就会牵扯到不同的数据层接口（针对具体的一张表的操作可能就是一个接口）
 * 不同的数据表可能存在有相同的工作操作：根据ID查询，更新，删除等，但是不同的数据表的操作可能针对于主键类型以及操作结构类型上会有极大的不同
 * 那么可以考虑对数据层接口做进一步的抽象管理，将所有的公共的数据操作方法定义在此接口之中，随后所有的数据层接口去继承该接口
 * 
 * ID:表示不同数据库表主键的类型
 * VO:表示不同数据库表映射转换的数据类型
 * 
 * 在IbaseDao中，一般包含如下的几类操作方法：
 * 	1.数据更新：建议doXxx的形式进行命名---doEdit()、doRemove()
 * 	2.数据查询：建议使用findXxx形式命名---findById()
 * 	3.数据统计：建议使用getXxx()形式命名---getAllCount()
 * 
 * 将公共的方法，定义在该接口中后，就可以针对具体的数据库表，创建对应的接口，继承该接口
 * 同时可以根据实际的情况，扩充接口中的方法
 * */
public interface IbaseDao<ID,VO> {
	/*
	 * 实现信息的增加操作，该方法主要执行insert命令
	 * @param vo：增加数据所保存的Vo对象实例
	 * @return 返回true表示成功，返回false表示失败
	 * @throws SQLException,当前进行的是数据库的操作，所以所有的异常为SQLException
	 * 
	 * */
	public boolean doCreate(VO vo)throws SQLException;
	/*
	 * 实现信息的修改操作，该方法主要根据主键执行update命令
	 * @param vo：要修改数据的Vo对象实例
	 * @return 返回true表示成功，返回false表示失败
	 * @throws SQLException,当前进行的是数据库的操作，所以所有的异常为SQLException
	 * 
	 * */
	public boolean doEdit(VO vo)throws SQLException;
	
	/*
	 * 实现数据的批量删除操作，该方法主要执行delete命令
	 * @param ids：批量删除数据的VO对象集合
	 * @return 删除指定数量数据后返回true表示成功，返回false表示失败
	 * @throws SQLException,当前进行的是数据库的操作，所以所有的异常为SQLException
	 * 
	 * */
	public boolean doRemove(Set<String> ids)throws SQLException;
	
	/*
	 * 根据主键id查询数据表信息，主要执行select操作
	 * @param id：传入的主键id
	 * @return 如数据存在将查询到的单条数据封装成VO对象进行返回，否则返回null
	 * @throws SQLException,当前进行的是数据库的操作，所以所有的异常为SQLException
	 * 
	 * */
	public VO findById(ID id)throws SQLException;
	
	/*
	 * 查询整表信息
	 * @return 返回多条数据，每条数据封装成VO对象后放入List集合中，返回集合。若数据表为空，则返回空集合（empty集合长度为0，并不是null）
	 * @throws SQLException,当前进行的是数据库的操作，所以所有的异常为SQLException
	 * 
	 * */
	public List<VO> findAll()throws SQLException;
	
	
	/*
	 * 实现分页查询表格
	 * @param curpage：表示当前页
	 * @param pagesize：表示一页显示的行数
	 * @return 返回多条数据，每条数据封装成VO对象后放入List集合中，返回集合。若数据表为空，则返回空集合（empty集合长度为0，并不是null）
	 * @throws SQLException,当前进行的是数据库的操作，所以所有的异常为SQLException
	 * 
	 * */
	public List<VO> findSplit(Integer curpage,Integer pagesize)throws SQLException;
	
	/*
	 * 进行数据的分页加载，并在加载的时候可以进行数据的模糊查询
	 * @param curpage：表示当前页
	 * @param pagesize：表示一页显示的行数
	 * @param colum:需要模糊查询的列（字段）
	 * @param keyword:模糊查询的关键字
	 * @return 返回多条数据，每条数据封装成VO对象后放入List集合中，返回集合。若数据表为空，则返回空集合（empty集合长度为0，并不是null）
	 * @throws SQLException,当前进行的是数据库的操作，所以所有的异常为SQLException
	 * 
	 * */
	public List<VO> findSplit(Integer curpage,Integer pagesize,String colum,String keyword)throws SQLException;
	
	/*
	 * 进行数据表全部数据的个数统计
	 * @return 返回数据的条数
	 * @throws SQLException,当前进行的是数据库的操作，所以所有的异常为SQLException
	 * 
	 * */
	public Long getAllCount()throws SQLException;
	
	/*
	 * 进行数据表中模糊查询的数据记录的个数统计
	 * @param colum:需要模糊查询的列（字段）
	 * @param keyword:模糊查询的关键字
	 * @return 查询到的结果的条数
	 * @throws SQLException,当前进行的是数据库的操作，所以所有的异常为SQLException
	 * 
	 * */
	public Long getAllCount(String colum,String keyword)throws SQLException;
	
}

package com.maker.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.maker.domain.Account;
import com.mysql.cj.xdevapi.SessionFactory;

public class MybatisTest{
	
	private static InputStream stream;
	static {
		try {
			stream=Resources.getResourceAsStream("sqlMapConfig.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 用mybatis进行一次查询
	 * */
	@Test
	public void test1() throws Exception{
		//获得核心配置文件
		
		//获得session工厂对象
		SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(stream);
		//获得session会话对象
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//执行操作,参数为namespace+id
		List<Account> list=sqlSession.selectList("accountMapper.findall");
		System.out.println(list);
		//释放资源
		sqlSession.close();
	}
	
	/**
	 * 插入数据测试
	 * */
	@Test
	public void test2()throws Exception{
		Account account=new Account();
		account.setName("xia");
		account.setMoney(1234.56);
		
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(stream);
		SqlSession sqlsession=sessionFactory.openSession();
		sqlsession.insert("accountMapper.add", account);
		//Mybatis中的更新操作，事务默认不会自动提交，需要手动提交
		sqlsession.commit();
		sqlsession.close();
		
	}
	
	/**
	 * 更新数据测试
	 * */
	@Test
	public void test3()throws Exception{
		Account account=new Account();
		account.setMoney(4567.89);
		account.setName("xia");
		SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(stream);
		SqlSession session=sessionFactory.openSession();
		session.update("accountMapper.update", account);
		session.commit();
		session.close();
		
	}
	
	/**
	 * 数据删除测试
	 * */
	@Test
	public void test4()throws Exception{
		SqlSessionFactory sessionFactroy=new SqlSessionFactoryBuilder().build(stream);
		SqlSession session=sessionFactroy.openSession();
		session.delete("accountMapper.delete", "xia");
		session.commit();
		session.close();
	}
}

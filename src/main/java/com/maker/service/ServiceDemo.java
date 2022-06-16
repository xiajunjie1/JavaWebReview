package com.maker.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.maker.dao.AccountMapper;
import com.maker.domain.Account;
/**
 * 对Mybatis代理开发模式进行测试
 * */
public class ServiceDemo {
	public static void main(String[] args){
		InputStream stream;
		try {
			stream = Resources.getResourceAsStream("sqlMapConfig.xml");
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(stream);
			SqlSession session=factory.openSession(true);
			//通过name与配置文件中的命名空间对应上，从而创建一个接口的代理类
			AccountMapper accountdao=session.getMapper(AccountMapper.class);
			List<Account> alist=accountdao.getAll();
			System.out.println(alist);
			Account account=accountdao.getOne("zhangsan");
			System.out.println(account);
			
			Account condition=new Account();
			//condition.setName("zhangsan");
			condition.setMoney(9000.00);
			System.out.println(condition.getMoney());
			List<Account> result=accountdao.getCondition(condition);
			System.out.println(result);
			
			List<String> names=new ArrayList<>();
			names.add("zhangsan");
			names.add("lisi");
			names.add("wangwu");
			List<Account> result2=accountdao.getConditionByNames(names);
			System.out.println(result2);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("文件读取错误");
			e.printStackTrace();
		}
		
	}
}

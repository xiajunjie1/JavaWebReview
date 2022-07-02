package com.maker.service.impl;

import java.io.IOException;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maker.dao.AccountDao;
import com.maker.domain.Account;
import com.maker.service.AccountService;
/**
 * 利用Spring整合mybatis代替原始整合方式，Spring生成Session工厂，同时为接口生成相应的mapper实现类
 * service层可以直接注入获得相应的mapper
 * */
@Service("aservice2")
public class AccountServiceImp2 implements AccountService {
	@Autowired
	private AccountDao adao;
	
	@Override
	public List<Account> listall() throws Exception {
		System.out.println("Service2执行");
		List<Account> alist=adao.findAll();
		return alist;
	}

	@Override
	public void save(Account ac) throws Exception {

		System.out.println("Service2执行");
		adao.save(ac);


	}

	@Override
	public void update(Account ac) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String name) throws Exception {
		// TODO Auto-generated method stub

	}

}

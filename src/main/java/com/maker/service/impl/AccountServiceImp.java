package com.maker.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import com.maker.dao.AccountDao;
import com.maker.domain.Account;
import com.maker.service.AccountService;
@Service("aservice")
public class AccountServiceImp implements AccountService {
	private static String filename="sqlMapConfig.xml";
	private static SqlSessionFactory factory;
	
	static {
		try {
			InputStream stream=Resources.getResourceAsStream(filename);
			AccountServiceImp.factory=new SqlSessionFactoryBuilder().build(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Account> listall() throws Exception {
		// TODO Auto-generated method stub
		SqlSession session=factory.openSession();
		AccountDao adao=session.getMapper(AccountDao.class);
		List<Account> alist=adao.findAll();
		return alist;
	}

	@Override
	public void save(Account ac) throws Exception {
		SqlSession session=factory.openSession();
		AccountDao adao=session.getMapper(AccountDao.class);
		
		adao.save(ac);
		session.commit();
		session.close();

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

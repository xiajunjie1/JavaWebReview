package com.mvc.test;

import java.sql.SQLException;
import java.util.Date;

import com.mvc.dao.ImemberDao;
import com.mvc.util.factory.ObjectFactory;
import com.mvc.vo.Member;

public class DaoTest {
	public static void main(String[] args){
		ImemberDao mdao=ObjectFactory.getDaoInstance("member.dao", ImemberDao.class);
		Member m=new Member();
		m.setMid("xia");
		m.setName("夏");
		m.setAge(25);
		m.setEmail("xia@100.com");
		m.setSex("male");
		m.setBirthday(new Date());
		m.setNote("this is a test!");
		try {
			System.out.println(mdao.doCreate(m));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

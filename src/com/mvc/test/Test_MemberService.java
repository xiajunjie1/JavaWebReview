package com.mvc.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.mvc.service.ImemberService;
import com.mvc.util.factory.ObjectFactory;
import com.mvc.vo.Member;
import com.sun.xml.internal.ws.policy.AssertionSet;

import junit.framework.Assert;

public class Test_MemberService {
	private ImemberService mserivce=ObjectFactory.getServiceInstance("member.service", ImemberService.class);
	@Test
	public void testAdd() throws Exception {
		/*
		Member m=new Member();
		m.setMid("jun");
		m.setName("俊");
		m.setSex("男");
		m.setEmail("12345@xia.com");
		m.setAge(20);
		m.setBirthday(new Date());
		Assert.assertTrue(this.mserivce.Add(m));
		//fail("Not yet implemented");*/
	}

	@Test
	public void testEdit() throws Exception {
		/*
		Member m=this.mserivce.get("jun");
		System.out.println(m);
		m.setEmail("943785@qq.com");
		m.setNote("test!!!!");
		
		Assert.assertTrue(this.mserivce.Edit(m));
		*/
	}

	@Test
	public void testRemoveByid() {
		
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testList() {
		fail("Not yet implemented");
	}

	@Test
	public void testSplit() {
		fail("Not yet implemented");
	}

}

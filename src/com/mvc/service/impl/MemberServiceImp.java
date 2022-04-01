package com.mvc.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mvc.commons.abs.AbstractService;
import com.mvc.commons.annotation.Aspect;
import com.mvc.commons.annotation.AutoWired;
import com.mvc.commons.annotation.Service;
import com.mvc.dao.ImemberDao;
import com.mvc.service.ImemberService;
import com.mvc.util.factory.ObjectFactory;
import com.mvc.vo.Member;

/**
 * 虽然所有的数据库连接关闭处理操作以及事务控制操作需要放在业务层
 * 
 * 如果说现在假设有一些权限的检测部分要进行处理，有两种做法
 * 	1.在业务层中进行权限的判断，如在AbstractService中进行权限的判断
 * 	2.利用切面的方式来完成
 * 
 * 切面是一种设计思想，它的核心概念在于：业务层只完成核心的功能，而所有辅助性的操作，全都交给切面来完成
 * 切面本质上就是一种代理设计模式的应用，只不过在后续所需要学习的Spring框架中，对于这种代理设计有了更好的实现
 * 才有了当前所谓的AOP编程技术。
 *	当前的模式中：每一个客户端的一个业务调用对应一个线程，调用一个业务对象
 *			每一个业务对象中又有一个Connection实例，由于获取Connection是通过ThreadLocal获取的，所以每一个线程当中
 *			可以实现自己的连接和数据库的事务管理，那么对于控制就可以考虑直接在一个代理类中完成
 *	 
 * */

@Service//标注为业务层
@Aspect//业务层需要开启事务
public class MemberServiceImp extends AbstractService implements ImemberService{
	//private ImemberDao mdao=ObjectFactory.getDaoInstance("member.dao", ImemberDao.class);
	//通过自动注入的方式获取接口实例
	@AutoWired
	private ImemberDao mdao;
	@Override
	public boolean Add(Member member) throws Exception {
		if(!super.checkAge(member.getAge())){
			//年龄不合法，返回false
			return false;
		}
		if(!super.checkSex(member.getSex())){
			return false;
		}
		if(this.mdao.findById(member.getMid())==null){
			//用户名不存在
			if(this.mdao.findByEmail(member.getEmail())==null){
				//邮箱不重复
				//进行用户的增加
				return this.mdao.doCreate(member);
			}
		}
		return false;
	}

	@Override
	public boolean Edit(Member member) throws Exception {
		if(!super.checkAge(member.getAge())){
			return false;
		}
		if(!super.checkSex(member.getSex())){
			return false;
		}
		Member emailmember=this.mdao.findByEmail(member.getEmail());
		if(emailmember!=null){
			if(!emailmember.getMid().equals(member.getMid())){
				//需要更新的邮箱已经存在
				return false;
			}
		}
		return this.mdao.doEdit(member);
	}

	@Override
	public boolean RemoveByid(String... ids) throws Exception {
		if(ids.length==0){
			return false;
		}
		//防止可能设置了重复的删除用户的信息，所以将数据存放在Set中
		Set<String> idset=new HashSet<>();
		idset.addAll(Arrays.asList(ids));
		return this.mdao.doRemove(idset);
	}

	@Override
	public Member get(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.mdao.findById(id);
	}

	@Override
	public List<Member> list() throws Exception {
		// TODO Auto-generated method stub
		return this.mdao.findAll();
	}

	@Override
	public Map<String, Object> split(int currentPage, int pagesize, String colum, String keyword) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<>();
		if(!super.checkEmpty(colum,keyword)){
			//colum或者keyword中有一个为空，不需要进行模糊查询
			map.put("allmember", this.mdao.findSplit(currentPage, pagesize));
			map.put("allrecords", this.mdao.getAllCount());
		}else{
			//colum和keyword都不为空，需要模糊查询
			map.put("allmember", this.mdao.findSplit(currentPage, pagesize, colum, keyword));
			map.put("allrecords", this.mdao.getAllCount(colum, keyword));
		}
		return map;
	}

}

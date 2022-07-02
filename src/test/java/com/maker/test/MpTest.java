package com.maker.test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maker.mapper.UserMapper;
import com.maker.domain.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:beans.xml")
public class MpTest {
	@Autowired
	private UserMapper mapper;
	@Test
	public void finallTest(){
		List<User> ulist=mapper.selectList(null);
		for(User u : ulist){
			System.out.println(u);
		}
		
	
		
}
	@Test
	public void insertTest(){
		User u=new User();
		u.setUsername("LeoMessi");
		u.setEmail("1@jay.com");
		u.setPassword("1234");
		u.setPhoneNum("86-18012345");
		int result=this.mapper.insert(u);
		System.out.println("result=>"+result);
		//id自增后，会回填到实体中，默认情况下，自动生成的id不是自增长的，所以会出现很大的数字
		//可以在实体类上，用@TableId来指定主键的类型，将其设为自增长
		System.out.println("id=>"+u.getId());
	}
	
	@Test
	public void updateByidTest(){
		User u=new User();
		u.setId(6l);//更新数据的条件
		u.setPhoneNum("86-6666");//需要更新的字段
		u.setPassword("666666");//需要更新的字段
		int result=this.mapper.updateById(u);
		System.out.println("result=>"+result);
	}
	
	@Test
	public void updateTest(){
		User u=new User();
		u.setPassword("88888");
		/*
		 * 此处的wrapper有两种
		 * 	QueryWrapper和UpdateWrapper两种
		 * 		QueryWrapper只能设置条件，而UpdateWrapper除了设置条件外，还可以设置要更新的字段
		 * 		即使用UpdateWrapper后，不需要用到实体类对象
		 * */
		QueryWrapper<User> wrapper=new QueryWrapper<User>();
		/*
		UpdateWrapper<User> uwrapper=new UpdateWrapper<>();
		uwrapper.set("password", "88888").eq("username", "xia");
		this.mapper.update(null, uwrapper);
		*/
		//设置条件表格属性username='xia'
		wrapper.eq("username", "xia");
		
		int result=this.mapper.update(u, wrapper);
		System.out.println("result=>"+result);
	}
	@Test
	public void deleteTest(){
		User u=new User();
		u.setUsername("jay");//设置删除条件
		//int result=this.mapper.deleteById(9l);//按id删除
		//System.out.println("result=>"+result);
		//Map<String,Object> columnMap=new HashMap<>();
		//columnMap.put("username", "jay");
		//如果有多个条件，就再添加一个键值对
		//this.mapper.deleteByMap(columnMap);//按map删除
		
		//通用删除，传递一个和数据库表对应的实体类对象
		QueryWrapper<User> queryWrapper=new QueryWrapper<>(u);
		
		this.mapper.delete(queryWrapper);
	}
	
	@Test
	public void selectTest(){
		User u=this.mapper.selectById(1L);
		System.out.println(u);
		
		List<Long> ids=Arrays.asList(1L,2L,3L,10L);
		//等价于 Select * from sys_user where id in (1,2,3,10)
		List<User> ulist=this.mapper.selectBatchIds(ids);
		System.out.println("【批量查询】"+ulist);
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("username", "zhangsan");
		//如果查询结果有多条，那么会报错
		User u2=this.mapper.selectOne(queryWrapper);
		System.out.println("【SelectOne查询】"+u2);
		QueryWrapper<User> wrapper2=new QueryWrapper<>();
		wrapper2.gt("id", 2);
		//等于select count(*) from sys_user
		Long count=this.mapper.selectCount(wrapper2);
		System.out.println("【查询条数】"+count);
		
		//根据条件查询多条数据
		List<User> ulist2=this.mapper.selectList(wrapper2);
		System.out.println("【根据条件查询多条数据】"+ulist2);
		
		//分页查询
		Page<User> page=new Page<>(1,3);
		Page<User> userPage=this.mapper.selectPage(page, null);
		System.out.println("【总页数】"+userPage.getPages());
		System.out.println("【当前页】"+userPage.getCurrent());
		List<User> usplit=userPage.getRecords();
		System.out.println("【分页查询结果】"+usplit);
		
		
	}
	/**
	 * 对QueryWrapper中的allEq方法进行测试
	 * */
	@Test
	public void allEqTest(){
		QueryWrapper<User> wrapper=new QueryWrapper<>();
		Map<String,Object> param=new HashMap<>();
		param.put("username", "xia");
		param.put("password", null);
		//wrapper.allEq(param);//查询不到结果
		//boolean null2IsNull=false;
		//wrapper.allEq(param, null2IsNull);//可以查到xia这个用户，第二参数表示是否将null值作为查询的条件，false就是不作为查询条件
		wrapper.allEq((k,v)->{return k.equals("username");}, param);//第一个参数为过滤器，是一个比较函数式接口，表示key等于username的项才作为条件进行查询
		List<User> ulist=this.mapper.selectList(wrapper);
		System.out.println(ulist);
	}
	@Test
	public void TestOr(){
		QueryWrapper<User> wrapper=new QueryWrapper<>();
		//Select * from sys_user where username='xia' or password='123'
		wrapper.eq("username", "xia").or().eq("password", "123").select("username","phoneNum");
		List<User> ulist=this.mapper.selectList(wrapper);
		System.out.println(ulist);
		
	}
	
	@Test
	public void TestSelect(){
		QueryWrapper<User> wrapper=new QueryWrapper<>();
		//Select username,phoneNum from sys_user where username='xia' or password='123'
		wrapper.eq("username", "xia").or().eq("password", "123").select("username","phoneNum").select("username","phoneNum");
		List<User> ulist=this.mapper.selectList(wrapper);
		System.out.println(ulist);
		
	}
	/**
	 * 使用AR的时候，不需要进行Mapper的注入，但是相关的Mapper类还是必须要存在
	 * 因为AR底层在实现的时候，实际上还是通过Mapper来进行了相关的数据库操作
	 * */
	@Test
	public void TestAR(){
		User u=new User();
		u.setId(2L);
		User result=u.selectById();
		System.out.println(result);
	}
}

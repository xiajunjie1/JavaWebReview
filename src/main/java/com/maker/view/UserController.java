package com.maker.view;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.maker.bean.User;
import com.maker.bean.Vo;
import com.maker.service.UserService;
//@Component("usercontroller")
//可以用以下注解替代，功能一样，不过一般用于web层
@Controller("usercontroller")
@RequestMapping("/user/")
public class UserController {
	@Autowired
	@Qualifier("uservice")
	private UserService uservice;
	@Value("xia")
	private String name;
	
	public void add(){
		try {
			uservice.UserSave();
			System.out.println("【name的值为：】"+name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("quick")
	public String save(){
		System.out.println("Controller save running...");
		return "saveSuc";
	}
	
	@RequestMapping("list")
	public ModelAndView list(){
		/*
		 * ModelAndView中，Model代表数据
		 * View代表显示的视图
		 * 设置Model它会将数据存到request属性中去
		 * 设置view，它会跳转到相应的视图页面
		 * */
		ModelAndView mav=new ModelAndView();
		mav.addObject("username", "xia");
		mav.setViewName("show");
		return mav;
	}
	
	/**
	 * 此处方法中的形参，会由SpringMVC来进行传入
	 * */
	@RequestMapping("get")
	public String getReq(HttpServletRequest req){
		String path=req.getParameter("url");
		return path;
	}
	
	/**
	 * 利用这种方式，可以通过一个方法返回JSON字符串
	 * */
	@RequestMapping("resp")
	@ResponseBody//该注解告诉SpringMVC，此方法不进行跳转，而是直接回写
	public String RespWriter(){
		return "Hello World";
	}
	@RequestMapping("getuser")
	@ResponseBody
	public User getUser(){
		User u=new User();
		u.setUsername("xia");
		u.setAddr("wuhan");
		u.setAge(25);
		return u;
	}
	@RequestMapping("getparam")
	@ResponseBody
	public void getparam(String username,int age,User user,String[] hobby){
		System.out.println(username);
		System.out.println(age);
		System.out.println(user);
		System.out.println(Arrays.toString(hobby));
	}
	@RequestMapping("getlistparam")
	@ResponseBody
	public void getListParam(Vo vo){
		System.out.println(vo);
	}
	
	@RequestMapping("getinfo")
	@ResponseBody
	public void getInfo(@RequestBody List<User> user){
		System.out.println(user);
	}
	
	/**
	 * 测试@RequestParam注解
	 * 通过该注解显示绑定传入参数的名字
	 * 使用该注解绑定参数名后，传入的参数名必须和value保持一致，才能将参数注入到形参当中，此时就算参数名和形参名相同，也会被视为没有指定请求参数
	 * */
	@RequestMapping("ptest")
	@ResponseBody
	public void paramTest(@RequestParam(value="name",required=false,defaultValue="xiajunjie")String username){
	System.out.println(username);	
	}
	
	
	@RequestMapping("getServletApi")
	@ResponseBody
	public void getServletApi(HttpServletRequest req,HttpServletResponse resp,HttpSession session){
		System.out.println(req);
		System.out.println(resp);
		System.out.println(session);
	}
	
	@RequestMapping("getHeader")
	@ResponseBody
	public void getHeader(@RequestHeader(value="User-Agent",required=false) String user_agent,@CookieValue(value="JSESSIONID") String Jsessionid){
		System.out.println(user_agent);
		System.out.println(Jsessionid);
	}
	
	@RequestMapping("fileupload")
	@ResponseBody
	public void getFile(HttpServletRequest req,String name,MultipartFile photo){
		String filename=name+photo.getOriginalFilename();
		String path=req.getServletContext().getRealPath("/upload/");
		System.out.println(filename);
		System.out.println(path);
		try {
			photo.transferTo(new File(path+filename));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

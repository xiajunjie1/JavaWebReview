package com.maker.UserManger.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.maker.UserManger.domain.Role;
import com.maker.UserManger.domain.User;
import com.maker.UserManger.service.RoleService;
import com.maker.UserManger.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	@Qualifier("user_service")
	private UserService uservice;
	@Autowired
	@Qualifier("role_service")
	private RoleService rservice;
	
	@RequestMapping("listuser")
	public ModelAndView listuser(){
		ModelAndView mav=new ModelAndView();
		List<User> ulist=uservice.listuser();
		mav.addObject("userlist",ulist);
		mav.setViewName("pages/userlist");
		return mav;
	}
	@RequestMapping("SaveUi")
	public ModelAndView saveUi(){
		ModelAndView mav=new ModelAndView();
		List<Role> rlist=rservice.listAll();
		mav.setViewName("pages/saveUser");
		mav.addObject("rlist", rlist);
		return mav;
	}
	
	@RequestMapping("saveuser")
	public String saveuser(User user,Integer[] rids){
		boolean result=uservice.saveUser(user, rids);
		if(result){
			return "redirect:listuser";
		}else{
			return "pages/uerr";
		}
	}
	@RequestMapping("userdel/{id}")
	public String deluser(@PathVariable Long id){
		if(this.uservice.delUser(id)){
			return "redirect:/user/listuser";
		}
		return "/pages/uerr";
	}
	/**
	 * 登录处理方法
	 * */
	@RequestMapping("login")
	public ModelAndView Login(String username,String password,HttpSession session){
		ModelAndView mav=new ModelAndView();
		if(username==null||"".equals(username)){
			mav.setViewName("pages/login");
			mav.addObject("errmsg","用户名不合规");
		}else{
			User user=this.uservice.Login(username, password);
			if(user==null){
				mav.setViewName("/pages/login");
				mav.addObject("errmsg","用户名或密码错误");
			}
			else{
				session.setAttribute("user", user);
				mav.setViewName("redirect:/pages/index");
			}
		}
		return mav;
	}
}
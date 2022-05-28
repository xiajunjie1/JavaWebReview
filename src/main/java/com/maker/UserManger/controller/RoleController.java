package com.maker.UserManger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.maker.UserManger.domain.Role;
import com.maker.UserManger.service.RoleService;

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	@Qualifier("role_service")
	private RoleService rservice;
	@RequestMapping("rolelist")
	public ModelAndView rolelist(){
		ModelAndView mav=new ModelAndView();
		List<Role> rlist=rservice.listAll();
		mav.addObject("rlist",rlist);
		mav.setViewName("pages/rolelist");
		return mav;
	}
	@RequestMapping("roleadd")
	public String roleadd(Role role){
		boolean result=this.rservice.addRole(role);
		if(result){
			return "redirect:rolelist";//重定向到另一个视图，不经过视图解析器，相对地址
		}else{
			return "pages/rolerr";
		}

		
	}
	

	
	public RoleService getRservice() {
		return rservice;
	}
	public void setRservice(RoleService rservice) {
		this.rservice = rservice;
	}
}

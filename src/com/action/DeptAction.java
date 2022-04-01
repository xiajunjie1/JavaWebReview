package com.action;

import com.mvc.commons.annotation.Controller;
import com.mvc.commons.annotation.RequestMapping;
import com.mvc.view.ModeAndView;

@Controller
@RequestMapping("/dept/")
public class DeptAction {
	@RequestMapping("add")
	public String add(){
		System.out.println("新增成功！");
		return "add.jsp";
	}
	@RequestMapping("edit")
	public ModeAndView edit(){
		ModeAndView mav=new ModeAndView("edit.jsp","msg","test");
		System.out.println("修改信息！");
		return mav;
	}
	
	@RequestMapping("remove")
	public void remove(){
		System.out.println("删除信息");
	}
}

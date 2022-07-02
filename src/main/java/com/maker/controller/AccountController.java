package com.maker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.maker.domain.Account;
import com.maker.service.AccountService;

@Controller
@RequestMapping("/account/")
public class AccountController {
@Autowired
@Qualifier("aservice2")//加载AccountServiceImp2
private AccountService acservice;
//设置response的字符编码
@RequestMapping(value="save",produces="text/html;charset=UTF-8")
@ResponseBody
public String save(Account ac){
	if(ac==null||ac.getName()==null||"".equals(ac.getName()))return"账户信息输入出错";
	try {
		acservice.save(ac);
		return "添加成功";
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return "出现错误，添加失败";
	}
}

@RequestMapping("list")
public ModelAndView list(){
	ModelAndView mav=new ModelAndView();
	try {
		List<Account> alist=acservice.listall();
		mav.addObject("alist", alist);
		mav.setViewName("show");
		return mav;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		mav.setViewName("err");
		return mav;
	}
}
@RequestMapping("saveUi")
public String saveUi(){
	return "Input";
}
}

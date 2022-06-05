package com.maker.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maker.service.Eservice;

@Controller
@RequestMapping("/exception/")
public class ExceptionController {
	@Autowired
	@Qualifier("Eservice")
	private Eservice eservice;
	@RequestMapping("show1")
	@ResponseBody
	public void show1()throws Exception{
		eservice.CommonsException();
	}
	@RequestMapping("show2")
	@ResponseBody
	public void show2()throws Exception{
		eservice.NpointerException();
	}
	@RequestMapping("show3")
	@ResponseBody
	public void show3()throws Exception{
		eservice.NumFormatException();
	}
	@RequestMapping("show4")
	@ResponseBody
	public void show4()throws Exception{
		eservice.MyException();
	}
}

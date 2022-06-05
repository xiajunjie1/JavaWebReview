package com.maker.UserManger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages/")
public class PagesController {
	@RequestMapping("index")
	public String index(){
		return "pages/main";
	}
}

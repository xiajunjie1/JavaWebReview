package com.maker.aopanno;

import org.springframework.stereotype.Component;

@Component("mytarget")
public class TestServiceImp implements TestService {

	@Override
	public void show() {
		System.out.println("执行【show】方法...");

	}

}

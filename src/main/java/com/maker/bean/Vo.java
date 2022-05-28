package com.maker.bean;

import java.util.List;

public class Vo {
	private List<User> user;


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return user.toString();
	}


	public List<User> getUser() {
		return user;
	}


	public void setUser(List<User> user) {
		this.user = user;
	}
	
	
}

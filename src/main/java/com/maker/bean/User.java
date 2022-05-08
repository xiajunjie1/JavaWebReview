package com.maker.bean;

public class User {
	private String username;
	private int age;
	private String addr;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (this.username==null?"/":this.username)+"-"+this.age+"-"+(this.addr==null?"/":this.addr);
	}
	
}

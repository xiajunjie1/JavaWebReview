package com.maker.domain;

import java.util.List;



public class SysUser {
	private Long id;
	private String username;
	private String email;
	private String password;
	private String phoneNum;

	private List<Role> role;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public List<Role> getRole() {
		return role;
	}
	public void setRole(List<Role> role) {
		this.role = role;
	}
	
	@Override
	public String toString(){
		return this.id+"-"+this.username+"-"+this.email+"-"+this.phoneNum+"-"+this.role;
	}
}

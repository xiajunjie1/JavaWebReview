package com.maker.UserManger.domain;

import java.util.List;

/**
 * User pojo类
 * 	id属性用包装基本类型的原因
 * 		数据库中，若没有数据，则默认置为null值，如果将null赋值给基本类型程序会报错，所以使用包装类型
 * */
public class User {
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
}

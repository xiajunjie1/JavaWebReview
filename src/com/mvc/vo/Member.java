package com.mvc.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 简单JAVA类，与实体表相对应,实现序列化接口，允许序列化
 * 简单java类在整个java开发过程中，一定存在反射机制良好的支持，利用反射机制可以有效实现
 * 数据操作的进一步抽象化处理，达到良好的可重用性设计
 *
 * 	
 * */
public class Member implements Serializable {
	private String mid;
	private String name;
	private Integer age;//数据库表中存在空的概念，而int无法描述空的概念，Integer可以描述空的概念
	private String email;
	private String sex;
	private Date birthday;
	private String note;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.mid+"-"+this.name+"-"+this.email+"-"+this.age+"-"+this.sex+"-"+String.valueOf(this.birthday)+"-"+this.note;
	}
}

package com.maker.domain;

import java.util.Date;
/**
 * 订单和用户表，从订单表的角度来看，它和用户表属于一对一的关系
 * */
public class Order {
	private Long oid;
	private Date ordertime;
	private Double total;
	//在数据库里，是通过外键产生联系的，但是在Java中，是通过对象来接收相应的数据的
	private User user;
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.oid+"-"+this.total+"-"+this.ordertime+"-"+(this.user!=null?this.user.toString():"");
	}
}

package com.maker.bean;

public class Account {
	private String name;
	private double money;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+"-"+this.money;
	}
}

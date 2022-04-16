package com.mvc.vo;

public class Dept {
	private Long deptno;
	private String deptname;
	private String loc;
	public Long getDeptno() {
		return deptno;
	}
	public void setDeptno(Long deptno) {
		this.deptno = deptno;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.deptno+"-"+this.deptname+"-"+this.loc;
	}
	
}

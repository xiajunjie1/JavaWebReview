package com.maker.dao;

import java.util.List;

import com.maker.domain.SysUser;

public interface SysUserMapper {
	public List<SysUser> findAll()throws Exception;
}

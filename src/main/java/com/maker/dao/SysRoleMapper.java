package com.maker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.maker.domain.Role;

public interface SysRoleMapper {
	@Select("Select * from sys_user_role ur,sys_role r where ur.userId=#{id} and ur.roleId=r.id")
	public List<Role> getRolesByUid(Long uid)throws Exception;
}

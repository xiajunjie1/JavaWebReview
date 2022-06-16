package com.maker.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.maker.domain.Role;
import com.maker.domain.SysUser;

public interface SysUserMapperAnno {
	@Select("Select * from sys_user")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="username",property="username"),
		@Result(column="email",property="email"),
		@Result(column="password",property="password"),
		@Result(column="phoneNum",property="phoneNum"),
		@Result(
				property="role",
				column="id",
				javaType=List.class,
				many=@Many(select="com.maker.dao.SysRoleMapper.getRolesByUid")
		)	
		
	})
	public List<SysUser> findAll()throws Exception;
}

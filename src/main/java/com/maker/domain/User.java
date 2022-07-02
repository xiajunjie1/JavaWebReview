package com.maker.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//生成getter和setter,重写toString等方法
@AllArgsConstructor//生成全参构造
@NoArgsConstructor//生成无参构造
@TableName("sys_user")//指定表名，由于mybatis-plus并不需要写相应的SQL语句即可完成查询，所以可以在此处指定实体类的表名
public class User extends Model<User>{
	@TableId(type=IdType.AUTO)//设置id为自增长
	private Long id;
	private String username;
	private String email;
	private String password;
	
	/*
	 *@TableField注解解决两类问题 
	 * 1.当对象属性名称和数据库表字段名称不一样时(非驼峰)
	 * 2.对象属性字段在表中不存在时，这时候需要用到@TableField(exist=false)
	 * @TableField(select=false)表示查询时不返回该字段的值
	 * */
	@TableField("phoneNum")
	private String phoneNum;

}

package com.maker.UserManger.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.maker.UserManger.dao.UserDao;
import com.maker.UserManger.domain.User;

public class UserDaoImp implements UserDao {
	private JdbcTemplate jtemplate;
	@Override
	public boolean doCreate(User vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doDelete(Long id) throws Exception {
		// TODO Auto-generated method stub
		int row=this.jtemplate.update("delete from sys_user where id=?",id);
		return row>0;
	}

	@Override
	public List<User> doQueryAll() throws Exception {
		// TODO Auto-generated method stub
		List<User> ulist=this.jtemplate.query("Select * from sys_user", new BeanPropertyRowMapper<User>(User.class));
		return ulist;
	}

	@Override
	public User doQueryById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findSplit(Integer curpage, Integer pagesize) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findSplit(Integer curpage, Integer pagesize, String colum, String keyword) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getrole(Long uid) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> ids=this.jtemplate.query("select roleId from sys_user_role where userId=?",new SingleColumnRowMapper<Integer>(Integer.class),uid);
		return ids;
	}

	public JdbcTemplate getJtemplate() {
		return jtemplate;
	}

	public void setJtemplate(JdbcTemplate jtemplate) {
		this.jtemplate = jtemplate;
	}
	/**
	 * 新增一个User，并且返回当前新增User自动生成的id值
	 * */
	@Override
	public Long doCreateGetId(User user) throws Exception {
		// TODO Auto-generated method stub

		/**
		 * {
			
		new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		 * */
		
		GeneratedKeyHolder generatedKeyHolder=new GeneratedKeyHolder();
		this.jtemplate.update((Connection con)->{
			//插入后返回插入项的ID值
			PreparedStatement pstmt=con.prepareStatement("insert into sys_user values(?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setObject(1, null);
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getPhoneNum());
			
			return pstmt;
		}, generatedKeyHolder);
		//返回的Id值保存在了GeneratedKeyHolder里面
		return generatedKeyHolder.getKey().longValue();
	}

	@Override
	public int doCreateuser_role(Long uid, Integer rid) throws Exception {
		// TODO Auto-generated method stub
		int row=this.jtemplate.update("insert into sys_user_role values(?,?)", uid,rid);
		return row;
	}

	@Override
	public User findByUnameAndPwd(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		String sql="Select * from sys_user where username=? and password=?";
		User user=this.jtemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username,password);
		return user;
	}

}

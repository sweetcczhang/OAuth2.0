package com.zcc.oauth2.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.zcc.oauth2.entity.UserBean;

@Repository
public class UserBeanDaoImpl implements UserBeanDao{
	
	  @Autowired
	 private JdbcTemplate jdbcTemplate;
	  
	  
	 public UserBean createUser(final UserBean user)
	     {
		  final  String sql="insert into oauth2_user(username,password,sex,nick_name,address,uid) values(?,?,?,?,?,?)";
		    
		    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		    jdbcTemplate.update(new PreparedStatementCreator(){
		    	
		    	 public PreparedStatement createPreparedStatement(Connection connection)throws SQLException{
		    		 PreparedStatement ppst=connection.prepareStatement(sql, new String[]{"id"});
		    		 int count=1;
		    		 ppst.setString(count++, user.getUsername());
		    		 ppst.setString(count++, user.getPassword());
		    		 ppst.setString(count++, user.getSex());
		    		 ppst.setString(count++, user.getNickName());
		    		 ppst.setString(count++, user.getAddress());
		    		 ppst.setString(count++, user.getUid());
		    		 
		    		 return ppst;
		    		 
		    	 }
		    
		    }, keyHolder);
		 
		  
		    user.setId(keyHolder.getKey().longValue());
		   return user;
	     }
	 public UserBean updateUser( UserBean user){
		 String sql="update oauth2_user set username=?,password=?,sex=?,nick_name=?,address=?,uid=? where id=?";
		 
		 jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getSex(),user.getNickName(),
				 
				 user.getAddress(), user.getUid(),user.getId());
		 return user;
	 }

	 public void deleteUser(Long id){
	   String sql="delete form oauth2_user where id=?";
	     jdbcTemplate.update(sql,id);
	 }
	 
	 public UserBean findOne(Long id){
		String sql="select id,username,password,sex,nick_name,address,uid from oauth2_user where id=?";
	   List<UserBean> userlist=jdbcTemplate.query(sql,new BeanPropertyRowMapper<UserBean>(UserBean.class),id);
		
	   if(userlist.size()==0)
	   {
		   return null;
	   }
	   
	   return userlist.get(0);
	 }
	 
	 public List<UserBean> findAll(){
	   String sql="select id,username,password,sex,nick_name,address,uid from oauth2_user";
	  List<UserBean> userList= jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class));
		 if(userList.size()==0){
			 return null;
		 }
		 
		 return userList;
	 }
	 public UserBean findByUsername(String username){
		 String sql="select id,username,password,sex,nick_name,address,uid from oauth2_user where username=?";
		List<UserBean> userList= jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class),username);
		 if(userList.size()==0)
		 {
			 return null;
		 }
		 return userList.get(0);
	 }
	 
	 public UserBean login(String username,String password){
		 String sql="select id,username,password,sex,nick_name,address,uid from oauth2_user where username=? and password=?";
		
		 List<UserBean> userList= jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class),username,password);
		 if(userList.size()==0)
		 {
			 return null;
		 }
		 return userList.get(0);
	 }
	@Override
	public UserBean findByUid(String uid) {
		// TODO Auto-generated method stub
		String sql="select id, username, password, sex, nick_name, address, uid from oauth2_user where uid=?";
		List<UserBean> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class),uid);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}
	 
}


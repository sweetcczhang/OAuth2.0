package com.zcc.oauth2.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.zcc.oauth2.entity.UserAuthorizeBean;

@Repository
public class UserAuthorizeBeanDaoImpl implements UserAuthorizeBeanDao{
   @Autowired
   private	JdbcTemplate jdbcTemplate;
	public UserAuthorizeBean creatUserAuthorizeBean(UserAuthorizeBean userAuthorizeBean){
		String sql="insert into oauth2_userAuthorize(appkey,user_id,client_name, create_time,description) values(?,?,?,?,?)";
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		 
		 jdbcTemplate.update(new PreparedStatementCreator(){
			 public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
	                int count = 1;
	                psst.setString(count++,userAuthorizeBean.getAppkey());
	                psst.setLong(count++,userAuthorizeBean.getUser_id());
	                psst.setString(count++, userAuthorizeBean.getClientName());
	                psst.setString(count++,userAuthorizeBean.getCreate_time());
	                psst.setString(count, userAuthorizeBean.getDescription());
	                return psst;
			 }	 
		 },keyHolder);
		 
		 userAuthorizeBean.setId(keyHolder.getKey().longValue());
		
		 //jdbcTemplate.update(sql,userAuthorizeBean.getAppkey(),userAuthorizeBean.getUser_id(),userAuthorizeBean.getClientName(),userAuthorizeBean.getCreate_time());
		
		 return userAuthorizeBean;
	}
	public UserAuthorizeBean updateUserAuthorizeBean(UserAuthorizeBean userAuthorizeBean){
		String sql="update oauth2_userAuthorize set appkey=?,user_id,client_name=?,create_time where ";
		
		return null;
	}
	public void deleteUserAuthorizeBean(String appkey,Long user_id){
		   String sql="delete from oauth2_userAuthorize where appkey=? and user_id=?";
		   jdbcTemplate.update(sql,appkey,user_id);
		
	}
	public List<UserAuthorizeBean> findAll(Long user_id){
		   
	   String sql="select id,appkey,user_id,client_name,create_time,description from oauth2_userAuthorize where user_id=?";
       List<UserAuthorizeBean>  userList=  jdbcTemplate
        		               .query(sql, new BeanPropertyRowMapper<UserAuthorizeBean>(UserAuthorizeBean.class),user_id);
		 if(userList.size()==0){
			 return null;
		 }
		
		return userList;
	}
	public UserAuthorizeBean findOne(String appkey,Long user_id){
		System.out.println("是否执行到这了，fuck，怎么回事啊");
		String sql="select appkey,user_id,client_name,create_time,description from oauth2_userAuthorize where appkey=? and user_id=?";
		List<UserAuthorizeBean> userList=jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserAuthorizeBean>(UserAuthorizeBean.class),appkey,user_id);
		System.out.println("是否执行到这了，fuck，怎么回事啊11");
		if(userList.size()==0){
			return null;
		}
		return userList.get(0);
	}
	
	@Override
	public void deleteUserAuthorizeBean(Long id) {
		// TODO Auto-generated method stub
		   String sql="delete from oauth2_userAuthorize where id=?";
		   jdbcTemplate.update(sql,id);
		
	}
}

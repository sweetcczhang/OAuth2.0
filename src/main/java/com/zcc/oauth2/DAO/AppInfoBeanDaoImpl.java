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


import com.zcc.oauth2.entity.AppInfoBean;

@Repository
public class AppInfoBeanDaoImpl implements AppInfoBeanDao{
    
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public AppInfoBean createAppInfoBean(AppInfoBean appInfoBean) {
		// TODO Auto-generated method stub
		String sql="insert into oauth2_app(user_id, app_name, description, app_url, redirect_url,"
				+ " app_id, app_secret, create_time) values(?,?,?,?,?,?,?,?)"; 
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){
			
			public PreparedStatement createPreparedStatement(Connection con)throws SQLException{
				
				PreparedStatement psst = con.prepareStatement(sql, new String[]{"id"});
				int count=1;
				psst.setLong(count++, appInfoBean.getUser_id());
				psst.setString(count++, appInfoBean.getAppName());
				psst.setString(count++, appInfoBean.getDescription());
				psst.setString(count++, appInfoBean.getAppUrl());
				psst.setString(count++, appInfoBean.getRedirectUrl());
				psst.setString(count++, appInfoBean.getAppId());
				psst.setString(count++, appInfoBean.getAppSecret());
                psst.setString(count++, appInfoBean.getCreateTime());				
				
				
				return psst;
				
			}
			  
			
		},keyHolder);
		
		appInfoBean.setId(keyHolder.getKey().longValue());
		
		return appInfoBean;
	}

	@Override
	public AppInfoBean updateAppInfoBean(AppInfoBean appInfoBean) {
		// TODO Auto-generated method stub
		String sql="update oauth2_app set user_id=?,app_name=?, description=?, app_url=?, redirect_url=?"
				+ ", app_id=?, app_secret=?, create_time=? where id=?";
		jdbcTemplate.update(sql,appInfoBean.getUser_id(),appInfoBean.getAppName(),appInfoBean.getDescription()
				,appInfoBean.getAppUrl(),appInfoBean.getRedirectUrl(),appInfoBean.getAppId(),appInfoBean.getAppSecret()
				,appInfoBean.getCreateTime(),appInfoBean.getId());
		return appInfoBean;
	}

	@Override
	public AppInfoBean findByAppId(String appId) {
		// TODO Auto-generated method stub
		String sql="select id, user_id, app_name, description, app_url, redirect_url,"
				+ " app_id, app_secret, create_time from oauth2_app where app_id=?"; 
		List<AppInfoBean> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<AppInfoBean>(AppInfoBean.class),appId);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
		
	}

	@Override
	public AppInfoBean findBySecret(String appSecret) {
		// TODO Auto-generated method stub
		String sql="select id, user_id, app_name, description, app_url, redirect_url,"
				+ " app_id, app_secret, create_time from oauth2_app where app_secret=?"; 
		List<AppInfoBean> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<AppInfoBean>(AppInfoBean.class),appSecret);
		if(list.size()==0){
		return null;
		}
		return list.get(0);
	}

	@Override
	public AppInfoBean findById(Long id) {
		// TODO Auto-generated method stub
		String sql="select id, user_id, app_name, description, app_url, redirect_url,"
				+ " app_id, app_secret, create_time from oauth2_app where id=?"; 
		List<AppInfoBean> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<AppInfoBean>(AppInfoBean.class),id);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<AppInfoBean> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		String sql="select id, user_id, app_name, description, app_url, redirect_url,"
				+ " app_id, app_secret, create_time from oauth2_app where user_id=?"; 
		List<AppInfoBean> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<AppInfoBean>(AppInfoBean.class),userId);
		if(list.size()==0){
			return null;
		}
		return list;
	}

	@Override
	public void deleteAppInfoBean(Long id) {
		// TODO Auto-generated method stub
		String sql="delete from oauth2_app where id=?";
		jdbcTemplate.update(sql, id);
		
	}

	@Override
	public List<AppInfoBean> findAll() {
		// TODO Auto-generated method stub
		String sql="select id, user_id, app_name, description, app_url, redirect_url,"
				+ " app_id, app_secret, create_time from oauth2_app"; 
		List<AppInfoBean> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<AppInfoBean>(AppInfoBean.class));
		if(list.size()==0){
			return null;
		}
		return list;
	}

}

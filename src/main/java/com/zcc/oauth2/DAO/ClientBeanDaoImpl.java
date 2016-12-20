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
import com.zcc.oauth2.entity.ClientBean;

@Repository
public class ClientBeanDaoImpl implements ClientBeanDao{
	
	    @Autowired
	    private JdbcTemplate jdbcTemplate;
	 
	    @Override
	    public ClientBean createClient(final ClientBean client) {//
	        final String sql = "insert into oauth2_client(user_id, client_name, description, web_url, redirect_url, client_id,"
	        		+ " client_secret, create_time) values(?,?,?,?,?,?,?,?)";
       
	        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	        jdbcTemplate.update(new PreparedStatementCreator() {
	        	// @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
	                int count = 1;
	                psst.setLong(count++, client.getUser_id());
	                psst.setString(count++, client.getClientName());
	                psst.setString(count++, client.getDescription());
	                psst.setString(count++, client.getWebUrl());
	                psst.setString(count++, client.getRedirectUrl());
	                psst.setString(count++, client.getClientId());
	                psst.setString(count++, client.getClientSecret());
	                psst.setString(count++, client.getCreateTime());
	                return psst;
	            }
	        }, keyHolder);

	        client.setId(keyHolder.getKey().longValue());
	        return client;
	    }
	   
	  @Override
	  public ClientBean  updateClient(ClientBean client){//
		  String sql="update oauth2_client set user_id=?,client_name=?,description=?,web_url=?,redirect_url=?,"
		  		+ " client_id=?,client_secret=?,create_time=? where id=?";
		  jdbcTemplate.update(sql,client.getUser_id(), client.getClientName(),client.getDescription(),client.getWebUrl(),
				   client.getRedirectUrl(), client.getClientId(),client.getClientSecret(),client.getCreateTime(), client.getId());
		  return client;
	  }
	  
	  @Override   
	  public void deleteClient(Long ClientId){//
		   String sql="delete from oauth2_client where id=?" ;
		   jdbcTemplate.update(sql,ClientId);
	  }
	  
	  @Override
	  public  ClientBean findOne(Long clientId){//
	     String sql="select id, user_id, client_name, description, web_url, redirect_url,"
	     		+ " client_id, client_secret, create_time from oauth2_client where id=?";
	    
	     List<ClientBean>clientList= jdbcTemplate.query(sql,
	    		new BeanPropertyRowMapper<ClientBean>(ClientBean.class),clientId);
	   
	    if(clientList.size() == 0) {
            return null;
        }
	    System.out.println("WebUrl:"+clientList.get(0).getWebUrl());
	    System.out.println("redirectUrl:"+clientList.get(0).getRedirectUrl());
        return clientList.get(0);
	  }
	  @Override
	  public   List<ClientBean> findAll(){//
		  String sql="select id, user_id, client_name, description, web_url, redirect_url,"
		     		+ " client_id, client_secret, create_time from oauth2_client";
		
		  List<ClientBean> clientList=jdbcTemplate.query(sql,
				  new BeanPropertyRowMapper<ClientBean>(ClientBean.class));
		
		  if(clientList.size()==0){
			  return null;
		  }
		  return clientList;
	  }
	  @Override
	  public  ClientBean findByClientId(String clientId){//
		  String sql="select id, user_id, client_name, description, web_url, redirect_url,"
		     		+ " client_id, client_secret, create_time from oauth2_client where client_id=?";
		 
		  List<ClientBean>clientList=jdbcTemplate.query(sql
				  ,new BeanPropertyRowMapper<ClientBean>(ClientBean.class),clientId);
		  if(clientList.size()==0){
			  return null;
		  }
		  return clientList.get(0);
	  }
	  @Override
	  public  ClientBean findByClientSecret(String clientSecret){//
		  
		  String sql="select id, user_id, client_name, description, web_url, redirect_url,"
		     		+ " client_id, client_secret, create_time from oauth2_client where client_secret=?";
		  
		  List<ClientBean> clientList=jdbcTemplate.query(sql,
				  new BeanPropertyRowMapper<ClientBean>(ClientBean.class), clientSecret);
		  
		  if(clientList.size()==0){
			  return null;
		  }
		  return clientList.get(0);
		  
	  }

	@Override
	public List<ClientBean> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		 String sql="select id, user_id, client_name, description, web_url, redirect_url,"
		     		+ " client_id, client_secret, create_time from oauth2_client where user_id=?";
		  
		  List<ClientBean> clientList=jdbcTemplate.query(sql,
				  new BeanPropertyRowMapper<ClientBean>(ClientBean.class), userId);
		  
		  if(clientList.size()==0){
			  return null;
		  }
		  return clientList;
		  
	}

	@Override
	public List<ClientBean> findByRedirectUri(String url) {
		// TODO Auto-generated method stub
		 String sql="select id, user_id, client_name, description, web_url, redirect_url,"
		     		+ " client_id, client_secret, create_time from oauth2_client where redirect_url=?";
		  
		  List<ClientBean> clientList=jdbcTemplate.query(sql,
				  new BeanPropertyRowMapper<ClientBean>(ClientBean.class), url);
		  if(clientList.size()==0){
			  return null;
		  }
		return clientList;
	}
}

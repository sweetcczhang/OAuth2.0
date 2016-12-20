package com.zcc.oauth2.service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcc.oauth2.DAO.ClientBeanDao;
import com.zcc.oauth2.entity.ClientBean;



@Transactional
@Service
public class ClientServiceImpl implements ClientService{
	
	   @Autowired
	  private ClientBeanDao clientDao;
	 
	   public ClientBean createClient(ClientBean client)
	        {
		      client.setClientId(UUID.randomUUID().toString());
	          client.setClientSecret(UUID.randomUUID().toString());
	          Date date=new Date();
			  SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			  client.setCreateTime(sf.format(date));
	          return clientDao.createClient(client);
	       }
	 
	    public ClientBean updateClient(ClientBean client){
	    	
	        	return clientDao.updateClient(client);
	       }
	    public void deleteClient(Long clientId){
	    	
	    	clientDao.deleteClient(clientId);
	    }

	 public   ClientBean findOne(Long clientId){
	    	
		 return clientDao.findOne(clientId);
	    }

	public  List<ClientBean> findAll(){
		
		return clientDao.findAll();
		
	}

	public    ClientBean findByClientId(String clientId){
		
		   return clientDao.findByClientId(clientId);
	}
	public    ClientBean findByClientSecret(String clientSecret){
		
		return  clientDao.findByClientSecret(clientSecret);
		
	}

	@Override
	public List<ClientBean> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return clientDao.findByUserId(userId);
	}

	@Override
	public List<ClientBean> findByRedirect(String url) {
		// TODO Auto-generated method stub
		return  clientDao.findByRedirectUri(url);
	}

}

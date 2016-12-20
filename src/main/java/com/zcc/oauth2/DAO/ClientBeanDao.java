package com.zcc.oauth2.DAO;

import java.util.List;

import com.zcc.oauth2.entity.ClientBean;


public interface ClientBeanDao {

	    public ClientBean createClient(ClientBean client);
	    
	    public ClientBean updateClient(ClientBean client);
	    
	    public List<ClientBean> findByRedirectUri(String url);
	    
	    public void deleteClient(Long id);

	    public ClientBean findOne(Long id);

	    public List<ClientBean> findAll();

	    public ClientBean findByClientId(String clientId);
	    
	    public ClientBean findByClientSecret(String clientSecret);
	    
	    public List<ClientBean> findByUserId(Long userId);
	    
	    
	    
	    
}

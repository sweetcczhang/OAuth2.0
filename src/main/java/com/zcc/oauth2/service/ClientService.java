package com.zcc.oauth2.service;

import java.util.List;

import com.zcc.oauth2.entity.ClientBean;

public interface ClientService {
	
	public ClientBean createClient(ClientBean client);
   
	public ClientBean updateClient(ClientBean client);
   
    public void deleteClient(Long id);

    public   ClientBean findOne(Long id);

    public  List<ClientBean> findAll();

    public  ClientBean findByClientId(String clientId);
    
    public    ClientBean findByClientSecret(String clientSecret);
    
    public  List<ClientBean> findByUserId(Long userId);
    
    public List<ClientBean> findByRedirect(String url);
    
}

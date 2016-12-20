package com.zcc.oauth2.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zcc.oauth2.entity.DeveloperBean;

@Repository
public class DeveloperBeanDaoImpl implements DeveloperBeanDao{
    
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public DeveloperBean createDeveloperBean(DeveloperBean developerBean) {
		// TODO Auto-generated method stub
		String sql="insert into developer_info(user_id,developer_type,company_name,company_address,link_man,"
				+ "email,telephone,qq)values(?,?,?,?,?,?,?,?)";
		 jdbcTemplate.update(sql,developerBean.getUserId(),developerBean.getDeveloperType(),developerBean.getCompanyName(),
				 developerBean.getCompanyAddress(),developerBean.getLinkMan(),developerBean.getEmail(),
				 developerBean.getTelePhone(),developerBean.getQq());
		return developerBean;
	}

	@Override
	public DeveloperBean updateDeveloperBean(DeveloperBean developerBean) {
		// TODO Auto-generated method stub
		String sql=" update developer_info set developer_type=?, company_name=? ,company_address=?,"
				+ " link_man=?, email=?, telephone=?, qq=? where user_id=?";
		 jdbcTemplate.update(sql,developerBean.getDeveloperType(),developerBean.getCompanyName(),
				 developerBean.getCompanyAddress(),developerBean.getLinkMan(),developerBean.getEmail(),
				 developerBean.getTelePhone(),developerBean.getQq(),developerBean.getUserId());
		
		 return developerBean;
	}

	@Override
	public DeveloperBean getDeveloperBeanByUserId(Long userId) {
		// TODO Auto-generated method stub
		System.out.println("userId:"+userId);
		System.out.println(userId);
		int userid=1;
		String sql="select user_id, developer_type, company_name, company_address, link_man,"
				+ " email, telephone, qq from developer_info where user_id=?";
		 List<DeveloperBean> list= jdbcTemplate.query(sql, new BeanPropertyRowMapper<DeveloperBean>(DeveloperBean.class),userId);
		
		 if(list.size()==0){
			System.out.println("-----这有问题？-------");
			 return null;
		 }
		 
		return list.get(0);
	}

	@Override
	public List<DeveloperBean> findAll() {
		// TODO Auto-generated method stub
		String sql="user_id, select user_id, developer_type, company_name, company_address, link_man,"
				+ " email, telephone, qq from developer_info";
		List<DeveloperBean> list=jdbcTemplate.query(sql, new BeanPropertyRowMapper<DeveloperBean>(DeveloperBean.class));
		if(list.size()==0){
			return null;
		}
		return list;
	}
    
}

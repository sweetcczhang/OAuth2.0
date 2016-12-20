package com.zcc.oauth2.weibo.weibo4j;
import java.util.List;

import com.zcc.oauth2.weibo.model.PostParameter;
import com.zcc.oauth2.weibo.model.User;
import com.zcc.oauth2.weibo.model.WeiboException;
import com.zcc.oauth2.weibo.util.WeiboConfig;

public class Users extends Weibo {

	private static final long serialVersionUID = 4742830953302255953L;
   // public String baseURL="https://api.weibo.com/2/";
	public Users(String access_token) {
		this.access_token = access_token;
	}

	/*----------------------------用户接口----------------------------------------*/
	/**
	 * 根据用户ID获取用户信息
	 * 
	 * @param uid
	 *            需要查询的用户ID
	 * @return User
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see http://open.weibo.com/wiki/2/users/show
	 * @since JDK 1.5
	 */
	public User showUserById(String uid) throws WeiboException {
		return new User(client.get(
				WeiboConfig.getValue("baseURL") + "users/show.json",
				new PostParameter[] { new PostParameter("uid", uid) },
				access_token).asJSONObject());
	}

	/**
	 * 根据用户ID获取用户信息
	 * 
	 * @param screen_name
	 *            用户昵称
	 * @return User
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see http://open.weibo.com/wiki/2/users/show
	 * @since JDK 1.5
	 */
	public User showUserByScreenName(String screen_name) throws WeiboException {
		return new User(client.get(
				WeiboConfig.getValue("baseURL")+ "users/show.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }, access_token).asJSONObject());
	}

	/**
	 * 通过个性化域名获取用户资料以及用户最新的一条微博
	 * 
	 * @param domain
	 *            需要查询的个性化域名。
	 * @return User
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see http://open.weibo.com/wiki/2/users/domain_show
	 * @since JDK 1.5
	 */
	public User showUserByDomain(String domain) throws WeiboException {
		return new User(client.get(
				WeiboConfig.getValue("baseURL") + "users/domain_show.json",
				new PostParameter[] { new PostParameter("domain", domain) },
				access_token).asJSONObject());
	}

	/**
	 * 批量获取用户的粉丝数、关注数、微博数
	 * 
	 * @param uids
	 *            需要获取数据的用户UID，多个之间用逗号分隔，最多不超过100个
	 * @return jsonobject
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see http://open.weibo.com/wiki/2/users/domain_show
	 * @since JDK 1.5
	 */
	
}

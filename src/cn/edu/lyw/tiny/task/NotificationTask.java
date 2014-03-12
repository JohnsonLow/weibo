/**
 *NotificationTask.java
 * Administrator
 * 上午10:39:57
 */
package cn.edu.lyw.tiny.task;

import java.util.Map;
import java.util.TimerTask;

import cn.edu.lyw.tiny.util.JSONUtils;

import weibo4j.http.HttpClient;
import weibo4j.http.Response;
import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;

/**
 * @description  
 * @version 1.0
 * 
 */
public class NotificationTask extends TimerTask  {
	String url = "https://rm.api.weibo.com/2/remind/unread_count.json";
	String token;
	String uid;
	/**
	 * 
	 */
	public NotificationTask(String token,String uid) {
		this.token = token;
		this.uid = uid;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		HttpClient client = new HttpClient();
		client.setToken(token);
		try {
			Response res = client.get(url,new PostParameter[]{
					new PostParameter("uid", uid)
			});
			Map<String,Object> result = JSONUtils.getObject(res.getResponseAsString(),
					Map.class);
			if(result != null && result.size()>0){
				
				int mention_status = (Integer)result.get("mention_status");
				if(mention_status>0){
					
				}
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}

}

package cn.edu.lyw.tiny.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import weibo4j.util.WeiboConfig;
import android.util.Log;

/**
 * @description  
 * @version 1.0
 * 
 */
public class MissionUtil {
	private static HttpClient client = new HttpClient();
	private static StringBuffer url = new StringBuffer();
	/**
	 * 任务列表类型
	 */
	private static final int URL_LIST = 1;
	
	/**
	 * 任务详情类型
	 */
	private static final int URL_INFO = 2;
	
	/**
	 * 检查是否已绑定
	 */
	private static final int URL_CHECK = 3;
	
	
	/**
	 * 获取任务列表
	 * @param handlerId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getMissionList(int handlerId){
		String jsonStr = null;
		try {
			GetMethod method = new GetMethod(getUrl(URL_LIST,handlerId));
			client.executeMethod(method);
			jsonStr = method.getResponseBodyAsString();
			if(StringUtility.isNotBlank(jsonStr)){
				return JSONUtils.getObject(jsonStr,List.class);
			}
		} catch (Exception e) {
			Log.e("MISSION_ERROR",e.getMessage());
		}
		return null;
	}
	
	/**
	 * 
	 * @param type 为1时，获得任务列表URL 为2时，获得任务详情URL
	 * @param id
	 * @return url 
	 */
	private static String getUrl(int type ,Object id){
		url.delete(0, url.length());
		switch (type) {
		case URL_LIST://获取任务列表
			url.append(WeiboConfig.getValue("mission.list")
					).append(id);
			break;
		case URL_INFO://获取任务详情
			url.append(WeiboConfig.getValue("mission.info")
					).append(id);
			break;
		case URL_CHECK:
			url.append(WeiboConfig.getValue("bind.check")).append(id);
			break;
		default: 
			break;
		}
		return url.toString();
	}
	/**
	 * @param taskId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getMission(
			int taskId) {
		GetMethod method = new GetMethod(getUrl(URL_INFO,taskId));
		try {
			client.executeMethod(method);
			String json = method.getResponseBodyAsString();
			if(StringUtility.isNotBlank(json)){
				return JSONUtils.getObject(json, Map.class);
			}
		} catch (Exception e) {
			Log.e("MISSION_ERROR",e.getMessage());
		}
		return null;
	}
	/**
	 * @param uid
	 */
	public static Integer checkBind(String uid) {
		GetMethod method = new GetMethod(getUrl(URL_CHECK,uid));
		Integer handlerId = -1;
		try {
			client.executeMethod(method);
			handlerId = Integer.parseInt(method.getResponseBodyAsString());
		}catch (Exception e) {
			Log.e("BIND_ERROR",e.getMessage());
		}
		return handlerId;
	}

	/**
	 * @param userid
	 * @param userName
	 * @param password
	 * @return
	 */
	public static Integer bind(String userid, String userName,
			String password,String weiboName) {
		url.delete(0, url.length());
		url.append(WeiboConfig.getValue("bind.bind")).append("?userName=").append(
				userName).append("&password=").append(password).append("&uid=").append(
						userid).append("&weiboName=").append(weiboName);
		GetMethod method = new GetMethod(url.toString());
		Integer result = -1;
		try{
			client.executeMethod(method);
			result = Integer.parseInt(method.getResponseBodyAsString());
		}catch(Exception e){
			Log.e("BIND_ERROR",e.getMessage());
		}
		return result;
	}
	public static String getStatus(Object obj){
		if(obj != null){
			Integer status = (Integer)obj;
			switch(status.intValue()){
				case 1:
					return "待处理";
				case 2:
					return "处理中";
				case 3:
					return "已完成";
			}
		}
		return null;
	}

	/**
	 * @param object
	 * @return
	 */
	public static String getHandlerType(Object object) {
		if(object != null){
			Integer type = (Integer) object;
			switch(type){
			case 1001: return "转发";
			case 1002: return "完成";
			}
		}
		return "";
	}
}
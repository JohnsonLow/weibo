/**
 *MissionUtil.java
 * Administrator
 * 下午4:53:52
 */
package cn.edu.lyw.tiny.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import weibo4j.util.WeiboConfig;
import android.util.Log;

/**
 * @description  
 * @author lyw
 * @updatetime 2014-3-3 下午4:53:52
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
	private static String getUrl(int type ,int id){
		url.delete(0, url.length());
		switch (type) {
		case URL_LIST://获取任务列表
			url.append(WeiboConfig.getValue("taskListURL")
					).append(id);
			break;
		case URL_INFO://获取任务详情
			url.append(WeiboConfig.getValue("taskInfoURL")
					).append(id);
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
	public static Map<String,Map<String, Object>> getMission(
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
}
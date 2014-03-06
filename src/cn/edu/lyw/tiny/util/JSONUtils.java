package cn.edu.lyw.tiny.util;

import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;


public class JSONUtils {
	private static ObjectMapper mapper = new ObjectMapper();
	/**
	 * 将普通对象转换为JSON字符串
	 * @param obj
	 * @return
	 */
	public static String getJSONString(Object obj){
		String str = null;
		if(obj != null){
			try {
				str = mapper.writeValueAsString(obj);
			} catch (Exception e) {
				Log.e("JSON_ERROR", "将对象转换为JSON字符串时出错");
			}
		}
		return str;
	}
	
	public static <T>T getObject(String jsonStr,Class<T> valueType){
		if(StringUtility.isNotBlank(jsonStr)){
			try {
				return mapper.readValue(jsonStr, valueType);
			} catch (Exception e) {
				Log.e("JSON_ERROR","将JSON字符串转换为对象时出错");
			}
		}
		return null;
	}
}

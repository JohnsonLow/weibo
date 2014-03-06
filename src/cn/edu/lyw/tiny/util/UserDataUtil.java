package cn.edu.lyw.tiny.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import cn.edu.lyw.tiny.model.UserData;

/**
 * @description 用户数据的工具处理类
 * @version 1.0
 * 
 */
public class UserDataUtil {
	public static final String USERDATA = "userData";
	
	private static UserData userData;
	/**
	 * 检查accessToken是否有效
	 * 
	 * @param accessToken
	 *            accessToken
	 * @param time
	 *            失效时间
	 * @return 如果有效返回true
	 */
	public static boolean isTokenValid(String accessToken, String time) {
		long expirestime = Long.parseLong(time);
		return (!TextUtils.isEmpty(accessToken) && (expirestime == 0 || (System.currentTimeMillis() < expirestime)));
	}
	/**
	 * 更新userData信息
	 * 
	 * @param context
	 *            Context
	 * @param userData
	 *            userData信息
	 */
	public static void updateUserData(Context context, UserData userData) {
		SharedPreferences.Editor editor = context.getSharedPreferences(ConstantUtil.TINYWEIBO, Context.MODE_PRIVATE)
				.edit();
		synchronized (UserDataUtil.class) {
			UserDataUtil.userData = userData;
			editor.putString(USERDATA, JSONUtils.getJSONString(userData));
			editor.commit();
		}
	}

	/**
	 * 删除userData
	 * 
	 * @param context
	 *            Context
	 */
	public static void clearUserData(Context context) {
		SharedPreferences.Editor editor = context.getSharedPreferences(ConstantUtil.TINYWEIBO, Context.MODE_PRIVATE)
				.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * 读取userData
	 * 
	 * @param context
	 *            Context
	 * @return 用户信息
	 */
	public static UserData readUserData(Context context) {
		SharedPreferences pref = context.getSharedPreferences(ConstantUtil.TINYWEIBO, Context.MODE_PRIVATE);
		synchronized (UserDataUtil.class) {
			if(userData == null){
				userData = JSONUtils.getObject(pref.getString(USERDATA, ""), UserData.class);
			}
		}
		return userData;
	}
}

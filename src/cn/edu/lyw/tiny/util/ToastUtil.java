package cn.edu.lyw.tiny.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @description Toast提示信息的简易包装类
 * @version 1.0
 * 
 */
public class ToastUtil {

	/**
	 * 显示长时间的Toast信息
	 * 
	 * @param context
	 *            Context
	 * @param message
	 *            信息
	 */
	public static void showLongToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * 显示短时间的Toast信息
	 * 
	 * @param context
	 *            Context
	 * @param message
	 *            信息
	 */
	public static void showShortToast(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

}

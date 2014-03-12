package cn.edu.lyw.tiny;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import cn.edu.lyw.tiny.util.ToastUtil;
import cn.edu.lyw.tiny.util.UserDataUtil;

/**
 * @description 设置界面的退出对话框
 * @version 1.0
 * 
 */
public class DialogSettingsExit extends Activity {

	/** 退出界面的布局 */
	private LinearLayout ll_settings_exit;

	/*
	 * (non-Javadoc)创建界面的回调方法
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_settings_exit);
		ll_settings_exit = (LinearLayout) findViewById(R.id.ll_settings_exit);
		ll_settings_exit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToastUtil.showShortToast(getApplicationContext(), "点击窗口外部关闭窗口！");
			}
		});
	}

	/*
	 * (non-Javadoc)触摸事件的回调方法
	 * 
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	/**
	 * 取消退出
	 * 
	 * @param v
	 *            按钮组件
	 */
	public void btn_settings_exitcancle(View v) {
		finish();
	}

	/**
	 * 确定退出
	 * 
	 * @param v
	 *            按钮组件
	 */
	public void btn_settings_exitok(View v) {
		UserDataUtil.clearUserData(MainWeibo.instance);
		finish();
		MainWeibo.instance.finish();
	}

}

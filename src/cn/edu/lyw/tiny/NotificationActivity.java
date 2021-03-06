package cn.edu.lyw.tiny;

import cn.edu.lyw.tiny.util.ConstantUtil;
import cn.edu.lyw.tiny.util.ToastUtil;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

/**
 * @description  
 * @version 1.0
 * 
 */
public class NotificationActivity extends Activity {
	private LinearLayout ll_noti_layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_noti);
		ll_noti_layout = (LinearLayout) findViewById(R.id.ll_noti_layout);
		ll_noti_layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToastUtil.showShortToast(getApplicationContext(), "点击窗口外部关闭窗口！");
			}
		});
	}
	/** 退出界面的布局 */


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
	public void btn_noti_cancle(View v) {
		finish();
	}

	/**
	 * 确定退出
	 * 
	 * @param v
	 *            按钮组件
	 */
	public void btn_noti_ok(View v) {
		finish();
		MainWeibo.instance.mTabPager.setCurrentItem(0);
		MainWeibo.instance.loadTimeline(ConstantUtil.TIMELINE_MENTIONS);
	}
}

/**
 *BindDialog.java
 * Administrator
 * 上午11:24:33
 */
package cn.edu.lyw.tiny;

import cn.edu.lyw.tiny.util.ToastUtil;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

/**
 * @description  
 * @author lyw
 * @updatetime 2014-3-6 上午11:24:33
 * @version 1.0
 * 
 */
public class BindDialog extends Activity{


	/** 退出界面的布局 */
	private LinearLayout ll_bind_layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_bindacount);
		ll_bind_layout = (LinearLayout) findViewById(R.id.ll_bind_layout);
		ll_bind_layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ToastUtil.showShortToast(getApplicationContext(), "点击窗口外部关闭窗口！");
			}
		});
	}

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
	public void btn_exit_cancle(View v) {
		finish();
	}

	/**
	 * 确定退出
	 * 
	 * @param v
	 *            按钮组件
	 */
	public void btn_exit_ok(View v) {
		finish();
		MainWeibo.instance.finish();
	}

}

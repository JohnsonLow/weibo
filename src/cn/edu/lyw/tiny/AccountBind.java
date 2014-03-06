package cn.edu.lyw.tiny;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import cn.edu.lyw.tiny.util.ToastUtil;

/**
 * 绑定用户的对话框
 * @version 1.0
 * 
 */
public class AccountBind extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_bind);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		ToastUtil.showShortToast(getApplicationContext(), "请绑定微博管理系统的账号");
		ToastUtil.showLongToast(getApplicationContext(),  "请绑定微博管理系统的账号");
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

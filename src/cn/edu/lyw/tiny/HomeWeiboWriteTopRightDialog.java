package cn.edu.lyw.tiny;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import cn.edu.lyw.tiny.util.ConstantUtil;

/**
 * @description 微博图片类型的选择界面
 * @version 1.0
 * 
 */
public class HomeWeiboWriteTopRightDialog extends Activity {

	/*
	 * (non-Javadoc)创建界面的回调方法
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_weibowrite_dialog_choosetype);
	}

	/*
	 * (non-Javadoc)触摸事件的回调方法
	 * 
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		setResult(RESULT_CANCELED);
		finish();
		return true;
	}

	/**
	 * 从本地相册选择图片
	 * 
	 * @param v
	 *            View组件
	 */
	public void ll_pickpic(View v) {
		setResult(RESULT_OK, (new Intent()).putExtra(ConstantUtil.PICTURE_TYPE, ConstantUtil.REQUEST_PICK_PICTURE));
		finish();
	}

	/**
	 * 拍照
	 * 
	 * @param v
	 *            View组件
	 */
	public void ll_takepic(View v) {
		setResult(RESULT_OK, (new Intent()).putExtra(ConstantUtil.PICTURE_TYPE, ConstantUtil.REQUEST_TAKE_PICTURE));
		finish();
	}

}

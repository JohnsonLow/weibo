package cn.edu.lyw.tiny;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import cn.edu.lyw.tiny.model.UserData;
import cn.edu.lyw.tiny.util.MissionUtil;
import cn.edu.lyw.tiny.util.StringUtility;
import cn.edu.lyw.tiny.util.ToastUtil;
import cn.edu.lyw.tiny.util.UserDataUtil;

/**
 * 绑定用户的对话框
 * @version 1.0
 * 
 */
public class AccountBind extends Activity {
	private ProgressDialog mSpinner;
	private EditText userNameEdit;
	private EditText passwordEdit;
	private UserData userData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_bind);
		userNameEdit = (EditText)findViewById(R.id.bind_username_edit);
		passwordEdit = (EditText)findViewById(R.id.bind_passwd_edit);
		userData = UserDataUtil.readUserData(this);
		checkBindUser();
		mSpinner = new ProgressDialog(this);
		mSpinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mSpinner.setMessage("正在验证管理系统");
		mSpinner.show();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		ToastUtil.showShortToast(getApplicationContext(), "请绑定微博管理系统的账号");
		return true;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			setResult(RESULT_CANCELED);
			finish();
		}
		return true;
	}
	public void doBind(Integer handlerId){
		String msg ="绑定微博管理系统账号失败，请联系管理员";
		if(handlerId != null){
			if(handlerId>0){
				enterMainWeibo(handlerId);
				return;
			}else{
				switch(handlerId){
					case -1 : msg = "输入的账号不存在，请重新输入"; break;
					case -2 : msg="密码错误，请重新输入"; break;
					case -3 : msg="该系统账号已被其他微博绑定，不可重复绑定"; break;
				}
			}
		}else{
			
		}
		hideSpiner();
		ToastUtil.showShortToast(getApplicationContext(), msg);
	}
	public void bind_check(View v){
		mSpinner.setMessage("正在进行用户绑定");
		final String userName = getText(userNameEdit) ;
		final String password = getText(passwordEdit);
		if(StringUtility.isNotBlank(userName) && StringUtility.isNotBlank(password)){
			new AsyncTask<Void, Void, Integer>() {
				@Override
				protected void onPostExecute(Integer result) {
					doBind(result);
				}
				@Override
				protected Integer doInBackground(Void... params) {
					return MissionUtil.bind(userData.getUserid(),userName,
							password,userData.getNickname());
				}
				
			};
		}else{
			ToastUtil.showShortToast(getApplicationContext(), "用户名或密码不可为空");
		}
	}
	/**
	 * @param userNameEdit2
	 * @return
	 */
	private String getText(EditText text) {
		Editable t = text.getText();
		if(t != null){
			return t.toString();
		}
		return null;
	}

	/**
	 * 验证是否
	 */
	private void checkBindUser() {
		new AsyncTask<Void, Void, Integer>() {
			@Override
			protected void onPostExecute(Integer result) {
				doBind(result);
			}
			@Override
			protected Integer doInBackground(Void... params) {
				return MissionUtil.checkBind(userData.getUserid());
			}
		}.execute();
	}

	/**
	 * @param handlerId 
	 * 
	 */
	protected void enterMainWeibo(Integer handlerId) {
		userData.setHandlerId(handlerId);
		UserDataUtil.updateUserData(AccountBind.this, userData);
		hideSpiner();
		setResult(RESULT_OK);
		finish();
	}
	private void hideSpiner(){
		if(mSpinner.isShowing()){
			mSpinner.dismiss();
	    }
	}
}

/**
 * TinyWeibo 微微博 
 * Copyright 2012 China ITElite Team
 * All Rights Reserved.
 * Created on 2012-12-20 23:53:32
 */
package cn.edu.lyw.tiny;


import java.io.ByteArrayOutputStream;
import java.io.IOException;

import weibo4j.util.WeiboConfig;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import cn.edu.lyw.tiny.model.UserData;
import cn.edu.lyw.tiny.util.ConstantUtil;
import cn.edu.lyw.tiny.util.ToastUtil;
import cn.edu.lyw.tiny.util.UserDataUtil;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.WeiboParameters;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.utils.LogUtil;

/**
 * @description 用户登录注册界面
 * @version 1.0
 * 
 */
public class Welcome extends Activity {
	

	private WeiboAuth mWeiboAuth;
	
    private ProgressDialog mSpinner;
	/*
	 * (non-Javadoc)创建界面的回调方法
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWeiboAuth = new WeiboAuth(this, ConstantUtil.CONSUMER_KEY, ConstantUtil.REDIRECT_URL, ConstantUtil.SCOPE);
		setContentView(R.layout.welcome);
	}

	/**
	 * 登录方法
	 * 
	 * @param v
	 *            View组件
	 */
	public void welcome_login(View v) {
		mWeiboAuth.authorize(new AuthDialogListener(), WeiboAuth.OBTAIN_AUTH_CODE);
	}

	/**
	 * 进入主界面
	 */
	private void enterCheckBind() {
		Intent intent = new Intent(Welcome.this, AccountBind.class);
		startActivity(intent);
		if(mSpinner.isShowing()){
			mSpinner.dismiss();
	    }
		Welcome.this.finish();
	}
	

	/**
	 * 用户注册
	 * 
	 * @param v
	 *            View组件
	 */
	public void welcome_register(View v) {
		Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ConstantUtil.REGISTER_URL));
		startActivity(webIntent);
	}
	

	/**
	 * The listener interface for receiving authDialog events. The class that is interested in processing a authDialog
	 * event implements this interface, and the object created with that class is registered with a component using the
	 * component's <code>addAuthDialogListener<code> method. When
	 * the authDialog event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see AuthDialogEvent
	 */
	class AuthDialogListener implements WeiboAuthListener {

		/*
		 * (non-Javadoc)授权完成的回调方法
		 * 
		 * @see com.weibo.sdk.android.WeiboAuthListener#onComplete(android.os.Bundle)
		 */
		@Override
		public void onComplete(Bundle values) {
			String code = values.getString("code");
			if(code == null){
				ToastUtil.showShortToast(getApplicationContext(), "授权失败");
				return;
			}
			fetchTokenAsync(code);
		}

		/*
		 * (non-Javadoc)授权取消的回调方法
		 * 
		 * @see com.weibo.sdk.android.WeiboAuthListener#onCancel()
		 */
		@Override
		public void onCancel() {
			ToastUtil.showShortToast(getApplicationContext(), "取消授权");
		}

		/* (non-Javadoc)
		 * @see com.sina.weibo.sdk.auth.WeiboAuthListener#onWeiboException(com.sina.weibo.sdk.exception.WeiboException)
		 */
		@Override
		public void onWeiboException(
				com.sina.weibo.sdk.exception.WeiboException arg0) {
			ToastUtil.showShortToast(getApplicationContext(), "授权异常");
			
		}
	}

	  /**
     * 异步获取 Token。
     * 
     * @param authCode  授权 Code，该 Code 是一次性的，只能被获取一次 Token
     */
	public void fetchTokenAsync(String code) {
		mSpinner = new ProgressDialog(this);
		mSpinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mSpinner.setMessage("正在进行授权认证");
		mSpinner.show();
		WeiboParameters requestParams = new WeiboParameters();
        requestParams.add(WBConstants.AUTH_PARAMS_CLIENT_ID, ConstantUtil.CONSUMER_KEY);
        requestParams.add(WBConstants.AUTH_PARAMS_CLIENT_SECRET, ConstantUtil.CONSUMER_SECRET);
        requestParams.add(WBConstants.AUTH_PARAMS_GRANT_TYPE, "authorization_code");
        requestParams.add(WBConstants.AUTH_PARAMS_CODE,          code);
        requestParams.add(WBConstants.AUTH_PARAMS_REDIRECT_URL,  ConstantUtil.REDIRECT_URL);
        AsyncWeiboRunner.request(WeiboConfig.getValue("accessTokenURL"), requestParams, "POST", new RequestListener() {
            @Override
            public void onComplete(String response) {
                // 获取 Token 成功
                Oauth2AccessToken token = Oauth2AccessToken.parseAccessToken(response);
                if (token != null && token.isSessionValid()) {
        			UserDataUtil.updateUserData(Welcome.this, new UserData(token));
        			enterCheckBind();
                } else {
                    LogUtil.e("LOGIN_ERROR", "Failed to receive access token");
                }
            }
    
			@Override
            public void onComplete4binary(ByteArrayOutputStream responseOS) {
                LogUtil.e("LOGIN_TAG", "onComplete4binary...");
            }
    
            @Override
            public void onIOException(IOException e) {
                LogUtil.e("LOGIN_ERROR", "onIOException： " + e.getMessage());
            }
    
            @Override
            public void onError(WeiboException e) {
                LogUtil.e("LOGIN_ERROR", "WeiboException： " + e.getMessage());
            }
        });
	}
}

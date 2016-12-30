package com.jxutcm.controllers.start;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import test.com.jxutcm.controllers.model.User;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.gson.Gson;
import com.jxutcm.business.util.ConfigurationFile;
import com.jxutcm.business.util.ToolKit;
import com.jxutcm.business.util.UserConst;
import com.jxutcm.controllers.start.R;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/** 
 * @author 
 * @version 
 */
public class RegisterUI extends Activity {
	//Android通过注解初始化View
	@ViewInject(R.id.resign_account)
	private EditText re_account;//账号
	@ViewInject(R.id.register_password)
	private EditText re_password;	//密码
	@ViewInject(R.id.resgin_username)
	private EditText re_name;		//昵称
	//private EditText re_password2;	//！重复密码	
	@ViewInject(R.id.resign_btn)
	private Button re_resign_btn;
	private RadioGroup sex = null;
	private RadioButton male = null;
	private User user;
	private String re_sex="";
	private String re_communication="";
	private String re_communication_way="";//test
	private TextView re_communication_way_choice = null;
	private EditText re_communication_content=null;//电话号码
	private boolean usernameCursor=true;// 判读用户名输入框是失去光标还是获得光标
	private boolean repasswordCursor = true;// 判读重复密码输入框是失去光标还是获得光标

	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if (msg.what == 1) {//等于1是说明接收到了注册成功的信息，当注册成功时，服务器会返回1给客户端
				
					Toast.makeText(RegisterUI.this, "注册成功", Toast.LENGTH_SHORT)
					.show();
					//保存信息
//					ToolKit.createSharedPreferencesString(RegisterUI.this, UserConst.USER_KEY_ACCOUNT, user.getUsername());
//					ToolKit.createSharedPreferencesString(RegisterUI.this, UserConst.USER_KEY_NAME, user.getPassword());
					startActivity(new Intent(RegisterUI.this,LoginUI.class));
					finish();		
			} else if(msg.what==0){
					Toast.makeText(RegisterUI.this, "该账号已经存在，请更改账号",
					Toast.LENGTH_SHORT).show();
					
			}else {
					Toast.makeText(RegisterUI.this, "该账号已经存在，请更改账号",
					Toast.LENGTH_SHORT).show();
//					Toast.makeText(RegisterUI.this, "未连接服务端", Toast.LENGTH_SHORT)
//					.show();
				}		
			}
		};
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resign_ui);
		ViewUtils.inject(this);
		initListener();
	}

	public void initListener(){
		re_resign_btn.setOnClickListener(new RegisterListener());
//		re_account.setOnFocusChangeListener(new CheckUsernameListener());
//		re_password1.setOnFocusChangeListener(new RePasswordListener());
		
	}
	
	
	private int proessResign() {
		if (TextUtils.isEmpty(re_account.getText().toString())) {
			re_account.setError(Html.fromHtml("<font color=red>帐号不能为空</font>"));
			//让他成为焦点
			re_account.requestFocus();
			return 0;
		}
		if (TextUtils.isEmpty(re_password.getText().toString())) {
			re_password.setError(Html.fromHtml("<font color=red>密码不能为空</font>"));
			re_password.requestFocus();
			return 0;
		}
		return 1;
//		if (TextUtils.isEmpty(re_name.getText().toString())) {
//			re_name.setError(Html.fromHtml("<font color=red>用户不能为空</font>"));
//			return ;
//		}
	}
	/**
	 * 执行注册的方法
	 */
	public void excuteRegister(){
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				
				HttpClient client = new DefaultHttpClient();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				NameValuePair pair = new BasicNameValuePair("index", "2");
				list.add(pair);
				NameValuePair pair1 = new BasicNameValuePair("username", re_account.getText().toString());
				NameValuePair pair2 = new BasicNameValuePair("password", re_password.getText().toString());
				NameValuePair pair3 = new BasicNameValuePair("sex", re_sex);
				NameValuePair pair4 = new BasicNameValuePair("communication_way", re_communication);
				NameValuePair pair5 = new BasicNameValuePair("communication_num",""/* re_communication_content.getText().toString()*/);
				list.add(pair1);
				list.add(pair2);
				list.add(pair3);
				list.add(pair4);
				list.add(pair5);
				try {
					HttpEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
					Log.i("register----------->", "HttpPost");
					HttpPost post = new HttpPost(ConfigurationFile.serverAddress);
					Log.i("register----------->", "HttpPost");
					post.setEntity(entity);
					HttpResponse response = client.execute(post);
					Log.i("register----------->", "HttpPost?");
					if (response.getStatusLine().getStatusCode() == 200) {
						InputStream in = response.getEntity().getContent();
						handler.sendEmptyMessage(in.read());//将服务器端返回给客户端的标记直接传输给handler
						in.close();
					}
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		}.start();
	}
	/**
	 * CheckAccountListener
	 * 
	 * 当输入完用户名，输入框失去光标后,该用户名在数据库中是否存在
	 */
//	private class CheckUsernameListener implements OnFocusChangeListener {
//		@Override
//		public void onFocusChange(View v, boolean hasFocus) {
//			String myUsername = re_account.getText().toString();
//			if (!usernameCursor) {
//				if (isAccountExisted(myUsername)) {
//					Toast.makeText(RegisterUI.this, "该账号已经存在，请更改账号",
//							Toast.LENGTH_SHORT).show();
//				}
//			}
//		}
//
//
//	}

	/**
	 * RegisterListener
	 * 
	 * 点击注册按钮后，向服务器端发送注册信息，等到服务器返回确认信息后，提示注册成功，并自动返回登陆页
	 * 
	 */
	private class RegisterListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(proessResign()!=0){
				getUser();
				excuteRegister();				
			}	
			
		}
		/**
		 * 获取用户的注册信息获取用户在页面上填写的信息，并将这些信息封装到User类中
		 * @return User 包含有用户完整注册信息的User包装
		 */
		private User getUser() {
			User user = new User();
			user.setUsername(re_account.getText().toString());
			user.setPassword(re_password.getText().toString());
			user.setSex(re_sex);
			user.setCommunication(re_communication);
			user.setCommunication(re_communication_way/*re_communication_content.getText().toString()*/);
			return user;
		}
	}


}
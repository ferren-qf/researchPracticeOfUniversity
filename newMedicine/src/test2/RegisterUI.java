package test2;


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
import com.jxutcm.controllers.start.LoginUI;
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
	private EditText re_name;		//！昵称
	
	private EditText re_password2;	//！重复密码
	
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
//					Intent register_login = new Intent(RegisterUI.this, LoginUI.class);
//					startActivity(register_login);
					finish();		
			} else {
				if (re_account.getText().toString() == null) {
					Toast.makeText(RegisterUI.this, "用户名不能为空", Toast.LENGTH_SHORT)
					.show();
					//让他成为焦点
					re_account.requestFocus();
				} else{
					Toast.makeText(RegisterUI.this, "注册失败", Toast.LENGTH_SHORT)
					.show();
				}			
			}
		};
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
	
	
//	private void proessResign() {
//		if (TextUtils.isEmpty(re_useraccount.getText().toString())) {
//			re_useraccount.setError(Html.fromHtml("<font color=red>帐号不能为空</font>"));
//			return ;
//		}
//		if (TextUtils.isEmpty(re_password.getText().toString())) {
//			re_password.setError(Html.fromHtml("<font color=red>密码不能为空</font>"));
//			return ;
//		}
//		if (TextUtils.isEmpty(re_name.getText().toString())) {
//			re_name.setError(Html.fromHtml("<font color=red>用户不能为空</font>"));
//			return ;
//		}
//		String user_account=re_useraccount.getText().toString().trim();
//		String user_password=re_password.getText().toString().trim();
//		String user_name=re_name.getText().toString().trim();
//		//http://localhost:8080/AndroidExam/servlet/UserServlet?flag=resign&account=12345&password=123&name=张三
//		new HttpUtils().send(HttpMethod.GET, API.API_RESIGN_URL+"&account="+user_account+"&password="+user_password+"&name="+user_name,new RequestCallBack<String>() {
//			public void onFailure(HttpException arg0, String arg1) {
//				Log.e("注册失败", arg0+"   "+arg1);
//			}
//			public void onSuccess(ResponseInfo<String> result) {
//				Log.e("注册成功", result.result);
//				//解析json
//				Gson gson=new Gson();
//				ResponseObject<User> object=gson.fromJson(result.result, new TypeToken<ResponseObject<User>>(){}.getType());				
//				if (object.getState()==1) {
//					Toast.makeText(RegisterUI.this, "注册成功", Toast.LENGTH_LONG).show();
//					User user=object.getDatas();
//					Log.e("注册成功", user.getAccount()+"  "+user.getName());
//					//保存信息
//					ToolKit.createSharedPreferencesString(RegisterUI.this, UserConst.USER_KEY_ACCOUNT, user.getAccount());
//					ToolKit.createSharedPreferencesString(RegisterUI.this, UserConst.USER_KEY_NAME, user.getName());
//					//跳转登录界面
//					startActivity(new Intent(RegisterUI.this,LoginUI.class));
//					finish();
//				}else{
//					Toast.makeText(RegisterUI.this, "该帐号已经注�?, Toast.LENGTH_SHORT).show();
//				}
//			}
//		});
//
//	}
	/**
	 * 执行注册的方�?
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
	 * 当输入完用户名，输入框失去光标后,该用户名在数据库中是否存�?
	 */
	private class CheckUsernameListener implements OnFocusChangeListener {
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			String myUsername = re_account.getText().toString();
//			if (!usernameCursor) {
//				if (isAccountExisted(myUsername)) {
//					Toast.makeText(RegisterUI.this, "该账号已经存在，请更改账�?,
//							Toast.LENGTH_SHORT).show();
//				}
//			}
		}

		/**
		 * 用于�?��用户输入的用户名是否已经注册
		 * 将用户输入的用户名传动到服务器，在用户数据库中查找该用户名，若能够查找到则返回true，否则返回false
		 * @param username
		 *            用户输入的用户名
		 * @return 标记该用户名是否已经存在，存在为true，不存在为false
		 */
//		private boolean isAccountExisted(String account) {
//			boolean flag = false;
//			return flag;
//		}
	}

	/**
	 * RadioGroupSex
	 * 
	 * 性别复�?框的监听类，并将获得的�?别赋给成员变量re_sex 
	 */
//	private class RadioGroupSex implements RadioGroup.OnCheckedChangeListener {
//
//		@Override
//		public void onCheckedChanged(RadioGroup group, int checkedId) {
//			// TODO Auto-generated method stub
//			if (checkedId == male.getId()) {
//				re_sex = "�?;
//			} else {
//				re_sex = "�?;
//			}
//		}
//	}

	/**
	 * RePasswordListener
	 * 
	 * 重复输入密码失去光标的监听类
	 */
//	private class RePasswordListener implements OnFocusChangeListener {
//		@Override
//		public void onFocusChange(View v, boolean hasFocus) {
//			// TODO Auto-generated method stub
//			if (repasswordCursor=!repasswordCursor) {
//				if (!checkPassword(re_password1.getText().toString(), re_password2
//						.getText().toString())) {
//					re_password2.setText("");
//					//rePassword.requestFocus();
//					Toast.makeText(RegisterUI.this, "两次密码不一样，请重新输�?,
//							Toast.LENGTH_SHORT).show();
//				}
//			}
//		}
//
//		
//	}
	/**
	 * 比较两次输入的密码是否一�?
	 * rePassword输入完成后，光标改变监听，和password进行比较，如果不�?��，会有提示，并且两次密码密码清空
	 * 
	 * @param psw1
	 *            密码框中输入的密�?
	 * @param psw2
	 *            重复密码框中输入的密�?
	 * @return 两次密码�?��返回true，否则返回false
	 */
//	private boolean checkPassword(String psw1, String psw2) {
//		if (psw1.equals(psw2))
//			return true;
//		else
//			return false;
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
//			if (re_sex == null || re_communication_content.getText().toString() == null) {
//				String title = "提示�?;
//				String message = "您的信息不完整，填写完整信息有助于我们提供更好的服务";
//				new AlertDialog.Builder(RegisterUI.this).setTitle(title)
//						.setMessage(message)
//						.setPositiveButton("继续注册", new MakeSureListener())
//						.setNegativeButton("返回修改", null).show();
//			} else if (checkPassword(re_password1.getText().toString(), re_password2
//						.getText().toString())) {
				getUser();
				excuteRegister();
//			}else{
//				re_password2.setText("");
//				//rePassword.requestFocus();
//				Toast.makeText(RegisterUI.this, "两次密码不一样，请重新输�?,
//						Toast.LENGTH_SHORT).show();
//			}
			
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

//	/**
//	 * ExitListener
//	 * 
//	 * 设置返回按钮的点击监听，点击后回到登陆界�?
//	 */
//	private class ExitListener implements OnClickListener {
//
//		@Override
//		public void onClick(View v) {
//			// TODO Auto-generated method stub
//			startActivity(new Intent(RegisterUI.this,LoginUI.class));
//			finish();
//		}
//	}
//	/**
//	 * SpinnerListener
//	 * 
//	 * 联系方式的spinner组件commnunication监听，获得Item的内�?
//	 */
//	private class SpinnerListener implements OnItemSelectedListener {
//
//		@Override
//		public void onItemSelected(AdapterView<?> parent, View view,
//				int position, long id) {
//			// TODO Auto-generated method stub
//			re_communication = parent.getItemAtPosition(position).toString();
//			re_communication_way_choice.setText(re_communication);			
//		}
//
//		@Override
//		public void onNothingSelected(AdapterView<?> parent) {
//			// TODO Auto-generated method stub
//
//		}
//
//	}
//	/**
//	 * MakeSureListener
//	 * 
//	 * 确定提示框的确定按钮监听
//	 */
//	private class MakeSureListener implements
//			android.content.DialogInterface.OnClickListener {
//
//		@Override
//		public void onClick(DialogInterface dialog, int which) {
//			//第一次与第二次做比较
//			if (checkPassword(re_password1.getText().toString(), re_password2
//					.getText().toString())) {			
//			excuteRegister();
//		}else{
//			re_password2.setText("");
//			//rePassword.requestFocus();
//			Toast.makeText(RegisterUI.this, "两次密码不一样，请重新输�?,
//					Toast.LENGTH_SHORT).show();
//		}
//		}
//	}
}
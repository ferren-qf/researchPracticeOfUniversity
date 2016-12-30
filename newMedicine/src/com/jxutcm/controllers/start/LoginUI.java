package com.jxutcm.controllers.start;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;


import com.jxutcm.business.util.ConfigurationFile;
import com.jxutcm.controllers.start.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginUI extends Activity {
	@ViewInject(R.id.account)
	private EditText account;
	@ViewInject(R.id.password)
	private EditText password;
	@ViewInject(R.id.login_btn_resign)
	private Button login_btn_resign;
	@ViewInject(R.id.login_btn_login)
	private Button login_btn_login;

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 1) {//�����������ظ��ͻ��˵ı��Ϊ1ʱ��˵����½�ɹ�
				startActivity(new Intent(LoginUI.this,MainUI.class));
				Log.i("login_main----------------->", "success");
				finish();
			}else if(msg.what==0){
				Toast.makeText(LoginUI.this, "�˺Ż������������", Toast.LENGTH_SHORT)
				.show();
			}else if(msg.what==10){
				Toast.makeText(LoginUI.this, "����������ά��", Toast.LENGTH_SHORT)
				.show();
			}else{
				Toast.makeText(LoginUI.this, "����������ά��", Toast.LENGTH_SHORT)
				.show();
			}
		}
	};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_ui);
		//��ʼ��
		ViewUtils.inject(this);
		//���Ӽ���
		initListener();
//		account.setText(ToolKit.getString(LoginUI.this,UserConst.USER_KEY_ACCOUNT, null));
		
	}
	private void initListener(){
		login_btn_login.setOnClickListener(new LoginListener());
		login_btn_resign.setOnClickListener(new RegisterListener());
		
		
	}
	
	/**
	 * 
	 * ����Login����
	 * �û��ṩ�����ݺ��û����ݿ��е����ݽ���ƥ�䣬������Ե�½��������ʾ��ע��
	 */
	
	 private class LoginListener implements OnClickListener{	 
		 String myUserName = account.getText().toString();
		 String myPassword = password.getText().toString();
			@Override
			public void onClick(View v) {
				 if (TextUtils.isEmpty(account.getText().toString())) {
						account.setError(Html.fromHtml("<font color=red>�ʺŲ���Ϊ��</font>"));
						account.requestFocus();//������Ϊ����
						return ;
					}
					if (TextUtils.isEmpty(password.getText().toString())) {
						password.setError(Html.fromHtml("<font color=red>���벻��Ϊ��</font>"));
						password.requestFocus();
						return ;
					}
				
				new Thread (){
					public void run() {
						HttpClient client = new DefaultHttpClient();
						List<NameValuePair> list = new ArrayList<NameValuePair>();
						NameValuePair pair = new BasicNameValuePair("index", "0");
						list.add(pair);
						NameValuePair pair1 = new BasicNameValuePair("username", account.getText().toString());
						NameValuePair pair2 = new BasicNameValuePair("password",  password.getText().toString());
						list.add(pair1);
						list.add(pair2);
						try {
							UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
							HttpPost post = new HttpPost(ConfigurationFile.serverAddress);
							post.setEntity(entity);
							client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
							HttpResponse response = client.execute(post);
							//200��ʾ�ɹ�
							if (response.getStatusLine().getStatusCode() == 200) {
								InputStream in = response.getEntity().getContent();
								handler.sendEmptyMessage(in.read());//�����������ظ��ͻ��˵ı��ֱ�Ӵ���handler
								in.close();
							}
						} catch (ConnectTimeoutException e) {
							// TODO Auto-generated catch block
							handler.sendEmptyMessage(10);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							handler.sendEmptyMessage(10);
							e.printStackTrace();
						}
						
					}
					
				}.start();
			}			
	 }
	 /**
	  * ����register����
	  */
	 private class RegisterListener implements OnClickListener {

		 @Override
		 public void onClick(View v) {
			 // TODO Auto-generated method stub

			 startActivity(new Intent(LoginUI.this,RegisterUI.class));
			 Log.i("login_agreement----------------->", "success");
			 finish();
		 }
	 }

}

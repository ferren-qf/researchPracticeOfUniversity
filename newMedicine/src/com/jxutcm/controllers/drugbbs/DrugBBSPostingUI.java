package com.jxutcm.controllers.drugbbs;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jxutcm.business.util.ConfigurationFile;
import com.jxutcm.controllers.start.MainUI;
import com.jxutcm.controllers.start.R;


public class DrugBBSPostingUI extends Activity{
	private Button bbsposting_bt_sure;
	private EditText bbsposting_et_title;
	private EditText bbsposting_et_content;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		//无标题
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drugbbs_posting_ui);
		bbsposting_bt_sure=(Button)this.findViewById(R.id.bbsposting_btn_commit);
		bbsposting_et_title=(EditText)this.findViewById(R.id.bbsposting_edittext_title);
		bbsposting_et_content=(EditText)this.findViewById(R.id.bbsposting_edittext_content);
		bbsposting_bt_sure.setOnClickListener(new BBSPostingListener());
	
	}
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 1) {//当服务器返回给客户端的标记为1时，说明登陆成功
				startActivity(new Intent(DrugBBSPostingUI.this,MainUI.class));
				Log.i("bbsposting----------------->", "success");
				finish();
			}else {
				Toast.makeText(DrugBBSPostingUI.this, "发帖失败", Toast.LENGTH_SHORT)
				.show();
			}
		}
	};
	/*
	 * “确定”发送按钮监听事件
	 */
	public class BBSPostingListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			bbsPosting();		
		}		
	}
	/*
	 * 发帖至服务端
	 */
	public void bbsPosting(){
		new Thread(){
			public void run(){
				SimpleDateFormat formatter = new SimpleDateFormat ("yyyy/MM/dd HH:mm");
				Date curDate = new Date(System.currentTimeMillis());//获取当前时间
				String bbsTime = formatter.format(curDate);
				HttpClient client = new DefaultHttpClient();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				NameValuePair pair = new BasicNameValuePair("index", "6");
				NameValuePair pair1 = new BasicNameValuePair("bbsTitle", bbsposting_et_title.getText().toString());
				NameValuePair pair2 = new BasicNameValuePair("bbsTime", bbsTime);
				NameValuePair pair3 = new BasicNameValuePair("bbsContent",bbsposting_et_content.getText().toString());
				list.add(pair);
				list.add(pair1);
				list.add(pair2);
				list.add(pair3);
				try {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
					HttpPost post = new HttpPost(ConfigurationFile.serverAddress);
					post.setEntity(entity);
					HttpResponse response = client.execute(post);
					//是否有反馈
					if (response.getStatusLine().getStatusCode() == 200) {			
						InputStream in = response.getEntity().getContent();
						handler.sendEmptyMessage(in.read());//将服务器返回给客户端的标记直接传给handler
						in.close();
						Log.i("drugbbspostingui","bbsposting Create!");	 
					}else{
						Log.i("drugbbspostingui","bbsposting  lose");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}.start();
	}
}

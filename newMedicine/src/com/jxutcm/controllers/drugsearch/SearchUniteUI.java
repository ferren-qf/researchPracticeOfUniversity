package com.jxutcm.controllers.drugsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.jxutcm.business.util.ConfigurationFile;
import com.jxutcm.controllers.druglibrary.DrugLibraryInformationUI;
import com.jxutcm.controllers.start.R;

public class SearchUniteUI extends Activity{
	private EditText sn_et_chinaname ;
	private EditText sn_et_pingyinname;
	private EditText sn_et_englishname;
	private EditText sn_et_category;
	private EditText sn_et_preparetion;
	private EditText sn_et_storage;
	private EditText sn_et_description;
	private EditText sn_et_characters;
	private Button sn_btn_more;
	private Button sn_btn_searching;
//	private ListView sn_lv_showMap;
	
	private Bundle bundle;
	
	private String strChinaName;
	private String strPingyinName;
	private String strEnglishName;
	private String strCategory;
	private String strPreparetion;
	private String strStorage;
	private String strDescription;
	private String strCharacters;
	
	public void getTextTest(){
		if(sn_et_chinaname.getText()==null&&
			sn_et_pingyinname.getText()==null&&
			sn_et_englishname.getText()==null&&
			sn_et_category.getText()==null&&
			sn_et_preparetion.getText()==null&&
			sn_et_storage.getText()==null&&
			sn_et_description.getText()==null&&
			sn_et_characters.getText()==null){
			
		}else{
			strChinaName=sn_et_chinaname.getText().toString();
			strPingyinName=sn_et_pingyinname.getText().toString();
			strEnglishName=sn_et_englishname.getText().toString();
			strCategory=sn_et_category.getText().toString();
			strPreparetion=sn_et_preparetion.getText().toString();
			strStorage=sn_et_storage.getText().toString();
			strDescription=sn_et_description.getText().toString();
			strCharacters=sn_et_characters.getText().toString();
			getDrugOne();
		}
	}
	 private Handler handler =new Handler(){
			public void handleMessage(Message msg) {
				if(msg.what==1){
					startActivity(new Intent(SearchUniteUI.this,SearchMapUI.class).putExtras(bundle));
				}else if (msg.what == 0) {//无查询结果
					Toast.makeText(SearchUniteUI.this, "无查询结果", Toast.LENGTH_SHORT)
					.show();
				}
			}
		};
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.drugsearch_unite_ui);	
		initCo();
		initListener();
		
		
	}
	private void initCo(){
		sn_et_chinaname=(EditText)this.findViewById(R.id.searchunite_edittext_chinaname);
		sn_et_pingyinname=(EditText)this.findViewById(R.id.searchunite_edittext_pingyinname);
		sn_et_englishname=(EditText)this.findViewById(R.id.searchunite_edittext_englishname);
		sn_et_category=(EditText)this.findViewById(R.id.searchunite_edittext_category);
		sn_et_preparetion=(EditText)this.findViewById(R.id.searchunite_edittext_preparetion);
		sn_et_storage=(EditText)this.findViewById(R.id.searchunite_edittext_storage);
		sn_et_description=(EditText)this.findViewById(R.id.searchunite_edittext_description);
		sn_et_characters=(EditText)this.findViewById(R.id.searchunite_edittext_characters);
		sn_btn_more=(Button)this.findViewById(R.id.searchunite_btn_more);
		sn_btn_searching=(Button)this.findViewById(R.id.searchunite_btn_searching);
//		sn_lv_showMap=(ListView)this.findViewById(R.id);
	}
	private void initListener(){
		BtnListener btnListener=new BtnListener();
		sn_btn_more.setOnClickListener(btnListener);
		sn_btn_searching.setOnClickListener(btnListener);
		
	}
	/*
	 *从服务端得到联合查询得到的药品详细数据 
	 * 	
	 */
	public void getDrugOne(){
		new Thread(){
			public void run(){
				HttpClient client = new DefaultHttpClient();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				NameValuePair pair = new BasicNameValuePair("index", "13");
				NameValuePair pair1 = new BasicNameValuePair("strChinaName",strChinaName );
				NameValuePair pair2 = new BasicNameValuePair("strPingyinName",strPingyinName );
				NameValuePair pair3 = new BasicNameValuePair("strEnglishName",strEnglishName );
				NameValuePair pair4 = new BasicNameValuePair("strCategory",strCategory );
				NameValuePair pair5 = new BasicNameValuePair("strCharacters",strCharacters );
				NameValuePair pair6 = new BasicNameValuePair("strStorage",strStorage );
				NameValuePair pair7 = new BasicNameValuePair("strDescription",strDescription );			
				list.add(pair);
				list.add(pair1);
				list.add(pair2);
				list.add(pair3);
				list.add(pair4);
				list.add(pair5);
				list.add(pair6);
				list.add(pair7);
				try {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
					HttpPost post = new HttpPost(ConfigurationFile.serverAddress);
					post.setEntity(entity);
					HttpResponse response = client.execute(post);
					//是否有反馈
					if (response.getStatusLine().getStatusCode() == 200) {
						// 数据
						String responseData;
						InputStream in = response.getEntity().getContent();
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						responseData=br.readLine();
						if(responseData==null||responseData.equals("[\"null\",\"null\",\"null\",\"null\",\"null\"]")){
							handler.sendEmptyMessage(0);
						}else{
							//发送通知
							bundle =new Bundle();
							bundle.putString("strDrugMapData", responseData);
							handler.sendEmptyMessage(1);
							Log.i("drug","drugOneData Create!");
						}
							 
					}else{
						Log.i("drug","drugOneData lose!");
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

	
	/*
	 * 监听事件
	 */
	private class BtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.searchunite_btn_more:
				
				break;
			case R.id.searchunite_btn_searching:
				getTextTest();
				break;
			}
			
		}
		
	}
	
	
	
	
}

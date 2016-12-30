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

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.jxutcm.business.util.ConfigurationFile;
import com.jxutcm.controllers.start.LoginUI;
import com.jxutcm.controllers.start.R;
import com.jxutcm.controllers.druglibrary.DrugLibraryInformationUI;
import com.jxutcm.controllers.drugsearch.DrugInformationUI;
import com.jxutcm.model.ChemicalDrugs;
import com.jxutcm.model.Drug;
/**
 * 药品搜索Fragment界面
 * @author doyouknow
 *
 */

public class DrugSearchFragment extends Fragment implements OnClickListener{
	//标记
	public  int indexLenovo=1;
	//全局控件
	private Button search_btn_search;
	private Button search_btn_ea;
	private EditText search_ett_conent;
	private ListView search_lv_lenovos;
	private Bundle bundle;
	private boolean isRun;//是否运行
	private List<String> drugLenovos;//联想内容 String容器
	private String[] strDrugLenovos;//联想内容 String数组
	private Thread tdLenovo;
	private Thread tdGetDrugInformation;
	/*
	 * 新加 （让信息传到主线程中）
	 */
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
						if (msg.what == 1) {//当服务器返回给客户端的标记为3时，说明查询成功
			
							Log.i("search----------------->", "success");
							
						}else if(msg.what==2) {
							startActivity(new Intent(getActivity(),DrugLibraryInformationUI.class).putExtras(bundle));
						}else if(msg.what==3){
							showLvLenovo();
						}else if(msg.what==0){
							Toast.makeText(getActivity(), "无该药品信息", Toast.LENGTH_SHORT)
							.show();
						}
		} 
	};
	@Override  
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
			Bundle savedInstanceState) {  
		View drugSearchLayout = inflater.inflate(R.layout.drugsearch_layout,  
				container, false);  	
		initControls(drugSearchLayout);
		initListener();
		
		//search_lv_lenovos.setOnItemClickListener(listener);
		//search_lv_lenovos.setAdapter(new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_list_item_1, strDrugLenovos));
			
		return drugSearchLayout;  

	} 
	/*
	 * 初始化控件
	 */
	public void initControls(View drugSearchLayout){
		search_btn_search=(Button) drugSearchLayout.findViewById(R.id.drugsearch_btn2);
		search_ett_conent=(EditText) drugSearchLayout.findViewById(R.id.search_edittext_inputconent);
		search_btn_ea=(Button) drugSearchLayout.findViewById(R.id.drugsearch_btn1);
		search_lv_lenovos=(ListView) drugSearchLayout.findViewById(R.id.search_listview_lenovos);
	}
	/*
	 * 总监听事件（未处理）
	 */
	public void initListener(){
		search_btn_ea.setOnClickListener(this);
		search_btn_search.setOnClickListener(this);
		search_ett_conent.addTextChangedListener(watcher);
		//		new OnItemClickListener() {
		//			@Override
		//			public void onItemClick(AdapterView arg0, View arg1, int arg2,long arg3) {
		//				//点击后在标题上显示点击了第几行                    
		//				setTitle("你点击了第"+arg2+"行");
		//			}
		//		}
	}
	/*
	 * edittext监听事件
	 */
	private TextWatcher watcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
		}
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void afterTextChanged(Editable s) {	
			if(!search_ett_conent.getText().toString().isEmpty()&&search_ett_conent.getText().toString().length()>0){
				cTdLenovo();
			}
		}
	};
	/*
	 * 展示listView联想
	 */
	public void showLvLenovo(){	
		//将数据封装到ArrayAdapter 
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strDrugLenovos);
		search_lv_lenovos.setAdapter(arrayAdapter);//为ListView设置Adapter
	}
	/*
	 * 点击监听事件
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.drugsearch_btn2:
			cTdGetDrugInformation();		
			//			 Intent intent = new Intent();
			//			  intent.setActivity(this.getActivity(), DrugInformation.class);
			// startActivity(new Intent(LoginUI.this,RegisterUI.class));
			//			 startActivity(intent);
			//			 startActivity(new Intent(this.getActivity().this,DrugInformation.class)); //这里用getActivity().startActivity(intent);


			//直接从fragment中跳转

			//			 startActivityForResult(intent, 1);

			//这种写法接收返回的结果要在fragment中重写onActivityResult()方法;

			//从activity中跳转

			//			 getActivity().startActivityForResult(intent, 1);
			//这种写法要在Activity中重写onActivityResult()方法。

			break;
		case R.id.drugsearch_btn1:
			startActivity(new Intent(getActivity(),SearchUniteUI.class));			 
			break;
		default:  
			break;
		}
		//				Log.v("mylog","这是我的调试信息success");

	}
	/*
	 * 创建得到药品线程
	 */
	public void cTdGetDrugInformation(){
		tdGetDrugInformation=new Thread (){
			public void run() {
				HttpClient client = new DefaultHttpClient();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				NameValuePair pair = new BasicNameValuePair("index", "3");
				list.add(pair);
				NameValuePair pair1 = new BasicNameValuePair("drugCName", search_ett_conent.getText().toString());
				list.add(pair1);
				try {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
					HttpPost post = new HttpPost(ConfigurationFile.serverAddress);
					post.setEntity(entity);
					HttpResponse response = client.execute(post);
					if (response.getStatusLine().getStatusCode() == 200) {
						String responseData;
						InputStream in = response.getEntity().getContent();
						//							 handler.sendEmptyMessage(in.read());//将服务器返回给客户端的标记直接传给handler
						// 获得服务器响应的数据
						//Error:加了utf-8 就少了一个字符串 
						//BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						//	String retData = null;
						//while ((retData = br.readLine()) != null) {
						//	responseData += retData;
						//	}						
						// 数据
						responseData=br.readLine();
						if(responseData.equals("0")||responseData==null){
							handler.sendEmptyMessage(0);
						}else{
							//	responseData.length();							
							Gson gson = new Gson();
							List mList=gson.fromJson(responseData,new TypeToken<ArrayList>() {}.getType());
							responseData=gson.toJson(mList.get(1));	
							in.close();
							bundle=new Bundle();
							bundle.putString("strDrugOneData",responseData);
							bundle.putString("strDrugKind",""+mList.get(0));
							handler.sendEmptyMessage(2);
						}
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
		};
		tdGetDrugInformation.start();
	}
	
	/*
	 * 创建联想线程
	 */
	public void cTdLenovo(){		
		tdLenovo=new Thread (){
			public void run() {
				HttpClient client = new DefaultHttpClient();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				NameValuePair pair = new BasicNameValuePair("index", "4");
				list.add(pair);
				NameValuePair pair1 = new BasicNameValuePair("drugLenovo", search_ett_conent.getText().toString());
				list.add(pair1);	 
				try {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
					HttpPost post = new HttpPost(ConfigurationFile.serverAddress);
					post.setEntity(entity);
					HttpResponse response = client.execute(post);
					//是否有反馈
					if (response.getStatusLine().getStatusCode() == 200) {
						String responseData;
						InputStream in = response.getEntity().getContent();
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						// 数据
						responseData=br.readLine();
						Gson gson = new Gson();
						// List drug =new Drug();
						drugLenovos=gson.fromJson(responseData, List.class);
						strDrugLenovos=new String[drugLenovos.size()];					 
						for(int i=0;i<drugLenovos.size();i++){
							strDrugLenovos[i]=drugLenovos.get(i);
						} 
						handler.sendEmptyMessage(3);
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		};
		tdLenovo.start();
	}
	public DrugSearchFragment(){

	}
}


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
 * ҩƷ����Fragment����
 * @author doyouknow
 *
 */

public class DrugSearchFragment extends Fragment implements OnClickListener{
	//���
	public  int indexLenovo=1;
	//ȫ�ֿؼ�
	private Button search_btn_search;
	private Button search_btn_ea;
	private EditText search_ett_conent;
	private ListView search_lv_lenovos;
	private Bundle bundle;
	private boolean isRun;//�Ƿ�����
	private List<String> drugLenovos;//�������� String����
	private String[] strDrugLenovos;//�������� String����
	private Thread tdLenovo;
	private Thread tdGetDrugInformation;
	/*
	 * �¼� ������Ϣ�������߳��У�
	 */
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
						if (msg.what == 1) {//�����������ظ��ͻ��˵ı��Ϊ3ʱ��˵����ѯ�ɹ�
			
							Log.i("search----------------->", "success");
							
						}else if(msg.what==2) {
							startActivity(new Intent(getActivity(),DrugLibraryInformationUI.class).putExtras(bundle));
						}else if(msg.what==3){
							showLvLenovo();
						}else if(msg.what==0){
							Toast.makeText(getActivity(), "�޸�ҩƷ��Ϣ", Toast.LENGTH_SHORT)
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
	 * ��ʼ���ؼ�
	 */
	public void initControls(View drugSearchLayout){
		search_btn_search=(Button) drugSearchLayout.findViewById(R.id.drugsearch_btn2);
		search_ett_conent=(EditText) drugSearchLayout.findViewById(R.id.search_edittext_inputconent);
		search_btn_ea=(Button) drugSearchLayout.findViewById(R.id.drugsearch_btn1);
		search_lv_lenovos=(ListView) drugSearchLayout.findViewById(R.id.search_listview_lenovos);
	}
	/*
	 * �ܼ����¼���δ����
	 */
	public void initListener(){
		search_btn_ea.setOnClickListener(this);
		search_btn_search.setOnClickListener(this);
		search_ett_conent.addTextChangedListener(watcher);
		//		new OnItemClickListener() {
		//			@Override
		//			public void onItemClick(AdapterView arg0, View arg1, int arg2,long arg3) {
		//				//������ڱ�������ʾ����˵ڼ���                    
		//				setTitle("�����˵�"+arg2+"��");
		//			}
		//		}
	}
	/*
	 * edittext�����¼�
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
	 * չʾlistView����
	 */
	public void showLvLenovo(){	
		//�����ݷ�װ��ArrayAdapter 
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strDrugLenovos);
		search_lv_lenovos.setAdapter(arrayAdapter);//ΪListView����Adapter
	}
	/*
	 * ��������¼�
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
			//			 startActivity(new Intent(this.getActivity().this,DrugInformation.class)); //������getActivity().startActivity(intent);


			//ֱ�Ӵ�fragment����ת

			//			 startActivityForResult(intent, 1);

			//����д�����շ��صĽ��Ҫ��fragment����дonActivityResult()����;

			//��activity����ת

			//			 getActivity().startActivityForResult(intent, 1);
			//����д��Ҫ��Activity����дonActivityResult()������

			break;
		case R.id.drugsearch_btn1:
			startActivity(new Intent(getActivity(),SearchUniteUI.class));			 
			break;
		default:  
			break;
		}
		//				Log.v("mylog","�����ҵĵ�����Ϣsuccess");

	}
	/*
	 * �����õ�ҩƷ�߳�
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
						//							 handler.sendEmptyMessage(in.read());//�����������ظ��ͻ��˵ı��ֱ�Ӵ���handler
						// ��÷�������Ӧ������
						//Error:����utf-8 ������һ���ַ��� 
						//BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						//	String retData = null;
						//while ((retData = br.readLine()) != null) {
						//	responseData += retData;
						//	}						
						// ����
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
	 * ���������߳�
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
					//�Ƿ��з���
					if (response.getStatusLine().getStatusCode() == 200) {
						String responseData;
						InputStream in = response.getEntity().getContent();
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						// ����
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


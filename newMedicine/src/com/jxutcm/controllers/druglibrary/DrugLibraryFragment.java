package com.jxutcm.controllers.druglibrary;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jxutcm.business.util.ConfigurationFile;
import com.jxutcm.controllers.drugsearch.DrugInformationUI;
import com.jxutcm.controllers.start.R;
import com.jxutcm.model.Drug;


public class DrugLibraryFragment extends Fragment{
	private Button library_bt_1;  //��ѧҩƷ 1
	private Button library_bt_2;// ֲ����֬����ȡ�� 2
	private Button library_bt_3; //ҩ�ø��� 3
	 													//�����Ƽ� 4
	private Button library_bt_5;// �г�ҩ 5
	private Button library_bt_6; //ҩ�ļ���Ƭ 6
	private Bundle bundle;
	private Thread tdGetDrugListData;
	private int drugKindNum=0;
	
	private Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what==1){
				drugKindNum=1;
				cTdGetDrugListData("1");
			}else if(msg.what==2){
				drugKindNum=2;
				cTdGetDrugListData("2");
			}else if(msg.what==3){
				drugKindNum=3;
				cTdGetDrugListData("3");
			}else if(msg.what==5){
				drugKindNum=5;
				cTdGetDrugListData("5");
			}else if(msg.what==6){
				drugKindNum=6;
				cTdGetDrugListData("6");
			}
		}
	};
	
	@Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState) {  
	        View drugLibraryLayout = inflater.inflate(R.layout.druglibrary_layout2,  
	                container, false);  
	        initCo(drugLibraryLayout);
	        initListener(); 
	        return drugLibraryLayout;  
	    }  
	
	
	/*
	 * �����õ�ҩƷ�߳�,���ѵõ������ݴ���չʾ����
	 */
	public void cTdGetDrugListData(String strDrugKind){
		final String mStrDrugKind=strDrugKind;
		tdGetDrugListData=new Thread (){
			public void run() {
				HttpClient client = new DefaultHttpClient();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				NameValuePair pair = new BasicNameValuePair("index", "11");
				NameValuePair pair1 = new BasicNameValuePair("strDrugKind",mStrDrugKind);
				list.add(pair);
				list.add(pair1);
				try {
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
					HttpPost post = new HttpPost(ConfigurationFile.serverAddress);
					post.setEntity(entity);
					HttpResponse response = client.execute(post);
					if (response.getStatusLine().getStatusCode() == 200) {
						String responseData;
						InputStream in = response.getEntity().getContent();
						//handler.sendEmptyMessage(in.read());//�����������ظ��ͻ��˵ı��ֱ�Ӵ���handler
						// ��÷�������Ӧ������
						//Error:����utf-8 ������һ���ַ��� 
						//BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
						BufferedReader br = new BufferedReader(new InputStreamReader(in));		
						responseData=br.readLine();
						Gson gson = new Gson();
//						List drugList =new ArrayList();
//						drugList=gson.fromJson(responseData,new TypeToken<List>(){}.getType());
						in.close();
						bundle=new Bundle();
						bundle.putString("strDrug",responseData);
						bundle.putInt("drugKindNum",drugKindNum);
						//����չʾ����
						startActivity(new Intent(getActivity(),DrugLibraryListUI.class).putExtras(bundle));
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
		tdGetDrugListData.start();
	}
	public class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()){
			case R.id.library_btn_ChemicalDrugs:
				handler.sendEmptyMessage(1);
				break;
			case R.id.library_btn_VegetableFatsAndExtracts:
				handler.sendEmptyMessage(2);
				break;
			case R.id.library_btn_PharmaceuticalExcipients:
				handler.sendEmptyMessage(3);
				break;
			case R.id.library_btn_proprietarychinesemedicines:
				handler.sendEmptyMessage(5);
				break;
			case R.id.library_btn_HerbsAndPieces:
				handler.sendEmptyMessage(6);
				break;
			default:
				break;
			}
			
		}
		
	}
	/*
	 * ��ʼ���ؼ�
	 */
	public void  initCo(View drugLibraryLayout){
	    library_bt_1=(Button)drugLibraryLayout.findViewById(R.id.library_btn_ChemicalDrugs);
        library_bt_2=(Button)drugLibraryLayout.findViewById(R.id.library_btn_VegetableFatsAndExtracts);
        library_bt_3=(Button)drugLibraryLayout.findViewById(R.id.library_btn_PharmaceuticalExcipients);
        library_bt_5=(Button)drugLibraryLayout.findViewById(R.id.library_btn_proprietarychinesemedicines);
        library_bt_6=(Button)drugLibraryLayout.findViewById(R.id.library_btn_HerbsAndPieces);
	}
	/*
	 * ��ʼ������
	 */
	public void initListener(){
		  library_bt_1.setOnClickListener(new ButtonListener());
	        library_bt_2.setOnClickListener(new ButtonListener());
	        library_bt_3.setOnClickListener(new ButtonListener());
	        library_bt_5.setOnClickListener(new ButtonListener());
	        library_bt_6.setOnClickListener(new ButtonListener());
	}
}

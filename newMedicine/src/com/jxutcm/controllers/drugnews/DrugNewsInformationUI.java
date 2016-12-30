package com.jxutcm.controllers.drugnews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jxutcm.controllers.start.R;
import com.jxutcm.model.News;

public class DrugNewsInformationUI  extends Activity{
	private News news;
	private ListView search_lv_showinformation;
	private String[] strDrugInformation;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.druginformation_ui);	
		search_lv_showinformation=(ListView) findViewById(R.id.search_listview_showinformation);
		getNewsOneData();
		showNewsOneData();
	
	}
	
	
	/*
	 * �õ������������ݣ���������Ӧ��������Ϣ
	 */
	public void getNewsOneData(){
		Bundle bundle = this.getIntent().getExtras();  
		String jsonStrNews = bundle.getString("strNewsOneData");
		Gson gson = new Gson();
		news=gson.fromJson(jsonStrNews,News.class);
	}
	/*
	 * չʾ������Ϣ
	 */
	public void showNewsOneData(){
		strDrugInformation=new String[]{""+news.getNewsTitle(),""+news.getNewsTime(),""+news.getNewsContent()};
		//�����ݷ�װ��ArrayAdapter 
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strDrugInformation);
		search_lv_showinformation.setAdapter(arrayAdapter);//ΪListView����Adapter
		
	}
}

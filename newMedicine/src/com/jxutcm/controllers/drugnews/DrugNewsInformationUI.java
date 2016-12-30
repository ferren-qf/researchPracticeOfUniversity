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
	 * 得到传过来的数据，解析成相应的新闻信息
	 */
	public void getNewsOneData(){
		Bundle bundle = this.getIntent().getExtras();  
		String jsonStrNews = bundle.getString("strNewsOneData");
		Gson gson = new Gson();
		news=gson.fromJson(jsonStrNews,News.class);
	}
	/*
	 * 展示新闻信息
	 */
	public void showNewsOneData(){
		strDrugInformation=new String[]{""+news.getNewsTitle(),""+news.getNewsTime(),""+news.getNewsContent()};
		//将数据封装到ArrayAdapter 
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strDrugInformation);
		search_lv_showinformation.setAdapter(arrayAdapter);//为ListView设置Adapter
		
	}
}

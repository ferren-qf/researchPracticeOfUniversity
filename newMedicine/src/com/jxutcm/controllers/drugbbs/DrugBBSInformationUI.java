package com.jxutcm.controllers.drugbbs;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import com.jxutcm.controllers.start.R;
import com.jxutcm.model.BBS;

public class DrugBBSInformationUI extends Activity{
	private BBS bbs;
	private ListView search_lv_showinformation;
	private String[] strInformation;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.druginformation_ui);	
		search_lv_showinformation=(ListView) findViewById(R.id.search_listview_showinformation);
		getOneData();
		showOneData();
	}
	/*
	 * �õ������������ݣ���������Ӧ��������Ϣ
	 */
	public void getOneData(){
		Bundle bundle = this.getIntent().getExtras();  
		String jsonStr = bundle.getString("bbs");
		Gson gson = new Gson();
		bbs=gson.fromJson(jsonStr,BBS.class);
	}
	/*
	 * չʾ������Ϣ
	 */
	public void showOneData(){
		strInformation=new String[]{bbs.getBbsTitle()," "+bbs.getBbsTime(),""+bbs.getBbsContent()};
		//�����ݷ�װ��ArrayAdapter 
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInformation);
		search_lv_showinformation.setAdapter(arrayAdapter);//ΪListView����Adapter
	}
}

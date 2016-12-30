package com.jxutcm.controllers.drugsearch;



import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jxutcm.controllers.start.R;
import com.jxutcm.model.ChemicalDrugs;
import com.jxutcm.model.Drug;



public class DrugInformationUI extends Activity{

	private List<Drug> list;//Ҫ��ʾ���б�
	private ListView search_lv_showinformation;
	private String[] strDrugInformation;

	private boolean isloading = false;//�ж��Ƿ����ڼ�����
	
	Drug drug;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.druginformation_ui);	
		search_lv_showinformation=(ListView) findViewById(R.id.search_listview_showinformation);
		getDrugData();
		showDrugData();
	
	}
	/*
	 * �õ������������ݣ���������Ӧ��ҩƷ��Ϣ
	 */
	public void getDrugData(){
		Bundle bundle = this.getIntent().getExtras();  
		String jsonStrDrug = bundle.getString("strDrug");
		Gson gson = new Gson();
		drug=gson.fromJson(jsonStrDrug, ChemicalDrugs.class);
	}
	
	/*
	 * չʾҩƷ��Ϣ
	 */
	public void showDrugData(){
		Log.i("drugCName","nulllllll");	
		//�����ݷ�װ��ArrayAdapter 
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strDrugInformation);
		search_lv_showinformation.setAdapter(arrayAdapter);//ΪListView����Adapter
		
	}
}

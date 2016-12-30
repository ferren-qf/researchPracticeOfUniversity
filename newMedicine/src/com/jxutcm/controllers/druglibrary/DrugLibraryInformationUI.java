package com.jxutcm.controllers.druglibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jxutcm.controllers.start.R;
import com.jxutcm.model.ChemicalDrugs;
import com.jxutcm.model.HerbsAndPieces;
import com.jxutcm.model.PharmaceuticalExcipients;
import com.jxutcm.model.ProprietaryChineseMedicines;
import com.jxutcm.model.VegetableFatsAndExtracts;

public class DrugLibraryInformationUI extends Activity{
	private Object drug;
	private ListView search_lv_showinformation;
	private String[] strDrugInformation;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.druginformation_ui);	
		search_lv_showinformation=(ListView) findViewById(R.id.search_listview_showinformation);
		getDrugOneData();
		showDrugOneData();
	}
	/*
	 * 得到传过来的数据，解析成相应的新闻信息
	 */
	public void getDrugOneData(){
		Bundle bundle = this.getIntent().getExtras();  
		String jsonStrDrug = bundle.getString("strDrugOneData");
		String strKind = bundle.getString("strDrugKind");
		Gson gson = new Gson();
		if(strKind.equals("chemicalDrugs")){
			drug=gson.fromJson(jsonStrDrug,ChemicalDrugs.class);			
		}else if(strKind.equals("herbsAndPieces")){
			drug=gson.fromJson(jsonStrDrug,HerbsAndPieces.class);
		}else if(strKind.equals("pharmaceuticalExcipients")){
			drug=gson.fromJson(jsonStrDrug,PharmaceuticalExcipients.class);
		}else if(strKind.equals("proprietaryChineseMedicines")){
			drug=gson.fromJson(jsonStrDrug,ProprietaryChineseMedicines.class);
		}else if(strKind.equals("vegetableFatsAndExtracts")){
			drug=gson.fromJson(jsonStrDrug,VegetableFatsAndExtracts.class);
		}
	}
	/*
	 * 展示新闻信息
	 */
	public void showDrugOneData(){
		
		ArrayAdapter<String> arrayAdapter=null;
		if(drug instanceof ChemicalDrugs){
			ChemicalDrugs mDrug=(ChemicalDrugs) drug;
			strDrugInformation=new String[]{
					""+mDrug.getDrugName(),
					""+mDrug.getDrugPinyinName(),
					""+mDrug.getDrugEnglishName(),
					""+mDrug.getDrugCharacters(),
					""+mDrug.getDrugDescription()};
		}else if(drug instanceof HerbsAndPieces){
			HerbsAndPieces mDrug=(HerbsAndPieces)drug;
			strDrugInformation=new String[]{
					""+mDrug.getHerbsName(),
					""+mDrug.getHerbsPinyinName(),
					""+mDrug.getHerbsEnglishName(),
					""+mDrug.getHerbsNotices(),
					""+mDrug.getHerbsDosage(),
					""+mDrug.getHerbsDescription(),
					""+mDrug.getHerbsCharacters(),
					""+mDrug.getHerbsFunctions()};
		}else if(drug instanceof PharmaceuticalExcipients){
			PharmaceuticalExcipients mDrug=(PharmaceuticalExcipients) drug;
			strDrugInformation=new String[]{
					""+mDrug.getExcipientName(),
					""+mDrug.getExcipientPinyinName(),
					""+mDrug.getExcipientEnglishName(),
					""+mDrug.getExcipientCharacters(),
					""+mDrug.getExcipientDescription(),
					""+mDrug.getExcipientStorage()};
		}else if(drug instanceof ProprietaryChineseMedicines){
			ProprietaryChineseMedicines mDrug=(ProprietaryChineseMedicines) drug;
			strDrugInformation=new String[]{
					""+mDrug.getMedicineName(),
					""+mDrug.getMedicinePinyinName(),
					""+mDrug.getMedicineDescription(),
					""+mDrug.getMedicinePrescriptions(),
					""+mDrug.getMedicineMethod(),
					""+mDrug.getMedicineCharacters(),
					""+mDrug.getMedicineFunctions(),
					""+mDrug.getMedicineDosage(),
					""+mDrug.getMedicineStandard(),
					""+mDrug.getMedicineStorage()};
		}else if(drug instanceof VegetableFatsAndExtracts){
			VegetableFatsAndExtracts mDrug=(VegetableFatsAndExtracts) drug;
			strDrugInformation=new String[]{
					""+mDrug.getPlantsName(),
					""+mDrug.getPlantsPinyinName(),
					""+mDrug.getPlantsEnglishName(),
					""+mDrug.getPlantsDescription(),
					""+mDrug.getPlantsMethod(),
					""+mDrug.getPlantsCharacters(),
					""+mDrug.getPlantsPreparation(),
					""+mDrug.getPlantsStorage()};
		}
		//将数据封装到ArrayAdapter 
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strDrugInformation);
		search_lv_showinformation.setAdapter(arrayAdapter);//为ListView设置Adapter
		
	}
}

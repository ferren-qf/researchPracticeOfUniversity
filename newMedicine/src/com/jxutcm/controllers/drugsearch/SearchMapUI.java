package com.jxutcm.controllers.drugsearch;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jxutcm.controllers.druglibrary.DrugLibraryInformationUI;
import com.jxutcm.controllers.start.LoginUI;
import com.jxutcm.controllers.start.R;
import com.jxutcm.model.ChemicalDrugs;
import com.jxutcm.model.HerbsAndPieces;
import com.jxutcm.model.PharmaceuticalExcipients;
import com.jxutcm.model.ProprietaryChineseMedicines;
import com.jxutcm.model.VegetableFatsAndExtracts;

public class SearchMapUI extends Activity{
	private ListView search_lv_showinformation;
	private LayoutInflater mInflater;
	private String[] strDrugList;
	private List drugList;
	
	private List<ChemicalDrugs> chemicalDrugsList;
	private List<HerbsAndPieces> herbsAndPiecesList;
	private List<PharmaceuticalExcipients> pharmaceuticalExcipientsList;
	private List<ProprietaryChineseMedicines> proprietaryChineseMedicinesList;
	private List<VegetableFatsAndExtracts> vegetableFatsAndExtractsList;
//	private List<ChemicalDrugs>
	/*
	 * 查询得到的各类药品String数组
	 */
	private String[] strChemicalDrugs;
	private String[] strHerbsAndPieces;
	private String[] strPharmaceuticalExcipients;
	private String[] strProprietaryChineseMedicines;
	private String[] strVegetableFatsAndExtracts;
	private String[] strAll;	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
						if (msg.what == 0) {//无查询结果
							Toast.makeText(SearchMapUI.this, "无查询结果", Toast.LENGTH_SHORT)
							.show();
						}
		} 

	};
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.druginformation_ui);
		search_lv_showinformation=(ListView) findViewById(R.id.search_listview_showinformation);
		getDrugMapData();
		showDrugOneData();
	}
	/*
	 * 得到传过来的数据，解析成相应的信息
	 */
	
	public void getDrugMapData(){
		Bundle bundle = this.getIntent().getExtras();  
		String jsonStrNews = bundle.getString("strDrugMapData");	
		Gson gson = new Gson();
		strDrugList=gson.fromJson(jsonStrNews,new TypeToken<String[]>(){}.getType() );
		String str0=strDrugList[0];
		String str1=strDrugList[1];
		String str2=strDrugList[2];
		String str3=strDrugList[3];
		String str4=strDrugList[4];
		chemicalDrugsList=gson.fromJson(str0,new TypeToken<List<ChemicalDrugs>>(){}.getType());
		herbsAndPiecesList=gson.fromJson(str1,new TypeToken<List<HerbsAndPieces>>(){}.getType());
		pharmaceuticalExcipientsList=gson.fromJson(str2,new TypeToken<List<PharmaceuticalExcipients>>(){}.getType());
		proprietaryChineseMedicinesList=gson.fromJson(str3,new TypeToken<List<ProprietaryChineseMedicines>>(){}.getType());
		vegetableFatsAndExtractsList=gson.fromJson(str4,new TypeToken<List<VegetableFatsAndExtracts>>(){}.getType());
//		gson.
//		drugMap=gson.fromJson(jsonStrNews,new TypeToken<Map<String,List>>(){}.getType() );
//		if(drugMap.get("chemicalDrugs").get(0) instanceof ChemicalDrugs){
//			System.out.println();
//		}
		
		
	}
	/*
	 * 展示信息
	 */
	public void showDrugOneData(){
		int num0=0;
		int num1=0;
		int num2=0;
		int num3=0;
		int num4=0;
		List<String> strList=new ArrayList<String>();
		if(chemicalDrugsList!=null){
			num0=chemicalDrugsList.size();
			strChemicalDrugs=new String[num0];
			for(int i=0;i<num0;i++){		
//				strChemicalDrugs[i]=chemicalDrugsList.get(i).getDrugName();
				strList.add(chemicalDrugsList.get(i).getDrugName());
			}
			
		}
		if(herbsAndPiecesList!=null){
			num1=herbsAndPiecesList.size();
			strHerbsAndPieces=new String[num1];
			for(int i=0;i<num1;i++){		
//				strHerbsAndPieces[i]=herbsAndPiecesList.get(i).getHerbsName();
				strList.add(herbsAndPiecesList.get(i).getHerbsName());
			}
			
		}
		if(pharmaceuticalExcipientsList!=null){
			num2=pharmaceuticalExcipientsList.size();
			strPharmaceuticalExcipients=new String[num2];
			for(int i=0;i<num2;i++){		
//				strPharmaceuticalExcipients[i]=pharmaceuticalExcipientsList.get(i).getExcipientName();
				strList.add(pharmaceuticalExcipientsList.get(i).getExcipientName());
			}
			
		}
		if(proprietaryChineseMedicinesList!=null){
			num3=proprietaryChineseMedicinesList.size();
			strProprietaryChineseMedicines=new String[num3];
			for(int i=0;i<num3;i++){		
//				strProprietaryChineseMedicines[i]=proprietaryChineseMedicinesList.get(i).getMedicineName();
				strList.add(proprietaryChineseMedicinesList.get(i).getMedicineName());
			}
			
		}
		if(vegetableFatsAndExtractsList!=null){
			num4=vegetableFatsAndExtractsList.size();
			strVegetableFatsAndExtracts=new String[num4];
			for(int i=0;i<num4;i++){		
//				strVegetableFatsAndExtracts[i]=vegetableFatsAndExtractsList.get(i).getPlantsName();
				strList.add(vegetableFatsAndExtractsList.get(i).getPlantsName());
			}
			
		}
		strAll=new String[strList.size()];
		for(int i=0;i<strList.size();i++){
			strAll[i]=strList.get(i);
		}
		if(strList.size()==0){
			handler.sendEmptyMessage(0);
		}else{			
			//将数据封装到ArrayAdapter 
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strAll);
			search_lv_showinformation.setAdapter(arrayAdapter);//为ListView设置Adapter	
		}

	}
	/*
	 *ListView适配器
	 */
    public class Adapter extends BaseAdapter {  
    	private List drugListData;
    	private int drugKindNum;
    	public Adapter(){

    	}  
    	public Adapter(Context context,List drugListData) {  
    		mInflater=LayoutInflater.from(context);
    		this.drugListData=drugListData;
    	}
    	public Adapter(Context context,List drugListData,int drugKindNum){
    		this(context,drugListData);
    		this.drugKindNum=drugKindNum;
    	}
        @Override  
        public boolean areAllItemsEnabled() {  
            return super.areAllItemsEnabled();  
        }  
             
        @Override  
        public int getCount() {  
            return drugListData.size();  
        }  
        @Override  
        public String[] getItem(int position) { 
        	switch (drugKindNum){
        	case 1:
        		ChemicalDrugs drug1= chemicalDrugsList.get(position);        		
        		//对应数据库中的表名
        		String[] str1={"chemicalDrugs",drug1.getDrugId()};
        	}   	
        	return null;
        }  
          
        @Override  
        public long getItemId(int position) {  
            return position;  
        }  
  
        @Override  
        public View getView( final int position, View convertView,  
        		ViewGroup parent) {
        	ViewHolder holder = new ViewHolder();       	
        	
        	if (convertView == null) {			
        		holder = new ViewHolder();  
        		convertView = mInflater.inflate(R.layout.list_item_librarylist, null);  
        		holder.title = (TextView) convertView.findViewById(R.id.list_textview_name);  
        		holder.time = (TextView) convertView.findViewById(R.id.list_textview_pingyinname);  
        		convertView.setTag(holder);

        	} else {  
        		holder = (ViewHolder) convertView.getTag();  
        	} 
//        	BBS bbs = bbsListData.get(position); 
//        	newsListListener=new NewsListListener(position);
        	switch(drugKindNum){
        	case 1:
        		final ChemicalDrugs  drug1=(ChemicalDrugs) drugList.get(position);
        		// 进行数据设置  
        		holder.title.setText(drug1.getDrugName());  
        		holder.time.setText(drug1.getDrugPinyinName());  
        		//设置点击监听事件
        		//holder.setOnClickListener(newsListListener);
        	break;
        	case 2:
        	final VegetableFatsAndExtracts drug2=(VegetableFatsAndExtracts)drugList.get(position); 
        		holder.title.setText(drug2.getPlantsName());  
        		holder.time.setText(drug2.getPlantsPinyinName()); 
        	break;
        	case 3:
        		final PharmaceuticalExcipients drug3=(PharmaceuticalExcipients)drugList.get(position); 
        		holder.title.setText(drug3.getExcipientName());  
        		holder.time.setText(drug3.getExcipientPinyinName());
        		break;
        	case 4:
        		//生物制剂
        	break;
        	case 5:
        		final ProprietaryChineseMedicines drug5=(ProprietaryChineseMedicines)drugList.get(position); 
        		holder.title.setText(drug5.getMedicineName());  
        		holder.time.setText(drug5.getMedicinePinyinName());
        		break;
        	case 6:
        		final HerbsAndPieces drug6=(HerbsAndPieces)drugList.get(position); 
        		holder.title.setText(drug6.getHerbsName());  
        		holder.time.setText(drug6.getHerbsPinyinName());
        		break;
        	
        	}
        	
        	return convertView;  

        }


    }
    /*
     * 内部类优化作用?
     */
	public final class ViewHolder{
//		public TextView id;
		public TextView title;
//		public TextView name;
//		public TextView address;
		public TextView time;
//		public ImageView img;
	}
	
	
}

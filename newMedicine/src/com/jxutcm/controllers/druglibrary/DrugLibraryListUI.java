package com.jxutcm.controllers.druglibrary;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jxutcm.business.util.ConfigurationFile;
import com.jxutcm.controllers.drugsearch.DrugInformationUI;
import com.jxutcm.controllers.start.R;
import com.jxutcm.model.ChemicalDrugs;
import com.jxutcm.model.HerbsAndPieces;
import com.jxutcm.model.News;
import com.jxutcm.model.PharmaceuticalExcipients;
import com.jxutcm.model.ProprietaryChineseMedicines;
import com.jxutcm.model.VegetableFatsAndExtracts;

public class DrugLibraryListUI extends Activity {
	private ListView library_lv_druglist;
	private List drugList;
    private LayoutInflater mInflater;
    private Bundle bundle;
	
    
    private Handler handler =new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what==2){
				startActivity(new Intent(DrugLibraryListUI.this,DrugLibraryInformationUI.class).putExtras(bundle));
			}
		}
	};
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.druglibrarylist_ui);	
		initCo();
		getDrugList();
		
	}
	/*
	 *从服务端得到一种药品详细数据 
	 * 	
	 */
	public void getDrugOne(String[] drugKindAndId){
		final String mDrugKind=drugKindAndId[0];
		final String mId=drugKindAndId[1];
		new Thread(){
			public void run(){
				HttpClient client = new DefaultHttpClient();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				NameValuePair pair = new BasicNameValuePair("index", "12");
				NameValuePair pair1 = new BasicNameValuePair("strDrugKind",mDrugKind );
				NameValuePair pair2 = new BasicNameValuePair("strDrugId",mId );
				list.add(pair);
				list.add(pair1);
				list.add(pair2);
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
						//发送通知
						bundle =new Bundle();
						bundle.putString("strDrugOneData", responseData);
						bundle.putString("strDrugKind",mDrugKind);
						handler.sendEmptyMessage(2);
						Log.i("drug","drugOneData Create!");	 
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
	 * 初始化化控件
	 */
	public void initCo(){
		library_lv_druglist=(ListView)this.findViewById(R.id.library_listview_druglist);
	}
	/*
	 * 初始化监听事件
	 */
	public void initListener(List drugList,int drugKindNum){
		//adapter 已在getDrugList中初始化完成
		library_lv_druglist.setAdapter(new Adapter(this,drugList,drugKindNum));
		library_lv_druglist.setOnItemClickListener(new ItemListener());
	}
	/*
	 * 得到传来的drugList json数据，并解析成相应drugList，添加initListener()监听
	 */
	public void getDrugList(){
		//化学药品 1
		// 植物油脂及提取物 2
		//药用辅料 3
		//生物制剂 4
		// 中成药 5
		//药材及饮片 6
		Bundle bundle = this.getIntent().getExtras(); 
		int drugKindNum=bundle.getInt("drugKindNum");
		String jsonStrDrug = bundle.getString("strDrug");
		Gson gson = new Gson();
		switch(drugKindNum){
		case 1:
			drugList=gson.fromJson(jsonStrDrug, new TypeToken<List<ChemicalDrugs>>(){}.getType());
			break;
		case 2:
			drugList=gson.fromJson(jsonStrDrug, new TypeToken<List<VegetableFatsAndExtracts>>(){}.getType());
			break;
		case 3:
			drugList=gson.fromJson(jsonStrDrug, new TypeToken<List<PharmaceuticalExcipients>>(){}.getType());
			break;
		case 4:
			break;
		case 5:
			drugList=gson.fromJson(jsonStrDrug, new TypeToken<List<ProprietaryChineseMedicines>>(){}.getType());
			break;
		case 6:			
			drugList=gson.fromJson(jsonStrDrug, new TypeToken<List<HerbsAndPieces>>(){}.getType());
			break;
		case 0:			
			break;
		default:
			break;
		}
		initListener(drugList,drugKindNum);
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
        		ChemicalDrugs drug1= (ChemicalDrugs)drugListData.get(position);        		
        		//对应数据库中的表名
        		String[] str1={"chemicalDrugs",drug1.getDrugId()};
        		return str1;
        	case 2:
        		VegetableFatsAndExtracts drug2=(VegetableFatsAndExtracts)drugList.get(position);
        		String[] str2={"vegetableFatsAndExtracts",drug2.getPlantsId()};
        		return str2;
        	case 3:
        		PharmaceuticalExcipients drug3=(PharmaceuticalExcipients)drugList.get(position);
        		String[] str3={"pharmaceuticalExcipients",drug3.getExcipientId()};
        		return str3;
        	case 4:
        		//生物药剂
        		break;
        	case 5:
        		ProprietaryChineseMedicines drug5=(ProprietaryChineseMedicines)drugList.get(position);
        		String[] str5={"proprietaryChineseMedicines",drug5.getMedicineId()};
        		return str5;
        	case 6:
        		HerbsAndPieces drug6=(HerbsAndPieces)drugList.get(position);
        		String[] str6={"herbsAndPieces",drug6.getHerbsId()};
        		return str6;
        	default:
        		break;
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
	/*
	 * 增加listView 中item的监听
	 */
	private class ItemListener implements OnItemClickListener{
		private int drugKindNum;
		public ItemListener(){}
		public ItemListener(int drugKindNum){
			this.drugKindNum=drugKindNum;
			
		}
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			String[] strDrugKindAndId=(String[]) library_lv_druglist.getItemAtPosition(position);
			getDrugOne(strDrugKindAndId);
		}
		
	}
	
	
	
}

package com.jxutcm.controllers.drugnews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
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
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jxutcm.business.util.ConfigurationFile;
import com.jxutcm.controllers.drugsearch.DrugInformationUI;
import com.jxutcm.controllers.start.R;
import com.jxutcm.model.News;

public class DrugNewsFragment extends Fragment{
		private ListView news_lv_showinterest;//listView����
		private ListView news_lv_showhealth;
		private Button news_bt_interest;
		private Button news_bt_health; 
		private LinearLayout ll_loading;//������ʾ���ڼ��ص�progress
		private List<News> newsListData;//��������
		private List<News> newsInterestListData;//Ȥ�����ݰ�
		private List<News> newsHealthListData;//����֪ʶ���ݰ�
		
		private LayoutInflater mInflater;
		private Adapter newsInterestAdapter;
		private Adapter newsHealthAdapter;
	
		private Bundle bundle;
		private boolean isScrolling = false;//�Ƿ����ڹ���
		private boolean isloading = false;//�ж��Ƿ����ڼ�����
		private boolean isGetNewListData=false;
		
		private Handler handler = new Handler(){
			public void handleMessage(Message msg) {
				if (msg.what == 1) {// ���ݰ���ʼ���ɹ�							
					newsInterestListData=new ArrayList<News>();
					newsHealthListData=new ArrayList<News>();
					for(News news:newsListData){
						Log.i("whatKind",news.getNewsKind());
							if(news.getNewsKind().equals("1")){
								newsInterestListData.add(news);
							}else{
								newsHealthListData.add(news);
							}
						}			
						newsInterestAdapter=new Adapter(getActivity(),newsInterestListData);
						newsHealthAdapter=new Adapter(getActivity(),newsHealthListData);
						news_lv_showhealth.setVisibility(View.GONE);
						news_lv_showinterest.setAdapter(newsInterestAdapter);
						news_lv_showhealth.setAdapter(newsHealthAdapter);
						isGetNewListData=true;
				}else if(msg.what==2&&isGetNewListData==true){//չʾȤ�Ž���
					news_lv_showinterest.setVisibility(View.VISIBLE);
					news_lv_showhealth.setVisibility(View.GONE);
				}else if(msg.what==3&&isGetNewListData==true){///չʾ����֪ʶ����
					news_lv_showhealth.setVisibility(View.VISIBLE);
					news_lv_showinterest.setVisibility(View.GONE);
				}else if(msg.what==4){//���͵������Ÿ���ϸչʾ����
					startActivity(new Intent(getActivity(),DrugNewsInformationUI.class).putExtras(bundle));
				}   
			};
		};
		
		@Override  
		public View onCreateView(LayoutInflater inflater, ViewGroup container,  
				Bundle savedInstanceState) {  
			View drugNewsLayout = inflater.inflate(R.layout.drugnews_layout,  
					container, false);  
			initCo(drugNewsLayout);
			initListener();
			getNewsListData();
			return drugNewsLayout;  
		}
		public void initCo(View drugNewsLayout){
			news_lv_showinterest=(ListView) drugNewsLayout.findViewById(R.id.news_listview_showinterest);
			news_lv_showhealth=(ListView) drugNewsLayout.findViewById(R.id.news_listview_showhealth);
			news_bt_health=(Button)drugNewsLayout.findViewById(R.id.news_button_health);
			news_bt_interest=(Button)drugNewsLayout.findViewById(R.id.news_button_interest);
		}
		public void initListener(){
			news_bt_health.setOnClickListener(new NewsButtonListener());
			news_bt_interest.setOnClickListener(new NewsButtonListener());
			news_lv_showinterest.setOnItemClickListener(new ItemListener("interest"));
			news_lv_showhealth.setOnItemClickListener(new ItemListener("health"));
		}
		/*
		 * �õ���������ŵ�����
		 */
		public void getNewsListData(){
			new Thread(){
				public void run(){
					HttpClient client = new DefaultHttpClient();
					List<NameValuePair> list = new ArrayList<NameValuePair>();
					NameValuePair pair = new BasicNameValuePair("index", "7");
					list.add(pair);
					//	NameValuePair pair1 = new BasicNameValuePair("drugLenovo", search_ett_conent.getText().toString());
					//	list.add(pair1);	 
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
							newsListData=gson.fromJson(responseData, new TypeToken<List<News>>(){}.getType());
							handler.sendEmptyMessage(1);
							Log.i("news","bbsListData Create!");	 
						}else{
							Log.i("news","bbsListData lose");
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
		 * �õ�����������ϸ����
		 */
		public void getNewsOneData(String title){
			final String mtitle=title;
			new Thread(){
				public void run(){
					HttpClient client = new DefaultHttpClient();
					List<NameValuePair> list = new ArrayList<NameValuePair>();
					NameValuePair pair = new BasicNameValuePair("index", "8");
					NameValuePair pair1 = new BasicNameValuePair("newsTitle",mtitle );
					list.add(pair);
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
										
//							newsOneData=gson.fromJson(responseData, News.class);
							//����֪ͨ
							bundle =new Bundle();
							bundle.putString("strNewsOneData", responseData);
							handler.sendEmptyMessage(4);
//							Log.i("news","bbsOneData Create!");	 
						}else{
//							Log.i("news","bbsOneData lose");
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
		 * button����
		 */
		public class NewsButtonListener implements OnClickListener{

			@Override
			public void onClick(View v) {
				if(v==news_bt_interest){
					handler.sendEmptyMessage(2);
				}else if(v==news_bt_health){
					handler.sendEmptyMessage(3);
				}
				
				
			}
			
		}
		/*
		 *ListView������
		 */
	    public class Adapter extends BaseAdapter {  
	    	private List<News> newsListData;
	    	
	    	public Adapter(){

	    	}  
	    	  public Adapter(Context context,List<News> newsListData) {  
		        	mInflater=LayoutInflater.from(context);
		        	this.newsListData=newsListData;
		        }
	        @Override  
	        public boolean areAllItemsEnabled() {  
	            return super.areAllItemsEnabled();  
	        }  
	          
	        
	        @Override  
	        public int getCount() {  
	            return newsListData.size();  
	        }  
	        @Override  
	        public String getItem(int position) {  
	        	
	        	return newsListData.get(position).getNewsTitle();  
	        }  
	          
	        @Override  
	        public long getItemId(int position) {  
	            return position;  
	        }  
	  
	        @Override  
	        public View getView( final int position, View convertView,  
	        		ViewGroup parent) {

//	        	BBS bbs = bbsListData.get(position); 
//	        	newsListListener=new NewsListListener(position);
	        	final News news=newsListData.get(position);
	        	ViewHolder holder = new ViewHolder(); 
	        	
	        	if (convertView == null) {			
	        		holder = new ViewHolder();  
	        		convertView = mInflater.inflate(R.layout.list_item_bbs, null);  
	        		holder.title = (TextView) convertView.findViewById(R.id.list_main_txt_title);  
	        		holder.time = (TextView) convertView.findViewById(R.id.list_main_txt_time);  
	        		convertView.setTag(holder);

	        	} else {  
	        		holder = (ViewHolder) convertView.getTag();  
	        	} 
	        	// ������������  
	        	holder.title.setText(news.getNewsTitle());  
	        	holder.time.setText(news.getNewsTime());  
//	        	holder.setOnClickListener(newsListListener);
	        	return convertView;  

	        }


	    }
	    //�ڲ����Ż�����?
		public final class ViewHolder{
//			public TextView id;
			public TextView title;
//			public TextView name;
//			public TextView address;
			public TextView time;
//			public ImageView img;
		}
	    /*
	     * ���item�����¼�
	     */
		private class ItemListener implements OnItemClickListener{
			String strNewsKind;
			String title;
			public ItemListener(String strNewsKind){
				this.strNewsKind=strNewsKind;
			}
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(strNewsKind.equals("interest")){
					title=(String) news_lv_showinterest.getItemAtPosition(position);
				}else{
					title=(String) news_lv_showhealth.getItemAtPosition(position);
				}
				//���õ�����������
				getNewsOneData(title);
				
			}
	    	
	    }
		


}

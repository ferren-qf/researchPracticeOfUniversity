package com.jxutcm.controllers.drugbbs;


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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jxutcm.business.util.ConfigurationFile;
import com.jxutcm.controllers.drugbbs.DrugBBSPostingUI;
import com.jxutcm.controllers.drugsearch.DrugInformationUI;
import com.jxutcm.controllers.start.LoginUI;
import com.jxutcm.controllers.start.MainUI;
import com.jxutcm.controllers.start.R;
import com.jxutcm.model.BBS;
public class DrugBBSFragment extends Fragment{
	ListView bbs_lv_showbbsdata; 
	 /* 集合数据 */  
    public List<BBS>  bbsListData;
    private Button bbs_bt_posting;
    private BBS bbsListDataOne;
    private Bundle bundle;
    private LayoutInflater mInflater;
    private Thread tdGetBBSListData;    
    private BBSListTitleListener bbsListTitleListener;
    private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if (msg.what == 1) {//等于1是说明接收到了数据初始化成功消息			
				bbs_lv_showbbsdata.setAdapter(new Adapter(getActivity()));	
			}else if(msg.what==2){//展示信息
				startActivity(new Intent(getActivity(),DrugBBSInformationUI.class).putExtras(bundle));
			}else {
				Toast.makeText(getActivity(), "服务器正在维护", Toast.LENGTH_SHORT)
				.show();
			} 
		};
	};
	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {  
        View drugBBSLayout = inflater.inflate(R.layout.drugbbs_layout,  
                container, false);  
        bbs_lv_showbbsdata = (ListView) drugBBSLayout.findViewById(R.id.bbs_listview_showbbsdata);
        bbs_bt_posting=(Button)drugBBSLayout.findViewById(R.id.bbs_button_posting);
        bbs_bt_posting.setOnClickListener(new BBSPostingListener());
        cTdGetBBSListData();
        return drugBBSLayout;  
    } 
	
	
	
	/*
	 * 发帖按钮监听事件
	 */
	public class BBSPostingListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			startActivity(new Intent(getActivity(),DrugBBSPostingUI.class));	
		}
		
	}
	
	
	/*
	 * 创建用于接收帖子的线程
	 * 
	 */
	public void cTdGetBBSListData(){
		tdGetBBSListData=new Thread(){
			@Override
			public void run(){
				getBBSListData();
			}
		};
		tdGetBBSListData.start();
	}
	
//	// 加载多次  
//    public void onActivityCreated(Bundle savedInstanceState) {  
//        super.onActivityCreated(savedInstanceState);  
//        Log.i(tag, "onActivityCreated"); 
//       
//        bbs_lv_showbbsdata=(ListView) findViewById(android.R.id.search_listview_lenovos);  
//        if(bbsListData != null && bbsListData.size() > 0) {  
//            return;  
//        }  
//          
//        bbsListData = new ArrayList<News>();  
//        setListAdapter(mAdapter);  
//        mListView.onRefreshComplete();  
//        mListView.setonRefreshListener(new OnRefreshListener() {  
//            public void onRefresh() {  
//                isClear = true;  
//                // 初始化数据  
//                AnsynHttpRequest.requestByGet(context, callbackData, R.string.http_news, url, true, true, true);  
//            }  
//        });  
//        // 初始化数据  
//        AnsynHttpRequest.requestByGet(context, callbackData, R.string.http_news, url, true, true, false);  
//    }  
//
//	
	/*
	 * 连接服务端，发送传递帖子请求，并接收所有帖子
	 */
	public void getBBSListData(){
		HttpClient client = new DefaultHttpClient();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair pair = new BasicNameValuePair("index", "5");
		list.add(pair);
		//	NameValuePair pair1 = new BasicNameValuePair("drugLenovo", search_ett_conent.getText().toString());
		//	list.add(pair1);	 
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
				Gson gson = new Gson();				
				bbsListData=gson.fromJson(responseData, new TypeToken<List<BBS>>(){}.getType());
				handler.sendEmptyMessage(1);
				Log.i("functions","bbsListData Create!");	 
			}else{
				Log.i("bbs","bbsListData lose");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/*
	 * 得到一篇帖子
	 */
	public void getBBSOneData(String strBbsId){
		final String mStrBbsId=strBbsId;
		new Thread(){
			public void run(){
		HttpClient client = new DefaultHttpClient();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		NameValuePair pair = new BasicNameValuePair("index", "25");
		NameValuePair pair1 = new BasicNameValuePair("strBbsId",mStrBbsId );
		list.add(pair);
		list.add(pair1);	 
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
				if(responseData==null||responseData=="0"){
					handler.sendEmptyMessage(0);
					return;
				}
				Log.i("functions","bbsOneData Create!");
				bundle=new Bundle();
				bundle.putString("bbs",responseData);
				handler.sendEmptyMessage(2);//跳转至新的论坛内容页面
			}else{
				Log.i("bbs","bbsListDataOne lose");
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
    //内部类优化作用
	public final class ViewHolder{
		
		public TextView title;
//		public TextView name;
//		public TextView address;
		public TextView time;
//		public ImageView img;
	}
	
	/*
	 *ListView适配器
	 */
    public class Adapter extends BaseAdapter {  

    	
    	public Adapter(){

    	}  
        @Override  
        public boolean areAllItemsEnabled() {  
            return super.areAllItemsEnabled();  
        }  
          
        public Adapter(Context context) {  
        	mInflater=LayoutInflater.from(context);
        }  
        @Override  
        public int getCount() {  
            return bbsListData.size();  
        }  
        @Override  
        public Object getItem(int position) {  
            return null;  
        }  
          
        @Override  
        public long getItemId(int position) {  
            return position;  
        }  
  
        @Override  
        public View getView( final int position, View convertView,  
        		ViewGroup parent) {

//        	BBS bbs = bbsListData.get(position); 
        	bbsListTitleListener=new BBSListTitleListener(position);
        	final BBS bbs=bbsListData.get(position);
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
        	// 进行数据设置  
        	holder.title.setText(bbs.getBbsTitle());  
        	holder.time.setText(bbs.getBbsTime());  
        	holder.title.setOnClickListener(bbsListTitleListener);
        	return convertView;  
        }


    } 
    /*
     * listView监听事件
     * 
     */
    private class BBSListTitleListener implements OnClickListener{
    	int mPosition;
		public BBSListTitleListener(int position){
			this.mPosition=position;
		}
    	@Override
		public void onClick(View v) {
    		String bbsId=bbsListData.get(mPosition).getBbsId();
			getBBSOneData(bbsId);
			Log.i("bbs list view chick on",""+mPosition);
		}
    	
    }
    /*
     * 异步回调回来并处理数据 
     */  
//    private  ObserverCallBack callbackData = new ObserverCallBack(){  
//        public void back(String data, int url) {  
//        }};

}

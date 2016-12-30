package test2;

import java.io.IOException;
import java.io.InputStream;
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
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jxutcm.controllers.start.R;
import com.jxutcm.controllers.drugbbs.DrugBBSFragment;
import com.jxutcm.controllers.druglibrary.DrugLibraryFragment;
import com.jxutcm.controllers.drugnews.DrugNewsFragment;
import com.jxutcm.controllers.drugsearch.DrugSearchFragment;

public class MainActivity extends Activity implements OnClickListener{	
	/**
	 *	药文
	 *	药搜
	* 	药库 
	*	药谈	
	*/
	/** 
     * 设置界面布局 
     */ 
	/**
     * 在Tab布局上显示药文图标的控件 
     * 在Tab布局上显示药搜图标的控件 
     * 在Tab布局上显示药库图标的控件 
     * 在Tab布局上显示药物论坛图标的控件
     * 
     */ 
	 /** 
     * 在Tab布局上显示药文标题的控件
     * 在Tab布局上显示药搜标题的控件
     * 在Tab布局上显示药库标题的控件
     * 在Tab布局上显示药物标题的控件  
     */   
	private DrugNewsFragment drugNewsFragment; 
	private View drugNewsLayout;
	private ImageView drugNewsImage;  
	private TextView drugNewsText; 
	
	
	
	private DrugSearchFragment drugSearchFragment;
	private Button drugSearchButton;
	private View drugSearchLayout;
	private ImageView drugSearchImage;     
	private TextView drugSearchText;  

	private DrugLibraryFragment drugLibraryFragment;
	private View drugLibraryLayout;
	private ImageView drugLibraryImage;  
	private TextView drugLibraryText;    
	
	private DrugBBSFragment drugBBSFragment;
	private View drugBBSLayout;    
    private ImageView drugBBSImage;   
    private TextView drugBBSText;   
  
    /** 
     * 用于对Fragment进行管理 
     */  
    private FragmentManager fragmentManager;
	
//    @Override  
//	 protected void onCreate(Bundle savedInstanceState) {  
//		 super.onCreate(savedInstanceState);  
//		 requestWindowFeature(Window.FEATURE_NO_TITLE);  
//		 setContentView(R.layout.fragment_main);  
//		 // 初始化布局元素  
//		 //	        initViews();  
//		 //	        fragmentManager = getFragmentManager();  
//		 // 第一次启动时选中第0个tab  
//		 //	        setTabSelection(0);
//		 //	        initListener();
//	 }  
	
	 /** 
	     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。 
	     */  
	    private void initViews() {  
	    
	    	drugNewsLayout = findViewById(R.id.drugnews_layout); 	    	
	    	drugNewsImage = (ImageView) findViewById(R.id.drugnews_image);  
	    	drugNewsText = (TextView) findViewById(R.id.drugnews_text);  
	    	drugNewsLayout.setOnClickListener(this);  
	    	
	    	drugSearchLayout = findViewById(R.id.drugsearch_layout);  
	    	drugSearchButton=(Button) findViewById(R.id.drugsearch_btn2);
	    	Log.v("mylog","这是我的调试信息12315464");
	    	drugSearchImage = (ImageView) findViewById(R.id.durgsearch_image);  
	    	drugSearchText = (TextView) findViewById(R.id.drugsearch_text); 
	    	//监听
	    	drugSearchButton.setOnClickListener(new DrugSearchListener());
	    	drugSearchLayout.setOnClickListener(this);  

	    	drugLibraryLayout = findViewById(R.id.druglibrary_layout);  
	    	drugLibraryText = (TextView) findViewById(R.id.druglibrary_text);  
	    	drugLibraryImage = (ImageView) findViewById(R.id.druglibrary_image);  
	        drugLibraryLayout.setOnClickListener(this);  
	        
	        drugBBSLayout = findViewById(R.id.drugbbs_layout);  
	        drugBBSImage = (ImageView) findViewById(R.id.drugbbs_image);  	       
	        drugBBSText = (TextView) findViewById(R.id.drugbbs_text);  
	        drugBBSLayout.setOnClickListener(this);  
	    }
	    @Override  
	    public void onClick(View v) {  
	        switch (v.getId()) {  
	        case R.id.drugnews_layout:  
	            // 当点击了消息tab时，选中第1个tab  
	            setTabSelection(0);  
	            break;  
	        case R.id.drugsearch_layout:  
	            // 当点击了联系人tab时，选中第2个tab  
	            setTabSelection(1);  
	            break;  
	        case R.id.druglibrary_layout:  
	            // 当点击了动态tab时，选中第3个tab  
	            setTabSelection(2);  
	            break;  
	        case R.id.drugbbs_layout:  
	            // 当点击了设置tab时，选中第4个tab  
	            setTabSelection(3);  
	            break;  
	        default:  
	            break;  
	        }  
	    }  
	  
	    /** 
	     * 根据传入的index参数来设置选中的tab页。 
	     *  
	     * @param index 
	     *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。 
	     */  
	    private void setTabSelection(int index) {  
	        // 每次选中之前先清楚掉上次的选中状态  
	        clearSelection();  
	        // 开启一个Fragment事务  
	        FragmentTransaction transaction = fragmentManager.beginTransaction();  
	        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况  
	        hideFragments(transaction);  
	        switch (index) {  
	        case 0:  
	            // 当点击了消息tab时，改变控件的图片和文字颜色  
	            drugNewsImage.setImageResource(R.drawable.drugnews);  
	            drugNewsText.setTextColor(Color.WHITE);  
	            if (drugNewsFragment == null) {  
	                // 如果MessageFragment为空，则创建一个并添加到界面上  
	                drugNewsFragment = new DrugNewsFragment(); 
//	                drugNewsFragment.set
	                transaction.add(R.id.content, drugNewsFragment);  
	            } else {  
	                // 如果MessageFragment不为空，则直接将它显示出来  
	                transaction.show(drugNewsFragment);  
	            }  
	            break;  
	        case 1:  
	            // 当点击了联系人tab时，改变控件的图片和文字颜色  
	            drugSearchImage.setImageResource(R.drawable.drugsearch);  
	            drugSearchText.setTextColor(Color.WHITE);  
	            if (drugSearchFragment == null) {  
	                // 如果ContactsFragment为空，则创建一个并添加到界面上  
	            	drugSearchFragment = new DrugSearchFragment();
	            	
	                transaction.add(R.id.content, drugSearchFragment);  
	            } else {  
	                // 如果ContactsFragment不为空，则直接将它显示出来  
	                transaction.show(drugSearchFragment);  
	            }  
	            break;  
	        case 2:  
	            // 当点击了动态tab时，改变控件的图片和文字颜色  
	            drugLibraryImage.setImageResource(R.drawable.druglibrary);  
	            drugLibraryText.setTextColor(Color.WHITE);  
	            if (drugLibraryFragment == null) {  
	                // 如果NewsFragment为空，则创建一个并添加到界面上  
	                drugLibraryFragment = new DrugLibraryFragment();  
	                transaction.add(R.id.content, drugLibraryFragment);  
	            } else {  
	                // 如果NewsFragment不为空，则直接将它显示出来  
	                transaction.show(drugLibraryFragment);  
	            }  
	            break;  
	        case 3:  
	        default:  
	            // 当点击了药谈tab时，改变控件的图片和文字颜色  
	            drugBBSImage.setImageResource(R.drawable.drugbbs);  
	            drugBBSText.setTextColor(Color.WHITE);  
	            if (drugBBSFragment == null) {  
	                // 如果SettingFragment为空，则创建一个并添加到界面上  
	            	drugBBSFragment = new DrugBBSFragment();  
	                transaction.add(R.id.content, drugBBSFragment);  
	            } else {  
	                // 如果SettingFragment不为空，则直接将它显示出来  
	                transaction.show(drugBBSFragment);  
	            }  
	            break;  
	        }  
	        transaction.commit();  
	    }  
	  
	    /** 
	     * 清除掉所有的选中状态。 
	     */  
	    private void clearSelection() {  
	    	drugNewsImage.setImageResource(R.drawable.drugnews); //_unselected  
	    	drugNewsText.setTextColor(Color.parseColor("#82858b"));  
	        drugSearchImage.setImageResource(R.drawable.drugsearch);  
	        drugSearchText.setTextColor(Color.parseColor("#82858b"));  
	        drugLibraryImage.setImageResource(R.drawable.druglibrary);  
	        drugLibraryText.setTextColor(Color.parseColor("#82858b"));  
	        drugBBSImage.setImageResource(R.drawable.drugbbs);  
	        drugBBSText.setTextColor(Color.parseColor("#82858b"));  
	    }  
	  
	    /** 
	     * 将所有的Fragment都置为隐藏状态。 
	     *  
	     * @param transaction 
	     *            用于对Fragment执行操作的事务 
	     */  
	    private void hideFragments(FragmentTransaction transaction) {  
	        if (drugNewsFragment != null) {  
	            transaction.hide(drugNewsFragment);  
	        }  
	        if (drugSearchFragment != null) {  
	            transaction.hide(drugSearchFragment);  
	        }  
	        if (drugLibraryFragment != null) {  
	            transaction.hide(drugLibraryFragment);  
	        }  
	        if (drugBBSFragment != null) {  
	            transaction.hide(drugBBSFragment);  
	        }  
	    } 
	    	   
	    private Handler handler = new Handler(){
	    	@Override
	    	public void handleMessage(Message msg) {
	    		// TODO Auto-generated method stub
	    		super.handleMessage(msg);
	    		if (msg.what == 3) {//当服务器返回给客户端的标记为3时，说明查询成功

	    			Log.i("search----------------->", "success");
	    			finish();
	    		}else {
	    			Toast.makeText(MainActivity.this, "查询失败", Toast.LENGTH_SHORT)
	    			.show();
	    		}
	    	}
	    };
	    public class DrugSearchListener implements OnClickListener{
	    	
			@Override
			public void onClick(View v) {
					Log.v("mylog","这是我的调试信息success");
					new Thread (){
						public void run() {
							HttpClient client = new DefaultHttpClient();
							List<NameValuePair> list = new ArrayList<NameValuePair>();
							NameValuePair pair = new BasicNameValuePair("index", "3");
							list.add(pair);
							try {
								UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
								HttpPost post = new HttpPost("http://10.0.2.2:8080/server/Servlet");
								post.setEntity(entity);
								HttpResponse response = client.execute(post);
								if (response.getStatusLine().getStatusCode() == 200) {
									InputStream in = response.getEntity().getContent();
									handler.sendEmptyMessage(in.read());//将服务器返回给客户端的标记直接传给handler
									in.close();
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
						
					}.start();
					
				
				}
				
			}
	    	
	 
	   
}

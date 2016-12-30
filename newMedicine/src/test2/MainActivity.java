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
	 *	ҩ��
	 *	ҩ��
	* 	ҩ�� 
	*	ҩ̸	
	*/
	/** 
     * ���ý��沼�� 
     */ 
	/**
     * ��Tab��������ʾҩ��ͼ��Ŀؼ� 
     * ��Tab��������ʾҩ��ͼ��Ŀؼ� 
     * ��Tab��������ʾҩ��ͼ��Ŀؼ� 
     * ��Tab��������ʾҩ����̳ͼ��Ŀؼ�
     * 
     */ 
	 /** 
     * ��Tab��������ʾҩ�ı���Ŀؼ�
     * ��Tab��������ʾҩ�ѱ���Ŀؼ�
     * ��Tab��������ʾҩ�����Ŀؼ�
     * ��Tab��������ʾҩ�����Ŀؼ�  
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
     * ���ڶ�Fragment���й��� 
     */  
    private FragmentManager fragmentManager;
	
//    @Override  
//	 protected void onCreate(Bundle savedInstanceState) {  
//		 super.onCreate(savedInstanceState);  
//		 requestWindowFeature(Window.FEATURE_NO_TITLE);  
//		 setContentView(R.layout.fragment_main);  
//		 // ��ʼ������Ԫ��  
//		 //	        initViews();  
//		 //	        fragmentManager = getFragmentManager();  
//		 // ��һ������ʱѡ�е�0��tab  
//		 //	        setTabSelection(0);
//		 //	        initListener();
//	 }  
	
	 /** 
	     * �������ȡ��ÿ����Ҫ�õ��Ŀؼ���ʵ���������������úñ�Ҫ�ĵ���¼��� 
	     */  
	    private void initViews() {  
	    
	    	drugNewsLayout = findViewById(R.id.drugnews_layout); 	    	
	    	drugNewsImage = (ImageView) findViewById(R.id.drugnews_image);  
	    	drugNewsText = (TextView) findViewById(R.id.drugnews_text);  
	    	drugNewsLayout.setOnClickListener(this);  
	    	
	    	drugSearchLayout = findViewById(R.id.drugsearch_layout);  
	    	drugSearchButton=(Button) findViewById(R.id.drugsearch_btn2);
	    	Log.v("mylog","�����ҵĵ�����Ϣ12315464");
	    	drugSearchImage = (ImageView) findViewById(R.id.durgsearch_image);  
	    	drugSearchText = (TextView) findViewById(R.id.drugsearch_text); 
	    	//����
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
	            // ���������Ϣtabʱ��ѡ�е�1��tab  
	            setTabSelection(0);  
	            break;  
	        case R.id.drugsearch_layout:  
	            // ���������ϵ��tabʱ��ѡ�е�2��tab  
	            setTabSelection(1);  
	            break;  
	        case R.id.druglibrary_layout:  
	            // ������˶�̬tabʱ��ѡ�е�3��tab  
	            setTabSelection(2);  
	            break;  
	        case R.id.drugbbs_layout:  
	            // �����������tabʱ��ѡ�е�4��tab  
	            setTabSelection(3);  
	            break;  
	        default:  
	            break;  
	        }  
	    }  
	  
	    /** 
	     * ���ݴ����index����������ѡ�е�tabҳ�� 
	     *  
	     * @param index 
	     *            ÿ��tabҳ��Ӧ���±ꡣ0��ʾ��Ϣ��1��ʾ��ϵ�ˣ�2��ʾ��̬��3��ʾ���á� 
	     */  
	    private void setTabSelection(int index) {  
	        // ÿ��ѡ��֮ǰ��������ϴε�ѡ��״̬  
	        clearSelection();  
	        // ����һ��Fragment����  
	        FragmentTransaction transaction = fragmentManager.beginTransaction();  
	        // �����ص����е�Fragment���Է�ֹ�ж��Fragment��ʾ�ڽ����ϵ����  
	        hideFragments(transaction);  
	        switch (index) {  
	        case 0:  
	            // ���������Ϣtabʱ���ı�ؼ���ͼƬ��������ɫ  
	            drugNewsImage.setImageResource(R.drawable.drugnews);  
	            drugNewsText.setTextColor(Color.WHITE);  
	            if (drugNewsFragment == null) {  
	                // ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������  
	                drugNewsFragment = new DrugNewsFragment(); 
//	                drugNewsFragment.set
	                transaction.add(R.id.content, drugNewsFragment);  
	            } else {  
	                // ���MessageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����  
	                transaction.show(drugNewsFragment);  
	            }  
	            break;  
	        case 1:  
	            // ���������ϵ��tabʱ���ı�ؼ���ͼƬ��������ɫ  
	            drugSearchImage.setImageResource(R.drawable.drugsearch);  
	            drugSearchText.setTextColor(Color.WHITE);  
	            if (drugSearchFragment == null) {  
	                // ���ContactsFragmentΪ�գ��򴴽�һ������ӵ�������  
	            	drugSearchFragment = new DrugSearchFragment();
	            	
	                transaction.add(R.id.content, drugSearchFragment);  
	            } else {  
	                // ���ContactsFragment��Ϊ�գ���ֱ�ӽ�����ʾ����  
	                transaction.show(drugSearchFragment);  
	            }  
	            break;  
	        case 2:  
	            // ������˶�̬tabʱ���ı�ؼ���ͼƬ��������ɫ  
	            drugLibraryImage.setImageResource(R.drawable.druglibrary);  
	            drugLibraryText.setTextColor(Color.WHITE);  
	            if (drugLibraryFragment == null) {  
	                // ���NewsFragmentΪ�գ��򴴽�һ������ӵ�������  
	                drugLibraryFragment = new DrugLibraryFragment();  
	                transaction.add(R.id.content, drugLibraryFragment);  
	            } else {  
	                // ���NewsFragment��Ϊ�գ���ֱ�ӽ�����ʾ����  
	                transaction.show(drugLibraryFragment);  
	            }  
	            break;  
	        case 3:  
	        default:  
	            // �������ҩ̸tabʱ���ı�ؼ���ͼƬ��������ɫ  
	            drugBBSImage.setImageResource(R.drawable.drugbbs);  
	            drugBBSText.setTextColor(Color.WHITE);  
	            if (drugBBSFragment == null) {  
	                // ���SettingFragmentΪ�գ��򴴽�һ������ӵ�������  
	            	drugBBSFragment = new DrugBBSFragment();  
	                transaction.add(R.id.content, drugBBSFragment);  
	            } else {  
	                // ���SettingFragment��Ϊ�գ���ֱ�ӽ�����ʾ����  
	                transaction.show(drugBBSFragment);  
	            }  
	            break;  
	        }  
	        transaction.commit();  
	    }  
	  
	    /** 
	     * ��������е�ѡ��״̬�� 
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
	     * �����е�Fragment����Ϊ����״̬�� 
	     *  
	     * @param transaction 
	     *            ���ڶ�Fragmentִ�в��������� 
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
	    		if (msg.what == 3) {//�����������ظ��ͻ��˵ı��Ϊ3ʱ��˵����ѯ�ɹ�

	    			Log.i("search----------------->", "success");
	    			finish();
	    		}else {
	    			Toast.makeText(MainActivity.this, "��ѯʧ��", Toast.LENGTH_SHORT)
	    			.show();
	    		}
	    	}
	    };
	    public class DrugSearchListener implements OnClickListener{
	    	
			@Override
			public void onClick(View v) {
					Log.v("mylog","�����ҵĵ�����Ϣsuccess");
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
									handler.sendEmptyMessage(in.read());//�����������ظ��ͻ��˵ı��ֱ�Ӵ���handler
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

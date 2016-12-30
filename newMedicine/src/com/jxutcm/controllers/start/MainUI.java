package com.jxutcm.controllers.start;



import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.jxutcm.controllers.rightUI.FeedBackUI;
import com.jxutcm.controllers.start.R;
import com.jxutcm.controllers.drugbbs.DrugBBSFragment;
import com.jxutcm.controllers.druglibrary.DrugLibraryFragment;
import com.jxutcm.controllers.drugnews.DrugNewsFragment;
import com.jxutcm.controllers.drugsearch.DrugSearchFragment;
import com.jxutcm.controllers.leftUI.ChangeUerDataUI;
import com.jxutcm.controllers.leftUI.ExamUI;
import com.jxutcm.controllers.leftUI.ScoreUI;
import com.jxutcm.controllers.leftUI.UerInfor;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/** 
 * @author doyouknow
 * @version 
 */
public class MainUI extends SlidingFragmentActivity implements OnClickListener {
	/**
	 * �����淶
	 * �ؼ�������������_�ؼ���_����
	 */
	
	@ViewInject(R.id.left_btn_exam)
	private Button btn_exam;
	@ViewInject(R.id.left_btn_check)
	private Button btn_check;
	@ViewInject(R.id.left_btn_mement)
	private Button btn_mement;
	@ViewInject(R.id.left_btn_userinfor)
	private Button btn_ureinfor;
	@ViewInject(R.id.right_btn_about)
	private Button btn_about;
	@ViewInject(R.id.right_btn_exit)
	private Button btn_exit;
	@ViewInject(R.id.right_btn_feedback)
	private Button btn_feedback;
	
	
	
	@ViewInject(R.id.main_username)
	private EditText main_username;
	@ViewInject(R.id.main_account)
	private EditText main_account;
	
	
	
	/*
	 * 
	 * ���� �����߼� �ܷ���
	 */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		onCreateFra(savedInstanceState);
		onCreateSli(savedInstanceState);
		
		
	}
	/*
	 * ������ ���跢�����¼� �ܷ���
	 * 
	 */
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		onClickFra(v);
		onClickSli(v);
		
		
	}


	public void onCreateSli(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
		
		// ���������沼��		
//		setContentView(R.layout.main_ui);
//		myActivityGroup.onCreate(savedInstanceState);
		//�������˵�
		setBehindContentView(R.layout.left_menu);
		//�����Ҳ�˵�
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setSecondaryMenu(R.layout.right_menu);
		// �������Ҳ˵�������.
		slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
		// ����������Ļ�����Ի��������˵�
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// �������������������Ļ�ϵĿ��
		slidingMenu.setBehindOffset(200);
		ViewUtils.inject(this);
		btn_exam.setOnClickListener(this);
		btn_check.setOnClickListener(this);
		btn_about.setOnClickListener(this);
		btn_mement.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_feedback.setOnClickListener(this);
		btn_ureinfor.setOnClickListener(this);
//		main_account.setText("�ʺ�:"+ToolKit.getString(MainUI.this, UserConst.USER_KEY_ACCOUNT, ""));
//		main_username.setText("����:"+ToolKit.getString(MainUI.this, UserConst.USER_KEY_NAME, ""));
	}

	
	
	public void onClickSli(View v) {

		switch (v.getId()) {
		case R.id.left_btn_exam:
			//��ʼ����
			startActivity(new Intent(MainUI.this,ExamUI.class));
			break;
		case R.id.left_btn_check:
			//�鿴����
//			startActivity(new Intent(MainUI.this,ScoreUI.class));
			break;
		case R.id.left_btn_mement:
			//�޸���Ϣ
			startActivity(new Intent(MainUI.this,ChangeUerDataUI.class));
			break;
		case R.id.left_btn_userinfor:
			startActivity(new Intent(MainUI.this,UerInfor.class));
			break;
		case R.id.right_btn_about:
			proessAbout();
			//�й���Ϣ
			break;
		case R.id.right_btn_feedback:
			//����
			startActivity(new Intent(MainUI.this,FeedBackUI.class));
			break;  
		case R.id.right_btn_exit:
			//�˳�
			proessFinish();
			break;  
		default:  
			break;  
		
		}


	}

	

	private void proessFinish() {
		new AlertDialog.Builder(MainUI.this)
		.setTitle("��ʾ")
		.setMessage("��Ҫ�˳��������?")
		.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		}).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();

			}
		}).create().show();

	}

	private void proessAbout() {
		new AlertDialog.Builder(MainUI.this)
		.setTitle("�������")
		.setMessage("����֧��                   175416603     1131213953 ")
		.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		}).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		}).create().show();

	}
	
	/////////////////////////////////////////////////////////////////
	
	/**
	 *	ҩ��
	 *	ҩ��
	* 	ҩ��
	*	ҩ̸	
	*/
	private DrugNewsFragment drugNewsFragment; 
	private DrugSearchFragment drugSearchFragment;
	private DrugLibraryFragment drugLibraryFragment;
	private DrugBBSFragment drugBBSFragment;
	
	
	/** 
     * ���ý��沼�� 
     */ 
	private View drugNewsLayout;
	private View drugSearchLayout;
	private View drugLibraryLayout;
    private View drugBBSLayout;
  
    /**
     * ��Tab��������ʾҩ��ͼ��Ŀؼ� 
     * ��Tab��������ʾҩ��ͼ��Ŀؼ� 
     * ��Tab��������ʾҩ��ͼ��Ŀؼ� 
     * ��Tab��������ʾҩ����̳ͼ��Ŀؼ�
     * 
     */ 
    private ImageView drugNewsImage;  
    private ImageView drugSearchImage;     
    private ImageView drugLibraryImage;  
    private ImageView drugBBSImage;   
    /** 
     * ��Tab��������ʾҩ�ı���Ŀؼ�
     * ��Tab��������ʾҩ�ѱ���Ŀؼ�
     * ��Tab��������ʾҩ�����Ŀؼ�
     * ��Tab��������ʾҩ�����Ŀؼ�  
     */   
    private TextView drugNewsText; 
    private TextView drugSearchText;  
    private TextView drugLibraryText;    
    private TextView drugBBSText;   
    
   
    /** 
     * ���ڶ�Fragment���й��� 
     */  
    private FragmentManager fragmentManager;
  
	    protected void onCreateFra(Bundle savedInstanceState) {    
	        requestWindowFeature(Window.FEATURE_NO_TITLE);  
	        setContentView(R.layout.fragment_main);  
	        // ��ʼ������Ԫ��  
	        initViews();  
	        fragmentManager = getFragmentManager();  
	        // ��һ������ʱѡ�е�0��tab  
	        setTabSelection(0);  
	      
	    }  
	
	 /** 
	     * �������ȡ��ÿ����Ҫ�õ��Ŀؼ���ʵ���������������úñ�Ҫ�ĵ���¼��� 
	     */  
	    private void initViews() {  
	    	drugNewsLayout = findViewById(R.id.drugnews_layout);  
	    	drugNewsImage = (ImageView) findViewById(R.id.drugnews_image);  
	    	drugNewsText = (TextView) findViewById(R.id.drugnews_text);  
	    	drugNewsLayout.setOnClickListener(this);  
	    	
	    	drugSearchLayout = findViewById(R.id.drugsearch_layout);  
	    	drugSearchImage = (ImageView) findViewById(R.id.durgsearch_image);  
	    	drugSearchText = (TextView) findViewById(R.id.drugsearch_text);  
	    	
	    	drugSearchLayout.setOnClickListener(this);
	    	
	    	
	    	drugLibraryLayout = findViewById(R.id.druglibrary_layout);  
	    	drugLibraryImage = (ImageView) findViewById(R.id.druglibrary_image);  
	    	drugLibraryText = (TextView) findViewById(R.id.druglibrary_text);  
	    	drugLibraryLayout.setOnClickListener(this);  

	    	drugBBSLayout = findViewById(R.id.drugbbs_layout);          
	        drugBBSImage = (ImageView) findViewById(R.id.drugbbs_image);         
	        drugBBSText = (TextView) findViewById(R.id.drugbbs_text);         
	        drugBBSLayout.setOnClickListener(this);  
	        
	     
	    	 
	    }
	 
	    public void onClickFra(View v) {  
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
	            drugNewsImage.setImageResource(R.drawable.drugnews2);  
	            drugNewsText.setTextColor(Color.WHITE);  
	            if (drugNewsFragment == null) {  
	                // ���MessageFragmentΪ�գ��򴴽�һ������ӵ�������  
	                drugNewsFragment = new DrugNewsFragment();  
	                transaction.add(R.id.content, drugNewsFragment);  
	            } else {  
	                // ���MessageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����  
	                transaction.show(drugNewsFragment);  
	            }  
	            break;  
	        case 1: 
	            // ���������ϵ��tabʱ���ı�ؼ���ͼƬ��������ɫ  
	            drugSearchImage.setImageResource(R.drawable.drugsearch2);  
	            drugSearchText.setTextColor(Color.WHITE);  
	            if (drugSearchFragment == null) {  
	                // ���ContactsFragmentΪ�գ��򴴽�һ������ӵ�������  
	            	drugSearchFragment = new DrugSearchFragment();  
//	            	search_btn_search=findId(R.id.drugsearch_btn2);
	            	
	                transaction.add(R.id.content, drugSearchFragment);  
	            } else {  
	                // ���ContactsFragment��Ϊ�գ���ֱ�ӽ�����ʾ����  
	            	
	                transaction.show(drugSearchFragment);  
	            }  
	            break;  
	        case 2:  
	            // ������˶�̬tabʱ���ı�ؼ���ͼƬ��������ɫ  
	            drugLibraryImage.setImageResource(R.drawable.druglibrary2);  
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
	            drugBBSImage.setImageResource(R.drawable.drugbbs2);  
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
}

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
	 * 命名规范
	 * 控件命名：界面名_控件名_功能
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
	 * 监听 运行逻辑 总方法
	 */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		onCreateFra(savedInstanceState);
		onCreateSli(savedInstanceState);
		
		
	}
	/*
	 * 监听后 所需发生的事件 总方法
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
		
		// 设置主界面布局		
//		setContentView(R.layout.main_ui);
//		myActivityGroup.onCreate(savedInstanceState);
		//设置左侧菜单
		setBehindContentView(R.layout.left_menu);
		//设置右侧菜单
		SlidingMenu slidingMenu = getSlidingMenu();
		slidingMenu.setSecondaryMenu(R.layout.right_menu);
		// 设置左右菜单都可用.
		slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
		// 设置整个屏幕都可以滑动出来菜单
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// 设置主界面可以留在屏幕上的宽度
		slidingMenu.setBehindOffset(200);
		ViewUtils.inject(this);
		btn_exam.setOnClickListener(this);
		btn_check.setOnClickListener(this);
		btn_about.setOnClickListener(this);
		btn_mement.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_feedback.setOnClickListener(this);
		btn_ureinfor.setOnClickListener(this);
//		main_account.setText("帐号:"+ToolKit.getString(MainUI.this, UserConst.USER_KEY_ACCOUNT, ""));
//		main_username.setText("姓名:"+ToolKit.getString(MainUI.this, UserConst.USER_KEY_NAME, ""));
	}

	
	
	public void onClickSli(View v) {

		switch (v.getId()) {
		case R.id.left_btn_exam:
			//开始考试
			startActivity(new Intent(MainUI.this,ExamUI.class));
			break;
		case R.id.left_btn_check:
			//查看排名
//			startActivity(new Intent(MainUI.this,ScoreUI.class));
			break;
		case R.id.left_btn_mement:
			//修改信息
			startActivity(new Intent(MainUI.this,ChangeUerDataUI.class));
			break;
		case R.id.left_btn_userinfor:
			startActivity(new Intent(MainUI.this,UerInfor.class));
			break;
		case R.id.right_btn_about:
			proessAbout();
			//有关信息
			break;
		case R.id.right_btn_feedback:
			//反馈
			startActivity(new Intent(MainUI.this,FeedBackUI.class));
			break;  
		case R.id.right_btn_exit:
			//退出
			proessFinish();
			break;  
		default:  
			break;  
		
		}


	}

	

	private void proessFinish() {
		new AlertDialog.Builder(MainUI.this)
		.setTitle("提示")
		.setMessage("你要退出该软件吗?")
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		}).setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();

			}
		}).create().show();

	}

	private void proessAbout() {
		new AlertDialog.Builder(MainUI.this)
		.setTitle("关于软件")
		.setMessage("技术支持                   175416603     1131213953 ")
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		}).setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		}).create().show();

	}
	
	/////////////////////////////////////////////////////////////////
	
	/**
	 *	药文
	 *	药搜
	* 	药库
	*	药谈	
	*/
	private DrugNewsFragment drugNewsFragment; 
	private DrugSearchFragment drugSearchFragment;
	private DrugLibraryFragment drugLibraryFragment;
	private DrugBBSFragment drugBBSFragment;
	
	
	/** 
     * 设置界面布局 
     */ 
	private View drugNewsLayout;
	private View drugSearchLayout;
	private View drugLibraryLayout;
    private View drugBBSLayout;
  
    /**
     * 在Tab布局上显示药文图标的控件 
     * 在Tab布局上显示药搜图标的控件 
     * 在Tab布局上显示药库图标的控件 
     * 在Tab布局上显示药物论坛图标的控件
     * 
     */ 
    private ImageView drugNewsImage;  
    private ImageView drugSearchImage;     
    private ImageView drugLibraryImage;  
    private ImageView drugBBSImage;   
    /** 
     * 在Tab布局上显示药文标题的控件
     * 在Tab布局上显示药搜标题的控件
     * 在Tab布局上显示药库标题的控件
     * 在Tab布局上显示药物标题的控件  
     */   
    private TextView drugNewsText; 
    private TextView drugSearchText;  
    private TextView drugLibraryText;    
    private TextView drugBBSText;   
    
   
    /** 
     * 用于对Fragment进行管理 
     */  
    private FragmentManager fragmentManager;
  
	    protected void onCreateFra(Bundle savedInstanceState) {    
	        requestWindowFeature(Window.FEATURE_NO_TITLE);  
	        setContentView(R.layout.fragment_main);  
	        // 初始化布局元素  
	        initViews();  
	        fragmentManager = getFragmentManager();  
	        // 第一次启动时选中第0个tab  
	        setTabSelection(0);  
	      
	    }  
	
	 /** 
	     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。 
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
	            drugNewsImage.setImageResource(R.drawable.drugnews2);  
	            drugNewsText.setTextColor(Color.WHITE);  
	            if (drugNewsFragment == null) {  
	                // 如果MessageFragment为空，则创建一个并添加到界面上  
	                drugNewsFragment = new DrugNewsFragment();  
	                transaction.add(R.id.content, drugNewsFragment);  
	            } else {  
	                // 如果MessageFragment不为空，则直接将它显示出来  
	                transaction.show(drugNewsFragment);  
	            }  
	            break;  
	        case 1: 
	            // 当点击了联系人tab时，改变控件的图片和文字颜色  
	            drugSearchImage.setImageResource(R.drawable.drugsearch2);  
	            drugSearchText.setTextColor(Color.WHITE);  
	            if (drugSearchFragment == null) {  
	                // 如果ContactsFragment为空，则创建一个并添加到界面上  
	            	drugSearchFragment = new DrugSearchFragment();  
//	            	search_btn_search=findId(R.id.drugsearch_btn2);
	            	
	                transaction.add(R.id.content, drugSearchFragment);  
	            } else {  
	                // 如果ContactsFragment不为空，则直接将它显示出来  
	            	
	                transaction.show(drugSearchFragment);  
	            }  
	            break;  
	        case 2:  
	            // 当点击了动态tab时，改变控件的图片和文字颜色  
	            drugLibraryImage.setImageResource(R.drawable.druglibrary2);  
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
	            drugBBSImage.setImageResource(R.drawable.drugbbs2);  
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
}

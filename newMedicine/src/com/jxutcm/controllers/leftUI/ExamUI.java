package com.jxutcm.controllers.leftUI;

import com.jxutcm.controllers.start.R;

import android.app.Activity;
import android.os.Bundle;

import android.view.Window;




public class ExamUI extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exam_ui);
//		ViewUtils.inject(this);
//		btn_commit.setOnClickListener(this);
//		btn_load.setOnClickListener(this);
		//一进到这个考试界面就要加载
//		pressData();
		
	}
//	@Override  
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
//            Bundle savedInstanceState) {  
//        View examFragment = inflater.inflate(R.layout.exam_ui,  
//                container, false);  
//        return 	examFragment;  
//    }  
}

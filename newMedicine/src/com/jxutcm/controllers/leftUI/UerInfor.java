package com.jxutcm.controllers.leftUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.jxutcm.controllers.start.R;

public class UerInfor extends Activity{ 
    public void onCreate(Bundle savedInstanceState){
		//Œﬁ±ÍÃ‚
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userinfo_ui);
	}

}

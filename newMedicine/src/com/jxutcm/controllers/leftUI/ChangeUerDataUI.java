package com.jxutcm.controllers.leftUI;

import com.jxutcm.controllers.start.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;



public class ChangeUerDataUI extends Activity{ 
	    public void onCreate(Bundle savedInstanceState){
			//Œﬁ±ÍÃ‚
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			super.onCreate(savedInstanceState);
			setContentView(R.layout.changeuerdata_ui);
		}
	 
}

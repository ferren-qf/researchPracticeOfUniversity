package com.jxutcm.controllers.rightUI;



import com.jxutcm.controllers.start.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
/**
 * �������
 * @author qiye
 *
 */
public class FeedBackUI extends Activity {
	public void onCreate(Bundle savedInstanceState){
		//�ޱ���
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback_ui);
	}
}

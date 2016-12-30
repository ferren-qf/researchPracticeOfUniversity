package test2;

import java.util.List;

import com.jxutcm.controllers.drugnews.DrugNewsFragment.ViewHolder;
import com.jxutcm.controllers.start.R;
import com.jxutcm.model.News;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SearchUniteUI2 extends Activity{
	private ListView lv;
	private LayoutInflater mInflater;
	
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.druglibrary_layout2);	
	}
	
	/*
	 *ListView适配器
	 */
    public class Adapter extends BaseAdapter {  

    	public Adapter(){

    	}  
    	  public Adapter(Context context,List<News> newsListData) {  
	        	mInflater=LayoutInflater.from(context);
	        }
        @Override  
        public boolean areAllItemsEnabled() {  
            return super.areAllItemsEnabled();  
        }  
          
        
        @Override  
        public int getCount() {  
            return 2;  
        }  
        @Override  
        public String getItem(int position) {  
        	
        	return null;  
        }  
          
        @Override  
        public long getItemId(int position) {  
            return position;  
        }  
  
        @Override  
        public View getView( final int position, View convertView,  
        		ViewGroup parent) {
        	
        	if (convertView == null) {			 
        		convertView = mInflater.inflate(R.layout.druglibrary_layout, null); 
        	} else {  
        		
        	} 
        	// 进行数据设置  

        	return convertView;  

        }


    }
}

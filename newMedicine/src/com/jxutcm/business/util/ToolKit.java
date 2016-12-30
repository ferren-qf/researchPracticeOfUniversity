package com.jxutcm.business.util;
import android.content.Context;
import android.content.SharedPreferences;
public class ToolKit {

	private static  final String NOTE_NAME="nick_exam";
	//创建一个配置文件
	public static SharedPreferences getSharedPreferences(Context context){
		return context.getSharedPreferences(NOTE_NAME, Context.MODE_PRIVATE);
	}

	//创建数据
	public static void createSharedPreferences(Context mContext,String key,boolean value){
		SharedPreferences preferences=getSharedPreferences(mContext);
		SharedPreferences.Editor editor=preferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	//读取数据
	public static boolean getBoolean(Context context,String key,boolean defValue){
		return getSharedPreferences(context).getBoolean(key, defValue);
	}

	
	public static void createSharedPreferencesString(Context mContext,String key,String value){
		SharedPreferences preferences=getSharedPreferences(mContext);
		SharedPreferences.Editor editor=preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	public static String getString(Context context,String key,String defValue){
		return getSharedPreferences(context).getString(key, defValue);
	}
	public static void createSharedPreferencesInt(Context mContext,String key,int value){
		SharedPreferences preferences=getSharedPreferences(mContext);
		SharedPreferences.Editor editor=preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	public static int getInt(Context context,String key,int defValue){
		return getSharedPreferences(context).getInt(key, defValue);
	}
}

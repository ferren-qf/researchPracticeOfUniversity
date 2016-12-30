package com.jxutcm.business.util;
/** 
 * @author 
 * @version 
 */
public class API {
	
	private final static String API_URL="http://169.254.126.178:8080/AndroidExam";
	//http://localhost:8080/AndroidExam/servlet/UserServlet?flag=login&account=123&password=123
	public static final String API_LOGIN_URL=API_URL+"/servlet/UserServlet?flag=login";
	
	public static final String API_RESIGN_URL=API_URL+"/servlet/UserServlet?flag=resign";
	
	public static final String API_SHOWEXAM_URL=API_URL+"/servlet/QuestionServlet";
	
	//http://localhost:8080/AndroidExam/servlet/ScoreInsertServlet?stuaccounts=1908&stunames=–°¿‡&scoreTols=1
	
	public static final String API_SCROEINSET_URL=API_URL+"/servlet/ScoreInsertServlet";
	
	
	public static final String API_SCROESELECT_URL=API_URL+"/servlet/ScoreListServlet";



}

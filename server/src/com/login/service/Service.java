package com.login.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.login.bean.BBS;
import com.login.bean.Drug;
import com.login.bean.News;
import com.login.bean.User;
import com.login.dao.DaoImpl;

/**
 * Service
 * @author doyouknow
 * 是MVC中的控制层，检查用户名是否已经注册，注册用户，登陆时检查用户名与密码是否匹配
 * 即判断是否有（可运行）
 */

public class Service {	
	private User user = null;
	private DaoImpl dao= new DaoImpl();
	public Drug drug=null;
	public List<String> drugLenovos; 
	public List<BBS> bbsListData;
	public List<News> newsListData;
	public List drugListData;
	public News newsOneData;
	public BBS bbsOneData;
	public Object drugOneData;
	public String strDrugKind;
	
	public Map<String,List> drugMapData;
	/**
	 * 检查用户名是否已被注册过
	 * @param username 用户输入的用户名
	 * @return 1为已注册过，0为没有注册过 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int checkUsername(String username) throws ClassNotFoundException, SQLException{
		int flag = 0;
		user = dao.selectOne(username);
		if (user != null) {
			flag = 1;
		}
		return flag;
	} 
	/**
	 * 检查有没有该药品
	 * 1为有，0为没有
	 */
	public int canSerachDrug(String drugCName) throws ClassNotFoundException,SQLException{
		int flag=0;
		drugOneData=dao.selectDrugOne(drugCName);
		strDrugKind=dao.strDrugKind;
		if(drugOneData !=null){
			flag=1;
		}
		return flag;
	}
	
	/**
	 * 用户注册
	 * 将用户信息插入数据库中
	 * @param user 用户信息的封装类
	 * @return 1 成功插入，0没有成功插入
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int register (User user) throws ClassNotFoundException, SQLException{
		
		return dao.insert(user);
	}
	
	/**
	 * 用户登录
	 * 根据用户名在数据库中查找用户,该用户存在且输入的密码和该用户在数据库中存储的密码一致，则可以登录
	 * @param username 用户输入的用户名
	 * @param password 用户输入的密码
	 * @return 1 可以登录，0 不可以登录
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int canLogin (String username, String password) throws ClassNotFoundException, SQLException{
		int flag = 1;
		user = dao.selectOne(username);
		if (user == null) {
			flag = 0;
		}
		else if (!password.equals(user.getPassword())) {
			flag = 0;
		}
		return flag;
	}
	
	/*
	 *判断是否有该首字符的药品
	 * 
	 */
	public int canLenovo(String drugLenovo) throws ClassNotFoundException, SQLException{
		int flag=1;
		drugLenovos=dao.selectDrugLenovo(drugLenovo);
		if(drugLenovos==null){
			flag=0;
		}
		return flag;
	}
	/*
	 * 判断是bbs库中是否调用了15条数据
	 */
	public int isBBSListData() throws ClassNotFoundException, SQLException{
		int flag=1;
		bbsListData=dao.selectBBSListData();
		if(bbsListData.size()!=15){
			flag=0;
		}
		return flag;
	}
	/*
	 * 是否存入帖子成功
	 */
	public int isBBSPosting(String bbsTitle,String bbsTime,String bbsContent) throws ClassNotFoundException, SQLException{
		int flag=1;
		flag=dao.storagePost(bbsTitle, bbsTime,bbsContent);		
		return flag;
	}
	/*
	 * 是否调出一个帖子成功
	 */
	public int isBBSOneData(String bbsId) throws ClassNotFoundException, SQLException{
		int flag=1;
		bbsOneData=dao.selectBBSOneData(bbsId);
		if(bbsOneData==null){
			flag=0;
		}
		return flag;
	}
	/*
	 * 是否调出news List成功
	 */
	public int isNewsListData() throws ClassNotFoundException, SQLException{
		int flag=1;
		newsListData=dao.selectNewsListData();
		if(newsListData.size()!=15){
			flag=0;
		}
		return flag;
	}
	/*
	 * 是否调出一条新闻成功
	 */
	public int isNewsOneData(String newsTitle) throws ClassNotFoundException, SQLException{
		int flag=1;
		newsOneData=dao.selectNewsOneData(newsTitle);
		if(newsOneData==null){
			flag=0;
		}
		return flag;
	}
	/*
	 * 调出某类药库中所有关键数据
	 */
	public int isDrugListData(String strDrugKind) throws ClassNotFoundException, SQLException{
		int flag=1;
		drugListData=dao.selectDrugListData(strDrugKind);
		if(drugListData.isEmpty()){
			flag=0;
		}
		return flag;
	}
	/*
	 *  调出某类药库中某种药品详细数据
	 */
	public int isDrugOneData(String strDrugKind,String strDrugId) throws ClassNotFoundException, SQLException{
		int flag=1;
		drugOneData=dao.selectDrugOneData(strDrugKind,strDrugId);
		if(drugOneData==null){
			flag=0;
		}
		return flag;
	}
	/*
	 * 联合查询，是否查有数据
	 */
	public int isDrugMapData(String[] str) throws ClassNotFoundException, SQLException{
		int flag=1;
		drugMapData=dao.selectDrugMapData(str);
		if(drugMapData==null&&drugMapData.isEmpty()){
			flag=0;
		}
		return flag;
	}
}

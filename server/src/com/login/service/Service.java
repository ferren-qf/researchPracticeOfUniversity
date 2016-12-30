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
 * ��MVC�еĿ��Ʋ㣬����û����Ƿ��Ѿ�ע�ᣬע���û�����½ʱ����û����������Ƿ�ƥ��
 * ���ж��Ƿ��У������У�
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
	 * ����û����Ƿ��ѱ�ע���
	 * @param username �û�������û���
	 * @return 1Ϊ��ע�����0Ϊû��ע��� 
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
	 * �����û�и�ҩƷ
	 * 1Ϊ�У�0Ϊû��
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
	 * �û�ע��
	 * ���û���Ϣ�������ݿ���
	 * @param user �û���Ϣ�ķ�װ��
	 * @return 1 �ɹ����룬0û�гɹ�����
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int register (User user) throws ClassNotFoundException, SQLException{
		
		return dao.insert(user);
	}
	
	/**
	 * �û���¼
	 * �����û��������ݿ��в����û�,���û����������������͸��û������ݿ��д洢������һ�£�����Ե�¼
	 * @param username �û�������û���
	 * @param password �û����������
	 * @return 1 ���Ե�¼��0 �����Ե�¼
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
	 *�ж��Ƿ��и����ַ���ҩƷ
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
	 * �ж���bbs�����Ƿ������15������
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
	 * �Ƿ�������ӳɹ�
	 */
	public int isBBSPosting(String bbsTitle,String bbsTime,String bbsContent) throws ClassNotFoundException, SQLException{
		int flag=1;
		flag=dao.storagePost(bbsTitle, bbsTime,bbsContent);		
		return flag;
	}
	/*
	 * �Ƿ����һ�����ӳɹ�
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
	 * �Ƿ����news List�ɹ�
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
	 * �Ƿ����һ�����ųɹ�
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
	 * ����ĳ��ҩ�������йؼ�����
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
	 *  ����ĳ��ҩ����ĳ��ҩƷ��ϸ����
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
	 * ���ϲ�ѯ���Ƿ��������
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

package com.login.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.login.bean.BBS;
import com.login.bean.ChemicalDrugs;
import com.login.bean.Drug;
import com.login.bean.HerbsAndPieces;
import com.login.bean.News;
import com.login.bean.PharmaceuticalExcipients;
import com.login.bean.ProprietaryChineseMedicines;
import com.login.bean.User;
import com.login.bean.VegetableFatsAndExtracts;
import com.server.util.AddInfomation;


public class DaoImpl {
	public String strDrugKind;
	Dao dao = new Dao();
	private static AddInfomation addInformation=new AddInfomation();
	
	
	/*
	 * 查询数据库中的一条数据
	 * 根据主键查询数据库中一条数据，主要用于检查数据库中是否存在此用户
	 * @param name 用户名
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public User selectOne(String username) throws ClassNotFoundException, SQLException{
		User user = new User();
		String str = "select * from user u where u.username = '"+ username+"'";
		Connection con = dao.getCon();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(str);
		while (rs.next()) {
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setSex(rs.getString("sex"));
			user.setCommunication_way(rs.getString("communication_way"));
			user.setCommunication_num(rs.getString("communication_num"));
		}
		dao.closeAll(rs, statement, con);
		return user;
	}
	/*
	 * 查询数据数据库中的一条数据
	 *	drug
	 */
	public Object selectDrugOne(String drugcname) throws ClassNotFoundException, SQLException{
		String str1 ="select * from chemicalDrugs u where u.drugName = '"+ drugcname+"'";
		String str2 ="select * from herbsAndPieces u where u.herbsName = '"+ drugcname+"'";
		String str3 ="select * from pharmaceuticalExcipients u where u.excipientName = '"+ drugcname+"'";
		String str4 ="select * from proprietaryChineseMedicines u where u.medicineName = '"+ drugcname+"'";
		String str5 ="select * from vegetableFatsAndExtracts u where u.plantsName = '"+ drugcname+"'";
		Connection con = dao.getCon();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(str1);
		while (rs.next()) {
			ChemicalDrugs drugOneData=new ChemicalDrugs();
			addInformation.addChemicalDrugs(drugOneData,rs);
			dao.closeAll(rs, statement, con);
			this.strDrugKind="chemicalDrugs";
			return drugOneData;
		}
		rs=statement.executeQuery(str2);
		while (rs.next()) {
			HerbsAndPieces drugOneData=new HerbsAndPieces();
			addInformation.addHerbsAndPieces(drugOneData,rs);
			dao.closeAll(rs, statement, con);
			this.strDrugKind="herbsAndPieces";
			return drugOneData;
		}
		rs=statement.executeQuery(str3);
		while (rs.next()) {
			PharmaceuticalExcipients drugOneData=new PharmaceuticalExcipients();
			addInformation.addPharmaceuticalExcipients(drugOneData,rs);
			dao.closeAll(rs, statement, con);
			this.strDrugKind="pharmaceuticalExcipients";
			return drugOneData;
		}
		rs=statement.executeQuery(str4);
		while (rs.next()) {
			ProprietaryChineseMedicines drugOneData=new ProprietaryChineseMedicines();
			addInformation.addProprietaryChineseMedicines(drugOneData,rs);
			dao.closeAll(rs, statement, con);
			this.strDrugKind="proprietaryChineseMedicines";
			return drugOneData;
		}
		rs=statement.executeQuery(str5);
		while (rs.next()) {
			VegetableFatsAndExtracts drugOneData=new VegetableFatsAndExtracts();
			addInformation.addVegetableFatsAndExtracts(drugOneData,rs);
			dao.closeAll(rs, statement, con);
			this.strDrugKind="vegetableFatsAndExtracts";
			return drugOneData;
		}
		return null;
	}
	
	
	/*
	 * 向数据库中插入一条数据
	 * 用于用户注册
	 * @param user
	 * @return 1表示插入成功，0表示插入失败
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	public int insert(User user) throws ClassNotFoundException{
		// TODO Auto-generated method stub
		String sql = "insert into user values ('"+ user.getUsername()+"','"+ user.getPassword()+
		"','"+ user.getSex()+"','"+ user.getCommunication_way()+"','"+ user.getCommunication_num()+
		"')";
		Statement st;
		int result;
		try {
			Connection con = dao.getCon();
			st = con.createStatement();
			//执行修改返回2
			result = st.executeUpdate(sql);
		} catch (SQLException e) {	
			return 0;
		}
		return 1;
	}
	/*
	 * 联想查询
	 * 
	 */
	public List<String>/*StringBuffer*/ selectDrugLenovo(String drugLenovo) throws ClassNotFoundException, SQLException{
		List<String> drugLenovos=new ArrayList<String>();
		ResultSet rs;
//		StringBuffer drugLenovos=new StringBuffer();
		String str="SELECT * FROM chemicaldrugs WHERE drugName like '%"+drugLenovo+"%' ";
		String str2="SELECT * FROM herbsAndPieces WHERE herbsName like '%"+drugLenovo+"%' " ;
		String str3="SELECT * FROM pharmaceuticalExcipients WHERE excipientName like '%"+drugLenovo+"%' ";
		String str4="SELECT * FROM proprietaryChineseMedicines WHERE medicineName like '%"+drugLenovo+"%' ";
		String str5="SELECT * FROM vegetableFatsAndExtracts WHERE plantsName like '%"+drugLenovo+"%' ";
		Connection con = dao.getCon();
		Statement statement = con.createStatement();
		rs = statement.executeQuery(str);
		while (rs.next()) {
			drugLenovos.add(rs.getString("drugName"));
		}
		rs = statement.executeQuery(str2);
		while (rs.next()) {
			drugLenovos.add(rs.getString("herbsName"));
		}
		rs = statement.executeQuery(str3);
		while (rs.next()) {
			drugLenovos.add(rs.getString("excipientName"));
		}
		rs = statement.executeQuery(str4);
		while (rs.next()) {
			drugLenovos.add(rs.getString("medicineName"));
		}
		rs = statement.executeQuery(str5);
		while (rs.next()) {
			drugLenovos.add(rs.getString("plantsName"));
		}
		dao.closeAll(rs, statement, con);
		return drugLenovos;
	}
	/*
	 * 查询寻帖子(15条)
	 * 
	 */
	public List<BBS> selectBBSListData() throws ClassNotFoundException, SQLException{
		List<BBS> bbsListData=new ArrayList<BBS> () ;	
		String str = "select * from bbs ";
		Connection con = dao.getCon();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(str);
		while (rs.next()) {
			BBS bbs =new BBS();
			bbs.setBbsTitle(rs.getString("bbsTitle"));
			bbs.setBbsTime(rs.getString("bbsTime"));
			bbs.setBbsId(rs.getString("bbsId"));
			bbsListData.add(bbs);
		}
		dao.closeAll(rs, statement, con);
		
		return bbsListData;
	}
	/*
	 * 存入帖子
	 */
	public int storagePost(String bbsTitle,String bbsTime,String bbsContent) throws ClassNotFoundException, SQLException{
		String sql = "INSERT INTO bbs(bbsTitle,bbsTime,bbsContent) VALUES";
		Connection con = dao.getCon();
	    sql += "("
	            + "'" +bbsTitle + "',"
	            + "'" + bbsTime + "',"
	            + "'" + bbsContent +"'"
	            + ")";
	    Statement statement = con.createStatement();
	    int nRows = statement.executeUpdate(sql);
	    dao.closeAll(null, statement, con);
	    return 1;
	   
	}
	/*
	 * 查询一篇帖子
	 */
	public BBS selectBBSOneData(String bbsId)throws ClassNotFoundException, SQLException{
		BBS bbsOneData=new BBS() ;	
		String str = "select * from bbs u where u.bbsId = '"+ bbsId+"'";
		Connection con = dao.getCon();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(str);
		while (rs.next()) {	
		bbsOneData.setBbsTitle(rs.getString("bbsTitle"));
		bbsOneData.setBbsTime(rs.getString("bbsTime"));
		bbsOneData.setBbsContent(rs.getString("bbsContent"));
//		bbsOneData.bbsPicturePath();
		dao.closeAll(rs, statement, con);	
		return bbsOneData;
		}
		return null;
	}
	
	/*
	 *所有新闻 
	 */
	public List<News> selectNewsListData() throws ClassNotFoundException, SQLException{
		List<News> newsListData=new ArrayList<News> () ;	
		String str = "select * from news ";
		Connection con = dao.getCon();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(str);
		while (rs.next()) {
			News news =new News();
			news.setNewsTitle(rs.getString("newsTitle"));
			news.setNewsTime(rs.getString("newsTime"));
//			news.setNewsContent(rs.getString("newsContent"));
			news.setNewsKind(rs.getString("newsKind"));
			newsListData.add(news);
		}
		dao.closeAll(rs, statement, con);	
		return newsListData;
	}
	/*
	 *一则新闻 
	 */
	public News selectNewsOneData(String newsTitle) throws ClassNotFoundException, SQLException{
		News newsOneData=new News () ;	
		String str = "select * from news u where u.newsTitle = '"+ newsTitle+"'";
		Connection con = dao.getCon();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(str);
		while (rs.next()) {	
		newsOneData.setNewsTitle(rs.getString("newsTitle"));
		newsOneData.setNewsTime(rs.getString("newsTime"));
		newsOneData.setNewsContent(rs.getString("newsContent"));
		newsOneData.setNewsKind(rs.getString("newsKind"));
		dao.closeAll(rs, statement, con);	
		return newsOneData;
		}
		return null;
	}
	/*
	 * 查询各类药品,入库
	 */
	public List selectDrugListData(String strDrugKind) throws ClassNotFoundException, SQLException{
		List drugListData=null;
		String str = "select * from "+strDrugKind;
		Connection con = dao.getCon();
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(str);
		if(strDrugKind.equals("chemicalDrugs")){
			drugListData=new ArrayList<ChemicalDrugs>();
			while (rs.next()) {
				addInformation.addChemicalDrugs(drugListData, rs);
			}			
		}else if(strDrugKind.equals("herbsAndPieces")){
			drugListData=new ArrayList<HerbsAndPieces>();
			while(rs.next()){
				addInformation.addHerbsAndPieces(drugListData, rs);
			}
		}else if(strDrugKind.equals("pharmaceuticalExcipients")){
			drugListData=new ArrayList<PharmaceuticalExcipients>();
			while(rs.next()){
				addInformation.addPharmaceuticalExcipients(drugListData, rs);
			}
		}else if(strDrugKind.equals("proprietaryChineseMedicines")){
			drugListData=new ArrayList<ProprietaryChineseMedicines>();
			while(rs.next()){
				addInformation.addProprietaryChineseMedicines(drugListData, rs);
			}
		}else if(strDrugKind.equals("vegetableFatsAndExtracts")){
			drugListData=new ArrayList<VegetableFatsAndExtracts>();
			while(rs.next()){
				addInformation.addVegetableFatsAndExtracts(drugListData, rs);
			}
		}
		dao.closeAll(rs, statement, con);	
		return drugListData;
	}
	/*
	 * 
	 * 联合查询
	 */
	public Map<String,List>selectDrugMapData(String str[])throws ClassNotFoundException, SQLException{
		Map<String,List> map= new HashMap<String,List>();
		List drugListData0 =new ArrayList();
		List drugListData1 =new ArrayList();
		List drugListData2 =new ArrayList();
		List drugListData3 =new ArrayList();
		List drugListData4 =new ArrayList();
		Connection con = dao.getCon();
		Statement statement = con.createStatement();
		ResultSet rs=null;
		String str1="SELECT * FROM chemicaldrugs WHERE drugName like '%"+str[0]+"%' " +
				                           "and drugPinyinName like '%"+str[1]+"%' " +
				                           "and drugEnglishName like '%"+str[2]+"%' " + 
				                           "and drugCategory like '%"+str[3]+"%' " +
				                           "and drugCharacters like '%"+str[4]+"%' " +
				                           "and drugStorage like '%"+str[5]+"%' " +
				                           "and drugDescription like '%"+str[6]+"%' ";
		
		String str2="SELECT * FROM herbsAndPieces WHERE herbsName like '%"+str[0]+"%' " + 
				                           "and herbsPinyinName like '%"+str[1]+"%' " +
                                           "and herbsEnglishName like '%"+str[2]+"%' " + 
//                                           "and herbsDescription like '%"+str[3]+"%' " +
                                           "and herbsCharacters like '%"+str[4]+"%' " +
                                           "and herbsStorage like '%"+str[5]+"%' " +
                                           "and herbsDescription like '%"+str[6]+"%' ";
		
		String str3="SELECT * FROM pharmaceuticalExcipients WHERE excipientName like '%"+str[0]+"%' " + 
				                           "and excipientPinyinName like '%"+str[1]+"%' " +
                                           "and excipientEnglishName like '%"+str[2]+"%' " + 
//                                           "and excipientDescription like '%"+str[3]+"%' " +
                                           "and excipientCharacters like '%"+str[4]+"%' " +
                                           "and excipientStorage like '%"+str[5]+"%' " +
                                           "and excipientDescription like '%"+str[6]+"%' ";
		
		String str4="SELECT * FROM proprietaryChineseMedicines WHERE medicineName like '%"+str[0]+"%' " + 
				                           "and medicinePinyinName like '%"+str[1]+"%' " +
//                                           "and medicineDescription like '%"+str[2]+"%' " + 
//                                           "and medicineDescription like '%"+str[3]+"%' " +
                                           "and medicinePrescriptions like '%"+str[4]+"%' " +
                                           "and medicineStorage like '%"+str[5]+"%' " +
                                           "and medicineDescription like '%"+str[6]+"%' ";
		
		String str5="SELECT * FROM vegetableFatsAndExtracts WHERE plantsName like '%"+str[0]+"%' " + 
				                           "and plantsPinyinName like '%"+str[1]+"%' " +
                                           "and plantsEnglishName like '%"+str[2]+"%' " + 
//                                           "and drugEnglishName like '%"+str[3]+"%' " +
                                           "and plantsCharacters like '%"+str[4]+"%' " +
                                           "and plantsStorage like '%"+str[5]+"%' " +
                                           "and plantsDescription like '%"+str[6]+"%' ";
		rs=statement.executeQuery(str1);
		while(rs.next()){
			ChemicalDrugs drugOneData=new ChemicalDrugs();
			addInformation.addChemicalDrugs(drugOneData, rs);
			drugListData0.add(drugOneData);
		}
		if(drugListData0.size()!=0){
		map.put("chemicalDrugs", drugListData0);
		}
		//*****************************
		rs=statement.executeQuery(str2);
		while(rs.next()){
			ChemicalDrugs drugOneData=new ChemicalDrugs();
			addInformation.addChemicalDrugs(drugOneData, rs);
			drugListData1.add(drugOneData);
		}
		if(drugListData1.size()!=0){
		map.put("herbsAndPieces", drugListData1);
		}
		//******************************
		rs=statement.executeQuery(str3);
		while(rs.next()){
			PharmaceuticalExcipients drugOneData=new PharmaceuticalExcipients();
			addInformation.addPharmaceuticalExcipients(drugOneData, rs);
			drugListData2.add(drugOneData);
		}
		if(drugListData2.size()!=0){
		map.put("pharmaceuticalExcipients", drugListData2);
		}
		//******************************
		rs=statement.executeQuery(str4);
		while(rs.next()){
			ProprietaryChineseMedicines drugOneData=new ProprietaryChineseMedicines();
			addInformation.addProprietaryChineseMedicines(drugOneData, rs);
			drugListData3.add(drugOneData);
		}
		if(drugListData3.size()!=0){
		map.put("proprietaryChineseMedicines", drugListData3);
		}
		//******************************
		rs=statement.executeQuery(str5);
		while(rs.next()){
			VegetableFatsAndExtracts drugOneData=new VegetableFatsAndExtracts();
			addInformation.addVegetableFatsAndExtracts(drugOneData, rs);
			drugListData4.add(drugOneData);
		}
		if(drugListData4.size()!=0){
		map.put("vegetableFatsAndExtracts", drugListData4);
		}
		dao.closeAll(rs, statement, con);
		return map;
		
		
	}
	
	/*
	 * 查询各类药品 某种药品
	 */
	public Object selectDrugOneData(String strDrugKind,String strDrugId) throws ClassNotFoundException, SQLException{
		Connection con = dao.getCon();
		Statement statement = con.createStatement();
		ResultSet rs=null;
		if(strDrugKind.equals("chemicalDrugs")){
			String str1 ="select * from "+strDrugKind+" u where u.drugId = '"+ strDrugId+"'";
			rs = statement.executeQuery(str1);
			ChemicalDrugs drugOneData=new ChemicalDrugs();
			while (rs.next()) {
				addInformation.addChemicalDrugs(drugOneData, rs);
			}			
			dao.closeAll(rs, statement, con);	
			return drugOneData;
		}else if(strDrugKind.equals("herbsAndPieces")){
			String str2 ="select * from "+strDrugKind+" u where u.herbsId = '"+ strDrugId+"'";
			rs = statement.executeQuery(str2);
			HerbsAndPieces drugOneData=new HerbsAndPieces();
			while(rs.next()){
				addInformation.addHerbsAndPieces(drugOneData, rs);
			}
			dao.closeAll(rs, statement, con);	
			return drugOneData;
		}else if(strDrugKind.equals("pharmaceuticalExcipients")){
			String str3 ="select * from "+strDrugKind+" u where u.excipientId = '"+ strDrugId+"'";
			rs = statement.executeQuery(str3);
			PharmaceuticalExcipients drugOneData=new PharmaceuticalExcipients();
			while(rs.next()){
				addInformation.addPharmaceuticalExcipients(drugOneData, rs);
			}
			dao.closeAll(rs, statement, con);	
			return drugOneData;
		}else if(strDrugKind.equals("proprietaryChineseMedicines")){
			String str4 ="select * from "+strDrugKind+" u where u.medicineId = '"+ strDrugId+"'";
			rs = statement.executeQuery(str4);
			ProprietaryChineseMedicines drugOneData=new ProprietaryChineseMedicines();
			while(rs.next()){
				addInformation.addProprietaryChineseMedicines(drugOneData, rs);
			}
			dao.closeAll(rs, statement, con);	
			return drugOneData;
		}else if(strDrugKind.equals("vegetableFatsAndExtracts")){
			String str5 ="select * from "+strDrugKind+" u where u.plantsId = '"+ strDrugId+"'";
			rs = statement.executeQuery(str5);
			VegetableFatsAndExtracts drugOneData=new VegetableFatsAndExtracts();
			while(rs.next()){
				addInformation.addVegetableFatsAndExtracts(drugOneData, rs);
			}
			dao.closeAll(rs, statement, con);	
			return drugOneData;
		}else{
			dao.closeAll(rs, statement, con);	
			return null;
		}
	}
	
	/*
	 * 
	 */
}

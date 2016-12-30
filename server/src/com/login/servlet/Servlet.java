package com.login.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.login.bean.BBS;
import com.login.bean.ChemicalDrugs;
import com.login.bean.Drug;
import com.login.bean.HerbsAndPieces;
import com.login.bean.News;
import com.login.bean.PharmaceuticalExcipients;
import com.login.bean.ProprietaryChineseMedicines;
import com.login.bean.User;
import com.login.bean.VegetableFatsAndExtracts;
import com.login.service.Service;

public class Servlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Servlet() {
		super();
		System.out.println("Servlet()");
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get = ");
		response.setContentType("text/html");
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 *发送
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("name = ");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		//0
		String username = null;
		String password = null;
		User user = new User();
		Service service = new Service();		
		String drugCName=null;
		Drug drug =new Drug();
		//4
		String drugLenovo;
		List drugLenovos;
		//5
		String bbsData;
		List bbsListData;
		//6
		String bbsTitle;
		String bbsTime;
		String bbsContent;
		//7
		List<News> newsListData;
//		String drugCName=null;
//		Drug drug=new Drug();
		//8
		News newsOneData;
		String newsTitle;
		//11
		List drugListData;
		
		/**
		 * index 用于标记将要进行什么操作， 
		 * 0——用户登录 
		 * 1——查找该用户名是否已经存在；
		 * 2——用户注册
		 * 3——查询药品
		 * 
		 * 11——chemicalDrugs  HerbsAndPieces PharmaceuticalExcipients ProprietaryChineseMedicines VegetableFatsAndExtracts
		 * 
		 */
		
		
		int index = Integer.parseInt(request.getParameter("index"));
		System.out.println(index);
		
		int myResponse = 0;
		try {
			response.setCharacterEncoding("UTF-8");
			
			switch (index) {
			case 0:
				//登陆
				username = request.getParameter("username");
				password = request.getParameter("password");
				myResponse =service.canLogin(username, password);
				System.out.println("login--->"+ username +" "+ password);
				OutputStream out = response.getOutputStream();
				out.write(myResponse);
				out.flush();
				out.close();
				break;
			case 1:
				//查找该用户名是否存在
				username = request.getParameter("username");
				myResponse =service.checkUsername(username);
				System.out.println("checkUser--->" + " "+username);
				OutputStream out1 = response.getOutputStream();
				out1.write(myResponse);
				out1.flush();
				out1.close();					
				break;
			case 2:
				//注册
				user.setUsername(request.getParameter("username"));
				user.setPassword(request.getParameter("password"));
//				user.setSex(request.getParameter("sex"));
//				user.setCommunication_way(request
//						.getParameter("communication_way"));
//				user.setCommunication_num(request
//						.getParameter("communication_num"));
				myResponse =service.register(user);
				if(myResponse==1){
					System.out.println("register--->"+ username + " "+password);
					OutputStream out2 = response.getOutputStream();
					out2.write(myResponse);
					out2.flush();
					out2.close();					
				}else{
					
				}
				break;
			case 3:
				drugCName=request.getParameter("drugCName");
//				判断是否有相应药品
				myResponse=service.canSerachDrug(drugCName);				
				if(myResponse==1){
					String strDrugKind=service.strDrugKind;
					Gson gson =new Gson();
					List list=new ArrayList();
					list.add(strDrugKind);
					list.add(service.drugOneData);
					String json=gson.toJson(list);
					PrintWriter outw =response.getWriter();
					outw.write(json.toString());
					outw.flush();
					outw.close();
//					
				}else{
					System.out.println(drugCName);
					System.out.println("serach--->"+" *没有*"/*drugCName*/);
					PrintWriter outw =response.getWriter();
					outw.write("0");
					outw.flush();
					outw.close();
				}
				break;
			case 4://联想首字符
				drugLenovo=request.getParameter("drugLenovo");
				//判断是否有该首字符的药品
				myResponse=service.canLenovo(drugLenovo);
				if(myResponse==1){
					drugLenovos=service.drugLenovos;
					Gson gson=new Gson();
					String json=gson.toJson(drugLenovos);
					PrintWriter outw =response.getWriter();
					outw.write(json.toString());
					outw.flush();
					outw.close();
				}
			
				break;
			case 5://调服务端帖子，发送至客户端（展示帖子）
				myResponse=service.isBBSListData();
				bbsListData=service.bbsListData;
				Gson gson =new Gson();
				String json=gson.toJson(bbsListData);
				PrintWriter outw=response.getWriter();
				outw.write(json.toString());
				outw.flush();
				outw.close();	
				break;
			case 6://接收一位用户帖子，存储在服务端
				bbsTitle=request.getParameter("bbsTitle");
				bbsTime=request.getParameter("bbsTime");
				bbsContent=request.getParameter("bbsContent");
				myResponse=service.isBBSPosting(bbsTitle, bbsTime,bbsContent);
				OutputStream out6 = response.getOutputStream();
				out6.write(myResponse);
				out6.flush();
				out6.close();
				break;
			case 7://从数据库调出所有新闻粗略数据
				myResponse=service.isNewsListData();
				newsListData=service.newsListData;
				Gson gson7 =new Gson();
				String json7=gson7.toJson(newsListData);
				PrintWriter outw7=response.getWriter();
				outw7.write(json7.toString());
				outw7.flush();
				outw7.close();
				break;
			case 8://从数据库调出一组新闻详细数据
				newsTitle = request.getParameter("newsTitle");
				myResponse=service.isNewsOneData(newsTitle);
				if(myResponse==1){
				newsOneData=service.newsOneData;
				Gson gson8 =new Gson();
				String json8=gson8.toJson(newsOneData);
				PrintWriter outw8=response.getWriter();
				outw8.write(json8.toString());
				outw8.flush();
				outw8.close();}else{
					System.out.println("没有找到该新闻");
				}
				break;
			case 11:
				//11——chemicalDrugs  HerbsAndPieces PharmaceuticalExcipients ProprietaryChineseMedicines VegetableFatsAndExtracts
				String strDrugKind=request.getParameter("strDrugKind");
				String drugKind=null;
				if(strDrugKind.equals("1")){
					drugKind="chemicalDrugs";
				}else if(strDrugKind.equals("2")){
					drugKind="vegetableFatsAndExtracts";
				}else if(strDrugKind.equals("3")){
					drugKind="pharmaceuticalExcipients";
				}else if(strDrugKind.equals("5")){
					drugKind="proprietaryChineseMedicines";
				}else if(strDrugKind.equals("6")){
					drugKind="herbsAndPieces";
				}
				myResponse=service.isDrugListData(drugKind);
				if(myResponse==1){
					drugListData=service.drugListData;
					Gson gson11 =new Gson();
					String json11=gson11.toJson(drugListData);
					PrintWriter outw11=response.getWriter();
					outw11.write(json11.toString());
					outw11.flush();
					outw11.close();
				}
				break;
			case 12:
				Object drugOneData=null;
				String strDrugKind2=request.getParameter("strDrugKind");
				String strDrugId=request.getParameter("strDrugId");
				myResponse=service.isDrugOneData(strDrugKind2,strDrugId);
				if(myResponse==1){
					if(strDrugKind2.equals("chemicalDrugs")){
//						drugKind="chemicalDrugs";
						drugOneData=(ChemicalDrugs) service.drugOneData;
					}else if(strDrugKind2.equals("vegetableFatsAndExtracts")){
//						drugKind="vegetableFatsAndExtracts";
						drugOneData=(VegetableFatsAndExtracts) service.drugOneData;
					}else if(strDrugKind2.equals("pharmaceuticalExcipients")){
//						drugKind="pharmaceuticalExcipients";
						drugOneData=(PharmaceuticalExcipients) service.drugOneData;
					}else if(strDrugKind2.equals("proprietaryChineseMedicines")){
//						drugKind="proprietaryChineseMedicines";
						drugOneData=(ProprietaryChineseMedicines) service.drugOneData;
					}else if(strDrugKind2.equals("herbsAndPieces")){
//						drugKind="herbsAndPieces";
						drugOneData=(HerbsAndPieces) service.drugOneData;
					}
					Gson gson12 =new Gson();
					String json11=gson12.toJson(drugOneData);
					PrintWriter outw12=response.getWriter();
					outw12.write(json11.toString());
					outw12.flush();
					outw12.close();
				}
				break;
			case 13:
				String strChinaName=request.getParameter("strChinaName");
				String strPingyinName=request.getParameter("strPingyinName");
				String strEnglishName=request.getParameter("strEnglishName");
				String strCategory= request.getParameter("strCategory");
				String strPreparetion = request.getParameter("strCharacters");
				String strStorage= request.getParameter("strStorage");
				String strDescription= request.getParameter("strDescription");	
				String[] str=new String[]{strChinaName,strPingyinName,strEnglishName,strCategory,
										strPreparetion,strStorage,strDescription};
				myResponse=service.isDrugMapData(str);
				if(myResponse==1){
					Map<String,List> drugMapList=service.drugMapData;
					List<ChemicalDrugs> chemicalDrugsList=drugMapList.get("chemicalDrugs");
					List<HerbsAndPieces> herbsAndPiecesList=drugMapList.get("herbsAndPieces");
					List<PharmaceuticalExcipients> pharmaceuticalExcipientsList=drugMapList.get("pharmaceuticalExcipients");
					List<ProprietaryChineseMedicines> proprietaryChineseMedicinesList=drugMapList.get("proprietaryChineseMedicines");
					List<VegetableFatsAndExtracts> vegetableFatsAndExtractsList=drugMapList.get("vegetableFatsAndExtracts");
//					List<HerbsAndPieces> herbsAndPiecesList=drugMapList.get("chemicalDrugs");
					Gson gson13 =new Gson();
					String jsonDrugs1=gson13.toJson(chemicalDrugsList);
					String jsonDrugs2=gson13.toJson(herbsAndPiecesList);
					String jsonDrugs3=gson13.toJson(pharmaceuticalExcipientsList);
					String jsonDrugs4=gson13.toJson(proprietaryChineseMedicinesList);
					String jsonDrugs5=gson13.toJson(vegetableFatsAndExtractsList);
//					String jsonDrugs6=gson13.toJson(chemicalDrugsList);
					String[] jsonList=new String[]{jsonDrugs1,jsonDrugs2,jsonDrugs3,jsonDrugs4,jsonDrugs5};
					String json13=gson13.toJson(jsonList);
					PrintWriter outw13=response.getWriter();
					outw13.write(json13.toString());
					outw13.flush();
					outw13.close();
				}
				break;
			case 25:
				BBS bbsOneData;
				String strBBSId=request.getParameter("strBbsId");
				myResponse=service.isBBSOneData(strBBSId);
				bbsOneData=service.bbsOneData;
				Gson gson25 =new Gson();
				String json25=gson25.toJson(bbsOneData);
				PrintWriter outw25=response.getWriter();
				if(myResponse==1){
					outw25.write(json25.toString());
					outw25.flush();
					outw25.close();					
				}else{
					outw25.write(myResponse);
					outw25.flush();
					outw25.close();
				}
				break;
			default:
				break;
			}
			
//			OutputStream out = response.getOutputStream();
//			out.write(myResponse);
//			out.flush();
//			out.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
//daoimpl----->service--->servlet
	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		System.out.println("init()");
	}

}

package com.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	//驱动程序名
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	//URL指向要访问的数据库名
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/project";
	//MySql配置时的用户名密码
	private final static String USER = "root";
	private final static String PWD = "15963";
	public Connection getCon() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL,USER,PWD);
		System.out.println(con);
		return con;
	}
	
	public void closeAll(ResultSet rs , Statement st , Connection con) throws SQLException{
		if(rs != null){
			rs.close();
		}
		if(st != null){
			st.close();
		}
		if(con != null){
			con.close();
		}
	}
	
	
}

package com.jxutcm.model;

public class User {
	private String username = null;
	private String password = null;
	private String sex = null;
	private String communication_way = null;
	private String communication_num = null;
	
	public User(){}
	public User(String username, String password,String sex,String communication_way,String communication_num){
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.communication_way = communication_way;
		this.communication_num = communication_num;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCommunication_way() {
		return communication_way;
	}
	public void setCommunication_way(String communicationWay) {
		communication_way = communicationWay;
	}
	public String getCommunication_num() {
		return communication_num;
	}
	public void setCommunication_num(String communicationNum) {
		communication_num = communicationNum;
	}
	
}

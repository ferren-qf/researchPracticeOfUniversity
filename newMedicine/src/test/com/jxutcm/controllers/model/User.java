package test.com.jxutcm.controllers.model;

/**
 * User
 * �û���װ�࣬�û������԰����û��������롢�Ա���ϵ��ʽ��
 * 
 */
public class User {
	/**
	 * username �û���
	 * password ����
	 * sex �û��Ա�
	 * communication �û���ϵ��ʽ
	 */
	private String username = null;
	private String password = null;
	private String sex = null;
	private String communication = null;
	private String myCommunication_content = null;
	
	public String getMyCommunication_content() {
		return myCommunication_content;
	}
	public void setMyCommunication_content(String myCommunication_content) {
		this.myCommunication_content = myCommunication_content;
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
	public String getCommunication() {
		return communication;
	}
	public void setCommunication(String communication) {
		this.communication = communication;
	}

}

package test2;

 

public class User {
	//	  uid                char(32) not null,
	//	   account              varchar(20),
	//	   password             varchar(20),
	//	   name                 varchar(5),
	private String uid;
	private String account;
	private String passowrd;
	private String name;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassowrd() {
		return passowrd;
	}
	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

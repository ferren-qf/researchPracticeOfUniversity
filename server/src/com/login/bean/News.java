package com.login.bean;

public class News {
	private String newsKind ;
	private String newsTitle;
	private	String newsContent;
	private String newsTime;
	public News(){}
	public News(String newsKind, String newsTitle, String newsContent,
			String newsTime) {
		super();
		this.newsKind = newsKind;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsTime = newsTime;
	}
	public String getNewsKind() {
		return newsKind;
	}
	public void setNewsKind(String newsKind) {
		this.newsKind = newsKind;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public String getNewsTime() {
		return newsTime;
	}
	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}
	
	
}

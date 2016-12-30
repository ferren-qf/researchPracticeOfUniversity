package com.jxutcm.controllers.drugsearch;

public class Drug {
	private String drugName;
	private String drugProducingArea;
	private String drugFunctions;
	private String drugPicturePath;
	
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String bookName) {
		this.drugName = bookName;
	}
	public String getDrugProducingArea() {
		return drugProducingArea;
	}
	public void setDrugProducingArea(String bookMessage) {
		this.drugProducingArea = bookMessage;
	}
	public String getDrugFunctions() {
		return drugFunctions;
	}
	public void setDrugFunctions(String bookSynopsis) {
		this.drugFunctions = bookSynopsis;
	}
	public String getDrugPicturePath() {
		return drugPicturePath;
	}
	public void setDrugPicturePath(String bookPicturePath) {
		this.drugPicturePath = bookPicturePath;
	}
	
}

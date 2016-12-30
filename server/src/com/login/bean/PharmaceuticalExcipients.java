package com.login.bean;
/**
 * 药用辅料表
 * @author doyouknow
 *
 */

public class PharmaceuticalExcipients extends Drug{
	private String excipientId;//辅料序号
	private String excipientName;//辅料名称
	private String excipientPinyinName;//辅料拼音
	private String excipientEnglishName;//辅料英文名称
	private String excipientDescription;//辅料描述
	private String excipientCharacters;//辅料外观
	private String excipientCategory;//辅料类型
	private String excipientStorage;//辅料储藏方法
	private String excipientCategoryId;//类型序号
	
	public String getExcipientId() {
		return excipientId;
	}
	public void setExcipientId(String excipientId) {
		this.excipientId = excipientId;
	}
	public String getExcipientName() {
		return excipientName;
	}
	public void setExcipientName(String excipientName) {
		this.excipientName = excipientName;
	}
	public String getExcipientPinyinName() {
		return excipientPinyinName;
	}
	public void setExcipientPinyinName(String excipientPinyinName) {
		this.excipientPinyinName = excipientPinyinName;
	}
	public String getExcipientEnglishName() {
		return excipientEnglishName;
	}
	public void setExcipientEnglishName(String excipientEnglishName) {
		this.excipientEnglishName = excipientEnglishName;
	}
	public String getExcipientDescription() {
		return excipientDescription;
	}
	public void setExcipientDescription(String excipientDescription) {
		this.excipientDescription = excipientDescription;
	}
	public String getExcipientCharacters() {
		return excipientCharacters;
	}
	public void setExcipientCharacters(String excipientCharacters) {
		this.excipientCharacters = excipientCharacters;
	}
	public String getExcipientCategory() {
		return excipientCategory;
	}
	public void setExcipientCategory(String excipientCategory) {
		this.excipientCategory = excipientCategory;
	}
	public String getExcipientStorage() {
		return excipientStorage;
	}
	public void setExcipientStorage(String excipientStorage) {
		this.excipientStorage = excipientStorage;
	}
	public String getExcipientCategoryId() {
		return excipientCategoryId;
	}
	public void setExcipientCategoryId(String excipientCategoryId) {
		this.excipientCategoryId = excipientCategoryId;
	}
	
	
	
}

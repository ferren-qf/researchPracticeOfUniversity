package com.jxutcm.model;
/**
 * 植物油脂及提取物表
 * @author doyouknow
 *
 */
public class VegetableFatsAndExtracts extends Drug{
	private String plantsId;//植物序号
	private String plantsName;//植物名称
	private String plantsPinyinName;//植物拼音名称
	private String plantsEnglishName;//植物英文名
	private String plantsDescription;//植物描述
	private String plantsMethod;//植物使用方法
	private String plantsCharacters;//植物外观
	private String plantsStorage;//植物储藏方法
	private String plantsPreparation;//植物服用方法
	private String hasPreparation;// ————
	public String getPlantsId() {
		return plantsId;
	}
	public void setPlantsId(String plantsId) {
		this.plantsId = plantsId;
	}
	public String getPlantsName() {
		return plantsName;
	}
	public void setPlantsName(String plantsName) {
		this.plantsName = plantsName;
	}
	public String getPlantsPinyinName() {
		return plantsPinyinName;
	}
	public void setPlantsPinyinName(String plantsPinyinName) {
		this.plantsPinyinName = plantsPinyinName;
	}
	public String getPlantsEnglishName() {
		return plantsEnglishName;
	}
	public void setPlantsEnglishName(String plantsEnglishName) {
		this.plantsEnglishName = plantsEnglishName;
	}
	public String getPlantsDescription() {
		return plantsDescription;
	}
	public void setPlantsDescription(String plantsDescription) {
		this.plantsDescription = plantsDescription;
	}
	public String getPlantsMethod() {
		return plantsMethod;
	}
	public void setPlantsMethod(String plantsMethod) {
		this.plantsMethod = plantsMethod;
	}
	public String getPlantsCharacters() {
		return plantsCharacters;
	}
	public void setPlantsCharacters(String plantsCharacters) {
		this.plantsCharacters = plantsCharacters;
	}
	public String getPlantsStorage() {
		return plantsStorage;
	}
	public void setPlantsStorage(String plantsStorage) {
		this.plantsStorage = plantsStorage;
	}
	public String getPlantsPreparation() {
		return plantsPreparation;
	}
	public void setPlantsPreparation(String plantsPreparation) {
		this.plantsPreparation = plantsPreparation;
	}
	public String getHasPreparation() {
		return hasPreparation;
	}
	public void setHasPreparation(String hasPreparation) {
		this.hasPreparation = hasPreparation;
	}
	
}

package com.jxutcm.model;
/**
 * ��ѧҩƷ��
 * @author doyouknow
 *
 */

public class ChemicalDrugs extends Drug{
	private String drugId;
	private String drugName;
	private String drugPinyinName;//ҩƷ��ƴ������
	private String drugEnglishName;//ҩƷ��Ӣ������
	private String drugDescription;//ҩƷ������
	private String drugPrescriptions;//����ҩ
	private String drugCharacters;//ҩƷ���
	private String drugCategory;//ҩƷ����
	private String drugStandard;//ҩƷ���
	private String drugStorage;//ҩƷ���
	private String drugPreparation;//ҩƷҩ����ʹ��
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugPinyinName() {
		return drugPinyinName;
	}
	public void setDrugPinyinName(String drugPinyinName) {
		this.drugPinyinName = drugPinyinName;
	}
	public String getDrugEnglishName() {
		return drugEnglishName;
	}
	public void setDrugEnglishName(String drugEnglishName) {
		this.drugEnglishName = drugEnglishName;
	}
	public String getDrugDescription() {
		return drugDescription;
	}
	public void setDrugDescription(String drugDescription) {
		this.drugDescription = drugDescription;
	}
	public String getDrugPrescriptions() {
		return drugPrescriptions;
	}
	public void setDrugPrescriptions(String drugPrescriptions) {
		this.drugPrescriptions = drugPrescriptions;
	}
	public String getDrugCharacters() {
		return drugCharacters;
	}
	public void setDrugCharacters(String drugCharacters) {
		this.drugCharacters = drugCharacters;
	}
	public String getDrugCategory() {
		return drugCategory;
	}
	public void setDrugCategory(String drugCategory) {
		this.drugCategory = drugCategory;
	}
	public String getDrugStandard() {
		return drugStandard;
	}
	public void setDrugStandard(String drugStandard) {
		this.drugStandard = drugStandard;
	}
	public String getDrugStorage() {
		return drugStorage;
	}
	public void setDrugStorage(String drugStorage) {
		this.drugStorage = drugStorage;
	}
	public String getDrugPreparation() {
		return drugPreparation;
	}
	public void setDrugPreparation(String drugPreparation) {
		this.drugPreparation = drugPreparation;
	}

}

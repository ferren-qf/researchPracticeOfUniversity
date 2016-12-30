package com.jxutcm.model;
/**
 * 中成药表
 * @author doyouknow
 *
 */
public class ProprietaryChineseMedicines extends Drug{
	private String medicineId;//药品序号
	private String medicineName;//药物名
	private String medicinePinyinName;//药品拼音名称
	private String medicineDescription;//药品描述
	private String medicinePrescriptions;//药品处方
	private String medicineMethod;//药品使用方法
	private String medicineCharacters;//药品外观
	private String medicineFunctions;//药品功效
	private String medicineDosage;//药品服用方法
	private String medicineStandard;//药品规格
	private String medicineStorage;//药品储藏方法
	private String medicineNotices;//药品注意事项
	private String categoryId;//类型序号
	public String getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getMedicinePinyinName() {
		return medicinePinyinName;
	}
	public void setMedicinePinyinName(String medicinePinyinName) {
		this.medicinePinyinName = medicinePinyinName;
	}
	public String getMedicineDescription() {
		return medicineDescription;
	}
	public void setMedicineDescription(String medicineDescription) {
		this.medicineDescription = medicineDescription;
	}
	public String getMedicinePrescriptions() {
		return medicinePrescriptions;
	}
	public void setMedicinePrescriptions(String medicinePrescriptions) {
		this.medicinePrescriptions = medicinePrescriptions;
	}
	public String getMedicineMethod() {
		return medicineMethod;
	}
	public void setMedicineMethod(String medicineMethod) {
		this.medicineMethod = medicineMethod;
	}
	public String getMedicineCharacters() {
		return medicineCharacters;
	}
	public void setMedicineCharacters(String medicineCharacters) {
		this.medicineCharacters = medicineCharacters;
	}
	public String getMedicineFunctions() {
		return medicineFunctions;
	}
	public void setMedicineFunctions(String medicineFunctions) {
		this.medicineFunctions = medicineFunctions;
	}
	public String getMedicineDosage() {
		return medicineDosage;
	}
	public void setMedicineDosage(String medicineDosage) {
		this.medicineDosage = medicineDosage;
	}
	public String getMedicineStandard() {
		return medicineStandard;
	}
	public void setMedicineStandard(String medicineStandard) {
		this.medicineStandard = medicineStandard;
	}
	public String getMedicineStorage() {
		return medicineStorage;
	}
	public void setMedicineStorage(String medicineStorage) {
		this.medicineStorage = medicineStorage;
	}
	public String getMedicineNotices() {
		return medicineNotices;
	}
	public void setMedicineNotices(String medicineNotices) {
		this.medicineNotices = medicineNotices;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
}

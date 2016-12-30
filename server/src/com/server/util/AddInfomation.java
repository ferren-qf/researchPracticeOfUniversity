package com.server.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.login.bean.ChemicalDrugs;
import com.login.bean.Drug;
import com.login.bean.HerbsAndPieces;
import com.login.bean.PharmaceuticalExcipients;
import com.login.bean.ProprietaryChineseMedicines;
import com.login.bean.VegetableFatsAndExtracts;


/**
 * 添加药品信息至容器
 * @author doyouknow
 *
 */
public  class AddInfomation {
	//ChemicalDrugs
	public void addChemicalDrugs(List drugListData,ResultSet rs) throws SQLException{
		ChemicalDrugs chemicalDrugs =new ChemicalDrugs();
		chemicalDrugs.setDrugId(rs.getString("drugId"));
		chemicalDrugs.setDrugName(rs.getString("drugName"));
		chemicalDrugs.setDrugPinyinName(rs.getString("drugPinyinName"));
		drugListData.add(chemicalDrugs);
	}
	public void addChemicalDrugs(ChemicalDrugs drugOneData,ResultSet rs) throws SQLException{
		drugOneData.setDrugId(rs.getString("drugId"));
		drugOneData.setDrugName(rs.getString("drugName"));
		drugOneData.setDrugPinyinName(rs.getString("drugPinyinName"));
		drugOneData.setDrugEnglishName(rs.getString("drugEnglishName"));
		drugOneData.setDrugCharacters(rs.getString("drugCharacters"));
		drugOneData.setDrugDescription(rs.getString("drugDescription"));
		drugOneData.setDrugCategory(rs.getString("drugCategory"));
		drugOneData.setDrugPrescriptions(rs.getString("drugPrescriptions"));
		drugOneData.setDrugPreparation(rs.getString("drugPreparation"));
		drugOneData.setDrugStandard(rs.getString("drugStandard"));
		drugOneData.setDrugStorage(rs.getString("drugStorage"));
	}
	//HerbsAndPieces
	public void addHerbsAndPieces(List drugListData,ResultSet rs) throws SQLException{
		HerbsAndPieces drug =new HerbsAndPieces();
		drug.setHerbsId(rs.getString("herbsId"));
		drug.setHerbsName(rs.getString("herbsName"));
		drug.setHerbsPinyinName(rs.getString("herbsPinyinName"));
		drugListData.add(drug);
	}
	public void addHerbsAndPieces(HerbsAndPieces drugOneData,ResultSet rs) throws SQLException{
		drugOneData.setHerbsId(rs.getString("herbsId"));
		drugOneData.setHerbsName(rs.getString("herbsName"));
		drugOneData.setHerbsPinyinName(rs.getString("herbsPinyinName"));
		drugOneData.setHerbsEnglishName(rs.getString("herbsEnglishName"));
		drugOneData.setHerbsNotices(rs.getString("herbsNotices"));
		drugOneData.setHerbsDosage(rs.getString("herbsDosage"));
		drugOneData.setHerbsDescription(rs.getString("herbsDescription"));
		drugOneData.setHerbsCharacters(rs.getString("herbsCharacters"));
		drugOneData.setHerbsFunctions(rs.getString("herbsFunctions"));
	}
	//PharmaceuticalExcipients
	public void addPharmaceuticalExcipients(List drugListData,ResultSet rs) throws SQLException{
		if(rs.getString("excipientName")==null){
			return;
		}
		PharmaceuticalExcipients drug =new PharmaceuticalExcipients();
		drug.setExcipientId(rs.getString("excipientId"));
		drug.setExcipientName(rs.getString("excipientName"));
		drug.setExcipientPinyinName(rs.getString("excipientPinyinName"));
		drugListData.add(drug);
	}
	public void addPharmaceuticalExcipients(PharmaceuticalExcipients drugOneData,ResultSet rs) throws SQLException{
		if(rs.getString("excipientName")==null){
			return;
		}
		drugOneData.setExcipientId(rs.getString("excipientId"));
		drugOneData.setExcipientName(rs.getString("excipientName"));
		drugOneData.setExcipientPinyinName(rs.getString("excipientPinyinName"));
		drugOneData.setExcipientCharacters(rs.getString("excipientCharacters"));
		drugOneData.setExcipientDescription(rs.getString("excipientDescription"));
		drugOneData.setExcipientStorage(rs.getString("excipientStorage"));
		
	}
	//ProprietaryChineseMedicines
	public void addProprietaryChineseMedicines(List drugListData,ResultSet rs) throws SQLException{
		if(rs.getString("medicineName")==null){
			return;
		}
		ProprietaryChineseMedicines drug =new ProprietaryChineseMedicines();
		drug.setMedicineId(rs.getString("medicineId"));
		drug.setMedicineName(rs.getString("medicineName"));
		drug.setMedicinePinyinName(rs.getString("medicinePinyinName"));
		drugListData.add(drug);
	}
	public void addProprietaryChineseMedicines(ProprietaryChineseMedicines drugOneData,ResultSet rs) throws SQLException{
		if(rs.getString("medicineName")==null){
			return;
		}
		drugOneData.setMedicineId(rs.getString("medicineId"));
		drugOneData.setMedicineName(rs.getString("medicineName"));
		drugOneData.setMedicinePinyinName(rs.getString("medicinePinyinName"));
		drugOneData.setMedicineDescription(rs.getString("medicineDescription"));
		drugOneData.setMedicinePrescriptions(rs.getString("medicinePrescriptions"));
		drugOneData.setMedicineMethod(rs.getString("medicineMethod"));
		drugOneData.setMedicineCharacters(rs.getString("medicineCharacters"));
		drugOneData.setMedicineFunctions(rs.getString("medicineFunctions"));
		drugOneData.setMedicineDosage(rs.getString("medicineDosage"));
		drugOneData.setMedicineStandard(rs.getString("medicineStandard"));
		drugOneData.setMedicineStorage(rs.getString("medicineStorage"));
	}
	//VegetableFatsAndExtracts
	public void addVegetableFatsAndExtracts(List drugListData,ResultSet rs) throws SQLException{
		if(rs.getString("plantsName")==null){
			return;
		}
		VegetableFatsAndExtracts drug =new VegetableFatsAndExtracts();
		drug.setPlantsId(rs.getString("plantsId"));
		drug.setPlantsName(rs.getString("plantsName"));
		drug.setPlantsPinyinName(rs.getString("plantsPinyinName"));
		drugListData.add(drug);
	}
	public void addVegetableFatsAndExtracts(VegetableFatsAndExtracts drugOneData,ResultSet rs) throws SQLException{
		if(rs.getString("plantsName")==null){
			return;
		}
		drugOneData.setPlantsId(rs.getString("plantsId"));
		drugOneData.setPlantsName(rs.getString("plantsName"));
		drugOneData.setPlantsPinyinName(rs.getString("plantsPinyinName"));
		drugOneData.setPlantsDescription(rs.getString("plantsDescription"));
		drugOneData.setPlantsMethod(rs.getString("plantsMethod"));
		drugOneData.setPlantsCharacters(rs.getString("plantsCharacters"));
		drugOneData.setPlantsPreparation(rs.getString("plantsPreparation"));
		drugOneData.setPlantsStorage(rs.getString("plantsStorage"));
	}
	
}

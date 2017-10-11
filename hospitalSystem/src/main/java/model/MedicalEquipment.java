package model;

public class MedicalEquipment {
	private int equipmentCode;
	private String equipmentDescription;
	private String equipmentManufacturer;
	
	public MedicalEquipment() {
		
	}

	public MedicalEquipment(int equipmentCode, String equipmentDescription, String equipmentManufacturer) {
		this.equipmentCode = equipmentCode;
		this.equipmentDescription = equipmentDescription;
		this.equipmentManufacturer = equipmentManufacturer;
	}

	public int getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(int equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getEquipmentDescription() {
		return equipmentDescription;
	}

	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}

	public String getEquipmentManufacturer() {
		return equipmentManufacturer;
	}

	public void setEquipmentManufacturer(String equipmentManufacturer) {
		this.equipmentManufacturer = equipmentManufacturer;
	}
}

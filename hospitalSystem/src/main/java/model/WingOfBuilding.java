package model;

public class WingOfBuilding {
	private int wingCode;
	private String wingName;
	
	public WingOfBuilding() {
		
	}
	
	public WingOfBuilding(int wingCode, String wingName) {
		wingCode = wingCode;
		wingName = wingName;
	}

	public int getWingCode() {
		return wingCode;
	}

	public void setWingCode(int wingCode) {
		this.wingCode = wingCode;
	}

	public String getWingName() {
		return wingName;
	}

	public void setWingName(String wingName) {
		this.wingName = wingName;
	}
}

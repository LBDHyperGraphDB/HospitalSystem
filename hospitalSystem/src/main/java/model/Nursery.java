package model;

import java.lang.reflect.Field;

public class Nursery {
	private int nurseryCode;
	private String nurseryDescription;
	private int nurseryWingOfBuilding;
	
	public Nursery() {
		
	}

	public Nursery(int nurseryCode, String nurseryDescription, int nurseryWingOfBuilding) {
		this.nurseryCode = nurseryCode;
		this.nurseryDescription = nurseryDescription;
		this.nurseryWingOfBuilding = nurseryWingOfBuilding;
	}

	public int getNurseryCode() {
		return nurseryCode;
	}

	public void setNurseryCode(int nurseryCode) {
		this.nurseryCode = nurseryCode;
	}

	public String getNurseryDescription() {
		return nurseryDescription;
	}

	public void setNurseryDescription(String nurseryDescription) {
		this.nurseryDescription = nurseryDescription;
	}

	public int getNurseryWingOfBuilding() {
		return nurseryWingOfBuilding;
	}

	public void setNurseryWingOfBuilding(int nurseryWingOfBuilding) {
		this.nurseryWingOfBuilding = nurseryWingOfBuilding;
	}	
	
	public void setField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
	    Field field = getClass().getDeclaredField(fieldName);
	    field.set(this, value);
	}
}

package model;

import java.lang.reflect.Field;

public class WingOfBuilding {
	private int wingCode;
	private String wingName;
	
	public WingOfBuilding() {
		
	}
	
	public WingOfBuilding(int wingCode, String wingName) {
		this.wingCode = wingCode;
		this.wingName = wingName;
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
	
	public void setField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
	    Field field = getClass().getDeclaredField(fieldName);
	    field.set(this, value);
	}
}

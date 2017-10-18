package model;

import java.lang.reflect.Field;

public class Laboratory {
	private String laboratoryCnpj;
	private String laboratoryDescription;
	private String laboratoryAddress;
	private String laboratoryPhoneNumber;
	
	public Laboratory() {
		
	}

	public Laboratory(String laboratoryCnpj, String laboratoryDescription, String laboratoryAddress,
			String laboratoryPhoneNumber) {
		this.laboratoryCnpj = laboratoryCnpj;
		this.laboratoryDescription = laboratoryDescription;
		this.laboratoryAddress = laboratoryAddress;
		this.laboratoryPhoneNumber = laboratoryPhoneNumber;
	}

	public String getLaboratoryCnpj() {
		return laboratoryCnpj;
	}

	public void setLaboratoryCnpj(String laboratoryCnpj) {
		this.laboratoryCnpj = laboratoryCnpj;
	}

	public String getLaboratoryDescription() {
		return laboratoryDescription;
	}

	public void setLaboratoryDescription(String laboratoryDescription) {
		this.laboratoryDescription = laboratoryDescription;
	}

	public String getLaboratoryAddress() {
		return laboratoryAddress;
	}

	public void setLaboratoryAddress(String laboratoryAddress) {
		this.laboratoryAddress = laboratoryAddress;
	}

	public String getLaboratoryPhoneNumber() {
		return laboratoryPhoneNumber;
	}

	public void setLaboratoryPhoneNumber(String laboratoryPhoneNumber) {
		this.laboratoryPhoneNumber = laboratoryPhoneNumber;
	}
	
	public void setField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
	    Field field = getClass().getDeclaredField(fieldName);
	    field.set(this, value);
	}
}

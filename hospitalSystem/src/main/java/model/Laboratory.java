package model;

import java.lang.reflect.Field;
import java.util.List;

public class Laboratory {
	private String laboratoryCnpj;
	private String laboratoryDescription;
	private String laboratoryAddress;
	private String laboratoryPhoneNumber;
	private List examList;
	public Laboratory() {
		
	}

	public Laboratory(String laboratoryCnpj, String laboratoryDescription, String laboratoryAddress,
			String laboratoryPhoneNumber, List examList) {
		this.laboratoryCnpj = laboratoryCnpj;
		this.laboratoryDescription = laboratoryDescription;
		this.laboratoryAddress = laboratoryAddress;
		this.laboratoryPhoneNumber = laboratoryPhoneNumber;
		this.examList = examList;
	}

	public List getExamList() {
		return examList;
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
	
	public void setExamList(List examList) {
		this.examList = examList;
	}
	
	
	public void setField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
	    Field field = getClass().getDeclaredField(fieldName);
	    field.set(this, value);
	}
}

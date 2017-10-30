package model;

import java.lang.reflect.Field;

public class MedicalExam {
	private int examCode;
	private String examDescription;
	private String examRestriction;
	
	public MedicalExam() {
		
	}

	public MedicalExam(int examCode, String examDescription, String examRestriction) {
		super();
		this.examCode = examCode;
		this.examDescription = examDescription;
		this.examRestriction = examRestriction;
	}

	public int getExamCode() {
		return examCode;
	}

	public void setExamCode(int examCode) {
		this.examCode = examCode;
	}

	public String getExamDescription() {
		return examDescription;
	}

	public void setExamDescription(String examDescription) {
		this.examDescription = examDescription;
	}

	public String getExamRestriction() {
		return examRestriction;
	}

	public void setExamRestriction(String examRestriction) {
		this.examRestriction = examRestriction;
	}
	
	public void setField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
	    Field field = getClass().getDeclaredField(fieldName);
	    field.set(this, value);    
	}
}

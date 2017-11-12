package model;

import java.lang.reflect.Field;

public class MedicalExam {
	private String examLaboratoryCnpj;
	private String examPatientCpf;

	private int examCode;
	private String examDescription;
	private String examRestriction;

	public MedicalExam() {

	}

	public MedicalExam(String examLaboratoryCnpj, int examCode, String examDescription, String examRestriction) {
		super();
		this.examLaboratoryCnpj = examLaboratoryCnpj;
		this.examCode = examCode;
		this.examDescription = examDescription;
		this.examRestriction = examRestriction;
	}

	public String getExamLaboratoryCnpj() {
		return examLaboratoryCnpj;
	}

	public void setExamLaboratoryCnpj(String examLaboratoryCnpj) {
		this.examLaboratoryCnpj = examLaboratoryCnpj;
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

	public String getExamPatientCpf() {
		return examPatientCpf;
	}

	public void setExamPatientCpf(String examPatientCpf) {
		this.examPatientCpf = examPatientCpf;
	}

	public void setField(String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
		Field field = getClass().getDeclaredField(fieldName);
		field.set(this, value);
	}
}

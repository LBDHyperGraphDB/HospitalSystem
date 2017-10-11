package model;

import java.util.Date;

public class Nurse extends Person {
	private String nurseQualification;
	private int nurseCoren;
	
	public Nurse() {
		
	}
	
	public Nurse(String nurseQualification, int nurseCoren) {
		this.nurseQualification = nurseQualification;
		this.nurseCoren = nurseCoren;
	}
	
	public Nurse(String personCpf, String personName, String personAddress, String personGender,
			String personPhoneNumber, String personEmail, Date personBithDate, 
			String nurseQualification, int nurseCoren) {
		super(personCpf, personName, personAddress, personGender, personPhoneNumber, personEmail, personBithDate);
		this.nurseQualification = nurseQualification;
		this.nurseCoren = nurseCoren;
	}

	public String getNurseQualification() {
		return nurseQualification;
	}

	public void setNurseQualification(String nurseQualification) {
		this.nurseQualification = nurseQualification;
	}

	public int getNurseCoren() {
		return nurseCoren;
	}

	public void setNurseCoren(int nurseCoren) {
		this.nurseCoren = nurseCoren;
	}
}

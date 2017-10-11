package model;

import java.util.Date;

public class Patient extends Person {
	private String patientHealthInsurance;
	
	public Patient() {
		
	}
	
	public Patient(String patientHealthInsurance) {
		this.patientHealthInsurance = patientHealthInsurance;
	}

	public Patient(String personCpf, String personName, String personAddress, String personGender,
			String personPhoneNumber, String personEmail, Date personBithDate, String patientHealthInsurance) {
		super(personCpf, personName, personAddress, personGender, personPhoneNumber, personEmail, personBithDate);
		this.patientHealthInsurance = patientHealthInsurance;
	}

	public String getPatientHealthInsurance() {
		return patientHealthInsurance;
	}

	public void setPatientHealthInsurance(String patientHealthInsurance) {
		this.patientHealthInsurance = patientHealthInsurance;
	}
}

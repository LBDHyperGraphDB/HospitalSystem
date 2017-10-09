package hospitalSystem;

import java.util.Date;

public class Doctor extends Person {
	private String doctorCrm;
	private String doctorTitration;
	private String doctorMaritalState;
	private String doctorMotherName;
	private String doctorFatherName;
	
	public Doctor() {
		
	}

	public Doctor(String personCpf, String personName, String personAddress, String personGender,
			String personPhoneNumber, String personEmail, Date personBithDate, String doctorCrm,
			String doctorTitration, String doctorMaritalState, String doctorMotherName, 
			String doctorFatherName) {
		super(personCpf, personName, personAddress, personGender, personPhoneNumber, personEmail, personBithDate);
		this.doctorCrm = doctorCrm;
		this.doctorTitration = doctorTitration;
		this.doctorMaritalState = doctorMaritalState;
		this.doctorMotherName = doctorMotherName;
		this.doctorFatherName = doctorFatherName;
	}

	public Doctor(String doctorCrm, String doctorTitration, String doctorMaritalState, String doctorMotherName, String doctorFatherName) {
		this.doctorCrm = doctorCrm;
		this.doctorTitration = doctorTitration;
		this.doctorMaritalState = doctorMaritalState;
		this.doctorMotherName = doctorMotherName;
		this.doctorFatherName = doctorFatherName;
	}

	public String getDoctorCrm() {
		return doctorCrm;
	}

	public void setDoctorCrm(String doctorCrm) {
		this.doctorCrm = doctorCrm;
	}

	public String getDoctorTitration() {
		return doctorTitration;
	}

	public void setDoctorTitration(String doctorTitration) {
		this.doctorTitration = doctorTitration;
	}

	public String getDoctorMaritalState() {
		return doctorMaritalState;
	}

	public void setDoctorMaritalState(String doctorMaritalState) {
		this.doctorMaritalState = doctorMaritalState;
	}

	public String getDoctorMotherName() {
		return doctorMotherName;
	}

	public void setDoctorMotherName(String doctorMotherName) {
		this.doctorMotherName = doctorMotherName;
	}

	public String getDoctorFatherName() {
		return doctorFatherName;
	}

	public void setDoctorFatherName(String doctorFatherName) {
		this.doctorFatherName = doctorFatherName;
	}
}

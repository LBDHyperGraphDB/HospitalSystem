package model;

import java.util.Date;

public class Person {
	protected String personCpf;
	protected String personName;
	protected String personAddress;
	protected String personGender;
	protected String personPhoneNumber;
	protected String personEmail;
	protected String personBithDate;
	
	public Person() {
		
	}
	
	public Person(String personCpf, String personName, String personAddress, String personGender,
			String personPhoneNumber, String personEmail, String personBithDate) {
		this.personCpf = personCpf;
		this.personName = personName;
		this.personAddress = personAddress;
		this.personGender = personGender;
		this.personPhoneNumber = personPhoneNumber;
		this.personEmail = personEmail;
		this.personBithDate = personBithDate;
	}
	
	public String getPersonCpf() {
		return personCpf;
	}

	public void setPersonCpf(String personCpf) {
		this.personCpf = personCpf;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonAddress() {
		return personAddress;
	}

	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

	public String getPersonGender() {
		return personGender;
	}

	public void setPersonGender(String personGender) {
		this.personGender = personGender;
	}

	public String getPersonPhoneNumber() {
		return personPhoneNumber;
	}

	public void setPersonPhoneNumber(String personPhoneNumber) {
		this.personPhoneNumber = personPhoneNumber;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getPersonBithDate() {
		return personBithDate;
	}

	public void setPersonBithDate(String personBithDate) {
		this.personBithDate = personBithDate;
	}
}

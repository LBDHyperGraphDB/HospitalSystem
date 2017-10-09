package hospitalSystem;

public class Hospital {
	private String hospitalCnpj;
	private String hospitalName;
	private String hospitalAddress;
	private String hospitalPhoneNumber;
	private String hospitalWingsOfBuilding;
	
	public Hospital() {
		
	}

	public Hospital(String hospitalCnpj, String hospitalName, String hospitalAddress, String hospitalPhoneNumber,
			String hospitalWingsOfBuilding) {
		super();
		this.hospitalCnpj = hospitalCnpj;
		this.hospitalName = hospitalName;
		this.hospitalAddress = hospitalAddress;
		this.hospitalPhoneNumber = hospitalPhoneNumber;
		this.hospitalWingsOfBuilding = hospitalWingsOfBuilding;
	}

	public String getHospitalCnpj() {
		return hospitalCnpj;
	}

	public void setHospitalCnpj(String hospitalCnpj) {
		this.hospitalCnpj = hospitalCnpj;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getHospitalPhoneNumber() {
		return hospitalPhoneNumber;
	}

	public void setHospitalPhoneNumber(String hospitalPhoneNumber) {
		this.hospitalPhoneNumber = hospitalPhoneNumber;
	}

	public String getHospitalWingsOfBuilding() {
		return hospitalWingsOfBuilding;
	}

	public void setHospitalWingsOfBuilding(String hospitalWingsOfBuilding) {
		this.hospitalWingsOfBuilding = hospitalWingsOfBuilding;
	}
}

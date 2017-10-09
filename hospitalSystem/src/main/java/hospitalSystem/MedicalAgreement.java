package hospitalSystem;

public class MedicalAgreement {
	private int agreementCode;
	private String agreementDescription;
	private double agreementValue;
	private String agreementType;
	
	public MedicalAgreement() {
		
	}

	public MedicalAgreement(int agreementCode, String agreementDescription, double agreementValue,
			String agreementType) {
		this.agreementCode = agreementCode;
		this.agreementDescription = agreementDescription;
		this.agreementValue = agreementValue;
		this.agreementType = agreementType;
	}

	public int getAgreementCode() {
		return agreementCode;
	}

	public void setAgreementCode(int agreementCode) {
		this.agreementCode = agreementCode;
	}

	public String getAgreementDescription() {
		return agreementDescription;
	}

	public void setAgreementDescription(String agreementDescription) {
		this.agreementDescription = agreementDescription;
	}

	public double getAgreementValue() {
		return agreementValue;
	}

	public void setAgreementValue(double agreementValue) {
		this.agreementValue = agreementValue;
	}

	public String getAgreementType() {
		return agreementType;
	}

	public void setAgreementType(String agreementType) {
		this.agreementType = agreementType;
	}
}

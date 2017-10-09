package hospitalSystem;

public class Nursery {
	private int nurseryCode;
	private String nurseryDescription;
	private String nurseryWingOfBuilding;
	
	public Nursery() {
		
	}

	public Nursery(int nurseryCode, String nurseryDescription, String nurseryWingOfBuilding) {
		this.nurseryCode = nurseryCode;
		this.nurseryDescription = nurseryDescription;
		this.nurseryWingOfBuilding = nurseryWingOfBuilding;
	}

	public int getNurseryCode() {
		return nurseryCode;
	}

	public void setNurseryCode(int nurseryCode) {
		this.nurseryCode = nurseryCode;
	}

	public String getNurseryDescription() {
		return nurseryDescription;
	}

	public void setNurseryDescription(String nurseryDescription) {
		this.nurseryDescription = nurseryDescription;
	}

	public String getNurseryWingOfBuilding() {
		return nurseryWingOfBuilding;
	}

	public void setNurseryWingOfBuilding(String nurseryWingOfBuilding) {
		this.nurseryWingOfBuilding = nurseryWingOfBuilding;
	}	
}

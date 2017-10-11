package view;

import model.Hospital;
import dao.HospitalDAO;

public class HospitalSystem {

	public static void main(String[] args) {
		HospitalDAO hospitalDAO = new HospitalDAO();

		Hospital santaHelena = new Hospital("15550657000108", "Santa Helena", "SHLN 516 Conjunto D", "32150000");
		Hospital saoFranscisco = new Hospital("64064642000137", "São Franscisco", "SHLN 516 Conjunto D", "32150000");
		Hospital santaMarta = new Hospital("24735116000137", "Santa Marta", "SHLN 516 Conjunto D", "32150000");
		Hospital anchieta = new Hospital("88731722000167", "Anchieta", "SHLN 516 Conjunto D", "32150000");		
		
		hospitalDAO.addHospital(santaHelena);
		hospitalDAO.addHospital(saoFranscisco);
		hospitalDAO.addHospital(santaMarta);
		hospitalDAO.addHospital(anchieta);

		hospitalDAO.getAllHospitals();
	}

}

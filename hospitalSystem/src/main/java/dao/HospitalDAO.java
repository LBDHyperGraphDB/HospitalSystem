package dao;

import org.hypergraphdb.HyperGraph;

import model.Hospital;

public class HospitalDAO {
	String databaseLocation = "/home/karine/Documents/hypergraphdb-1.3"; 
	HyperGraph hospitalGraph = null; 
	
	public boolean insertHospital(Hospital hospital) {
		try { 
			hospitalGraph = new HyperGraph(databaseLocation);
			Hospital hospitalDB = new Hospital(hospital.getHospitalCnpj(),
					hospital.getHospitalName(),
					hospital.getHospitalAddress(), 
					hospital.getHospitalPhoneNumber()
			);
			
			hospitalGraph.add(hospitalDB);
			return true;
	   } catch (Throwable t) {
		   System.out.println("Insertion Error");
	       t.printStackTrace();
	       return false;
	   } finally {
	       hospitalGraph.close();
	   }
	}
}

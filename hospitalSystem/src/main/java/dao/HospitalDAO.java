package dao;

import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HGHandle;

import java.util.List;
import model.Hospital;

public class HospitalDAO {
	String databaseLocation = "../hypergraphdb-1.3"; 
	HyperGraph hospitalGraph = null;

	public boolean addHospital(Hospital hospital) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			// Avoid duplication: do not add if CNPJ exists.
			if (!this.findHospitalByCnpj(hospitalGraph, hospital.getHospitalCnpj())) {
				hospitalGraph.add(hospital);
				return true;
			}
			else {
				System.out.println("[ERRO]: O CNPJ " + hospital.getHospitalCnpj() + " já existe.");
				return true;
			}
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Hospital " + hospital.getHospitalName() + " não pôde ser adicionado.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   hospitalGraph.close();
	   }
	}
	
	public void getAllHospitals() {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			List<Hospital> hospitals = hg.getAll(hospitalGraph, hg.and(hg.type(Hospital.class)));
			
			System.out.println();
			if (hospitals.size() > 0) {
				System.out.println("------------------------------");
				System.out.println("         INFORMAÇÕES          ");
				System.out.println("------------------------------");
				
				for (Hospital hospital: hospitals) {
					System.out.println("Nome: " + hospital.getHospitalName());
					System.out.println("CNPJ: " + hospital.getHospitalCnpj());
					System.out.println("Endereço: " + hospital.getHospitalAddress());
					System.out.println("Telefone: " + hospital.getHospitalPhoneNumber());
					System.out.println("------------------------------");
				}
			} else
				System.out.print("Não há hospitais cadastrados.");
			System.out.println();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		   hospitalGraph.close();
	   }
	}
	
	public boolean findHospitalByCnpj(HyperGraph hospitalGraph, String cnpj) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			List<Hospital> hospitals = hg.getAll(hospitalGraph, hg.and(hg.type(Hospital.class), hg.eq("hospitalCnpj", cnpj)));
			if (hospitals.size() > 0)
				return true;
			else
				return false;
		} catch (Throwable t) {
			System.out.println("[ERRO]: Ocorreu um erro ao buscar o CNPJ " + cnpj + ".");
			t.printStackTrace();
			return false;
		}
	}
	
	public boolean updateHospital(String cnpj, String attribute, String value) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (this.findHospitalByCnpj(hospitalGraph, cnpj)) {
				Hospital hospital = new Hospital();
				hospital = hg.getOne(hospitalGraph, hg.and(hg.type(Hospital.class), hg.eq("hospitalCnpj", cnpj)));
				
				hospital.setField(attribute, value);
				HGHandle hospitalHandle = hospitalGraph.getHandle(hospital);
				hospitalGraph.update(hospitalHandle);
				return true;
			} else
				return false;
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Hospital de CNPJ " + cnpj + " não pôde ser atualizado.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   hospitalGraph.close();
	   }
	}
	
	public boolean deleteHospital(String cnpj) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (this.findHospitalByCnpj(hospitalGraph, cnpj)) {
				Hospital hospital = new Hospital();
				hospital = hg.getOne(hospitalGraph, hg.and(hg.type(Hospital.class), hg.eq("hospitalCnpj", cnpj)));
				
				HGHandle hospitalHandle = hospitalGraph.getHandle(hospital);
				hospitalGraph.remove(hospitalHandle);
				return true;
			} else
				return false;
			
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Hospital de CNPJ " + cnpj + " não pôde ser excluído.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   hospitalGraph.close();
	   }
	}
}

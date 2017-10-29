package dao;

import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HGHandle;

import java.util.List;
import model.Hospital;

public class HospitalDAO {
	HyperGraph hospitalGraph = null;
	
	public HospitalDAO(HyperGraph hospitalGraph) {
		this.hospitalGraph = hospitalGraph;
	}

	public boolean addHospital(Hospital hospital) {
		try {
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
	   }
	}
	
	public void getAllHospitals() {
		try {
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
		}
	}
	
	public boolean findHospitalByCnpj(HyperGraph hospitalGraph, String cnpj) {
		try {
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
	   }
	}
	
	public boolean deleteHospital(String cnpj) {
		try {
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
	   }
	}
}

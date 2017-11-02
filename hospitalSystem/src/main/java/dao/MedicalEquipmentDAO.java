package dao;

import java.util.List;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.HGQuery.hg;

import model.MedicalEquipment;

public class MedicalEquipmentDAO {

	String databaseLocation = "../hypergraphdb-1.3";
	HyperGraph hospitalGraph = null;
	
	public MedicalEquipmentDAO(HyperGraph hospitalGraph) {
		this.hospitalGraph = hospitalGraph;
	}
	
	public boolean addMedicalEquipment(MedicalEquipment medicalEquipment) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (!this.findMedicalEquipmentByCode(hospitalGraph, medicalEquipment.getEquipmentCode())) {
				hospitalGraph.add(medicalEquipment);
				System.out.println("[SUCESSO] Equipamento médico adicionado com sucesso!");
				return true;
			}else {
				System.out.println("[ERRO] O código" + medicalEquipment.getEquipmentCode() + "já existe");
				return false;
			}
		} catch(Throwable t) {
			System.out.println("[ERRO]: O equipamento " + medicalEquipment.getEquipmentDescription() + " não pôde ser adicionado.");
		    t.printStackTrace();
		    return false;
		} finally {
			hospitalGraph.close();
		}
	}
	
	public void getAllMedicalEquipments() {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			List<MedicalEquipment> medicalEquipments = hg.getAll(hospitalGraph, hg.and(hg.type(MedicalEquipment.class)));
			
			System.out.println();
			if (medicalEquipments.size() > 0) {
				System.out.println("------------------------------");
				System.out.println("         Equipamentos         ");
				System.out.println("------------------------------");
				
				for (MedicalEquipment medicalEquipment: medicalEquipments) {
					System.out.println("Código: " + medicalEquipment.getEquipmentCode());
					System.out.println("Descrição: " + medicalEquipment.getEquipmentDescription());
					System.out.println("Manufatura: " + medicalEquipment.getEquipmentManufacturer());
					System.out.println("------------------------------");
				}
			} else
				System.out.print("Não há equipamentos cadastrados.");
			System.out.println();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			hospitalGraph.close();
		}
	}
	
	public boolean findMedicalEquipmentByCode(HyperGraph hospitalGraph, int equipmentCode) {
		try {
			List<MedicalEquipment> medicalEquipments = hg.getAll(hospitalGraph, hg.and(hg.type(MedicalEquipment.class), hg.eq("equipmentCode", equipmentCode)));
			if (medicalEquipments.size() > 0)
				return true;
			else
				return false;
		} catch (Throwable t) {
			System.out.println("[ERRO]: Ocorreu um erro ao buscar o código do equipamento " + equipmentCode + ".");
			t.printStackTrace();
			return false;
		}
	}
	
	public boolean updateMedicalEquipment(int medicalEquipmentCode, String attribute, String value) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			
			if (this.findMedicalEquipmentByCode(hospitalGraph, medicalEquipmentCode)) {
				MedicalEquipment medicalEquipment = new MedicalEquipment();
				medicalEquipment = hg.getOne(hospitalGraph, hg.and(hg.type(MedicalEquipment.class), hg.eq("equipmentCode", medicalEquipmentCode)));
				
				medicalEquipment.setField(attribute, value);
				hospitalGraph.update(medicalEquipment);
				
				System.out.println("[SUCESSO] Equipamento atualizado com sucesso!");
				return true;
			} else
				return false;
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Equipamento de codigo" + value + " não pôde ser atualizado.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   hospitalGraph.close();
	   }
	}
	
	public boolean deleteMedicalEquipment(int code) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (this.findMedicalEquipmentByCode(hospitalGraph, code)) {
				MedicalEquipment medicalEquipment = new MedicalEquipment();
				medicalEquipment = hg.getOne(hospitalGraph, hg.and(hg.type(MedicalEquipment.class), hg.eq("equipmentCode", code)));
				
				HGHandle medicalEquipmentHandle = hospitalGraph.getHandle(medicalEquipment);
				hospitalGraph.remove(medicalEquipmentHandle);
				
				System.out.println("[SUCESSO] Equipamento excluído com sucesso!");
				return true;
			} else
				return false;
			
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Equipamento de código " + code + " não pôde ser excluído.");
	       t.printStackTrace();
	       return false;
	   } finally {
			   hospitalGraph.close();
	   }
	}
}

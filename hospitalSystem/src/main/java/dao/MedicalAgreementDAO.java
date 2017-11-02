package dao;

import java.util.List;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.HGQuery.hg;

import model.MedicalAgreement;

public class MedicalAgreementDAO {
	String databaseLocation = "../hypergraphdb-1.3";
	HyperGraph hospitalGraph = null;
	
	public MedicalAgreementDAO(HyperGraph hospitalGraph) {
		this.hospitalGraph = hospitalGraph;
	}
	
	public boolean addMedicalAgreement(MedicalAgreement medicalAgreement) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (!this.findMedicalAgreementByCode(hospitalGraph, medicalAgreement.getAgreementCode())) {
				hospitalGraph.add(medicalAgreement);
				System.out.println("[SUCESSO] Exame médico adicionado com sucesso!");
				return true;
			}else {
				System.out.println("[ERRO] O código" + medicalAgreement.getAgreementCode() + "já existe");
				return false;
			}
		} catch(Throwable t) {
			System.out.println("[ERRO]: O convênio " + medicalAgreement.getAgreementDescription() + " não pôde ser adicionado.");
		    t.printStackTrace();
		    return false;
		} finally {
			hospitalGraph.close();
		}
	}
	
	public void getAllMedicalAgreements() {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			List<MedicalAgreement> medicalAgreements = hg.getAll(hospitalGraph, hg.and(hg.type(MedicalAgreement.class)));
			
			System.out.println();
			if (medicalAgreements.size() > 0) {
				System.out.println("------------------------------");
				System.out.println("         CONVÊNIOS         ");
				System.out.println("------------------------------");
				
				for (MedicalAgreement medicalAgreement: medicalAgreements) {
					System.out.println("Código " + medicalAgreement.getAgreementCode());
					System.out.println("Descrição" + medicalAgreement.getAgreementDescription());
					System.out.println("Valor: " + medicalAgreement.getAgreementValue());
					System.out.println("Tipo: " + medicalAgreement.getAgreementType());
					System.out.println("------------------------------");
				}
			} else
				System.out.print("Não há convênios cadastrados.");
			System.out.println();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			hospitalGraph.close();
		}
	}
	
	public boolean findMedicalAgreementByCode(HyperGraph hospitalGraph, int agreementCode) {
		try {
			List<MedicalAgreement> medicalAgreements = hg.getAll(hospitalGraph, hg.and(hg.type(MedicalAgreement.class), hg.eq("agreementCode", agreementCode)));
			if (medicalAgreements.size() > 0)
				return true;
			else
				return false;
		} catch (Throwable t) {
			System.out.println("[ERRO]: Ocorreu um erro ao buscar o código do convênio " + agreementCode + ".");
			t.printStackTrace();
			return false;
		}
	}
	
	public boolean updateMedicalAgreement(int medicalAgreementCode, String attribute, String value) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			
			if (this.findMedicalAgreementByCode(hospitalGraph, medicalAgreementCode)) {
				MedicalAgreement medicalAgreement= new MedicalAgreement();
				medicalAgreement = hg.getOne(hospitalGraph, hg.and(hg.type(MedicalAgreement.class), hg.eq("agreementCode", medicalAgreementCode)));
				
				if(attribute == "agreementValue") {
					double newValue = Double.parseDouble(value);
					medicalAgreement.setAgreementValue(newValue);
					hospitalGraph.update(medicalAgreement);
				}else {
					medicalAgreement.setField(attribute, value);
					hospitalGraph.update(medicalAgreement);
				}
				System.out.println("[SUCESSO] Convênio atualizado com sucesso!");
				return true;
			} else
				return false;
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Convênio de codigo" + value + " não pôde ser atualizado.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   hospitalGraph.close();
	   }
	}
	
	public boolean deleteMedicalAgreement(int code) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (this.findMedicalAgreementByCode(hospitalGraph, code)) {
				MedicalAgreement medicalAgreement = new MedicalAgreement();
				medicalAgreement = hg.getOne(hospitalGraph, hg.and(hg.type(MedicalAgreement.class), hg.eq("agreementCode", code)));
				
				HGHandle medicalAgreementHandle = hospitalGraph.getHandle(medicalAgreement);
				hospitalGraph.remove(medicalAgreementHandle);
				
				System.out.println("[SUCESSO] Convênio excluído com sucesso!");
				return true;
			} else
				return false;
			
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Convênio de código " + code + " não pôde ser excluído.");
	       t.printStackTrace();
	       return false;
	   } finally {
			   hospitalGraph.close();
	   }
	}
}

package dao;

import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.HGQuery.hg;

import java.util.List;

import org.hypergraphdb.HGHandle;

import model.MedicalExam;

public class MedicalExamDAO {
	String databaseLocation = "../hypergraphdb-1.3";
	HyperGraph medicalExamGraph = null;
	
	public boolean addMedicalExam(MedicalExam medicalExam) {
		try {
			medicalExamGraph = new HyperGraph(databaseLocation);
			medicalExamGraph.add(medicalExam);
			System.out.println("[SUCESSO] Exame médico adicionado com sucesso!");
			return true;
		} catch(Throwable t) {
			System.out.println("[ERRO]: O Laboratório " + medicalExam.getExamDescription() + " não pôde ser adicionado.");
		    t.printStackTrace();
		    return false;
		} finally {
			medicalExamGraph.close();
		}
	}
	
	public void getAllMedicalExams() {
		try {
			medicalExamGraph = new HyperGraph(databaseLocation);
			
			List<MedicalExam> medicalExams = hg.getAll(medicalExamGraph, hg.and(hg.type(MedicalExam.class)));
			
			System.out.println();
			if (medicalExams.size() > 0) {
				System.out.println("------------------------------");
				System.out.println("         EXAMES         ");
				System.out.println("------------------------------");
				
				for (MedicalExam medicalExam: medicalExams) {
					System.out.println("Código " + medicalExam.getExamCode());
					System.out.println("Descrição: " + medicalExam.getExamDescription());
					System.out.println("Restrição: " + medicalExam.getExamRestriction());
					System.out.println("------------------------------");
				}
			} else
				System.out.print("Não há exames cadastrados.");
			System.out.println();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			medicalExamGraph.close();
		}
	}
	
	public boolean findMedicalExamByCode(HyperGraph medicalExamGraph, int medicalExamCode) {
		try {
			List<MedicalExam> medicalExams = hg.getAll(medicalExamGraph, hg.and(hg.type(MedicalExam.class), hg.eq("examCode", medicalExamCode)));
			if (medicalExams.size() > 0)
				return true;
			else
				return false;
		} catch (Throwable t) {
			System.out.println("[ERRO]: Ocorreu um erro ao buscar o código do exame " + medicalExamCode + ".");
			t.printStackTrace();
			return false;
		}
	}
	
	public boolean updateMedicalExam(int medicalExamCode, String attribute, String value) {
		try {
			medicalExamGraph = new HyperGraph(databaseLocation);
			
			if (this.findMedicalExamByCode(medicalExamGraph, medicalExamCode)) {
				MedicalExam medicalExam = new MedicalExam();
				medicalExam = hg.getOne(medicalExamGraph, hg.and(hg.type(MedicalExam.class), hg.eq("examCode", medicalExamCode)));
				
				medicalExam.setField(attribute, value);
				medicalExamGraph.update(medicalExam);
				System.out.println("[SUCESSO] Exame atualizado com sucesso!");
				return true;
			} else
				return false;
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Exame de codigo" + value + " não pôde ser atualizado.");
	       t.printStackTrace();
	       return false;
	   } finally {
	       medicalExamGraph.close();
	   }
	}
}

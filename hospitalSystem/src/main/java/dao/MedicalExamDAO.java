package dao;

import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.HGQuery.hg;

import java.util.List;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGValueLink;

import model.MedicalExam;
import model.Patient;

public class MedicalExamDAO {
	String databaseLocation = "../hypergraphdb-1.3";
	HyperGraph hospitalGraph = null;
	PatientDAO patientDAO = null;

	public MedicalExamDAO(HyperGraph hospitalGraph) {
		this.hospitalGraph = hospitalGraph;
		patientDAO = new PatientDAO(hospitalGraph);
	}
	
	public boolean addMedicalExam(MedicalExam medicalExam) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (!this.findMedicalExamByCode(hospitalGraph, medicalExam.getExamCode())) {
				hospitalGraph.add(medicalExam);
				System.out.println("[SUCESSO] Exame médico adicionado com sucesso!");
				return true;
			}else {
				System.out.println("[ERRO] O código" + medicalExam.getExamCode() + "já existe");
				return false;
			}
		} catch(Throwable t) {
			System.out.println("[ERRO]: O Exame " + medicalExam.getExamDescription() + " não pôde ser adicionado.");
		    t.printStackTrace();
		    return false;
		} finally {
			hospitalGraph.close();
		}
	}
	
	public void getAllMedicalExams() {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			List<MedicalExam> medicalExams = hg.getAll(hospitalGraph, hg.and(hg.type(MedicalExam.class)));
			
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
			hospitalGraph.close();
		}
	}
	
	public boolean findMedicalExamByCode(HyperGraph hospitalGraph, int medicalExamCode) {
		try {
			List<MedicalExam> medicalExams = hg.getAll(hospitalGraph, hg.and(hg.type(MedicalExam.class), hg.eq("examCode", medicalExamCode)));
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
			hospitalGraph = new HyperGraph(databaseLocation);
			
			if (this.findMedicalExamByCode(hospitalGraph, medicalExamCode)) {
				MedicalExam medicalExam = new MedicalExam();
				medicalExam = hg.getOne(hospitalGraph, hg.and(hg.type(MedicalExam.class), hg.eq("examCode", medicalExamCode)));
				
				medicalExam.setField(attribute, value);
				hospitalGraph.update(medicalExam);
				System.out.println("[SUCESSO] Exame atualizado com sucesso!");
				return true;
			} else
				return false;
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Exame de codigo" + value + " não pôde ser atualizado.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   hospitalGraph.close();
	   }
	}
	
	public boolean deleteMedicalExam(int code) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (this.findMedicalExamByCode(hospitalGraph, code)) {
				MedicalExam medicalExam = new MedicalExam();
				medicalExam = hg.getOne(hospitalGraph, hg.and(hg.type(MedicalExam.class), hg.eq("examCode", code)));
				
				HGHandle medicalExamHandle = hospitalGraph.getHandle(medicalExam);
				hospitalGraph.remove(medicalExamHandle);
				
				System.out.println("[SUCESSO] Exame excluído com sucesso!");
				return true;
			} else
				return false;
			
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Exame de código " + code + " não pôde ser excluído.");
	       t.printStackTrace();
	       return false;
	   } finally {
			   hospitalGraph.close();
	   }
	}
	
	public boolean addMedicalExamToPatient(String cpf, int code) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			
			if (findMedicalExamByCode(hospitalGraph, code)) {
				MedicalExam medicalExam = new MedicalExam();
				medicalExam = hg.getOne(hospitalGraph, hg.and(hg.type(MedicalExam.class), hg.eq("examCode", code)));
				
				medicalExam.setExamPatientCpf(cpf);

				HGHandle addExam =  hospitalGraph.add(medicalExam);
				HGHandle patientExam = hospitalGraph.getHandle(patientDAO.findPatientByCpf(hospitalGraph, cpf));
				// Create the link / relationship between atoms
				new HGValueLink(hospitalGraph, addExam, patientExam);
				System.out.println("[SUCESSO] Paciente atualizado com sucesso!");
				return true;
			} else
				System.out.println("Não foi possível adicionar o exame");
				return false;
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: Não foi possível atualizar o paciente");
	       t.printStackTrace();
	       return false;
	   } finally {
		   hospitalGraph.close();
	   }
	}
	
	public void getExamsOfAPatient(String cpf) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			List<MedicalExam> exams = hg.getAll(hospitalGraph, hg.and(hg.type(MedicalExam.class), hg.eq("examPatientCpf", cpf)));
			System.out.println();
			Patient patient = hg.getOne(hospitalGraph, hg.and(hg.type(Patient.class), hg.eq("personCpf", cpf)));
			
            if (exams.size() > 0) {
            	System.out.println("CPF: " + patient.getPersonCpf());
                System.out.println("Nome do paciente: " + patient.getPersonName());
                System.out.println("------------------------------");
                System.out.println("           EXAMES          ");
                System.out.println("------------------------------");
                
                for (MedicalExam exam : exams) {
                	
                	String patientExam;
                	if(patient == null) {
                		patientExam = "Não há exames solicitados";
                	} else {
                		patientExam = patient.getPersonName();
                	}
                	
                    System.out.println("Código do exame: " + exam.getExamCode());
                    System.out.println("Descrição do exame: " + exam.getExamDescription());

                    System.out.println("------------------------------");
                }
            } else {
                System.out.print("Não há exames cadastrados.");
            }
            System.out.println();
		} catch (Throwable t) {
			 t.printStackTrace();
		} finally {
			hospitalGraph.close();
		}
	} 
}

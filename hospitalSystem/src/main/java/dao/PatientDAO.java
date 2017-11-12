package dao;

import java.util.List;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HyperGraph;

import model.Doctor;
import model.Patient;

public class PatientDAO {
    String databaseLocation = "../hypergraphdb-1.3";
    DoctorDAO doctorDAO = null;
    HyperGraph hospitalGraph = null;
    HGHandle doctorHandle;
    HGHandle patientHandle;

    public PatientDAO(HyperGraph hospitalGraph) {
        this.hospitalGraph = hospitalGraph;
        doctorDAO = new DoctorDAO(hospitalGraph);
    }

    public boolean addPatient(Patient patient) {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            // Avoid duplication: do not add if CPF exists.
            if (!this.findPatientByCpf(hospitalGraph, patient.getPersonCpf())) {
                hospitalGraph.add(patient);
                System.out.println("[SUCESSO] Paciente adicionado com sucesso!");
                return true;
            } else {
                System.out.println("[ERRO]: O CPF " + patient.getPersonCpf() + " já existe.");
                return true;
            }
        } catch (Throwable t) {
            System.out.println("[ERRO]: O paciente " + patient.getPersonName() + " não pôde ser adicionado.");
            t.printStackTrace();
            return false;
        } finally {
            hospitalGraph.close();
        }
    }

	public void getAllPatients() {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            List<Patient> patients = hg.getAll(hospitalGraph, hg.and(hg.type(Patient.class)));

            System.out.println();
            if (patients.size() > 0) {
                System.out.println("------------------------------");
                System.out.println("           PACIENTES          ");
                System.out.println("------------------------------");
                
                for (Patient patient : patients) {
                	Doctor doctor = hg.getOne(hospitalGraph, hg.and(hg.type(Doctor.class), hg.eq("doctorCrm", patient.getPatientDoctorCrm())));
                	String doctorName;
                	if(doctor == null) {
                		doctorName = "Não há médico vinculado";
                	} else {
                		doctorName = doctor.getPersonName();
                	}
                	
                	System.out.println("CPF: " + patient.getPersonCpf());
                    System.out.println("Nome: " + patient.getPersonName());
                    System.out.println("Data de Nascimento: " + patient.getPersonBithDate());
                    System.out.println("Endereço: " + patient.getPersonAddress());
                    System.out.println("Telefone: " + patient.getPersonPhoneNumber());
                    System.out.println("Email: " + patient.getPersonEmail());
                    System.out.println("Convênio: " + patient.getPatientHealthInsurance());
                    System.out.println("Médico Responsável: " + doctorName);

                    System.out.println("------------------------------");
                }
            } else {
                System.out.print("Não há pacientes cadastrados.");
            }
            System.out.println();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            hospitalGraph.close();
        }
    }

    public boolean findPatientByCpf(HyperGraph hospitalGraph, String cpf) {
        try {
            List<Patient> patients =
                    hg.getAll(hospitalGraph, hg.and(hg.type(Patient.class), hg.eq("personCpf", cpf)));
            if (patients.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            System.out.println("[ERRO]: Ocorreu um erro ao buscar o CPF " + cpf + ".");
            t.printStackTrace();
            return false;
        }
    }

    public boolean updatePatient(String cpf, String attribute, String value) {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            if (this.findPatientByCpf(hospitalGraph, cpf)) {
                Patient patient = new Patient();
                patient = hg.getOne(hospitalGraph, hg.and(hg.type(Patient.class), hg.eq("personCpf", cpf)));

                patient.setField(attribute, value);
                hospitalGraph.update(patient);
                System.out.println("[SUCESSO] Paciente atualizado com sucesso!");
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            System.out.println("[ERRO]: O paciente de CPF " + cpf + " não pôde ser atualizado.");
            t.printStackTrace();
            return false;
        } finally {
            hospitalGraph.close();
        }
    }

    public boolean deletePatient(String cpf) {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            if (this.findPatientByCpf(hospitalGraph, cpf)) {
                Patient patient = new Patient();
                patient = hg.getOne(hospitalGraph, hg.and(hg.type(Patient.class), hg.eq("personCpf", cpf)));

                HGHandle patientHandle = hospitalGraph.getHandle(patient);
                hospitalGraph.remove(patientHandle);

                System.out.println("[SUCESSO] Paciente excluida com sucesso!");
                return true;
            } else {
                return false;
            }

        } catch (Throwable t) {
            System.out.println("[ERRO]: O paciente de Cpf " + cpf + " não pode ser excluído.");
            t.printStackTrace();
            return false;
        } finally {
            hospitalGraph.close();
        }
    }
    
    public void getPatientsOfADoctor(String doctorCrm) {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            List<Patient> patients = hg.getAll(hospitalGraph, hg.and(hg.type(Patient.class), hg.eq("patientDoctorCrm", doctorCrm)));

            System.out.println();
            if (patients.size() > 0) {
                System.out.println("------------------------------");
                System.out.println("           PACIENTES          ");
                System.out.println("------------------------------");
                
                for (Patient patient : patients) {
                	Doctor doctor = hg.getOne(hospitalGraph, hg.and(hg.type(Doctor.class), hg.eq("doctorCrm", doctorCrm)));
                	String doctorName;
                	if(doctor == null) {
                		doctorName = "Não há médico vinculado";
                	} else {
                		doctorName = doctor.getPersonName();
                	}
                	
                	System.out.println("CPF: " + patient.getPersonCpf());
                    System.out.println("Nome: " + patient.getPersonName());
                    System.out.println("Email: " + patient.getPersonEmail());
                    System.out.println("Convênio: " + patient.getPatientHealthInsurance());
                    System.out.println("Médico Responsável: " + doctorName);

                    System.out.println("------------------------------");
                }
            } else {
                System.out.print("Não há pacientes cadastrados.");
            }
            System.out.println();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            hospitalGraph.close();
        }
    }
}

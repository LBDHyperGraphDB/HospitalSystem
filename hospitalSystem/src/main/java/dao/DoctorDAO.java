package dao;

import java.util.List;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HyperGraph;

import model.Doctor;

public class DoctorDAO {
    String databaseLocation = "../hypergraphdb-1.3";
    HyperGraph hospitalGraph = null;

    public DoctorDAO(HyperGraph hospitalGraph) {
        this.hospitalGraph = hospitalGraph;
    }

    public boolean addDoctor(Doctor doctor) {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            // Avoid duplication: do not add if CRM exists.
            if (!this.findDoctorByCRM(hospitalGraph, doctor.getDoctorCrm())) {
                hospitalGraph.add(doctor);
                System.out.println("[SUCESSO] M�dico adicionado com sucesso!");
                return true;

            } else {
                System.out.println("[ERRO]: O CRM " + doctor.getDoctorCrm() + " já existe.");
                return true;
            }
        } catch (Throwable t) {
            System.out.println("[ERRO]: O m�dico " + doctor.getPersonName() + " não pôde ser adicionado.");
            t.printStackTrace();
            return false;
        } finally {
            hospitalGraph.close();
        }

    }

    public void getAllDoctors() {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            List<Doctor> doctors = hg.getAll(hospitalGraph, hg.and(hg.type(Doctor.class)));

            System.out.println();
            if (doctors.size() > 0) {
                System.out.println("------------------------------");
                System.out.println("         M�DICOS         ");
                System.out.println("------------------------------");

                for (Doctor doctor : doctors) {
                    System.out.println("Nome: " + doctor.getPersonName());
                    System.out.println("Email: " + doctor.getPersonEmail());
                    System.out.println("CRM: " + doctor.getDoctorCrm());
                    System.out.println("Estado Civil: " + doctor.getDoctorMaritalState());
                    System.out.println("Titula��o: " + doctor.getDoctorTitration());

                    System.out.println("------------------------------");
                }
            } else {
                System.out.print("Não há m�dicos cadastrados.");
            }
            System.out.println();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            hospitalGraph.close();
        }
    }

    public boolean findDoctorByCRM(HyperGraph hospitalGraph, String Crm) {
        try {
            List<Doctor> doctors = hg.getAll(hospitalGraph, hg.and(hg.type(Doctor.class), hg.eq("doctorCrm", Crm)));
            if (doctors.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            System.out.println("[ERRO]: Ocorreu um erro ao buscar o CRM " + Crm + ".");
            t.printStackTrace();
            return false;

        }
    }

    public boolean updateDoctor(String Crm, String attribute, String value) {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            if (this.findDoctorByCRM(hospitalGraph, Crm)) {
                Doctor doctor = new Doctor();
                doctor = hg.getOne(hospitalGraph, hg.and(hg.type(Doctor.class), hg.eq("doctorCrm", Crm)));

                doctor.setField(attribute, value);
                hospitalGraph.update(doctor);
                System.out.println("[SUCESSO] m�dico atualizado com sucesso!");
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            System.out.println("[ERRO]: O m�dico de CRM " + Crm + " n�o pode ser atualizado.");
            t.printStackTrace();
            return false;
        } finally {
            hospitalGraph.close();
        }
    }

    public boolean deleteDoctor(String Crm) {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            if (this.findDoctorByCRM(hospitalGraph, Crm)) {
                Doctor doctor = new Doctor();
                doctor = hg.getOne(hospitalGraph, hg.and(hg.type(Doctor.class), hg.eq("doctorCrm", Crm)));

                HGHandle doctorHandle = hospitalGraph.getHandle(doctor);
                hospitalGraph.remove(doctorHandle);

                System.out.println("[SUCESSO] M�dico exclu�do com sucesso!");
                return true;
            } else {
                return false;
            }

        } catch (Throwable t) {
            System.out.println("[ERRO]: O m�dico de CRM " + Crm + " n�o pode ser exclu�do.");
            t.printStackTrace();
            return false;
        } finally {
            hospitalGraph.close();
        }
    }
}

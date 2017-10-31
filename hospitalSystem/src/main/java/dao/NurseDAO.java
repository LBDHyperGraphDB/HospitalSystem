package dao;

import java.util.List;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HyperGraph;

import model.Nurse;

public class NurseDAO {
    String databaseLocation = "../hypergraphdb-1.3";
    HyperGraph hospitalGraph = null;

    public NurseDAO(HyperGraph hospitalGraph) {
        this.hospitalGraph = hospitalGraph;
    }

    public boolean addNurse(Nurse nurse) {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            // Avoid duplication: do not add if COREN exists.
            if (!this.findNurseByCoren(hospitalGraph, nurse.getNurseCoren())) {
                hospitalGraph.add(nurse);
                System.out.println("[SUCESSO] Enfermeira adicionada com sucesso!");
                return true;
            } else {
                System.out.println("[ERRO]: O COREN " + nurse.getNurseCoren() + " já existe.");
                return true;
            }
        } catch (Throwable t) {
            System.out.println("[ERRO]: A Enfermeira " + nurse.getPersonName() + " não pôde ser adicionado.");
            t.printStackTrace();
            return false;
        } finally {
            hospitalGraph.close();
        }
    }

    public void getAllNurses() {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            List<Nurse> nurses = hg.getAll(hospitalGraph, hg.and(hg.type(Nurse.class)));

            System.out.println();
            if (nurses.size() > 0) {
                System.out.println("------------------------------");
                System.out.println("         ENFERMEIRAS         ");
                System.out.println("------------------------------");

                for (Nurse nurse : nurses) {
                    System.out.println("Nome: " + nurse.getPersonName());
                    System.out.println("Qualificaão: " + nurse.getNurseQualification());
                    System.out.println("COREN: " + nurse.getNurseCoren());

                    System.out.println("------------------------------");
                }
            } else {
                System.out.print("Não há enfermeiras cadastradas.");
            }
            System.out.println();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            hospitalGraph.close();
        }
    }

    public boolean findNurseByCoren(HyperGraph hospitalGraph, String coren) {
        try {
            List<Nurse> nurses =
                    hg.getAll(hospitalGraph, hg.and(hg.type(Nurse.class), hg.eq("nurseCoren", coren)));
            if (nurses.size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            System.out.println("[ERRO]: Ocorreu um erro ao buscar o COREN " + coren + ".");
            t.printStackTrace();
            return false;
        }
    }

    public boolean updateNurse(String coren, String attribute, String value) {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            if (this.findNurseByCoren(hospitalGraph, coren)) {
                Nurse nurse = new Nurse();
                nurse = hg.getOne(hospitalGraph, hg.and(hg.type(Nurse.class), hg.eq("nurseCoren", coren)));

                nurse.setField(attribute, value);
                hospitalGraph.update(nurse);
                System.out.println("[SUCESSO] Enfermeira atualizada com sucesso!");
                return true;
            } else {
                return false;
            }
        } catch (Throwable t) {
            System.out.println("[ERRO]: A enfermeira de COREN " + coren + " não pôde ser atualizado.");
            t.printStackTrace();
            return false;
        } finally {
            hospitalGraph.close();
        }
    }

    public boolean deleteNurse(String coren) {
        try {
            hospitalGraph = new HyperGraph(databaseLocation);
            if (this.findNurseByCoren(hospitalGraph, coren)) {
                Nurse nurse = new Nurse();
                nurse = hg.getOne(hospitalGraph, hg.and(hg.type(Nurse.class), hg.eq("nurseCoren", coren)));

                HGHandle nurseHandle = hospitalGraph.getHandle(nurse);
                hospitalGraph.remove(nurseHandle);

                System.out.println("[SUCESSO] Enfermeira excluida com sucesso!");
                return true;
            } else {
                return false;
            }

        } catch (Throwable t) {
            System.out.println("[ERRO]: A enfermeira de Coren " + coren + " não pode ser excluída.");
            t.printStackTrace();
            return false;
        } finally {
            hospitalGraph.close();
        }
    }
}

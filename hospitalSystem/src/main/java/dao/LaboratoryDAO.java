package dao;

import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HyperGraph;

import java.util.ArrayList;
import java.util.List;
import model.Laboratory;
import model.MedicalExam;

public class LaboratoryDAO {
	String databaseLocation = "../hypergraphdb-1.3";
	HyperGraph hospitalGraph = null;

	public LaboratoryDAO(HyperGraph hospitalGraph) {
		this.hospitalGraph = hospitalGraph;
	}

	public boolean addLaboratory(Laboratory laboratory) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			// Avoid duplication: do not add if CNPJ exists.
			if (!this.findLaboratoryByCnpj(hospitalGraph, laboratory.getLaboratoryCnpj())) {
				hospitalGraph.add(laboratory);
				System.out.println("[SUCESSO] Laboratório adicionado com sucesso!");
				return true;
			} else {
				System.out.println("[ERRO]: O CNPJ " + laboratory.getLaboratoryCnpj() + " já existe.");
				return true;
			}
		} catch (Throwable t) {
			System.out.println(
					"[ERRO]: O Laboratório " + laboratory.getLaboratoryDescription() + " não pôde ser adicionado.");
			t.printStackTrace();
			return false;
		} finally {
			hospitalGraph.close();
		}
	}

	public boolean verifyExistenceLaboratories() {
		boolean result = false;

		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			List<Laboratory> laboratories = hg.getAll(hospitalGraph, hg.and(hg.type(Laboratory.class)));
			if (laboratories.size() > 0) {
				result = true;
				return result;
			} else {
				System.out.print("Não há laboratórios cadastrados.");
				System.out.println();
				result = false;
				return result;
			}
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			hospitalGraph.close();
		}
		return result;
	}

	public void getAllLaboratories() {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			List<Laboratory> laboratories = hg.getAll(hospitalGraph, hg.and(hg.type(Laboratory.class)));

			System.out.println();
			if (laboratories.size() > 0) {
				System.out.println("------------------------------");
				System.out.println("         LABORATÓRIOS         ");
				System.out.println("------------------------------");

				for (Laboratory laboratory : laboratories) {
					System.out.println("Nome: " + laboratory.getLaboratoryDescription());
					System.out.println("CNPJ: " + laboratory.getLaboratoryCnpj());
					System.out.println("Endereço: " + laboratory.getLaboratoryAddress());
					System.out.println("Telefone: " + laboratory.getLaboratoryPhoneNumber());
					System.out.println("------------------------------");
				}

			} else
				System.out.print("Não há laboratórios cadastrados.");
			System.out.println();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			hospitalGraph.close();
		}

	}

	public void getAllLaboratoriesExams() {
		try {

			hospitalGraph = new HyperGraph(databaseLocation);
			List<Laboratory> laboratories = hg.getAll(hospitalGraph, hg.and(hg.type(Laboratory.class)));

			System.out.println();
			if (laboratories.size() > 0) {
				System.out.println("------------------------------");
				System.out.println("         LABORAT�RIOS         ");
				System.out.println("------------------------------");

				for (Laboratory laboratory : laboratories) {
					System.out.println("Nome: " + laboratory.getLaboratoryDescription());
					System.out.println("CNPJ: " + laboratory.getLaboratoryCnpj());
					System.out.println("Endere�o: " + laboratory.getLaboratoryAddress());
					System.out.println("Telefone: " + laboratory.getLaboratoryPhoneNumber());
					List<MedicalExam> medicalExams = hg.getAll(hospitalGraph, hg.and(hg.type(MedicalExam.class),
							hg.eq("examLaboratoryCnpj", laboratory.getLaboratoryCnpj())));
					System.out.println("------------------------------");
					System.out.println("         Exames  de  " + laboratory.getLaboratoryDescription() + "         ");
					System.out.println("------------------------------");
					for (MedicalExam exam : medicalExams) {
						System.out.println("C�digo " + exam.getExamCode());
						System.out.println("Descri��o: " + exam.getExamDescription());
						System.out.println("Restri��o: " + exam.getExamRestriction());
						System.out.println("------------------------------");
					}
					System.out.println("------------------------------");
				}
			} else
				System.out.print("Não há laboratórios cadastrados.");
			System.out.println();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			hospitalGraph.close();
		}
	}

	public boolean findLaboratoryByCnpj(HyperGraph hospitalGraph, String cnpj) {
		try {
			List<Laboratory> laboratories = hg.getAll(hospitalGraph,
					hg.and(hg.type(Laboratory.class), hg.eq("laboratoryCnpj", cnpj)));
			if (laboratories.size() > 0)
				return true;
			else
				return false;
		} catch (Throwable t) {
			System.out.println("[ERRO]: Ocorreu um erro ao buscar o CNPJ " + cnpj + ".");
			t.printStackTrace();
			return false;
		}
	}

	public boolean updateLaboratory(String cnpj, String attribute, String value) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (this.findLaboratoryByCnpj(hospitalGraph, cnpj)) {
				Laboratory laboratory = new Laboratory();
				laboratory = hg.getOne(hospitalGraph, hg.and(hg.type(Laboratory.class), hg.eq("laboratoryCnpj", cnpj)));

				laboratory.setField(attribute, value);
				hospitalGraph.update(laboratory);
				System.out.println("[SUCESSO] Laboratório atualizado com sucesso!");
				return true;
			} else
				return false;
		} catch (Throwable t) {
			System.out.println("[ERRO]: O Laboratório de CNPJ " + cnpj + " não pôde ser atualizado.");
			t.printStackTrace();
			return false;
		} finally {
			hospitalGraph.close();
		}
	}

	public boolean deleteLaboratory(String cnpj) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (this.findLaboratoryByCnpj(hospitalGraph, cnpj)) {
				Laboratory laboratory = new Laboratory();
				laboratory = hg.getOne(hospitalGraph, hg.and(hg.type(Laboratory.class), hg.eq("laboratoryCnpj", cnpj)));

				HGHandle laboratoryHandle = hospitalGraph.getHandle(laboratory);
				hospitalGraph.remove(laboratoryHandle);

				System.out.println("[SUCESSO] Laboratório excluído com sucesso!");
				return true;
			} else
				return false;

		} catch (Throwable t) {
			System.out.println("[ERRO]: O Laboratório de CNPJ " + cnpj + " não pôde ser excluído.");
			t.printStackTrace();
			return false;
		} finally {
			hospitalGraph.close();
		}
	}
}

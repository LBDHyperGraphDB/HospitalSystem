package dao;

import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HGHandle;

import java.util.List;
import model.Laboratory;

public class LaboratoryDAO {
	String databaseLocation = "../hypergraphdb-1.3"; 
	HyperGraph laboratoryGraph = null;

	public boolean addLaboratory(Laboratory laboratory) {
		try {
			laboratoryGraph = new HyperGraph(databaseLocation);
			
			// Avoid duplication: do not add if CNPJ exists.
			if (!this.findLaboratoryByCnpj(laboratoryGraph, laboratory.getLaboratoryCnpj())) {
				laboratoryGraph.add(laboratory);
				System.out.println("[SUCESSO] Laboratório adicionado com sucesso!");
				return true;
			}
			else {
				System.out.println("[ERRO]: O CNPJ " + laboratory.getLaboratoryCnpj() + " já existe.");
				return true;
			}
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Laboratório " + laboratory.getLaboratoryDescription() + " não pôde ser adicionado.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   laboratoryGraph.close();
	   }
	}
	
	public void getAllLaboratories() {
		try {
			laboratoryGraph = new HyperGraph(databaseLocation);
			
			List<Laboratory> laboratories = hg.getAll(laboratoryGraph, hg.and(hg.type(Laboratory.class)));
			
			System.out.println();
			if (laboratories.size() > 0) {
				System.out.println("------------------------------");
				System.out.println("         LABORATÓRIOS         ");
				System.out.println("------------------------------");
				
				for (Laboratory laboratory: laboratories) {
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
			laboratoryGraph.close();
		}
	}
	
	public boolean findLaboratoryByCnpj(HyperGraph laboratoryGraph, String cnpj) {
		try {
			List<Laboratory> laboratories = hg.getAll(laboratoryGraph, hg.and(hg.type(Laboratory.class), hg.eq("laboratoryCnpj", cnpj)));
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
			laboratoryGraph = new HyperGraph(databaseLocation);
			
			if (this.findLaboratoryByCnpj(laboratoryGraph, cnpj)) {
				Laboratory laboratory = new Laboratory();
				laboratory = hg.getOne(laboratoryGraph, hg.and(hg.type(Laboratory.class), hg.eq("laboratoryCnpj", cnpj)));
				
				laboratory.setField(attribute, value);
				laboratoryGraph.update(laboratory);
				System.out.println("[SUCESSO] Laboratório atualizado com sucesso!");
				return true;
			} else
				return false;
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Laboratório de CNPJ " + cnpj + " não pôde ser atualizado.");
	       t.printStackTrace();
	       return false;
	   } finally {
	       laboratoryGraph.close();
	   }
	}
	
	public boolean deleteLaboratory(String cnpj) {
		try {
			laboratoryGraph = new HyperGraph(databaseLocation);
			if (this.findLaboratoryByCnpj(laboratoryGraph, cnpj)) {
				Laboratory laboratory = new Laboratory();
				laboratory = hg.getOne(laboratoryGraph, hg.and(hg.type(Laboratory.class), hg.eq("laboratoryCnpj", cnpj)));
				
				HGHandle laboratoryHandle = laboratoryGraph.getHandle(laboratory);
				laboratoryGraph.remove(laboratoryHandle);
				
				System.out.println("[SUCESSO] Laboratório excluído com sucesso!");
				return true;
			} else
				return false;
			
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: O Laboratório de CNPJ " + cnpj + " não pôde ser excluído.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   laboratoryGraph.close();
	   }
	}
}

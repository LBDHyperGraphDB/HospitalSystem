package dao;

import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HyperGraph;

import java.util.List;
import model.WingOfBuilding;

public class WingOfBuildingDAO {
	String databaseLocation = "../hypergraphdb-1.3";
	HyperGraph hospitalGraph = null;
	
	public WingOfBuildingDAO(HyperGraph hospitalGraph) {
		this.hospitalGraph = hospitalGraph;
	}
	
	public boolean addWingOfBuilding(WingOfBuilding wingOfBuilding) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			// Avoid duplication: do not add if code exists.
			if (!this.findWingOfBuildingByCode(hospitalGraph, wingOfBuilding.getWingCode())) {
				hospitalGraph.add(wingOfBuilding);
				System.out.println("[SUCESSO] Ala adicionada com sucesso!");
				return true;
			}
			else {
				System.out.println("[ERRO]: O código " + wingOfBuilding.getWingCode() + " já existe.");
				return true;
			}
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: A ala " + wingOfBuilding.getWingName() + " não pôde ser adicionada.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   hospitalGraph.close();
	   }
	}
	
	public void getAllWingsOfBuilding() {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			List<WingOfBuilding> wingsOfBuilding = hg.getAll(hospitalGraph, hg.and(hg.type(WingOfBuilding.class)));
			
			System.out.println();
			if (wingsOfBuilding.size() > 0) {
				System.out.println("------------------------------");
				System.out.println("             ALAS             ");
				System.out.println("------------------------------");
				
				for (WingOfBuilding wingOfBuilding: wingsOfBuilding) {
					System.out.println("Nome: " + wingOfBuilding.getWingName());
					System.out.println("Código: " + wingOfBuilding.getWingCode());
					System.out.println("------------------------------");
				}
			} else
				System.out.print("Não há alas cadastradas.");
			System.out.println();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		   hospitalGraph.close();
	   }
	}
	
	public boolean findWingOfBuildingByCode(HyperGraph hospitalGraph, int code) {
		try {
			List<WingOfBuilding> wingsOfBuilding = hg.getAll(hospitalGraph, hg.and(hg.type(WingOfBuilding.class), hg.eq("wingCode", code)));
			if (wingsOfBuilding.size() > 0)
				return true;
			else
				return false;
		} catch (Throwable t) {
			System.out.println("[ERRO]: Ocorreu um erro ao buscar o código " + code + ".");
			t.printStackTrace();
			return false;
		}
	}
	
	public boolean updateWingOfBuilding(int code, String attribute, String value) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (this.findWingOfBuildingByCode(hospitalGraph, code)) {
				WingOfBuilding wingOfBuilding = new WingOfBuilding();
				wingOfBuilding = hg.getOne(hospitalGraph, hg.and(hg.type(WingOfBuilding.class), hg.eq("wingCode", code)));
				
				wingOfBuilding.setField(attribute, value);
				hospitalGraph.update(wingOfBuilding);
				System.out.println("[SUCESSO] Ala atualizada com sucesso!");
				return true;
			} else
				return false;
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: A ala de código " + code + " não pôde ser atualizada.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   hospitalGraph.close();
	   }
	}
	
	public boolean deleteWingOfBuilding(int code) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (this.findWingOfBuildingByCode(hospitalGraph, code)) {
				WingOfBuilding wingOfBuilding = new WingOfBuilding();
				wingOfBuilding = hg.getOne(hospitalGraph, hg.and(hg.type(WingOfBuilding.class), hg.eq("wingCode", code)));
				
				HGHandle wingOfBuildingHandle = hospitalGraph.getHandle(wingOfBuilding);
				hospitalGraph.remove(wingOfBuildingHandle);
				
				System.out.println("[SUCESSO] Ala excluída com sucesso!");
				return true;
			} else
				return false;
			
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: A ala de código " + code + " não pôde ser excluída.");
	       t.printStackTrace();
	       return false;
	   }
		finally {
		   hospitalGraph.close();
	   }
	}
}

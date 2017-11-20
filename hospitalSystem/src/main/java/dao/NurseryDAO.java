package dao;

import org.hypergraphdb.HGQuery.hg;
import org.hypergraphdb.HGValueLink;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HyperGraph;

import java.util.List;

import model.Nursery;
import model.WingOfBuilding;

public class NurseryDAO {
	String databaseLocation = "../hypergraphdb-1.3";
	HyperGraph hospitalGraph = null;
	WingOfBuildingDAO wingOfBuildingDAO = null;
	
	public NurseryDAO(HyperGraph hospitalGraph) {
		this.hospitalGraph = hospitalGraph;
		this.wingOfBuildingDAO = new WingOfBuildingDAO(hospitalGraph);
	}
	
	public boolean addNursery(Nursery nursery) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			// Avoid duplication: do not add if code exists.
			if (!this.findNurseryByCode(hospitalGraph, nursery.getNurseryCode()) &&
				wingOfBuildingDAO.findWingOfBuildingByCode(hospitalGraph, nursery.getNurseryWingOfBuilding())) {
				HGHandle addNursery =  hospitalGraph.add(nursery);
				HGHandle nurseryWing = hospitalGraph.getHandle(wingOfBuildingDAO.findWingOfBuildingByCode(hospitalGraph, nursery.getNurseryWingOfBuilding()));
				// Create the link / relationship between atoms
				new HGValueLink(hospitalGraph, addNursery, nurseryWing);
				System.out.println("[SUCESSO] Enfermaria adicionada com sucesso!");
				return true;
			} else if(!wingOfBuildingDAO.findWingOfBuildingByCode(hospitalGraph, nursery.getNurseryWingOfBuilding())) {
				System.out.println("[ERRO]: A ala de código " + nursery.getNurseryWingOfBuilding() + " não existe.");
				return true;
			}
			else {
				System.out.println("[ERRO]: O código " + nursery.getNurseryCode() + " já existe.");
				return true;
			}
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: A Enfermaria " + nursery.getNurseryDescription() + " não pôde ser adicionada.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   hospitalGraph.close();
	   }
	}
	
	public void getAllWards() {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			List<Nursery> wards = hg.getAll(hospitalGraph, hg.and(hg.type(Nursery.class)));
			
			System.out.println();
			if (wards.size() > 0) {
				System.out.println("------------------------------");
				System.out.println("          ENFERMARIAS         ");
				System.out.println("------------------------------");
				
				for (Nursery nursery: wards) {
					
					WingOfBuilding wingOfBuilding = hg.getOne(hospitalGraph, hg.and(hg.type(WingOfBuilding.class), hg.eq("wingCode", nursery.getNurseryWingOfBuilding())));
	            	String wingName;
	            	if(wingOfBuilding == null) {
	            		wingName = "Não há ala vinculada";
	            	} else {
	            		wingName = wingOfBuilding.getWingName();
	            	}
	            	
					System.out.println("Nome: " + nursery.getNurseryDescription());
					System.out.println("Código: " + nursery.getNurseryCode());
					System.out.println("Ala: " + wingName);
					System.out.println("------------------------------");
				}
			} else
				System.out.print("Não há enfermarias cadastradas.");
			System.out.println();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		   hospitalGraph.close();
	   }
	}
	
	public boolean findNurseryByCode(HyperGraph hospitalGraph, int code) {
		try {
			List<Nursery> wards = hg.getAll(hospitalGraph, hg.and(hg.type(Nursery.class), hg.eq("nurseryCode", code)));
			if (wards.size() > 0)
				return true;
			else
				return false;
		} catch (Throwable t) {
			System.out.println("[ERRO]: Ocorreu um erro ao buscar o código " + code + ".");
			t.printStackTrace();
			return false;
		}
	}
	
	public boolean updateNursery(int code, String attribute, String value) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			boolean response = true;
			
			if (this.findNurseryByCode(hospitalGraph, code)) {
				Nursery nursery = new Nursery();
				nursery = hg.getOne(hospitalGraph, hg.and(hg.type(Nursery.class), hg.eq("nurseryCode", code)));
				
				
				if(attribute == "nurseryWingOfBuilding") {
					int newValue = Integer.parseInt(value);
					if(wingOfBuildingDAO.findWingOfBuildingByCode(hospitalGraph, newValue)) {
						nursery.setNurseryWingOfBuilding(newValue);
						hospitalGraph.update(nursery);
						System.out.println("[SUCESSO] Enfermaria atualizada com sucesso!");
					} else {
						System.out.println("[ERRO] A ala de código " + nursery.getNurseryWingOfBuilding() + " não existe.");
						response = false;
					}
				} else {
					nursery.setField(attribute, value);
					hospitalGraph.update(nursery);
				}
				return response;
			} else
				return false;
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: A Enfermaria de código " + code + " não pôde ser atualizada.");
	       t.printStackTrace();
	       return false;
	   } finally {
		   hospitalGraph.close();
	   }
	}
	
	public boolean deleteNursery(int code) {
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			if (this.findNurseryByCode(hospitalGraph, code)) {
				Nursery nursery = new Nursery();
				nursery = hg.getOne(hospitalGraph, hg.and(hg.type(Nursery.class), hg.eq("nurseryCode", code)));
				
				HGHandle nurseryHandle = hospitalGraph.getHandle(nursery);
				hospitalGraph.remove(nurseryHandle);
				
				System.out.println("[SUCESSO] Enfermaria excluída com sucesso!");
				return true;
			} else
				return false;
			
	   } catch (Throwable t) {
		   System.out.println("[ERRO]: A Enfermaria de código " + code + " não pôde ser excluída.");
	       t.printStackTrace();
	       return false;
	   }
		finally {
		   hospitalGraph.close();
	   }
	}
}

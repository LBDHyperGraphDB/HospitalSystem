package controller;

import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import dao.NurseryDAO;
import dao.WingOfBuildingDAO;
import model.Nursery;
import view.Menu;

public class NurseryController {
	Menu menu = new Menu();
	NurseryDAO nurseryDAO = null;
	WingOfBuildingDAO wingOfBuildingDAO = null;
	Scanner scanner = new Scanner(System.in);
	
	private String name;
	private int nurseryCode;
	private int wingOfBuildingCode;
	private int option;
	
	public NurseryController(HyperGraph hospitalGraph) {
		this.nurseryDAO = new NurseryDAO(hospitalGraph);
		this.wingOfBuildingDAO = new WingOfBuildingDAO(hospitalGraph);
	}
	
	public void chooseAction (int option) {
		do {
			switch(option) {
				case 1:
					System.out.println("Digite o nome da enfermaria: ");
					name = scanner.nextLine();
					System.out.println("Digite o código da enfermaria: ");
					nurseryCode = scanner.nextInt();
					scanner.nextLine();
					wingOfBuildingDAO.getAllWingsOfBuilding();
					System.out.println("Digite o código da ala em que se encontra essa enfermaria: ");
					wingOfBuildingCode = scanner.nextInt();
					scanner.nextLine();
					
					Nursery nursery = new Nursery(nurseryCode, name, wingOfBuildingCode);
					nurseryDAO.addNursery(nursery);
					
					menu.crudMenu("Enfermaria");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
				case 2:
					System.out.println("Digite o código da enfermaria: ");
					nurseryCode = scanner.nextInt();
					
					System.out.println("Qual atributo deseja modificar?");
					System.out.println("1- Nome    2- Ala");
					
					int attributeNumber = scanner.nextInt();
					scanner.nextLine();
					
					String attribute = "";
					String attributeName = "";
					
					if(attributeNumber == 1) {
						attribute = "nurseryDescription";
						attributeName = "nome";
					} else if(attributeNumber == 2) {
						attribute = "nurseryWingOfBuilding";
						attributeName = "ala";
					}
					
					System.out.println("Digite o novo valor para " + attributeName + ": ");
					String value = scanner.nextLine();
					nurseryDAO.updateNursery(nurseryCode, attribute, value);
					
					menu.crudMenu("Enfermaria");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
				case 3:
					nurseryDAO.getAllWards();
					System.out.println("Deseja voltar ao menu (Sim / Não)?");
					String back = scanner.nextLine();
					
					if(menu.backToMenu(back)) {
						this.clear();
						menu.crudMenu("Enfermarias");
						option = scanner.nextInt();
						scanner.nextLine();
					} else {
						option = 5;
					}
					break;
				case 4:
					System.out.println("Digite o código da enfermaria: ");
					nurseryCode = scanner.nextInt();
					scanner.nextLine();
					
					nurseryDAO.deleteNursery(nurseryCode);
					
					menu.crudMenu("Enfermaria");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
				case 5:
					this.clear();
					System.out.println("Voltando ao menu principal...");
					break;
				default:
					this.clear();
					System.out.println("Opção inválida! Tente novamente");
					
					menu.crudMenu("Enfermaria");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
			}
			this.option = option;
		} while (option != 5);
	}

	public int getOption() {
		return option;
	}
	
	public void clear() {
		for (int i = 0; i < 3; i++) 
			System.out.println();
	}
}

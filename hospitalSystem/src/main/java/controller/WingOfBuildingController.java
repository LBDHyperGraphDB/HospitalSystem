package controller;

import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import dao.WingOfBuildingDAO;
import model.WingOfBuilding;
import view.Menu;

public class WingOfBuildingController {
	Menu menu = new Menu();
	WingOfBuildingDAO wingOfBuildingDAO = null;
	Scanner scanner = new Scanner(System.in);
	
	private String name;
	private int code;
	private int option;
	
	public WingOfBuildingController(HyperGraph hospitalGraph) {
		this.wingOfBuildingDAO = new WingOfBuildingDAO(hospitalGraph);
	}
	
	public void chooseAction (int option) {
		do {
			switch(option) {
				case 1:
					System.out.println("Digite o nome da ala: ");
					name = scanner.nextLine();
					System.out.println("Digite o código da ala: ");
					code = scanner.nextInt();
					scanner.nextLine();
					
					WingOfBuilding wingOfBuilding = new WingOfBuilding(code, name);
					wingOfBuildingDAO.addWingOfBuilding(wingOfBuilding);
					
					menu.crudMenu("Ala");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
				case 2:
					System.out.println("Digite o código da ala: ");
					code = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Digite o novo valor para nome: ");
					String value = scanner.nextLine();
					wingOfBuildingDAO.updateWingOfBuilding(code, "wingName", value);
					
					menu.crudMenu("Ala");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
				case 3:
					wingOfBuildingDAO.getAllWingsOfBuilding();
					System.out.println("Deseja voltar ao menu (Sim / Não)?");
					String back = scanner.nextLine();
					
					if(menu.backToMenu(back)) {
						this.clear();
						menu.crudMenu("Alas");
						option = scanner.nextInt();
						scanner.nextLine();
					} else {
						option = 5;
					}
					break;
				case 4:
					System.out.println("Digite o código da ala: ");
					code = scanner.nextInt();
					scanner.nextLine();
					
					wingOfBuildingDAO.deleteWingOfBuilding(code);
					
					menu.crudMenu("Ala");
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
					
					menu.crudMenu("Ala");
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

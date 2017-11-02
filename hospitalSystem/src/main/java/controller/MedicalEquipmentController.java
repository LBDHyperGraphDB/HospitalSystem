package controller;

import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import dao.MedicalEquipmentDAO;
import model.MedicalEquipment;
import view.Menu;

public class MedicalEquipmentController {

	Menu menu = new Menu();
	MedicalEquipmentDAO medicalEquipmentDAO = null;
	Scanner scanner = new Scanner(System.in);
	
	private int code;
	private String description;
	private String manufacturer;
	private int option;
	
	public MedicalEquipmentController(HyperGraph hospitalGraph) {
		this.medicalEquipmentDAO = new MedicalEquipmentDAO(hospitalGraph);
	}
	
	public void chooseAction (int option) {
		do {
			switch(option) {
				case 1:
					System.out.println("Digite o código do equipamento: ");
					code = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Digite a descrição do equipamento: ");
					description = scanner.nextLine();
					System.out.println("Digite o a manufatura do equipamento: ");
					manufacturer = scanner.nextLine();
					
					MedicalEquipment medicalEquipment = new MedicalEquipment(code, description, manufacturer);
					medicalEquipmentDAO.addMedicalEquipment(medicalEquipment);
					
					menu.crudMenu("Equipamentos");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
				case 2:
					System.out.println("Digite o código do equipamento: ");
					code = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Qual atributo deseja modificar?");
					System.out.println("1- Descrição    2- Manufatura");
					
					int attributeNumber = scanner.nextInt();
					scanner.nextLine();
					
					String attribute = "";
					String attributeName = "";
					if(attributeNumber == 1) {
						attribute = "equipmentDescription";
						attributeName = "Descrição";
					} else if(attributeNumber == 2) {
						attribute = "equipmentManufacturer";
						attributeName = "Manufatura";
					}

					
					System.out.println("Digite o novo valor para " + attributeName + ": ");
					String value = scanner.nextLine();
					medicalEquipmentDAO.updateMedicalEquipment(code, attribute, value);
					
					menu.crudMenu("Equipamentos");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
					
				case 3:
					medicalEquipmentDAO.getAllMedicalEquipments();
					System.out.println("Deseja voltar ao menu (Sim / Não)?");
					String back = scanner.nextLine();
					
					if(menu.backToMenu(back)) {
						this.clear();
						menu.crudMenu("Equipamentos");
						option = scanner.nextInt();
						scanner.nextLine();
					} else {
						option = 5;
					}
					break;
				case 4:
					System.out.println("Digite o código do equipamento: ");
					code = scanner.nextInt();
					scanner.nextLine();
					
					medicalEquipmentDAO.deleteMedicalEquipment(code);
					
					menu.crudMenu("Equipamentos");
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
					
					menu.crudMenu("Equipamentos");
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

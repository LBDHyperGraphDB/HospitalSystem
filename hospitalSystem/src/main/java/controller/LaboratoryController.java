package controller;

import java.util.Scanner;

import dao.LaboratoryDAO;
import model.Laboratory;
import view.Menu;

public class LaboratoryController {
	Menu menu = new Menu();
	LaboratoryDAO laboratoryDAO = new LaboratoryDAO();
	Scanner scanner = new Scanner(System.in);
	
	private String cnpj;
	private String description;
	private String address;
	private String phoneNumber;
	private int option;
	
	public void chooseAction (int option) {
		do {
			switch(option) {
				case 1:
					System.out.println("Digite o nome do laboratório: ");
					description = scanner.nextLine();
					System.out.println("Digite o CNPJ do laboratório: ");
					cnpj = scanner.nextLine();
					System.out.println("Digite o endereço do laboratório: ");
					address = scanner.nextLine();
					System.out.println("Digite o telefone do laboratório: ");
					phoneNumber = scanner.nextLine();
					
					Laboratory laboratory = new Laboratory(cnpj, description, address, phoneNumber);
					laboratoryDAO.addLaboratory(laboratory);
					
					menu.crudMenu("Laboratório");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
				case 2:
					System.out.println("Digite o CNPJ do laboratório: ");
					cnpj = scanner.nextLine();
					System.out.println("Qual atributo deseja modificar?");
					System.out.println("1- Nome    2- CNPJ    3- Endereço    4- Telefone");
					
					int attributeNumber = scanner.nextInt();
					scanner.nextLine();
					
					String attribute = "";
					String attributeName = "";
					if(attributeNumber == 1) {
						attribute = "laboratoryDescription";
						attributeName = "nome";
					} else if(attributeNumber == 2) {
						attribute = "laboratoryCnpj";
						attributeName = "CNPJ";
					} else if(attributeNumber == 3) {
						attribute = "laboratoryAddress";
						attributeName = "endereço";
					} else if(attributeNumber == 4) {
						attribute = "laboratoryPhoneNumber";
						attributeName = "telefone";
					}
					
					System.out.println("Digite o novo valor para " + attributeName + ": ");
					String value = scanner.nextLine();
					laboratoryDAO.updateLaboratory(cnpj, attribute, value);
					
					menu.crudMenu("Laboratório");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
				case 3:
					laboratoryDAO.getAllLaboratories();
					System.out.println("Deseja voltar ao menu (Sim / Não)?");
					String back = scanner.nextLine();
					
					if(menu.backToMenu(back)) {
						this.clear();
						menu.crudMenu("Laboratórios");
						option = scanner.nextInt();
						scanner.nextLine();
					} else {
						option = 5;
					}
					break;
				case 4:
					System.out.println("Digite o CNPJ do laboratório: ");
					cnpj = scanner.nextLine();
					
					laboratoryDAO.deleteLaboratory(cnpj);
					
					menu.crudMenu("Laboratório");
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
					
					menu.crudMenu("Laboratório");
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

package controller;

import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import dao.MedicalAgreementDAO;
import model.MedicalAgreement;

import view.Menu;

public class MedicalAgreementController {
	Menu menu = new Menu();
	MedicalAgreementDAO medicalAgreementDAO = null;
	Scanner scanner = new Scanner(System.in);
	
	private int code;
	private String description;
	private double value;
	private String type;
	private int option;
	
	public MedicalAgreementController(HyperGraph hospitalGraph) {
		this.medicalAgreementDAO = new MedicalAgreementDAO(hospitalGraph);
	}
	
	public void chooseAction (int option) {
		do {
			switch(option) {
				case 1:
					System.out.println("Digite o código do convênio: ");
					code = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Digite a descrição do convênio: ");
					description = scanner.nextLine();
					System.out.println("Digite o valor do convênio: ");
					value = scanner.nextDouble();
					scanner.nextLine();
					System.out.println("Digite o tipo do convênio: ");
					type = scanner.nextLine();
					
					MedicalAgreement medicalAgreement = new MedicalAgreement(code, description, value, type);
					medicalAgreementDAO.addMedicalAgreement(medicalAgreement);
					
					menu.crudMenu("Convênios");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
				case 2:
					System.out.println("Digite o código do convênio: ");
					code = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Qual atributo deseja modificar?");
					System.out.println("1- Descrição    2- Valor	3- Tipo");
					
					int attributeNumber = scanner.nextInt();
					scanner.nextLine();
					
					String attribute = "";
					String attributeName = "";
					if(attributeNumber == 1) {
						attribute = "agreementDescription";
						attributeName = "descrição";
					} else if(attributeNumber == 2) {
						attribute = "agreementValue";
						attributeName = "valor";
					} else if(attributeNumber == 3) {
						attribute = "agreementType";
						attributeName = "tipo";
					}

					
					System.out.println("Digite o novo valor para " + attributeName + ": ");
					String value = scanner.nextLine();
					medicalAgreementDAO.updateMedicalAgreement(code, attribute, value);
					
					menu.crudMenu("Convênios");
					option = scanner.nextInt();
					scanner.nextLine();
					break;
					
				case 3:
					medicalAgreementDAO.getAllMedicalAgreements();
					System.out.println("Deseja voltar ao menu (Sim / Não)?");
					String back = scanner.nextLine();
					
					if(menu.backToMenu(back)) {
						this.clear();
						menu.crudMenu("Convênios");
						option = scanner.nextInt();
						scanner.nextLine();
					} else {
						option = 5;
					}
					break;
				case 4:
					System.out.println("Digite o código do convênio: ");
					code = scanner.nextInt();
					scanner.nextLine();
					
					medicalAgreementDAO.deleteMedicalAgreement(code);
					
					menu.crudMenu("Convênios");
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

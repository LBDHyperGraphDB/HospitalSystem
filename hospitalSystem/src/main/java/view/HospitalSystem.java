package view;

import java.util.Scanner;

import controller.LaboratoryController;
import model.Hospital;
import dao.HospitalDAO;
import view.Menu;

public class HospitalSystem {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		Menu menu = new Menu();
		
		HospitalSystem view = new HospitalSystem();
		HospitalDAO hospitalDAO = new HospitalDAO();
		Hospital hospital = new Hospital("20162436000194", "Anchieta", "Setor C Norte QNC AE, Taguatinga, DF", "6133539000");
		
		boolean success = hospitalDAO.addHospital(hospital);
		
		int option = 0;
		
		if (success) {
			menu.mainMenu();
			option = scanner.nextInt();
		
			do {
				switch (option) {
				// WingsOfBuilding
				case 1:
					view.clear();
					menu.crudMenu("Alas");
					int wingOption = scanner.nextInt();
					scanner.nextLine();
					// TODO: Controller of wingsOfBuilding
					// wingsOfBuildingController.chooseAction(wingOption);
					break;
				// Nursery
				case 2:
					view.clear();
					menu.crudMenu("Enfermarias");
					int nurseryOption = scanner.nextInt();
					scanner.nextLine();
					// TODO: Controller of nursery
					// nurseryController.chooseAction(nurseryOption);
					break;
				// Laboratories
				case 3:
					view.clear();
					menu.crudMenu("Laboratórios");
					int laboratoryOption = scanner.nextInt();
					scanner.nextLine();
					
					if(laboratoryOption != 5) {
						LaboratoryController laboratoryController = new LaboratoryController();
						laboratoryController.chooseAction(laboratoryOption);
					} else {
						view.clear();
						menu.mainMenu();
						option = scanner.nextInt();
						scanner.nextLine();
					}
					break;
				// Medical Agreements
				case 4:
					view.clear();
					menu.crudMenu("Convênios");
					int medicalAgreementOption = scanner.nextInt();
					scanner.nextLine();
					// TODO: Controller of medicalAgreement
					// medicalAgreementController.chooseAction(medicalAgreementOption);
					break;
				// Medical Equipments
				case 5:
					view.clear();
					menu.crudMenu("Equipamentos");
					int medicalEquipmentOption = scanner.nextInt();
					scanner.nextLine();
					// TODO: Controller of medicalEquipment
					// medicalEquipmentController.chooseAction(medicalEquipmentOption);
					break;
				// Medical Exams
				case 6:
					view.clear();
					menu.crudMenu("Exames");
					int medicalExamOption = scanner.nextInt();
					scanner.nextLine();
					// TODO: Controller of medicalExam
					// medicalExamController.chooseAction(medicalExamOption);
					break;
				// Doctors
				case 7:
					view.clear();
					menu.crudMenu("Médicos");
					int doctorOption = scanner.nextInt();
					scanner.nextLine();
					// TODO: Controller of doctor
					// doctorController.chooseAction(doctorOption);
					break;
				// Nurses
				case 8:
					view.clear();
					menu.crudMenu("Enfermeiros");
					int nurseOption = scanner.nextInt();
					scanner.nextLine();
					// TODO: Controller of nurse
					// nurseController.chooseAction(nurseOption);
					break;
				// Patients
				case 9:
					view.clear();
					menu.crudMenu("Pacientes");
					int patientOption = scanner.nextInt();
					scanner.nextLine();
					// TODO: Controller of patient
					// patientController.chooseAction(patientOption);
					break;
				// About the hospital
				case 10:
					view.clear();
					hospitalDAO.getAllHospitals();
					System.out.println("Deseja voltar ao menu (Sim / Não)?");
					String back = scanner.next();
					
					if(menu.backToMenu(back)) {
						view.clear();
						menu.mainMenu();
						option = scanner.nextInt();
						scanner.nextLine();
					} else {
						view.clear();
						System.out.println("Fim da execução.");
						
						option = 11;
					}
					break;
				case 11:
					view.clear();
					System.out.println("Fim da execução.");
					break;
				default:
					view.clear();
					System.out.println("Opção inválida! Tente novamente");
					
					view.clear();
					menu.mainMenu();
					option = scanner.nextInt();
					scanner.nextLine();
					break;
				}
			} while (option != 11);
		}
	}
	
	public void clear() {
		for (int i = 0; i < 3; i++) 
			System.out.println();
	}
}

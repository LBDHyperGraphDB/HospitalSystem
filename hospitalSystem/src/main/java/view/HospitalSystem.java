package view;

import java.util.Scanner;

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
					break;
				// Nursery
				case 2:
					break;
				// Laboratories
				case 3:
					break;
				// Medical Agreements
				case 4:
					break;
				// Medical Equipments
				case 5:
					break;
				// Medical Exams
				case 6:
					break;
				// Doctors
				case 7:
					break;
				// Nurses
				case 8:
					break;
				// Patients
				case 9:
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

package view;

import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import controller.DoctorController;
import controller.LaboratoryController;
import controller.MedicalExamController;
import controller.NurseController;
import controller.NurseryController;
import controller.PatientController;
import controller.WingOfBuildingController;

import dao.HospitalDAO;
import model.Hospital;

public class HospitalSystem {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String databaseLocation = "../hypergraphdb-1.3"; 
		HyperGraph hospitalGraph = null;
		
		try {
			hospitalGraph = new HyperGraph(databaseLocation);
			
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
							
							WingOfBuildingController wingOfBuildingController = new WingOfBuildingController(hospitalGraph);
							if(wingOption != 5) {
								wingOfBuildingController.chooseAction(wingOption);
							} else {
								option = view.backToMainMenu(view, menu, scanner);
							}
							
							if(wingOfBuildingController.getOption() == 5) {
								option = view.backToMainMenu(view, menu, scanner);
							}
							break;
						// Nursery
						case 2:
							view.clear();
							menu.crudMenu("Enfermarias");
							int nurseryOption = scanner.nextInt();
							scanner.nextLine();
							
							NurseryController nursery = new NurseryController(hospitalGraph);
							if(nurseryOption != 5) {
								nursery.chooseAction(nurseryOption);
							} else {
								option = view.backToMainMenu(view, menu, scanner);
							}
							
							if(nursery.getOption() == 5) {
								option = view.backToMainMenu(view, menu, scanner);
							}
						// Laboratories
						case 3:
							view.clear();
							menu.crudMenu("Laboratórios");
							int laboratoryOption = scanner.nextInt();
							scanner.nextLine();
							
							LaboratoryController laboratoryController = new LaboratoryController(hospitalGraph);
							if(laboratoryOption != 5) {
								laboratoryController.chooseAction(laboratoryOption);
							} else {
								option = view.backToMainMenu(view, menu, scanner);
							}
							
							if(laboratoryController.getOption() == 5) {
								option = view.backToMainMenu(view, menu, scanner);
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
							int examOption = scanner.nextInt();
							scanner.nextLine();
							
							MedicalExamController medicalExamController = new MedicalExamController(hospitalGraph);
							if(examOption != 5) {
								medicalExamController.chooseAction(examOption);
							} else {
								option = view.backToMainMenu(view, menu, scanner);
							}
							
							if(medicalExamController.getOption() == 5) {
								option = view.backToMainMenu(view, menu, scanner);
							}
							break;
						// Doctors
	                    case 7:
	                        view.clear();
	                        menu.crudMenu("Médicos");
	                        int doctorOption = scanner.nextInt();
	                        scanner.nextLine();

	                        DoctorController doctorController = new DoctorController(hospitalGraph);
	                        if (doctorOption != 5) {
	                            doctorController.chooseAction(doctorOption);
	                        } else {
	                            option = view.backToMainMenu(view, menu, scanner);
	                        }
	                        break;

	                    // Nurses
	                    case 8:
	                        view.clear();
	                        menu.crudMenu("Enfermeiros");
	                        int nurseOption = scanner.nextInt();
	                        scanner.nextLine();

	                        NurseController nurseController = new NurseController(hospitalGraph);
	                        if (nurseOption != 5) {
	                            nurseController.chooseAction(nurseOption);
	                        } else {
	                            option = view.backToMainMenu(view, menu, scanner);
	                        }

	                        break;
	                    // Patients
	                    case 9:
	                        view.clear();
	                        menu.crudMenu("Pacientes");
	                        int patientOption = scanner.nextInt();
	                        scanner.nextLine();
	                        PatientController patientController = new PatientController(hospitalGraph);
	                        if (patientOption != 5) {
	                            patientController.chooseAction(patientOption);
	                        } else {
	                            option = view.backToMainMenu(view, menu, scanner);

	                        }
	                        break;
						// About the hospital
						case 10:
							view.clear();
							hospitalDAO.getAllHospitals();
							System.out.println("Deseja voltar ao menu (Sim / Não)?");
							String back = scanner.next();
							
							if(menu.backToMenu(back)) {
								option = view.backToMainMenu(view, menu, scanner);
							} else {
								option = 11;
							}
							break;
						case 11:
							break;
						default:
						option = 11;
						break;
					}
				} while (option != 11);
				view.clear();
				System.out.println("Fim da execução.");
			}
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		   hospitalGraph.close();
		}
	}

    public int backToMainMenu(HospitalSystem view, Menu menu, Scanner scanner) {
        view.clear();
        menu.mainMenu();
        int option = scanner.nextInt();
        scanner.nextLine();

        return option;
    }

    public void clear() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
}

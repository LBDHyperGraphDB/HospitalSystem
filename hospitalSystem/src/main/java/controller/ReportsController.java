package controller;

import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import dao.DoctorDAO;
import dao.NurseDAO;
import dao.PatientDAO;
import view.Menu;

public class ReportsController {
	Menu menu = new Menu();
	Scanner scanner = new Scanner(System.in);
	
	HyperGraph hospitalGraph = null;
	DoctorDAO doctorDAO = null;
	NurseDAO nurseDAO = null;
	PatientDAO patientDAO = null;
	
	private int option;
	private String back;
	
	public ReportsController(HyperGraph hospitalGraph) {
		this.hospitalGraph = hospitalGraph;
		this.doctorDAO = new DoctorDAO(hospitalGraph);
		this.nurseDAO = new NurseDAO(hospitalGraph);
		this.patientDAO = new PatientDAO(hospitalGraph);
    }
	
	public void chooseAction(int option) {
		do {
			switch (option) {
	        	case 1:
	        		menu.menuTitle("Médicos e Enfermeiros");
	        		doctorDAO.getAllDoctors();
	        		nurseDAO.getAllNurses();
	        		
	        		System.out.println("Deseja voltar ao menu (Sim / Não)?");
	                back = scanner.nextLine();

	                if (menu.backToMenu(back)) {
	                    this.clear();
	                    menu.reportsMenu("Relatórios");
	                    option = scanner.nextInt();
	                    scanner.nextLine();
	                } else {
	                    option = 6;
	                }
	                break;
	                
	        	case 2:
	        		menu.menuTitle("Médicos e Pacientes");
	        		System.out.println("Digite o CRM do médico desejado: ");
	        		String doctorCrm = scanner.nextLine();
	        		boolean found = doctorDAO.findDoctorByCRM(hospitalGraph, doctorCrm);
	        		
	        		if(found) {
	        			patientDAO.getPatientsOfADoctor(doctorCrm);
	        		} else {
	        			System.out.println("Não há pacientes cadastrados.");
	        		}
	        		
	        		System.out.println("Deseja voltar ao menu (Sim / Não)?");
	                back = scanner.nextLine();

	                if (menu.backToMenu(back)) {
	                    this.clear();
	                    menu.reportsMenu("Relatórios");
	                    option = scanner.nextInt();
	                    scanner.nextLine();
	                } else {
	                    option = 6;
	                }
	                break;
	        		
	            default:
	            	this.clear();
	                System.out.println("Opção inválida! Tente novamente");

	                menu.reportsMenu("Relatórios");
	                option = scanner.nextInt();
	                scanner.nextLine();
	                break;
	            }
			 	this.option = option;
	        } while(option != 6);
	}
	
	public int getOption() {
        return option;
    }
	public void clear() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
}

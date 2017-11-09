package controller;

import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import dao.DoctorDAO;
import dao.NurseDAO;
import view.Menu;

public class ReportsController {
	Menu menu = new Menu();
	Scanner scanner = new Scanner(System.in);
	DoctorDAO doctorDAO = null;
	NurseDAO nurseDAO = null;
	
	private int option;
	
	public ReportsController(HyperGraph hospitalGraph) {
		this.doctorDAO = new DoctorDAO(hospitalGraph);
		this.nurseDAO = new NurseDAO(hospitalGraph);
    }
	
	public void chooseAction(int option) {
		do {
			switch (option) {
	        	case 1:
	        		menu.menuTitle("Relação de médicos e enfermeiros");
	        		doctorDAO.getAllDoctors();
	        		nurseDAO.getAllNurses();
	        		
	        		System.out.println("Deseja voltar ao menu (Sim / Não)?");
	                String back = scanner.nextLine();

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

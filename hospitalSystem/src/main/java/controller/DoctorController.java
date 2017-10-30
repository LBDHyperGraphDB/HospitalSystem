package controller;

import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import dao.DoctorDAO;
import model.Doctor;
import view.Menu;

public class DoctorController {
    Menu menu = new Menu();
    DoctorDAO doctorDAO = null;
    Scanner scanner = new Scanner(System.in);
    private String crm;
    private String titration;
    private String maritalState;
    private String motherName;
    private String fatherName;
    private int option;

    public DoctorController(HyperGraph hospitalGraph) {
        this.doctorDAO = new DoctorDAO(hospitalGraph);
    }

    public void chooseAction(int option) {
        do {
            switch (option) {
            case 1:
                System.out.println("Digite o CRM do m�dico: ");
                crm = scanner.nextLine();
                System.out.println("Digite o titula��o do m�dico: ");
                titration = scanner.nextLine();
                System.out.println("Digite o estado civil do m�dico: ");
                maritalState = scanner.nextLine();
                System.out.println("Digite o nome da m�e do m�dico: ");
                motherName = scanner.nextLine();
                System.out.println("Digite o nome do pai do m�dico: ");
                fatherName = scanner.nextLine();

                Doctor doctor = new Doctor(crm, titration, maritalState, motherName, fatherName);
                doctorDAO.addDoctor(doctor);

                menu.crudMenu("M�dico");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 2:
                System.out.println("Digite o CRM do m�dico: ");
                crm = scanner.nextLine();
                System.out.println("Qual atributo deseja modificar?");
                System.out.println("1- Crm    2- Titula��o    3- Estado Civ�l    4- Nome da m�e    5- Nome do pai ");

                int attributeNumber = scanner.nextInt();
                scanner.nextLine();

                String attribute = "";
                String attributeName = "";
                if (attributeNumber == 1) {
                    attribute = "doctorCrm";
                    attributeName = "crm";
                } else if (attributeNumber == 2) {
                    attribute = "doctorTitration";
                    attributeName = "titration";
                } else if (attributeNumber == 3) {
                    attribute = "doctorMaritalState";
                    attributeName = "maritalState";
                } else if (attributeNumber == 4) {
                    attribute = "doctorMotherName";
                    attributeName = "motherName";
                } else if (attributeNumber == 5) {
                    attribute = "doctorFatherName";
                    attributeName = "fatherName";
                }

                System.out.println("Digite o novo valor para " + attributeName + ": ");
                String value = scanner.nextLine();
                doctorDAO.updateDoctor(crm, attribute, value);

                menu.crudMenu("M�dico");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 3:
                doctorDAO.getAllDoctors();
                System.out.println("Deseja voltar ao menu (Sim / Não)?");
                String back = scanner.nextLine();

                if (menu.backToMenu(back)) {
                    this.clear();
                    menu.crudMenu("M�dicos");
                    option = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    option = 5;
                }
                break;
            case 4:
                System.out.println("Digite o CRM do M�dico: ");
                crm = scanner.nextLine();

                doctorDAO.deleteDoctor(crm);

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

                menu.crudMenu("M�dico");
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
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
}

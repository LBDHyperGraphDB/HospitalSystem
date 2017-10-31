package controller;

import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import dao.NurseDAO;
import model.Nurse;
import view.Menu;

public class NurseController {

    Menu menu = new Menu();
    NurseDAO nurseDAO = null;
    Scanner scanner = new Scanner(System.in);

    private String qualification;
    String coren;
    private int option;

    public NurseController(HyperGraph hospitalGraph) {
        this.nurseDAO = new NurseDAO(hospitalGraph);
    }

    public void chooseAction(int option) {
        do {
            switch (option) {
            case 1:
                System.out.println("Digite a qualificação da enfermeira: ");
                qualification = scanner.nextLine();
                System.out.println("Digite o COREN da enfermeira: ");
                coren = scanner.nextLine();

                Nurse nurse = new Nurse(qualification, coren);
                nurseDAO.addNurse(nurse);

                menu.crudMenu("Enfermeira");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 2:
                System.out.println("Digite o COREN da enfermeira: ");
                coren = scanner.nextLine();
                System.out.println("Qual atributo deseja modificar?");
                System.out.println("1- Qualificação    2- COREN");

                int attributeNumber = scanner.nextInt();
                scanner.nextLine();

                String attribute = "";
                String attributeName = "";
                if (attributeNumber == 1) {
                    attribute = "nurseQualification";
                    attributeName = "qualification";
                } else if (attributeNumber == 2) {
                    attribute = "nurseCoren";
                    attributeName = "coren";

                }
                System.out.println("Digite o novo valor para " + attributeName + ": ");
                String value = scanner.nextLine();
                nurseDAO.updateNurse(coren, attribute, value);

                menu.crudMenu("Enfermeira");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 3:
                nurseDAO.getAllNurses();
                System.out.println("Deseja voltar ao menu (Sim / Não)?");
                String back = scanner.nextLine();

                if (menu.backToMenu(back)) {
                    this.clear();
                    menu.crudMenu("Enfermeiras");
                    option = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    option = 5;
                }
                break;
            case 4:
                System.out.println("Digite o COREN da enfermeira: ");
                coren = scanner.nextLine();

                nurseDAO.deleteNurse(coren);

                menu.crudMenu("Enfermeira");
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

                menu.crudMenu("Enfermeira");
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

package controller;

import java.util.Date;
import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import dao.NurseDAO;
import model.Nurse;
import view.Menu;

public class NurseController {

    Menu menu = new Menu();
    NurseDAO nurseDAO = null;
    Scanner scanner = new Scanner(System.in);

    private String cpf;
    private String name;
    private String address;
    private String gender;
    private String phoneNumber;
    private String email;
    private Date bithDate;
    private String qualification;
    private String coren;
    private int option;

    public NurseController(HyperGraph hospitalGraph) {
        this.nurseDAO = new NurseDAO(hospitalGraph);
    }

    public void chooseAction(int option) {
        do {
            switch (option) {
            case 1:
                System.out.println("Digite o CPF da enfermeira: ");
                cpf = scanner.nextLine();
                System.out.println("Digite o nome da enfermeira: ");
                name = scanner.nextLine();
                System.out.println("Digite o endereço da enfermeira: ");
                address = scanner.nextLine();
                System.out.println("Digite o genero da enfermeira: ");
                gender = scanner.nextLine();
                System.out.println("Digite o telefone da enfermeira: ");
                phoneNumber = scanner.nextLine();
                System.out.println("Digite o email da enfermeira: ");
                email = scanner.nextLine();
                System.out.println("Digite data de nascimento da enfermeira: ");
                bithDate = new Date();
                System.out.println("Digite a qualificação da enfermeira: ");
                qualification = scanner.nextLine();
                System.out.println("Digite o COREN da enfermeira: ");
                coren = scanner.nextLine();

                Nurse nurse = new Nurse(cpf, name, address, gender, phoneNumber, email, bithDate, qualification, coren);
                nurseDAO.addNurse(nurse);

                menu.crudMenu("Enfermeira");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 2:
                System.out.println("Digite o COREN da enfermeira: ");
                coren = scanner.nextLine();
                System.out.println("Qual atributo deseja modificar?");
                System.out.println(
                        "1- CPF    2- Nome    3- Endereço    4- Genero    5- Telefone    6- Email    7- Data de Nascimento     8- Qualificação    9- COREN");

                int attributeNumber = scanner.nextInt();
                scanner.nextLine();

                String attribute = "";
                String attributeName = "";
                if (attributeNumber == 1) {
                    attribute = "personCpf";
                    attributeName = "cpf";
                } else if (attributeNumber == 2) {
                    attribute = "personName";
                    attributeName = "name";
                } else if (attributeNumber == 3) {
                    attribute = "personAddress";
                    attributeName = "address";
                } else if (attributeNumber == 4) {
                    attribute = "personGender";
                    attributeName = "gender";
                } else if (attributeNumber == 5) {
                    attribute = "personPhoneNumber";
                    attributeName = "phoneNumber";
                } else if (attributeNumber == 6) {
                    attribute = "personEmail";
                    attributeName = "email";
                } else if (attributeNumber == 7) {
                    attribute = "personBithDate";
                    attributeName = "bithDate";
                } else if (attributeNumber == 8) {
                    attribute = "nurseQualification";
                    attributeName = "qualification";
                } else if (attributeNumber == 9) {
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

package controller;

import java.text.DateFormat;
import java.text.ParseException;
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
    private String bithDate;
    private String qualification;
    private String coren;
    private String date;
    private int option;

    public NurseController(HyperGraph hospitalGraph) {
        this.nurseDAO = new NurseDAO(hospitalGraph);
    }

    public void chooseAction(int option) {
        do {
            switch (option) {
            case 1:
                System.out.println("Digite o CPF do enfermeiro: ");
                cpf = scanner.nextLine();
                System.out.println("Digite o nome do enfermeiro: ");
                name = scanner.nextLine();
                System.out.println("Digite o endereço do enfermeiro: ");
                address = scanner.nextLine();
                System.out.println("Digite o gênero do enfermeiro: ");
                gender = scanner.nextLine();
                System.out.println("Digite o telefone do enfermeiro: ");
                phoneNumber = scanner.nextLine();
                System.out.println("Digite o e-mail do enfermeiro: ");
                email = scanner.nextLine();
                System.out.println("Digite data de nascimento do enfermeiro: ");
                bithDate = scanner.nextLine();
                System.out.println("Digite a formação do enfermeiro: ");
                qualification = scanner.nextLine();
                System.out.println("Digite o COREN do enfermeiro: ");
                coren = scanner.nextLine();

                Nurse nurse = new Nurse(cpf, name, address, gender, phoneNumber, email, date, qualification, coren);
                nurseDAO.addNurse(nurse);

                menu.crudMenu("Enfermeiro");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 2:
                System.out.println("Digite o COREN do enfermeiro: ");
                coren = scanner.nextLine();
                System.out.println("Qual atributo deseja modificar?");
                System.out.println("1- CPF    2- Nome    3- Endereço    4- Gênero    5- Telefone");
                System.out.println("6- E-mail    7- Data de Nascimento     8- Formação");

                int attributeNumber = scanner.nextInt();
                scanner.nextLine();

                String attribute = "";
                String attributeName = "";
                if (attributeNumber == 1) {
                    attribute = "personCpf";
                    attributeName = "CPF";
                } else if (attributeNumber == 2) {
                    attribute = "personName";
                    attributeName = "nome";
                } else if (attributeNumber == 3) {
                    attribute = "personAddress";
                    attributeName = "endereço";
                } else if (attributeNumber == 4) {
                    attribute = "personGender";
                    attributeName = "gênero";
                } else if (attributeNumber == 5) {
                    attribute = "personPhoneNumber";
                    attributeName = "telefone";
                } else if (attributeNumber == 6) {
                    attribute = "personEmail";
                    attributeName = "e-mail";
                } else if (attributeNumber == 7) {
                    attribute = "personBithDate";
                    attributeName = "data de nascimento";
                } else if (attributeNumber == 8) {
                    attribute = "nurseQualification";
                    attributeName = "formação";
                }
                
                System.out.println("Digite o novo valor para " + attributeName + ": ");
                String value = scanner.nextLine();
                nurseDAO.updateNurse(coren, attribute, value);

                menu.crudMenu("Enfermeiro");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 3:
                nurseDAO.getAllNurses();
                System.out.println("Deseja voltar ao menu (Sim / Não)?");
                String back = scanner.nextLine();

                if (menu.backToMenu(back)) {
                    this.clear();
                    menu.crudMenu("Enfermeiros");
                    option = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    option = 5;
                }
                break;
            case 4:
                System.out.println("Digite o COREN do enfermeiro: ");
                coren = scanner.nextLine();

                nurseDAO.deleteNurse(coren);

                menu.crudMenu("Enfermeiro");
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

                menu.crudMenu("Enfermeiro");
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

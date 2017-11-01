package controller;

import java.util.Date;
import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import dao.DoctorDAO;
import model.Doctor;
import view.Menu;

public class DoctorController {
    Menu menu = new Menu();
    DoctorDAO doctorDAO = null;
    Scanner scanner = new Scanner(System.in);
    private String cpf;
    private String name;
    private String address;
    private String gender;
    private String phoneNumber;
    private String email;
    private Date bithDate;
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
                System.out.println("Digite o CPF do médico: ");
                cpf = scanner.nextLine();
                System.out.println("Digite o nome do médico: ");
                name = scanner.nextLine();
                System.out.println("Digite o endereço do médico: ");
                address = scanner.nextLine();
                System.out.println("Digite o genero do médico: ");
                gender = scanner.nextLine();
                System.out.println("Digite o telefone do médico: ");
                phoneNumber = scanner.nextLine();
                System.out.println("Digite o email do médico: ");
                email = scanner.nextLine();
                System.out.println("Digite data de nascimento do médico: ");
                bithDate = new Date();
                System.out.println("Digite o CRM do médico: ");
                crm = scanner.nextLine();
                System.out.println("Digite o titulação do médico: ");
                titration = scanner.nextLine();
                System.out.println("Digite o estado civil do médico: ");
                maritalState = scanner.nextLine();
                System.out.println("Digite o nome da mãe do médico: ");
                motherName = scanner.nextLine();
                System.out.println("Digite o nome do pai do médico: ");
                fatherName = scanner.nextLine();

                Doctor doctor = new Doctor(cpf, name, address, gender, phoneNumber, email, bithDate, crm, titration, maritalState,
                        motherName, fatherName);
                doctorDAO.addDoctor(doctor);

                menu.crudMenu("médico");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 2:
                System.out.println("Digite o CRM do médico: ");
                crm = scanner.nextLine();
                System.out.println("Qual atributo deseja modificar?");
                System.out.println(
                        "1- CPF    2- Nome    3- Endereço    4- Genero    5- Telefone    6- Email    7- Data de Nascimento" +
                                "    8- Crm    9- Titulação    10- Estado Civil    11- Nome da mãe    12- Nome do pai ");

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
                    attribute = "doctorCrm";
                    attributeName = "crm";
                } else if (attributeNumber == 9) {
                    attribute = "doctorTitration";
                    attributeName = "titration";
                } else if (attributeNumber == 10) {
                    attribute = "doctorMaritalState";
                    attributeName = "maritalState";
                } else if (attributeNumber == 11) {
                    attribute = "doctorMotherName";
                    attributeName = "motherName";
                } else if (attributeNumber == 12) {
                    attribute = "doctorFatherName";
                    attributeName = "fatherName";
                }

                System.out.println("Digite o novo valor para " + attributeName + ": ");
                String value = scanner.nextLine();
                doctorDAO.updateDoctor(crm, attribute, value);

                menu.crudMenu("médico");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 3:
                doctorDAO.getAllDoctors();
                System.out.println("Deseja voltar ao menu (Sim / Não)?");
                String back = scanner.nextLine();

                if (menu.backToMenu(back)) {
                    this.clear();
                    menu.crudMenu("médicos");
                    option = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    option = 5;
                }
                break;
            case 4:
                System.out.println("Digite o CRM do médico: ");
                crm = scanner.nextLine();

                doctorDAO.deleteDoctor(crm);

                menu.crudMenu("médico");
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

                menu.crudMenu("médico");
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

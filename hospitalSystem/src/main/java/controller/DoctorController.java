package controller;

import java.text.DateFormat;
import java.text.ParseException;
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
    private String bithDate;
    private String crm;
    private String titration;
    private String maritalState;
    private String motherName;
    private String fatherName;
    private String date;
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
                System.out.println("Digite o gênero do médico: ");
                gender = scanner.nextLine();
                System.out.println("Digite o telefone do médico: ");
                phoneNumber = scanner.nextLine();
                System.out.println("Digite o e-mail do médico: ");
                email = scanner.nextLine();
                System.out.println("Digite data de nascimento do médico: ");
                bithDate = scanner.nextLine();
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

                Doctor doctor = new Doctor(cpf, name, address, gender, phoneNumber, email, date, crm, titration, maritalState,
                        motherName, fatherName);
                doctorDAO.addDoctor(doctor);

                menu.crudMenu("Médico");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 2:
                System.out.println("Digite o CRM do médico: ");
                crm = scanner.nextLine();
                System.out.println("Qual atributo deseja modificar?");
                System.out.println("1- CPF    2- Nome    3- Endereço    4- Genero    5- Telefone");
                System.out.println("6- E-mail    7- Data de Nascimento    8- Titulação");
                System.out.println("9- Estado Civil    10- Nome da mãe    11- Nome do pai ");
                
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
                	attribute = "doctorTitration";
                    attributeName = "titulação";
                } else if (attributeNumber == 9) {
                	attribute = "doctorMaritalState";
                    attributeName = "estado civil";
                } else if (attributeNumber == 10) {
                	attribute = "doctorMotherName";
                    attributeName = "nome da mãe";
                } else if (attributeNumber == 11) {
                	attribute = "doctorFatherName";
                    attributeName = "nome do pai";
                }

                System.out.println("Digite o novo valor para " + attributeName + ": ");
                String value = scanner.nextLine();
                doctorDAO.updateDoctor(crm, attribute, value);

                menu.crudMenu("Médico");
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

                menu.crudMenu("Médico");
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

                menu.crudMenu("Médico");
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

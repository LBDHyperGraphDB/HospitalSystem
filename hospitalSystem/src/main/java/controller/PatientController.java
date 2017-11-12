package controller;

import java.util.Date;
import java.util.Scanner;

import org.hypergraphdb.HyperGraph;

import dao.PatientDAO;
import model.Patient;
import view.Menu;

public class PatientController {
    Menu menu = new Menu();
    PatientDAO patientDAO = null;
    Scanner scanner = new Scanner(System.in);

    private String cpf;
    private String name;
    private String address;
    private String gender;
    private String phoneNumber;
    private String email;
    private Date bithDate;
    private String healthInsurance;
    private String doctorCRM;

    private int option;

    public PatientController(HyperGraph hospitalGraph) {
        this.patientDAO = new PatientDAO(hospitalGraph);
    }

    public void chooseAction(int option) {
        do {
            switch (option) {
            case 1:
                System.out.println("Digite o CPF do paciente: ");
                cpf = scanner.nextLine();
                System.out.println("Digite o nome do paciente: ");
                name = scanner.nextLine();
                System.out.println("Digite o endereço do paciente: ");
                address = scanner.nextLine();
                System.out.println("Digite o gênero do paciente: ");
                gender = scanner.nextLine();
                System.out.println("Digite o telefone do paciente: ");
                phoneNumber = scanner.nextLine();
                System.out.println("Digite o e-mail do paciente: ");
                email = scanner.nextLine();
                System.out.println("Digite data de nascimento do paciente: ");
                bithDate = new Date();
                System.out.println("Digite o número do plano de saúde do paciente: ");
                healthInsurance = scanner.nextLine();
                System.out.println("Digite o CRM do médico responsável");
                doctorCRM = scanner.nextLine();

                Patient patient = new Patient(cpf, name, address, gender, phoneNumber, email, bithDate, healthInsurance, doctorCRM);
                patientDAO.addPatient(patient);

                menu.crudMenu("Paciente");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 2:
                System.out.println("Digite o CPF do paciente: ");
                cpf = scanner.nextLine();
                System.out.println("Qual atributo deseja modificar?");
                System.out.println("1- CPF    2- Nome    3- Endereço    4- Gênero    5- Telefone");
                System.out.println("6- Email    7- Data de Nascimento    8- Número do Plano de Saúde");

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
                    attribute = "personHealthInsurance";
                    attributeName = "número do plano de saúde";
                }

                System.out.println("Digite o novo valor para " + attributeName + ": ");
                String value = scanner.nextLine();
                patientDAO.updatePatient(cpf, attribute, value);

                menu.crudMenu("Paciente");
                option = scanner.nextInt();
                scanner.nextLine();
                break;
            case 3:
                patientDAO.getAllPatients();
                System.out.println("Deseja voltar ao menu (Sim / Não)?");
                String back = scanner.nextLine();

                if (menu.backToMenu(back)) {
                    this.clear();
                    menu.crudMenu("Paciente");
                    option = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    option = 5;
                }
                break;
            case 4:
                System.out.println("Digite o CPF do paciente: ");
                cpf = scanner.nextLine();

                patientDAO.deletePatient(cpf);

                menu.crudMenu("Paciente");
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

                menu.crudMenu("Paciente");
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

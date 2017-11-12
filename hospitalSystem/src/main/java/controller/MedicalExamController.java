package controller;

import java.util.List;
import java.util.Scanner;

import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.HGQuery.hg;

import dao.LaboratoryDAO;
import dao.MedicalExamDAO;
import model.Laboratory;
import model.MedicalExam;
import view.Menu;

public class MedicalExamController {
	Menu menu = new Menu();
	MedicalExamDAO medicalExamDAO = null;
	LaboratoryDAO laboratoryDAO = null;
	Scanner scanner = new Scanner(System.in);
	private String examLaboratoryCnpj;
	private int code;
	private String description;
	private String restrition;
	private int option;
	HyperGraph hospitalGraph1 = null;

	public MedicalExamController(HyperGraph hospitalGraph) {
		this.medicalExamDAO = new MedicalExamDAO(hospitalGraph);
		this.laboratoryDAO = new LaboratoryDAO(hospitalGraph);
		this.hospitalGraph1 = hospitalGraph;
	}

	public void chooseAction(int option) {

		do {
			switch (option) {

			case 1:
				if (laboratoryDAO.verifyExistenceLaboratories()) {
					System.out.println("Digite o CNPJ do laboratório: ");
					examLaboratoryCnpj = scanner.nextLine();
					if (!laboratoryDAO.findLaboratoryByCnpj(hospitalGraph1, examLaboratoryCnpj)) {
						System.out.println("CNPJ n�o encontrado");
					} else {

						System.out.println("Digite o código do exame: ");
						code = scanner.nextInt();
						scanner.nextLine();
						System.out.println("Digite a descrição do exame: ");
						description = scanner.nextLine();
						System.out.println("Digite a restrição do exame ");
						restrition = scanner.nextLine();

						MedicalExam medicalExam = new MedicalExam(examLaboratoryCnpj, code, description, restrition);
						medicalExamDAO.addMedicalExam(medicalExam);
					}
				}

				menu.crudMenu("Exame");
				option = scanner.nextInt();
				scanner.nextLine();

				break;
			case 2:
				if (laboratoryDAO.verifyExistenceLaboratories()) {
					System.out.println("Digite o código do exame: ");
					code = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Qual atributo deseja modificar?");
					System.out.println("1- Descrição    2- Restrição");

					int attributeNumber = scanner.nextInt();
					scanner.nextLine();

					String attribute = "";
					String attributeName = "";
					if (attributeNumber == 1) {
						attribute = "examDescription";
						attributeName = "Descrição";
					} else if (attributeNumber == 2) {
						attribute = "examRestrition";
						attributeName = "Restrição";
					}

					System.out.println("Digite o novo valor para " + attributeName + ": ");
					String value = scanner.nextLine();
					medicalExamDAO.updateMedicalExam(code, attribute, value);
				}
				menu.crudMenu("Exames");
				option = scanner.nextInt();
				scanner.nextLine();
				break;
			case 3:
				if (laboratoryDAO.verifyExistenceLaboratories()) {
					medicalExamDAO.getAllMedicalExams();
				}

				System.out.println("Deseja voltar ao menu (Sim / Não)?");
				String back = scanner.nextLine();

				if (menu.backToMenu(back)) {
					this.clear();
					menu.crudMenu("Exames");
					option = scanner.nextInt();
					scanner.nextLine();
				} else {
					option = 5;
				}

				break;
			case 4:
				if (laboratoryDAO.verifyExistenceLaboratories()) {
					System.out.println("Digite o código do exame: ");
					code = scanner.nextInt();
					scanner.nextLine();

					medicalExamDAO.deleteMedicalExam(code);
				}
				menu.crudMenu("Exame");
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

				menu.crudMenu("Exame");
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
		for (int i = 0; i < 3; i++)
			System.out.println();
	}
}

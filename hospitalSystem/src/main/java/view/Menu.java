package view;

public class Menu {
	
	public boolean backToMenu(String response) {
		if ("Sim".equalsIgnoreCase(response)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void mainMenu() {
		System.out.println("------------------------------");
		System.out.println("             MENU             ");
		System.out.println("------------------------------");
		System.out.println("1. Alas");
		System.out.println("2. Enfermarias");
		System.out.println("3. Laboratórios");
		System.out.println("4. Convênios");
		System.out.println("5. Equipamentos");
		System.out.println("6. Exames");
		System.out.println("7. Médicos");
		System.out.println("8. Enfermeiros");
		System.out.println("9. Pacientes");
		System.out.println("10. Sobre o hospital");
		System.out.println("11. Sair");
		System.out.println("------------------------------");
		System.out.println("Digite a opção desejada:");
	}
}

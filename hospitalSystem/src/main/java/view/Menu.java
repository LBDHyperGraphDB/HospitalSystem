package view;

public class Menu {

    public boolean backToMenu(String response) {
        if ("Sim".equalsIgnoreCase(response)) {
            return true;
        } else {
            return false;
        }
    }

    public void menuTitle(String title) {
        title = title.toUpperCase();
        System.out.println("------------------------------");
        System.out.println("         " + title + "        ");
        System.out.println("------------------------------");
    }

    public void mainMenu() {
        this.menuTitle("Menu");
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
        System.out.println("11. Relatórios");
        System.out.println("12. Sair");
        System.out.println("------------------------------");
        System.out.println("Digite a opção desejada:");
    }

    public void crudMenu(String title) {
        this.menuTitle(title);
        System.out.println("1. Criar");
        System.out.println("2. Atualizar");
        System.out.println("3. Listar");
        System.out.println("4. Excluir");
        System.out.println("5. Voltar");
        System.out.println("------------------------------");
        System.out.println("Digite a opção desejada:");
    }
    
    public void reportsMenu(String title) {
    	this.menuTitle(title);
    	System.out.println("1. Relação de Médicos e Enfermeiros");
    	System.out.println("2. Relação de Pacientes de um Médico");
    	System.out.println("3. Relação de Exames solicitados a um Paciente");
    	System.out.println("4. Relação de todos os Convênios do hospital");
    	System.out.println("5. Relação dos Laboratórios e Exames realizados por ele");
    	System.out.println("6. Voltar");
    	System.out.println("Digite a opção desejada:");
    }
}

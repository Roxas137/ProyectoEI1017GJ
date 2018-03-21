package principal;

import menu.Menu_a_secas;

public class Main {

    public void ejecuta(){
        //Leer fichero
        //Mostrar menu
        while (true) {
            Menu_a_secas menu = new Menu_a_secas();
            menu.getMenu();
            menu.menuInicio();
        }
    }

}

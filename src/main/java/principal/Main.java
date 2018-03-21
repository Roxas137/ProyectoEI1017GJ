package principal;

import menu.MenuSwitch;

public class Main {

    public void ejecuta(){
        //Leer fichero
        //Mostrar menu
        while (true) {
            MenuSwitch menu = new MenuSwitch();
            menu.getMenu();
            menu.menuInicio();
        }
    }

}

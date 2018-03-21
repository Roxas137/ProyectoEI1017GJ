package principal;

import menu.MenuSwitch;

public class Main {

    public void ejecuta(){
        //Leer fichero
        while (true) {
            MenuSwitch menu = new MenuSwitch();
            menu.getMenu();
            menu.menuInicio();
        }
    }

}

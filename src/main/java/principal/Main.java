package principal;

import gestor.Metodos;
import menu.MenuSwitch;

public class Main {
    public Metodos metodo = new Metodos();

    public void ejecuta(){
        //Leer Fichero
        MenuSwitch menu = new MenuSwitch();
        while (true) {
            menu.menuInicio();
        }
    }

}

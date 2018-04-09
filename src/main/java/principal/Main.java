package principal;

import fichero.Fichero;
import gestor.Metodos;
import menu.MenuSwitch;

import java.util.Scanner;

public class Main {
    public Metodos metodo = new Metodos();

    public void ejecuta(){
        //Leer Fichero
        Fichero fichero = new Fichero();
        metodo = fichero.entrada();
        MenuSwitch menu = new MenuSwitch(metodo);
        Scanner sc = null;
        while (true) {
            menu.menuInicio(sc);
        }
    }

}

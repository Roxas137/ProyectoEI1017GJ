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
        fichero.entrada(metodo);
        MenuSwitch menu = new MenuSwitch();
        Scanner sc = null;
        while (true) {
            menu.menuInicio(sc);
        }
    }

}

package controlador.principal;

import controlador.fichero.Fichero;
import controlador.gestor.Metodos;
import controlador.menu.MenuSwitch;

import java.time.DateTimeException;
import java.util.NoSuchElementException;

public class Main {
    public Metodos metodo = new Metodos();

    public void ejecuta() {
        //Leer Fichero
        Fichero fichero = new Fichero();
        metodo = fichero.entrada();
        MenuSwitch menu = new MenuSwitch(metodo);
        while (true) {
            try {
                menu.menuInicio();
            } catch (DateTimeException e) {
                System.out.println("Error. Fechas incorrectas\n");
                e.printStackTrace();
            } catch (NoSuchElementException e) {
                System.out.println("Error, cliente no encontrado");
                e.printStackTrace();
            }
        }
    }

}
package controlador.principal;

import controlador.Controlador;
import controlador.fichero.Fichero;
import controlador.gestor.GestorOpciones;
import controlador.gestor.Metodos;
import controlador.menu.MenuSwitch;
import modelo.Modelo;
import vista.Vista;

import java.time.DateTimeException;
import java.util.NoSuchElementException;

public class Inicio {
    public Metodos metodo = new Metodos();

    public static void main(String[] args) {
        Inicio app = new Inicio();
        app.ejecuta();
    }

    public void ejecuta() {
        Fichero fichero = new Fichero();
        metodo = fichero.entrada();
        /*
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
        }*/

        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        modelo.setVista(vista);
        Controlador controlador = new Controlador(modelo, vista);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.creaGUI(new GestorOpciones(metodo));
    }
}

package controlador;

import modelo.Modelo;
import vista.Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements InterfazControlador, ActionListener {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        //Añadir los actionListener a los botones y demás
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

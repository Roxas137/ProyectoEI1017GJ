package controlador.atributos;

import controlador.principal.Fecha;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Llamada implements Fecha, Serializable {
    private final GregorianCalendar fecha; //Fecha de la llamada
    private int nTelefono;
    private double duracion;
    private double precio; //Precio de la llamada

    public Llamada() {
        nTelefono = 0;
        fecha = new GregorianCalendar();
        duracion = 10;
        precio = 0;
    }

    @Override
    public GregorianCalendar getFecha() {
        return fecha;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setnTelefono(int nTelefono) {
        this.nTelefono = nTelefono;
    }

    @Override
    public String toString() {
        return "Núm. Telefono:\t" + nTelefono + "\n" +
                "Fecha:\t\t\t" + fecha.toString() + "\n" +
                "Duración:\t\t" + duracion + "\n" +
                "Precio de la llamada:\t\t\t" + precio + "\n\n";
    }
}

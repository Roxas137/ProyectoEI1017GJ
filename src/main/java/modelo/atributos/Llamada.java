package modelo.atributos;

import modelo.fecha.Fecha;

import java.io.Serializable;
import java.util.Calendar;
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
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int anyo = fecha.get(Calendar.YEAR);
        return "Núm. Telefono:\t\t" + nTelefono + "\n" +
                "Fecha:\t\t" + dia + "/" + mes + "/" + anyo + "\n" +
                "Duración:\t\t" + duracion + "\n" +
                "Precio de la llamada:\t" + precio + "\n\n";
    }
}

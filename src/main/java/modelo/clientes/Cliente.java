package modelo.clientes;

import modelo.atributos.Direccion;
import modelo.constructores.ConstructorTarifa;
import modelo.fecha.Fecha;
import modelo.tarifa.Tarifa;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Cliente implements Fecha, Serializable { //abstract
    private final GregorianCalendar fecha;
    private Tarifa tarifa;
    private Direccion direccion;
    private String dni;
    private String email;
    private GregorianCalendar ultimaFactura;

    public Cliente() {
        ConstructorTarifa constructorTarifa = new ConstructorTarifa();
        tarifa = constructorTarifa.getInstanceBasica();
        direccion = new Direccion();
        dni = "";
        email = "";
        fecha = new GregorianCalendar();
        //nombre = "";
        ultimaFactura = new GregorianCalendar(1970, 1, 1);
    }

    abstract public String getNombre();

    abstract public void setNombre(String nombre);

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public GregorianCalendar getFecha() {
        return fecha;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) throws IllegalArgumentException {
        if (tarifa.getPrecio() < 0)
            throw new IllegalArgumentException("El precio de la modelo.tarifa no puede ser negativa:\n" + tarifa.toString());
        this.tarifa = tarifa;
    }

    public GregorianCalendar getUltimaFactura() {
        return ultimaFactura;
    }

    public void setUltimaFactura(GregorianCalendar ultimaFactura) {
        this.ultimaFactura = ultimaFactura;
    }

    @Override
    public String toString() {
        return "Nombre:\t" + this.getNombre() + "\n" +
                "DNI:\t" + dni + "\n" +
                "Direccion:\t" + direccion.toString() + "\n" +
                "Email:\t" + email + "\n" +
                "Fecha de alta:\t" + fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR) + "\n\n";
    }
}
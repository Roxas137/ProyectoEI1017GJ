package clientes;

import atributos.Direccion;
import constructores.ConstructorTarifa;
import principal.Fecha;
import tarifa.Tarifa;

import java.io.Serializable;
import java.util.Date;

public abstract class Cliente implements Fecha, Serializable { //abstract
    private Tarifa tarifa;
    private Direccion direccion;
    private String dni;
    private String email;
    private Date fecha;//FechaAlta
    private Date ultimaFactura;

    public Cliente() {
        ConstructorTarifa constructorTarifa = new ConstructorTarifa();
        tarifa = constructorTarifa.getInstanceBasica();
        direccion = new Direccion();
        dni = "";
        email = "";
        fecha = new Date();
        //nombre = "";
        ultimaFactura = new Date(1970, 1, 1);
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
    public Date getFecha() {
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
            throw new IllegalArgumentException("El precio de la tarifa no puede ser negativa:\n" + tarifa.toString());
        this.tarifa = tarifa;
    }

    public Date getUltimaFactura() {
        return ultimaFactura;
    }

    public void setUltimaFactura(Date ultimaFactura) {
        this.ultimaFactura = ultimaFactura;
    }

    public String clienteToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre:\t\t\t");
        sb.append(this.getNombre());
        sb.append("\n");

        sb.append("DNI:\t\t\t");
        sb.append(this.dni);
        sb.append("\n");

        sb.append("Direccion:\t\t");
        sb.append(this.direccion.direccionToString());
        sb.append("\n");

        sb.append("Email:\t\t\t");
        sb.append(this.email);
        sb.append("\n");

        sb.append("Fecha de alta:\t");
        sb.append(this.fecha.toString());
        sb.append("\n\n");

        return sb.toString();
    }
}

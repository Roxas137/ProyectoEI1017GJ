package clientes;

import atributos.Direccion;
import atributos.Tarifa;
import principal.Fecha;

import java.util.Date;

public abstract class Cliente implements Fecha { //abstract
    private Tarifa tarifa;
    private Direccion direccion;
    private String dni;
    private String email;
    private Date fecha;//FechaAlta
    private String nombre;
    private Date ultimaFactura;

    public Cliente() {
        tarifa = new Tarifa();
        direccion = new Direccion();
        dni = "";
        email = "";
        fecha = new Date();
        nombre = "";
        ultimaFactura = new Date(1970, 1, 1);
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public Date getFecha() {
        return fecha;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public Date getUltimaFactura() {
        return ultimaFactura;
    }

    public void setTarifa(Tarifa tarifa) throws IllegalArgumentException {
        if (tarifa.getPrecio() < 0)
            throw new IllegalArgumentException("El precio de la tarifa no puede ser negativa.");
        this.tarifa = tarifa;
    }

    public void setUltimaFactura(Date ultimaFactura) {
        this.ultimaFactura = ultimaFactura;
    }

    public String clienteToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre:\t\t\t");
        sb.append(this.nombre);
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

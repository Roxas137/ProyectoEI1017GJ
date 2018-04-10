package clientes;

import atributos.Direccion;
import tarifa.Basica;
import principal.Fecha;

import java.io.Serializable;
import java.util.Date;

public abstract class Cliente implements Fecha, Serializable { //abstract
    private Basica basica;
    private Direccion direccion;
    private String dni;
    private String email;
    private Date fecha;//FechaAlta
    private Date ultimaFactura;

    abstract public String getNombre();

    abstract public void setNombre(String nombre);

    public Cliente() {
        basica = new Basica();
        direccion = new Direccion();
        dni = "";
        email = "";
        fecha = new Date();
        //nombre = "";
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

    @Override
    public Date getFecha() {
        return fecha;
    }

    public String getDni() {
        return dni;
    }

    public Basica getBasica() {
        return basica;
    }

    public Date getUltimaFactura() {
        return ultimaFactura;
    }

    public void setBasica(Basica basica) throws IllegalArgumentException {
        if (basica.getPrecio() < 0)
            throw new IllegalArgumentException("El precio de la basica no puede ser negativa.");
        this.basica = basica;
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

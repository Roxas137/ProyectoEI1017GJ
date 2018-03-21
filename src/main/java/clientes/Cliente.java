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

    @Override
    public Date getFecha() {
        return fecha;
    }

    public String getDni() {
        return dni;
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
}

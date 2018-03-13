package clientes;

import atributos.Direccion;
import atributos.Tarifa;

import java.util.Date;

public abstract class Cliente { //abstract
    private Tarifa tarifa;
    private Direccion direccion;
    private String dni;
    private String email;
    private Date fechaAlta;
    private String nombre;
    private Date ultimaFactura;

    public Cliente() {
        tarifa = new Tarifa();
        direccion = new Direccion();
        dni = "";
        email = "";
        fechaAlta = new Date();
        nombre = "";
        ultimaFactura = new Date(1970, 1, 1);
    }

    public String getDni() {
        return dni;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Date getUltimaFactura() {
        return ultimaFactura;
    }

    public void setUltimaFactura(Date ultimaFactura) {
        this.ultimaFactura = ultimaFactura;
    }
}

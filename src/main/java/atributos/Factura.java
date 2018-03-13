package atributos;

import clientes.Cliente;
import clientes.Particular;

import java.util.Date;

public class Factura {
    private Tarifa tarifa;
    private int codigo;
    private Date fechaInicio;
    private Date fechaFin;
    private Cliente cliente;
    private double precio;

    public Factura() {
        tarifa = new Tarifa();
        codigo = 0;
        fechaInicio = getCliente().getUltimaFactura();
        fechaFin = new Date();
        cliente = new Particular(); //Por defecto, suponemos que es un cliente particular
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setTarifa(Tarifa nueva) {
        tarifa = nueva;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

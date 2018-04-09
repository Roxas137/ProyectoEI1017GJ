package atributos;

import clientes.Cliente;
import clientes.Particular;
import principal.Fecha;

import java.io.Serializable;
import java.util.Date;

public class Factura implements Fecha, Serializable{
    //private Tarifa tarifa;
    private int codigo;
    private Date fecha;
    private Cliente cliente;
    private double precio;

    public Factura() {
       // tarifa = new Tarifa();
        codigo = 0;
        fecha = new Date();
        cliente = new Particular(""); //Por defecto, suponemos que es un cliente particular
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public Date getFecha() {
        return fecha;
    }

    /*public void setTarifa(Tarifa nueva){
        tarifa = nueva;
    }*/

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPrecio(double precio) throws IllegalArgumentException{
        if (precio < 0)
            throw new IllegalArgumentException("El precio de la factura no puede ser negativo");
        this.precio = precio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Código de la factura: " + codigo + "\n");
        sb.append("Fecha de la factura: " + fecha.toString() + "\n");
        sb.append("Cliente: " + cliente.clienteToString() + "\n");
        sb.append("Precio de la factura: " + precio + "\n");
        return sb.toString();
    }
}

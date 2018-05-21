package modelo.atributos;

import modelo.clientes.Cliente;
import modelo.clientes.Particular;
import modelo.fecha.Fecha;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Factura implements Fecha, Serializable {
    private int codigo;
    private GregorianCalendar fecha;
    private Cliente cliente;
    private double precio;

    public Factura() {
        codigo = 0;
        fecha = new GregorianCalendar();
        cliente = new Particular(""); //Por defecto, suponemos que es un cliente particular
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public GregorianCalendar getFecha() {
        return fecha;
    }

    public void setFecha(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setPrecio(double precio) throws IllegalArgumentException {
        if (precio < 0)
            throw new IllegalArgumentException("El precio de la factura no puede ser negativo");
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Código de la factura: " + codigo + "\n" +
                "Fecha de la factura: " + fecha.toString() + "\n" +
                "Cliente: " + "\n" + cliente.toString() + "\n" +
                "Precio de la factura: " + precio + "\n";
    }
}

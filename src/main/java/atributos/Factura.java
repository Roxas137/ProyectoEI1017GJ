package atributos;

import clientes.Cliente;
import clientes.Particular;
import principal.Fecha;

import java.io.Serializable;
import java.util.Date;

public class Factura implements Fecha, Serializable {
    private int codigo;
    private Date fecha;
    private Cliente cliente;
    private double precio;

    public Factura() {
        codigo = 0;
        fecha = new Date();
        cliente = new Particular(""); //Por defecto, suponemos que es un cliente particular
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
        return  "Código de la factura: " + codigo + "\n" +
                "Fecha de la factura: " + fecha.toString() + "\n" +
                "Cliente: " + "\n" + cliente.clienteToString() + "\n" +
                "Precio de la factura: " + precio + "\n";
    }
}

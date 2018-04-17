package atributos;

import principal.Fecha;
import tarifa.Basica;
import tarifa.Tarifa;

import java.io.Serializable;
import java.util.Date;

public class Llamada implements Fecha, Serializable{
    private int nTelefono;
    private Date fecha; //Fecha de la llamada
    private double duracion;
    private double precio; //Tarifa en el momento de la llamada

    public Llamada() {
        nTelefono = 0;
        fecha = new Date();
        duracion = 10;
        precio = 0;
    }
    @Override
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getDuracion() {
        return duracion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public void setnTelefono(int nTelefono) {
        this.nTelefono = nTelefono;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Núm. Telefono:\t");
        sb.append(nTelefono);
        sb.append("\n");

        sb.append("Fecha:\t\t\t");
        sb.append(fecha.toString());
        sb.append("\n");

        sb.append("Duración:\t\t");
        sb.append(duracion);
        sb.append("\n");

        sb.append("Precio de la llamada:\t\t\t");
        sb.append(precio);
        sb.append("\n\n");

        return sb.toString();
    }
}

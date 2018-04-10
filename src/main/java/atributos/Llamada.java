package atributos;

import principal.Fecha;
import tarifa.Basica;

import java.io.Serializable;
import java.util.Date;

public class Llamada implements Fecha, Serializable{
    private int nTelefono;
    private Date fecha; //Fecha de la llamada
    private double duracion;
    private Basica basica; //Basica en el momento de la llamada

    public Llamada() {
        nTelefono = 0;
        fecha = new Date();
        duracion = 10;
        basica = new Basica();
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

    public Basica getBasica() {
        return basica;
    }

    public void setBasica(Basica basica) {
        this.basica = basica;
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

        sb.append("Basica:\t\t\t");
        sb.append(basica.getPrecio());
        sb.append("\n\n");

        return sb.toString();
    }
}

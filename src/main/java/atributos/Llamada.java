package atributos;

import principal.Fecha;

import java.util.Date;

public class Llamada implements Fecha{
    private int nTelefono;
    private Date fecha; //Fecha de la llamada
    private double duracion;
    private Tarifa tarifa; //Tarifa en el momento de la llamada

    public Llamada() {
        nTelefono = 0;
        fecha = new Date();
        duracion = 10;
        tarifa = new Tarifa();
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

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public void setnTelefono(int nTelefono) {
        this.nTelefono = nTelefono;
    }
}

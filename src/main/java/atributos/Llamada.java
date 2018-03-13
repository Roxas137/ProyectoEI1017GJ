package atributos;

import java.util.Date;

public class Llamada {
    private int nTelefono;
    private Date fechaLlamada;
    private double duracion;
    private Tarifa tarifa;

    public Llamada() {
        nTelefono = 0;
        fechaLlamada = new Date();
        duracion = 10;
        tarifa = new Tarifa();
    }

    public Date getFechaLlamada() {
        return fechaLlamada;
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
}

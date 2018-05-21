package modelo.tarifa;

import modelo.atributos.Llamada;

import java.util.Calendar;
import java.util.Optional;

/**
 * Created by al361891 on 10/04/18.
 */
public class HoraReducida extends Extras{
    private final int horaInicio;
    private final int horaFin;

    public HoraReducida (Tarifa horaReducida, int horaInicio, int horaFin, double precio) {
        super(horaReducida);
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        setPrecio(precio);
    }
    @Override
    public Optional<Double> calcularPrecio(Llamada llamada) {
        Optional<Double> precio = getTarifa().calcularPrecio(llamada);
        if (llamada.getFecha().get(Calendar.HOUR_OF_DAY) >= horaInicio && llamada.getFecha().get(Calendar.HOUR_OF_DAY) <= horaFin)
            precio = Optional.of(precioMinimo(precio.get(), getPrecio() * llamada.getDuracion()));
        return precio;
    }
}

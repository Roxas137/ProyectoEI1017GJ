package controlador.tarifa;

import controlador.atributos.Llamada;

import java.util.Calendar;
import java.util.Optional;

/**
 * Created by al361891 on 10/04/18.
 */
public class DiaReducido extends Extras {
    private final int dia;

    //Que Tarifa guarde un atributo Extras y que hora y dia sean hijas de Extras
    public DiaReducido(Tarifa diaReducido, int dia, double precio) {
        super(diaReducido);
        this.dia = dia;
        setPrecio(precio);
    }

    @Override
    public Optional<Double> calcularPrecio(Llamada llamada) {
        double precio = getTarifa().calcularPrecio(llamada);
        if (llamada.getFecha().get(Calendar.DAY_OF_WEEK) == dia)
            precio = (precioMinimo(precio, getPrecio() * llamada.getDuracion()));
        return Optional.of(precio);
    }
}

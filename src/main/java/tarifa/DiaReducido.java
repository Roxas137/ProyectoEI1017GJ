package tarifa;

import atributos.Llamada;

import java.util.Optional;

/**
 * Created by al361891 on 10/04/18.
 */
public class DiaReducido extends Extras {
    private int dia;

    //Que Tarifa guarde un atributo Extras y que hora y dia sean hijas de Extras
    public DiaReducido(Tarifa diaReducido, int dia, double precio) {
        super(diaReducido);
        this.dia = dia;
        setPrecio(precio);
    }

    @Override
    public Optional<Double> calcularPrecio(Llamada llamada) {
        Optional<Double> precio = getTarifa().calcularPrecio(llamada);
        if (llamada.getFecha().getDay() == dia)
            precio = Optional.of(precioMinimo(precio.get(), getPrecio() * llamada.getDuracion()));
        return precio;
    }
}

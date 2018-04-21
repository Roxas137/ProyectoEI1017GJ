package tarifa;

import atributos.Llamada;

import java.util.Optional;

/**
 * Created by al361891 on 10/04/18.
 */
public class DiaReducido extends Extras {
    private Tarifa diaReducido;
    private int dia;
    private double precio;

    public DiaReducido() {
        super();
    }
    //Que Tarifa guarde un atributo Extras y que hora y dia sean hijas de Extras
    public DiaReducido (Tarifa diaReducido, int dia, double precio){
        this.diaReducido = diaReducido;
        this.dia = dia;
        this.precio = precio;
    }

    @Override
    public Optional<Double> calcularPrecio(Llamada llamada) {
        if (llamada.getFecha().getDay() == dia)
            return Optional.of(precio*llamada.getDuracion());
        return diaReducido.calcularPrecio(llamada);
    }
}

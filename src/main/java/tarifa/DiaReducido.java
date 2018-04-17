package tarifa;

import atributos.Llamada;

import java.util.Optional;

/**
 * Created by al361891 on 10/04/18.
 */
public class DiaReducido extends Tarifa {
    private double precioDia;
    private int dia;

    public DiaReducido() {
        super();
        precioDia = 0;
        dia = 0;
    }
    @Override
    public Optional<Double> calcularPrecio(Llamada llamada) {
        if (llamada.getFecha().getDay() == dia)
            return Optional.of(precioDia*llamada.getDuracion());
        return Optional.empty();
    }
}

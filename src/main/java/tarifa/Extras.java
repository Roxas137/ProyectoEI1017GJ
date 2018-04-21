package tarifa;

import atributos.Llamada;

import java.util.Optional;

public abstract class Extras extends Tarifa{
    private Tarifa tarifa;

    @Override
    public abstract Optional<Double> calcularPrecio(Llamada llamada);

}

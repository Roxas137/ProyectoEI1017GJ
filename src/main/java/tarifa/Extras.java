package tarifa;

import atributos.Llamada;

import java.util.Optional;

public abstract class Extras extends Tarifa {
    private Tarifa tarifa;

    public Extras(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    @Override
    public abstract Optional<Double> calcularPrecio(Llamada llamada);

}

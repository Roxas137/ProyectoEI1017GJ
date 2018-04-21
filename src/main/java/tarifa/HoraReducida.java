package tarifa;

import atributos.Llamada;

import java.util.Optional;

/**
 * Created by al361891 on 10/04/18.
 */
public class HoraReducida extends Tarifa{
    public int horaInicio;

    @Override
    public Optional<Double> calcularPrecio(Llamada llamada) {
        return Optional.empty();
    }
}

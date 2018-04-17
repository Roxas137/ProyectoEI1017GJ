package tarifa;

import atributos.Llamada;

/**
 * Created by al361891 on 10/04/18.
 */
public class Basica extends Tarifa{

    public Basica(){
        super();
    }

    @Override
    public double calcularPrecio(Llamada llamada) {
        return llamada.getDuracion()*getPrecio();
    }
}

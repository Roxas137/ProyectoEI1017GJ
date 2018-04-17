package tarifa;

import atributos.Llamada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Tarifa implements Serializable{
    private double precio;

    public Tarifa(){
        this.precio = 0.01;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public abstract Optional<Double> calcularPrecio(Llamada llamada);
}

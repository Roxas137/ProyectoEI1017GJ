package tarifa;

import java.io.Serializable;
import java.util.ArrayList;

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
}

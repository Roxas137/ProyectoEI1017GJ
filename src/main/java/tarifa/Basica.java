package tarifa;

import java.io.Serializable;

public class Basica implements Serializable{
    private double precio;

    public Basica(){
        this.precio = 0.01;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

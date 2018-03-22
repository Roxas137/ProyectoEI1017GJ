package atributos;

public class Tarifa {
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

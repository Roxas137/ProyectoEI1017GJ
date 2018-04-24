package atributos;

import java.io.Serializable;

public class Direccion implements Serializable {
    private final int codigoPostal;
    private final String provincia;
    private final String poblacion;

    public Direccion() {
        codigoPostal = 0;
        provincia = "";
        poblacion = "";
    }

    public Direccion(int codigoPostal, String provincia, String poblacion) {
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }
    @Override
    public String toString() {
        return (codigoPostal + " " + provincia + " " + poblacion);
    }
}

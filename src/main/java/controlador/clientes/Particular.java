package controlador.clientes;

/**
 * Created by al361891 on 20/02/18.
 */
public class Particular extends Cliente {
    private final String apellidos;
    private String nombre;

    public Particular(String apellido) {
        super();
        this.apellidos = apellido;
        this.nombre = "";
    }

    public String getNombre() {
        return this.nombre + " " + this.apellidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

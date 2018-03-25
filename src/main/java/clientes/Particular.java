package clientes;

/**
 * Created by al361891 on 20/02/18.
 */
public class Particular extends Cliente {
    private String apellidos;

    public Particular(String apellido){
        super();
        this.apellidos = apellido;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}

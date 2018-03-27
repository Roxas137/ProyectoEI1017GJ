package clientes;

/**
 * Created by al361891 on 20/02/18.
 */
public class Particular extends Cliente {
    private String apellidos;
    private String nombre;

    public Particular(String apellido){
        super();
        this.apellidos = apellido;
        this.nombre = "";
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre + " " + this.apellidos;
    }

}

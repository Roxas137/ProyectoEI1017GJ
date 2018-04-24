package clientes;

public class Empresa extends Cliente {

    private String nombre;

    public Empresa() {
        super();
        this.nombre = "";
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

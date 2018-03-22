package atributos;

public class Direccion {
    private int codigoPostal;
    private String provincia;
    private String poblacion;

    public Direccion(){
        codigoPostal = 0;
        provincia = "";
        poblacion = "";
    }

    public Direccion(int codigoPostal, String provincia, String poblacion){
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    public String direccionToString(){
        return (codigoPostal+" "+provincia+" "+poblacion);
    }
}

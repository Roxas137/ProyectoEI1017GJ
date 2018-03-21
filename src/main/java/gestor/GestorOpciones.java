package gestor;

import atributos.Direccion;
import clientes.Cliente;
import clientes.Empresa;
import clientes.Particular;

import java.util.Iterator;
import java.util.Scanner;

public class GestorOpciones {

    Metodos metodo = new Metodos();

    //TODO
    public void salir() {
        // guardar datos en fichero
        System.exit(0);
    }

    public void altaCliente() {
        Cliente nuevo = new Particular();
        boolean incorrecto = false;
        String opcion;
        Scanner sc;
        do {
            System.out.println("Elije el tipo de cliente que quieres añadir: Particular (P) / Empresa (E)");
            sc = new Scanner(System.in);
            opcion = sc.next();
            opcion.toLowerCase();
            switch (opcion) {
                case "p": //Particular
                    break;
                case "e": //Empresa
                    nuevo = new Empresa();
                    break;
                default:
                    System.out.println("Opcion no valida\n");
                    incorrecto = true;
            }
        } while (incorrecto);
        System.out.println("Nombre: ");
        nuevo.setNombre(sc.next());
        if (opcion.equals("p")) {
            System.out.println("Apellido: ");
            nuevo.setNombre(sc.next());
        }
        System.out.println("DNI: ");
        nuevo.setDni(sc.next());

        System.out.println("Codigo Postal: ");
        int codpostal = sc.nextInt();
        System.out.println("Provincia: ");
        String provincia = sc.next();
        System.out.println("Poblacion: ");
        String poblacion = sc.next();
        Direccion dir = new Direccion(codpostal, provincia, poblacion);
        nuevo.setDireccion(dir);
        System.out.println("email: ");
        nuevo.setEmail(sc.next());
        metodo.addCliente(nuevo);
    }

    public void borrarCliente() {
        System.out.println("Introduce el DNI del cliente que quieres eliminar: ");
        Scanner sc = new Scanner(System.in);
        String dni = sc.next();

    }

    public void cambiarTarifa() {

    }

    public void verCliente() {

    }

    public void verTodosClientes() {
        Iterator<Cliente> it;

    }

    public void altaLlamada() {

    }

    public void verLlamadasCliente() {

    }

    public void emitirFactura() {

    }

    public void verFactura() {

    }

    public void verFacturasCliente() {

    }

    public void verClientesFechas() {

    }

    public void verLlamadasClienteFechas() {

    }

    public void verFacturasClienteFechas() {

    }

}

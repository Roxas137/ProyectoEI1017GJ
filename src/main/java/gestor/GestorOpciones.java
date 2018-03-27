package gestor;

import atributos.Direccion;
import atributos.Llamada;
import atributos.Tarifa;
import clientes.Cliente;
import clientes.Empresa;
import clientes.Particular;
import menu.MenuSwitch;
import principal.Main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;

public class GestorOpciones {

    private Metodos metodo = new Metodos();

    //hecho
    private String pedirDNI(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el DNI del cliente: ");
        String dni = sc.next();
        sc.close();
        return dni;
    }
    //TODO
    public void salir() {
        // guardar datos en fichero
        System.exit(0);
    }
    //hecho
    public void altaCliente() {
        Cliente nuevo = new Empresa();
        boolean incorrecto;
        String opcion;
        Scanner sc;
        do {
            incorrecto = false;
            System.out.println("Elije el tipo de cliente que quieres añadir: Particular (P) / Empresa (E)");
            sc = new Scanner(System.in);
            opcion = sc.next();
            opcion = opcion.toLowerCase();
            switch (opcion) {
                case "p": //Particular
                    System.out.println("Apellido: ");
                    nuevo = new Particular(sc.next());
                    break;
                case "e": //Empresa
                    break;
                default:
                    System.out.println("Opcion no valida\n");
                    incorrecto = true;
            }
        } while (incorrecto);
        System.out.println("Nombre: ");
        nuevo.setNombre(sc.next());
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
        System.out.println("Cliente Añadido Correctamente");
    }
    //no funciona
    public void borrarCliente() {
        metodo.removeCliente(pedirDNI());
    }
    //hecho
    public void cambiarTarifa() {
        Optional<Cliente> buscado = metodo.devuelveCliente(pedirDNI());
        if (!buscado.isPresent()){
            System.out.println("No se encuentra el cliente\n");
        }else{
            //pasar a string el cliente
            Cliente encontrado = buscado.get();
            System.out.println("Introduce la nueva tarifa: ");
            Tarifa nueva = new Tarifa();
            Scanner sc = new Scanner(System.in);
            nueva.setPrecio(sc.nextDouble());
            sc.close();
            metodo.cambiarTarifa(encontrado, nueva);
        }
    }
    //hecho
    public void verCliente() {
        Optional<Cliente> buscado = metodo.devuelveCliente(pedirDNI());
        if (buscado.equals(Optional.empty())){
            System.out.println("No se encuentra el cliente\n");
        }else{
            System.out.println(buscado.get().clienteToString());
        }
    }
    //hecho
    public void verTodosClientes() {
        System.out.println("Consultando Todos Los Clientes");
        Cliente unCliente;
        ArrayList<Cliente> listaDeClientes = metodo.listaClientes();
        Iterator<Cliente> it = listaDeClientes.iterator();
        while (it.hasNext()){
            unCliente = it.next();
            System.out.println("Tengo un nuevo cliente:");
            System.out.println(unCliente.clienteToString());
        }
        /*System.out.println("Todos los clientes mostrados\nPulsa intro para continuar\n");
        Scanner fin = new Scanner(System.in);
        fin.next();*/
    }
    //hecho (falta confirmar)
    public void altaLlamada() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca su dni:");
        String dni = sc.next();
        Optional<Cliente> buscado = metodo.devuelveCliente(dni);
        if (!buscado.isPresent()){
            System.out.println("No se encuentra el cliente\n");
        }else{
            //pasar a string el cliente
            Cliente encontrado = buscado.get();
            Llamada nueva = new Llamada();
            System.out.println("Desde que teléfono:");
            nueva.setnTelefono(sc.nextInt());
            System.out.println("Duracion:");
            nueva.setDuracion(sc.nextDouble());
            metodo.addLlamada(encontrado, nueva);
            System.out.println("Llamada añadida");
        }
    }
    //lanza noSuchElementException
    public void verLlamadasCliente() {
        String dni = pedirDNI();
        for (Llamada llamada : metodo.listaLlamadas(dni)){
            llamada.llamadaToString();
        }
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

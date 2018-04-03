package gestor;

import atributos.Direccion;
import atributos.Factura;
import atributos.Llamada;
import atributos.Tarifa;
import clientes.Cliente;
import clientes.Empresa;
import clientes.Particular;
import principal.FechaIntervalo;

import java.time.DateTimeException;
import java.util.*;

public class GestorOpciones {

    private Metodos metodo = new Metodos();

    //TODO
    public void salir() {
        // guardar datos en Fichero
        System.exit(0);
    }

    //hecho
    public void altaCliente(Scanner sc) {
        Cliente nuevo = new Empresa();
        String opcion;
        opcion = pedirTipo(sc);
        sc = new Scanner(System.in);
        switch (opcion) {
            case "p": //Particular
                System.out.println("Apellido: ");
                String apellido = sc.next();
                nuevo = new Particular(apellido);
                break;
            case "e": //Empresa
                break;
        }
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
    public void borrarCliente(Scanner sc) {
        metodo.removeCliente(pedirDNI(sc));
    }

    //hecho
    public void cambiarTarifa(Scanner sc) {
        Optional<Cliente> buscado = metodo.devuelveCliente(pedirDNI(sc));
        if (!buscado.isPresent()) {
            System.out.println("No se encuentra el cliente\n");
        } else {
            //pasar a string el cliente
            Cliente encontrado = buscado.get();
            System.out.println("Introduce la nueva tarifa: ");
            Tarifa nueva = new Tarifa();
            sc = new Scanner(System.in);
            nueva.setPrecio(sc.nextDouble());
            metodo.cambiarTarifa(encontrado, nueva);
        }
    }

    //hecho
    public void verCliente(Scanner sc) {
        Optional<Cliente> buscado = metodo.devuelveCliente(pedirDNI(sc));
        if (buscado.equals(Optional.empty())) {
            System.out.println("No se encuentra el cliente\n");
        } else {
            System.out.println(buscado.get().clienteToString());
        }
    }

    //hecho
    public void verTodosClientes() {
        System.out.println("Consultando Todos Los Clientes");
        Cliente unCliente;
        ArrayList<Cliente> listaDeClientes = metodo.listaClientes();
        Iterator<Cliente> it = listaDeClientes.iterator();
        while (it.hasNext()) {
            unCliente = it.next();
            System.out.println("Tengo un nuevo cliente:");
            System.out.println(unCliente.clienteToString());
        }
        /*System.out.println("Todos los clientes mostrados\nPulsa intro para continuar\n");
        Scanner fin = new Scanner(System.in);
        fin.next();*/
    }

    //hecho (falta confirmar)
    public void altaLlamada(Scanner sc) {
        sc = new Scanner(System.in);
        System.out.println("Introduzca su dni:");
        String dni = sc.next();
        Optional<Cliente> buscado = metodo.devuelveCliente(dni);
        if (!buscado.isPresent()) {
            System.out.println("No se encuentra el cliente\n");
        }
        else {
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
    public void verLlamadasCliente(Scanner sc) {
        String dni = pedirDNI(sc);
        if (metodo.getMapaClientes().containsKey(dni))
            for (Llamada llamada : metodo.listaLlamadas(dni))
                System.out.println(llamada.toString());
        else
            System.out.println("El cliente no existe");
    }

    //falta comprobar
    public void emitirFactura(Scanner sc) {
        String dni = pedirDNI(sc);
        System.out.println(metodo.emitirFactura(dni));
    }

    //falta comprobar
    public void verFactura(Scanner sc) {
        sc = new Scanner(System.in);
        System.out.println("Introduce el código de la factura: ");
        int codigo = sc.nextInt();
        //Comprobar codigo
        System.out.println(metodo.getTotalFacturas().get(codigo).toString());
    }

    //falta comprobar
    public void verFacturasCliente(Scanner sc) {
        String dni = pedirDNI(sc);
        ArrayList<Factura> facturas = metodo.facturasCliente(dni);
        for (Factura factura : facturas)
            System.out.println(factura.toString());
    }

    //falta comprobar
    public void verClientesFechas(Scanner sc) {
        FechaIntervalo<Cliente> fechas = new FechaIntervalo<>();
        Date inicio = pedirFecha("(Inicio)", sc);
        Date fin = pedirFecha("(Fin)", sc);
        try {
            comprobarFechas(inicio, fin);
        } catch (DateTimeException fechaIncorrecta) {
            System.out.println("Fecha no válida");
            return;
        }
        Collection<Cliente> clientes = metodo.getMapaClientes().values();
        fechas.fechasIntervalo(clientes, inicio, fin);
        for (Cliente cliente : fechas.getFechaCorrecta())
            System.out.println(cliente.clienteToString());
    }

    //falta comprobar
    public void verLlamadasClienteFechas(Scanner sc) {
        FechaIntervalo<Llamada> fechas = new FechaIntervalo<>();
        String dni = pedirDNI(sc);
        Date inicio = pedirFecha("(Inicio)", sc);
        Date fin = pedirFecha("(Fin)", sc);
        try {
            comprobarFechas(inicio, fin);
        } catch (DateTimeException fechaIncorrecta) {
            System.out.println("Fecha no válida");
            return;
        }
        ArrayList<Llamada> llamadas = metodo.listaLlamadas(dni);
        fechas.fechasIntervalo(llamadas, inicio, fin);
        for (Llamada llamada : fechas.getFechaCorrecta())
            System.out.println(llamada.toString());
    }

    //falta comprobar
    public void verFacturasClienteFechas(Scanner sc) {
        FechaIntervalo<Factura> fechas = new FechaIntervalo<>();
        String dni = pedirDNI(sc);
        Date inicio = pedirFecha(" (Inicio) ", sc);
        Date fin = pedirFecha(" (Fin) ", sc);
        try {
            comprobarFechas(inicio, fin);
        } catch (DateTimeException fechaIncorrecta) {
            System.out.println("Fecha no válida");
            return;
        }
        ArrayList<Factura> facturas = facturasCliente(dni);
        fechas.fechasIntervalo(facturas, inicio, fin);
        for (Factura factura : fechas.getFechaCorrecta())
            System.out.println(factura.toString());
    }


    private void comprobarFechas(Date inicio, Date fin) throws DateTimeException {
        if (inicio.after(fin))
            throw new DateTimeException("Fechas incorrectas");
    }

    private String pedirDNI(Scanner sc) {
        sc = new Scanner(System.in);
        System.out.println("Introduce el DNI del cliente: ");
        String dni = sc.next();
        return dni;
    }

    private Date pedirFecha(String detalle, Scanner sc) {
        sc = new Scanner(System.in);
        System.out.println("Introduce el día" + detalle + ": ");
        int dia = sc.nextInt();
        System.out.println("Introduce el mes:" + detalle + ": ");
        int mes = sc.nextInt();
        System.out.println("Introduce el año: " + detalle + ": ");
        int anyo = sc.nextInt();
        return new Date(anyo, mes, dia);
    }

    private ArrayList<Factura> facturasCliente(String dni) {
        ArrayList<Factura> facturas = new ArrayList<>();
        for (Factura factura : metodo.getTotalFacturas())
            if (factura.getCliente().getDni().equals(dni))
                facturas.add(factura);
        return facturas;
    }

    private String pedirTipo(Scanner sc) {
        boolean correcto = false;
        String opcion = "";
        sc = new Scanner(System.in);
        while (!correcto) {
            System.out.println("Elije el tipo de cliente que quieres añadir: Particular (P) / Empresa (E)");
            opcion = sc.next();
            opcion = opcion.toLowerCase();
            if (!opcion.equals("p") && !opcion.equals("e"))
                System.out.println("Opcion no valida\n");
            else
                correcto = true;
        }
        return opcion;
    }
}

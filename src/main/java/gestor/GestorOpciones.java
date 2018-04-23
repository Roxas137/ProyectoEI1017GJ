package gestor;

import atributos.Direccion;
import atributos.Factura;
import atributos.Llamada;
import clientes.Cliente;
import constructores.ConstructorCliente;
import constructores.ConstructorTarifa;
import fichero.Fichero;
import principal.FechaIntervalo;
import tarifa.Tarifa;

import java.time.DateTimeException;
import java.util.*;

public class GestorOpciones {

    private Metodos metodo;

    public GestorOpciones(Metodos metodos) {
        metodo = metodos;
    }


    public void salir() {
        Fichero fichero = new Fichero();
        fichero.salida(metodo);
        System.exit(0);
    }

    public void continuar(Scanner sc) {
        System.out.print("\nPulsa INTRO para continuar.\n");
        sc.nextLine();
        sc.nextLine();
    }

    public void altaCliente(Scanner sc) {
        ConstructorCliente constructorCliente = new ConstructorCliente();
        Cliente nuevo = constructorCliente.getInstanceEmpresa();
        String opcion;
        opcion = pedirTipo(sc);
        sc = new Scanner(System.in);
        switch (opcion) {
            case "p": //Particular
                System.out.println("Apellido: ");
                String apellido = sc.next();
                nuevo = constructorCliente.getInstanceParticular(apellido);
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
        continuar(sc);
    }

    public void borrarCliente(Scanner sc) {
        metodo.removeCliente(pedirDNI(sc));
        continuar(sc);
    }

    public void cambiarTarifa(Scanner sc) throws NoSuchElementException, DateTimeException {
        Optional<Cliente> buscado = metodo.devuelveCliente(pedirDNI(sc));
        if (!buscado.isPresent())
            throw new NoSuchElementException();
        else {
            ConstructorTarifa constructorTarifa = new ConstructorTarifa();
            Cliente encontrado = buscado.get();
            System.out.println("Introduce la nueva tarifa básica");
            sc = new Scanner(System.in);
            Tarifa basica = constructorTarifa.getInstanceBasica();
            basica.setPrecio(sc.nextDouble());
            System.out.println("¿Quieres una tarifa reducida por horas? S/N");
            String opcion = sc.next();
            opcion = opcion.toLowerCase();
            if (opcion.equals("s")) {
                System.out.println("Introduce una hora de inicio (0-23):");
                int horaInicio = sc.nextInt();
                System.out.println("Introduce una hora de fin (0-23):");
                int horaFin = sc.nextInt();
                System.out.println("Introduce el precio:");
                double precio = sc.nextDouble();
                if (compruebaHoras(horaInicio, horaFin))
                    throw new DateTimeException("Horas no validas");
                basica = constructorTarifa.getInstanceHoraReducida(basica, horaInicio, horaFin, precio);
                //Añadir tarifa a nueva
            }
            System.out.println("¿Quieres una tarifa reducida por días? S/N");
            opcion = sc.next();
            opcion = opcion.toLowerCase();
            if (opcion.equals("s")) {
                //Pedir dia
                //Pedir precio
                Tarifa diaRecudido = constructorTarifa.getInstanceHoraReducida();
                //Añadir tarifa a nueva
            }
            metodo.cambiarTarifa(encontrado, nueva);
        }
        continuar(sc);
    }

    public void verCliente(Scanner sc) throws NoSuchElementException {
        Optional<Cliente> buscado = metodo.devuelveCliente(pedirDNI(sc));
        if (!buscado.isPresent()) {
            throw new NoSuchElementException("No se encuentra el cliente");
        } else {
            System.out.println(buscado.get().clienteToString());
        }
        continuar(sc);
    }

    public void verTodosClientes(Scanner sc) {
        System.out.println("Consultando Todos Los Clientes");
        Cliente unCliente;
        ArrayList<Cliente> listaDeClientes = metodo.listaClientes();
        Iterator<Cliente> it = listaDeClientes.iterator();
        while (it.hasNext()) {
            unCliente = it.next();
            System.out.println("Tengo un nuevo cliente:");
            System.out.println(unCliente.clienteToString());
        }
        continuar(sc);
    }

    public void altaLlamada(Scanner sc) throws NoSuchElementException {
        sc = new Scanner(System.in);
        System.out.println("Introduzca su dni:");
        String dni = sc.next();
        Optional<Cliente> buscado = metodo.devuelveCliente(dni);
        if (!buscado.isPresent()) {
            throw new NoSuchElementException("No se encuentra el cliente");
        } else {
            //pasar a string el cliente
            Cliente encontrado = buscado.get();
            Llamada nueva = new Llamada();
            System.out.println("Desde que teléfono:");
            nueva.setnTelefono(sc.nextInt());
            System.out.println("Duracion:");
            nueva.setDuracion(sc.nextDouble());
            //Mirar cuál es la mejor tarifa del cliente para la llamada
            double precioMinimo = findPrecioMinimo(encontrado, nueva); //
            nueva.setPrecio(precioMinimo);
            metodo.addLlamada(encontrado, nueva);
            System.out.println("Llamada añadida");
        }
        continuar(sc);
    }

    public void verLlamadasCliente(Scanner sc) {
        String dni = pedirDNI(sc);
        if (metodo.getMapaClientes().containsKey(dni))
            for (Llamada llamada : metodo.listaLlamadas(dni))
                System.out.println(llamada.toString());
        else
            System.out.println("El cliente no existe");
        continuar(sc);
    }

    public void emitirFactura(Scanner sc) {
        String dni = pedirDNI(sc);
        System.out.println(metodo.emitirFactura(dni));
        continuar(sc);
    }

    public void verFactura(Scanner sc) {
        sc = new Scanner(System.in);
        System.out.println("Introduce el código de la factura: ");
        int codigo = sc.nextInt();
        //Comprobar codigo
        System.out.println(metodo.getTotalFacturas().get(codigo).toString());
        continuar(sc);
    }

    public void verFacturasCliente(Scanner sc) {
        String dni = pedirDNI(sc);
        ArrayList<Factura> facturas = metodo.facturasCliente(dni);
        for (Factura factura : facturas)
            System.out.println(factura.toString());
        continuar(sc);
    }


    public void verClientesFechas(Scanner sc) throws DateTimeException {
        FechaIntervalo<Cliente> fechas = new FechaIntervalo<>();
        Date inicio = pedirFecha("(Inicio)", sc);
        Date fin = pedirFecha("(Fin)", sc);
        if (inicio.after(fin))
            throw new DateTimeException("Fechas no validas");
        Collection<Cliente> clientes = metodo.getMapaClientes().values();
        fechas.fechasIntervalo(clientes, inicio, fin);
        ArrayList<Cliente> fechaCorrecta = fechas.getFechaCorrecta();
        for (Cliente cliente : fechaCorrecta)
            System.out.println(cliente.clienteToString());
        continuar(sc);
    }

    //TODO
    public void verLlamadasClienteFechas(Scanner sc) throws DateTimeException {
        FechaIntervalo<Llamada> fechas = new FechaIntervalo<>();
        String dni = pedirDNI(sc);
        Date inicio = pedirFecha("(Inicio)", sc);
        Date fin = pedirFecha("(Fin)", sc);
        if (inicio.after(fin))
            throw new DateTimeException("Fechas no validas");
        ArrayList<Llamada> llamadas = metodo.listaLlamadas(dni);
        fechas.fechasIntervalo(llamadas, inicio, fin);
        ArrayList<Llamada> fechaCorrecta = fechas.getFechaCorrecta();
        for (Llamada llamada : fechaCorrecta)
            System.out.println(llamada.toString());
        continuar(sc);
    }

    //TODO
    public void verFacturasClienteFechas(Scanner sc) throws DateTimeException {
        FechaIntervalo<Factura> fechas = new FechaIntervalo<>();
        String dni = pedirDNI(sc);
        Date inicio = pedirFecha(" (Inicio) ", sc);
        Date fin = pedirFecha(" (Fin) ", sc);
        if (inicio.after(fin))
            throw new DateTimeException("Fechas no validas");
        ArrayList<Factura> facturas = facturasCliente(dni);
        fechas.fechasIntervalo(facturas, inicio, fin);
        ArrayList<Factura> fechaCorrecta = fechas.getFechaCorrecta();
        for (Factura factura : fechaCorrecta)
            System.out.println(factura.toString());
        continuar(sc);
    }

    private String pedirDNI(Scanner sc) {
        sc = new Scanner(System.in);
        System.out.println("Introduce el DNI del cliente: ");
        return sc.next();
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

    private double findPrecioMinimo(Cliente cliente, Llamada llamada) {
        Optional<Double> precio = cliente.getTarifa().calcularPrecio(llamada);
        return precio.isPresent() ? precio.get() : -1;
    }

    private String pedirOpcionSN(Scanner sc) {
        return sc.next().toLowerCase();
    }

    private boolean compruebaHoras(int horaInicio, int horaFin) {
        return horaInicio < horaFin;
    }
}

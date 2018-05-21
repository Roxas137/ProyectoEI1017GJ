package controlador.gestor;

import modelo.atributos.Direccion;
import modelo.atributos.Factura;
import modelo.atributos.Llamada;
import modelo.clientes.Cliente;
import modelo.constructores.ConstructorCliente;
import modelo.constructores.ConstructorTarifa;
import controlador.fichero.Fichero;
import modelo.fecha.FechaIntervalo;
import modelo.tarifa.Tarifa;

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

    public void altaCliente() {
        ConstructorCliente constructorCliente = new ConstructorCliente();
        //Inicializamos el cliente como empresa y si es un particular luego lo cambiamos
        Cliente nuevo = constructorCliente.getInstanceEmpresa();
        String opcion;
        opcion = pedirTipo();
        Scanner sc = new Scanner(System.in);
        if (opcion.equals("p")) { //Particular
            System.out.println("Apellido: ");
            String apellido = sc.next();
            nuevo = constructorCliente.getInstanceParticular(apellido);
        }
        System.out.println("Nombre: ");
        nuevo.setNombre(sc.next());
        System.out.println(opcion.equals("p") ? "DNI: " : "CIF: ");
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
        metodo.removeCliente(pedirDNI());
        continuar(sc);
    }

    public void cambiarTarifa() throws NoSuchElementException, DateTimeException {
        Optional<Cliente> buscado = metodo.devuelveCliente(pedirDNI());
        Scanner sc = new Scanner(System.in);
        if (!buscado.isPresent())
            throw new NoSuchElementException();
        else {
            ConstructorTarifa constructorTarifa = new ConstructorTarifa();
            Cliente encontrado = buscado.get();
            System.out.println("Introduce la nueva modelo.tarifa básica");
            Tarifa basica = constructorTarifa.getInstanceBasica();
            basica.setPrecio(sc.nextDouble());
            System.out.println("¿Quieres una modelo.tarifa reducida por horas? S/N");
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
            }
            System.out.println("¿Quieres una modelo.tarifa reducida por días? S/N");
            opcion = sc.next();
            opcion = opcion.toLowerCase();
            if (opcion.equals("s")) {
                System.out.println("Introduce un día de la semana (0(domingo)-6(sabado)):");
                int dia = sc.nextInt();
                System.out.println("Introduce el precio:");
                double precio = sc.nextInt();
                basica = constructorTarifa.getInstanceDiaReducido(basica, dia, precio);
            }
            metodo.cambiarTarifa(encontrado, basica);
        }
        continuar(sc);
    }

    public void verCliente(Scanner sc) throws NoSuchElementException {
        Optional<Cliente> buscado = metodo.devuelveCliente(pedirDNI());
        if (!buscado.isPresent())
            throw new NoSuchElementException("No se encuentra el cliente");
        else
            System.out.println(buscado.get().toString());
        continuar(sc);
    }

    public void verTodosClientes(Scanner sc) {
        System.out.println("Consultando Todos Los Clientes");
        Cliente unCliente;
        ArrayList<Cliente> listaDeClientes = metodo.listaClientes();
        for (Cliente listaDeCliente : listaDeClientes) {
            unCliente = listaDeCliente;
            System.out.println("Tengo un nuevo cliente:");
            System.out.println(unCliente.toString());
        }
        continuar(sc);
    }

    public void altaLlamada() throws NoSuchElementException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca su dni:");
        String dni = sc.next();
        Optional<Cliente> buscado = metodo.devuelveCliente(dni);
        if (!buscado.isPresent())
            throw new NoSuchElementException("No se encuentra el cliente");
        else {
            //pasar a string el cliente
            Cliente encontrado = buscado.get();
            Llamada nueva = new Llamada();
            System.out.println("Desde que teléfono:");
            nueva.setnTelefono(sc.nextInt());
            System.out.println("Duracion:");
            nueva.setDuracion(sc.nextDouble());
            //Mirar cuál es la mejor modelo.tarifa del cliente para la llamada
            double precioMinimo = findPrecioMinimo(encontrado, nueva); //
            nueva.setPrecio(precioMinimo);
            metodo.addLlamada(encontrado, nueva);
            System.out.println("Llamada añadida");
        }
        continuar(sc);
    }

    public void verLlamadasCliente(Scanner sc) {
        String dni = pedirDNI();
        if (metodo.getMapaClientes().containsKey(dni))
            for (Llamada llamada : metodo.listaLlamadas(dni))
                System.out.println(llamada.toString());
        else
            System.out.println("El cliente no existe");
        continuar(sc);
    }

    public void emitirFactura(Scanner sc) {
        String dni = pedirDNI();
        System.out.println(metodo.emitirFactura(dni));
        continuar(sc);
    }

    public void verFactura() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el código de la factura: ");
        int codigo = sc.nextInt();
        //Comprobar codigo
        System.out.println(metodo.getTotalFacturas().get(codigo).toString());
        continuar(sc);
    }

    public void verFacturasCliente(Scanner sc) {
        String dni = pedirDNI();
        ArrayList<Factura> facturas = metodo.facturasCliente(dni);
        for (Factura factura : facturas)
            System.out.println(factura.toString());
        continuar(sc);
    }


    public void verClientesFechas(Scanner sc) throws DateTimeException {
        FechaIntervalo<Cliente> fechas = new FechaIntervalo<>();
        GregorianCalendar inicio = pedirFecha("(Inicio)");
        GregorianCalendar fin = pedirFecha("(Fin)");
        if (inicio.after(fin))
            throw new DateTimeException("Fechas no validas");
        Collection<Cliente> clientes = metodo.getMapaClientes().values();
        fechas.fechasIntervalo(clientes, inicio, fin);
        ArrayList<Cliente> fechaCorrecta = fechas.getFechaCorrecta();
        for (Cliente cliente : fechaCorrecta)
            System.out.println(cliente.toString());
        continuar(sc);
    }

    public void verLlamadasClienteFechas(Scanner sc) throws DateTimeException {
        FechaIntervalo<Llamada> fechas = new FechaIntervalo<>();
        String dni = pedirDNI();
        GregorianCalendar inicio = pedirFecha("(Inicio)");
        GregorianCalendar fin = pedirFecha("(Fin)");
        if (inicio.after(fin))
            throw new DateTimeException("Fechas no validas");
        ArrayList<Llamada> llamadas = metodo.listaLlamadas(dni);
        fechas.fechasIntervalo(llamadas, inicio, fin);
        ArrayList<Llamada> fechaCorrecta = fechas.getFechaCorrecta();
        for (Llamada llamada : fechaCorrecta)
            System.out.println(llamada.toString());
        continuar(sc);
    }

    public void verFacturasClienteFechas(Scanner sc) throws DateTimeException {
        FechaIntervalo<Factura> fechas = new FechaIntervalo<>();
        String dni = pedirDNI();
        GregorianCalendar inicio = pedirFecha(" (Inicio) ");
        GregorianCalendar fin = pedirFecha(" (Fin) ");
        if (inicio.after(fin))
            throw new DateTimeException("Fechas no validas");
        ArrayList<Factura> facturas = facturasCliente(dni);
        fechas.fechasIntervalo(facturas, inicio, fin);
        ArrayList<Factura> fechaCorrecta = fechas.getFechaCorrecta();
        for (Factura factura : fechaCorrecta)
            System.out.println(factura.toString());
        continuar(sc);
    }

    private String pedirDNI() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el DNI del cliente: ");
        return sc.next();
    }

    private GregorianCalendar pedirFecha(String detalle) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el día" + detalle + ": ");
        int dia = sc.nextInt();
        System.out.println("Introduce el mes:" + detalle + ": ");
        int mes = sc.nextInt();
        System.out.println("Introduce el año: " + detalle + ": ");
        int anyo = sc.nextInt();
        return new GregorianCalendar(anyo, mes, dia);
    }

    private ArrayList<Factura> facturasCliente(String dni) {
        ArrayList<Factura> facturas = new ArrayList<>();
        for (Factura factura : metodo.getTotalFacturas())
            if (factura.getCliente().getDni().equals(dni))
                facturas.add(factura);
        return facturas;
    }

    private String pedirTipo() {
        boolean correcto = false;
        String opcion = "";
        Scanner sc = new Scanner(System.in);
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
        return precio.orElse(-1d);
    }

    private String pedirOpcionSN(Scanner sc) {
        return sc.next().toLowerCase();
    }

    private boolean compruebaHoras(int horaInicio, int horaFin) {
        return horaInicio < horaFin;
    }

    private void continuar(Scanner sc) {
        System.out.print("\nPulsa INTRO para continuar.\n");
        sc.nextLine();
        sc.nextLine();
    }
}

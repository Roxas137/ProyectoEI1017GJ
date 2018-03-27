package gestor;

import atributos.Factura;
import atributos.Llamada;
import atributos.Tarifa;
import clientes.Cliente;

import java.util.*;

/**
 * Created by al361891 on 20/02/18.
 */
public class Metodos {

    private HashSet<Cliente> conjuntoClientes = new HashSet<Cliente>();
    private ArrayList<Factura> totalFacturas = new ArrayList<Factura>();
    private HashMap<String, ArrayList<Llamada>> llamadasCliente = new HashMap<String, ArrayList<Llamada>>();

    public boolean addCliente(Cliente nuevo) {                     //1
        boolean modificado = conjuntoClientes.add(nuevo);
        if (modificado)
            llamadasCliente.put(nuevo.getDni(), new ArrayList<Llamada>());
        return modificado;
    }

    private Optional<Cliente> recorrerConjuntoClientes(String dni) {
        for (Cliente cliente : conjuntoClientes)
            if (cliente.getDni().equals(dni))
                return Optional.of(cliente);
        return Optional.empty();
    }

    public boolean removeCliente(String dni) {                //2
        Optional<Cliente> correcto = recorrerConjuntoClientes(dni);
        return correcto.isPresent() && conjuntoClientes.remove(correcto.get());
    }

    public void cambiarTarifa(Cliente cliente, Tarifa nueva) {     //3
        cliente.setTarifa(nueva);
    }

    public Optional<Cliente> devuelveCliente(String dni) {                   //4
        for (Cliente cliente : conjuntoClientes)
            if (cliente.getDni().equals(dni))
                return Optional.of(cliente);
        return Optional.empty();
    }

    public ArrayList<Cliente> listaClientes() {                    //5
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        for (Cliente cliente : conjuntoClientes)
            listaClientes.add(cliente);
        return listaClientes;
    }

    public void addLlamada(Cliente cliente, Llamada llamada) {    //6
        ArrayList<Llamada> aux = llamadasCliente.get(cliente.getDni());
        aux.add(llamada);
        llamadasCliente.put(cliente.getDni(), aux);
    }

//    public void addLlamada(Cliente cliente) {                          //6b
//        ArrayList<Llamada> aux = llamadasCliente.get(cliente.getDni());
//        Llamada nueva = new Llamada();
//        nueva.setTarifa(cliente.getTarifa());
//        aux.add(nueva);
//        llamadasCliente.put(cliente.getDni(), aux);
//    }

    public ArrayList<Llamada> listaLlamadas(String dni) {   //7
        return llamadasCliente.get(dni);
    }

    public double emitirFactura(Cliente cliente) {                 //8
        double importe = 0;
        ArrayList<Llamada> listaLlamadas = llamadasCliente.get(cliente.getDni());
        Date fechaUltimaFactura = cliente.getUltimaFactura();
        for (Llamada llamada : listaLlamadas)
            if (llamada.getFecha().compareTo(fechaUltimaFactura) > 0)
                importe += llamada.getDuracion() * llamada.getTarifa().getPrecio();
        Factura nueva = new Factura();
        nueva.setCliente(cliente);
        nueva.setFecha(new Date());
        cliente.setUltimaFactura(new Date());
        nueva.setCodigo(totalFacturas.size());
        nueva.setPrecio(importe);
        totalFacturas.add(nueva);
        return importe;
    }

    public Factura verFactura(int codigo) {                         //9
        return totalFacturas.get(codigo);
    }

    public ArrayList<Factura> facturasCliente(Cliente cliente) {  //10
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        for (Factura factura : totalFacturas)
            if (factura.getCliente().getDni().equals(cliente.getDni()))
                facturas.add(factura);
        return facturas;
    }
}
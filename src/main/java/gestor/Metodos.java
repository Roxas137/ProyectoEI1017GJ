package gestor;

import atributos.Factura;
import atributos.Llamada;
import clientes.Cliente;
import tarifa.Tarifa;

import java.io.Serializable;
import java.util.*;

/**
 * Created by al361891 on 20/02/18.
 */
public class Metodos implements Serializable {

    private HashMap<String, Cliente> mapaClientes = new HashMap<>();
    private ArrayList<Factura> totalFacturas = new ArrayList<>();
    private HashMap<String, ArrayList<Llamada>> llamadasCliente = new HashMap<>();

    public void addCliente(Cliente nuevo) {                     //1
        boolean modificado = mapaClientes.containsKey(nuevo.getDni());
        if (!modificado) {
            mapaClientes.put(nuevo.getDni(), nuevo);
            llamadasCliente.put(nuevo.getDni(), new ArrayList<>());
        }
    }

    public void removeCliente(String dni) throws NoSuchElementException {                //2
        if (!mapaClientes.containsKey(dni)) {
            throw new NoSuchElementException("No se encuentra el cliente");
        }
        mapaClientes.remove(dni);
    }

    public void cambiarTarifa(Cliente cliente, Tarifa nueva) {     //3
        cliente.setTarifa(nueva);
    }

    public Optional<Cliente> devuelveCliente(String dni) {                   //4
        if (mapaClientes.containsKey(dni))
            return Optional.of(mapaClientes.get(dni));
        return Optional.empty();
    }

    public ArrayList<Cliente> listaClientes() {                    //5
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        listaClientes.addAll(mapaClientes.values());
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

    public Factura emitirFactura(String dni) throws NoSuchElementException {                 //8
        double importe = 0;
        Optional<Cliente> cliente = devuelveCliente(dni);
        if (!cliente.isPresent())
            throw new NoSuchElementException("Cliente no encontrado");
        ArrayList<Llamada> listaLlamadas = llamadasCliente.get(dni);
        Date fechaUltimaFactura = cliente.get().getUltimaFactura();
        for (Llamada llamada : listaLlamadas)
            if (llamada.getFecha().compareTo(fechaUltimaFactura) > 0)
                importe += llamada.getPrecio() * llamada.getDuracion();
        Factura nueva = new Factura();
        nueva.setCliente(cliente.get());
        Date ahora = new Date();
        nueva.setFecha(ahora);
        cliente.get().setUltimaFactura(ahora);
        nueva.setCodigo(totalFacturas.size());
        importe = Math.round(importe);
        nueva.setPrecio(importe);
        totalFacturas.add(nueva);
        return nueva;
    }

    public Factura verFactura(int codigo) {                         //9
        return totalFacturas.get(codigo);
    }

    public ArrayList<Factura> facturasCliente(String dni) {  //10
        ArrayList<Factura> facturas = new ArrayList<>();
        for (Factura factura : totalFacturas)
            if (factura.getCliente().getDni().equals(dni))
                facturas.add(factura);
        return facturas;
    }


    public HashMap<String, Cliente> getMapaClientes() {
        return mapaClientes;
    }

    public ArrayList<Factura> getTotalFacturas() {
        return totalFacturas;
    }
}